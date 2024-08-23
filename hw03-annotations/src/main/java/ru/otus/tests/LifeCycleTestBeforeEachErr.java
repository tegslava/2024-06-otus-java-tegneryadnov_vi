package ru.otus.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.annotations.AfterEach;
import ru.otus.annotations.BeforeEach;
import ru.otus.annotations.Test;

@SuppressWarnings("java:S112")
public class LifeCycleTestBeforeEachErr {
    private static final Logger logger = LoggerFactory.getLogger(LifeCycleTestBeforeEachErr.class);

    @BeforeEach
    void setUp() {
        throw new RuntimeException("Ooops in setUp");
    }

    @AfterEach
    void tearDown() {
        logger.info("tearDown");
    }

    @Test
    void buildMessageTest1() {
        logger.info("buildMessageTest1");
    }

    @Test
    void buildMessageTest2() {
        logger.info("buildMessageTest2");
    }

    @Test
    void buildMessageTest3() {
        logger.info("buildMessageTest3");
    }
}
