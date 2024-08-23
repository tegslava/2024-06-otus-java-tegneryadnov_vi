package ru.otus.processor;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.annotations.AfterEach;
import ru.otus.annotations.BeforeEach;
import ru.otus.annotations.Test;
import ru.otus.testresults.TestResults;

public final class ProcessorTestAnnotations {
    private static final Logger logger = LoggerFactory.getLogger(ProcessorTestAnnotations.class);

    private ProcessorTestAnnotations() {}

    public static void execute(Class<?> clazz, TestResults results) {
        var methodsTest = getAnnotatedMethods(clazz, Test.class);
        if (methodsTest.length == 0) {
            logger.error("No methods found, annotated with @Test. Testing stopped");
            return;
        }
        var methodsBeforeEach = getAnnotatedMethods(clazz, BeforeEach.class);
        var methodsAfterEach = getAnnotatedMethods(clazz, AfterEach.class);
        results.init(clazz.getCanonicalName()).setTemplate("\n{} test results: passed {}, failed {}\n");
        for (var methodTest : methodsTest) {
            Object o = getClassInstance(clazz);
            if (o == null) return;
            try {
                methodsExecute(o, null, methodsBeforeEach);
                methodsExecute(o, results, methodTest);
            } catch (RuntimeException e) {
                logger.error(Arrays.toString(e.getStackTrace()));
            } finally {
                methodsExecute(o, null, methodsAfterEach);
            }
        }
    }

    private static Object getClassInstance(Class<?> clazz) {
        Object o = null;
        try {
            o = clazz.getConstructor().newInstance();
        } catch (InstantiationException
                | IllegalAccessException
                | InvocationTargetException
                | NoSuchMethodException e) {
            logger.error("Error creating a testing class instance. Testing stopped: {}", clazz);
            return null;
        }
        return o;
    }

    private static void methodsExecute(Object o, TestResults result, Method... methods) {
        for (Method method : methods) {
            try {
                if (method.trySetAccessible()) method.invoke(o);
                if (result != null) result.addSucces();
            } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
                logger.error("An error occurred during the execution of the method {}", method);
                logger.error(Arrays.toString(e.getStackTrace()));
                if (result != null) result.addFail();
            }
        }
    }

    private static Method[] getAnnotatedMethods(Class<?> clazz, Class<? extends Annotation> annotation) {
        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(x -> x.isAnnotationPresent(annotation))
                .toArray(Method[]::new);
    }
}
