package ru.otus.testresults;

public interface TestResults {
    TestResults init(String testName);

    TestResults addSucces();

    TestResults addFail();

    TestResults show();

    TestResults setTemplate(String template);
}
