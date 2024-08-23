package ru.otus;

import static ru.otus.executor.TestExecutor.execute;

import ru.otus.tests.LifeCycleTest;
import ru.otus.tests.LifeCycleTestAfterEachErr;
import ru.otus.tests.LifeCycleTestBeforeEachErr;
import ru.otus.tests.LifeCycleTestErr;

public class MainApp {
    public static void main(String[] args) {
        execute(LifeCycleTest.class);
        execute(LifeCycleTestBeforeEachErr.class);
        execute(LifeCycleTestErr.class);
        execute(LifeCycleTestAfterEachErr.class);
    }
}
