package ru.otus.executor;

import ru.otus.processor.ProcessorTestAnnotations;
import ru.otus.testresults.Results;

public class TestExecutor {
    private TestExecutor() {}

    public static void execute(Class<?> clazz) {
        Results results = new Results();
        ProcessorTestAnnotations.execute(clazz, results);
        results.show();
    }
}
