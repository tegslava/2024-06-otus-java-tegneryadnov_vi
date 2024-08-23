package ru.otus.testresults;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Results implements TestResults {
    private static final Logger logger = LoggerFactory.getLogger(Results.class);
    private int success;
    private int fail;
    private String name = "";
    private String template;

    @Override
    public TestResults init(String testName) {
        success = 0;
        fail = 0;
        name = testName;
        return this;
    }

    @Override
    public TestResults addSucces() {
        success++;
        return this;
    }

    @Override
    public TestResults addFail() {
        fail++;
        return this;
    }

    @Override
    public TestResults setTemplate(String template) {
        this.template = template;
        return this;
    }

    @Override
    public TestResults show() {
        logger.info(template, name, success, fail);
        return this;
    }
}
