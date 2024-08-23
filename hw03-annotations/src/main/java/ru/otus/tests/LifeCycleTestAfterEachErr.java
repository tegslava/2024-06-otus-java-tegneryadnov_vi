package ru.otus.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.annotations.AfterEach;
import ru.otus.annotations.BeforeEach;
import ru.otus.annotations.Test;

@SuppressWarnings("java:S112")
public class LifeCycleTestAfterEachErr {
    private static final Logger logger = LoggerFactory.getLogger(LifeCycleTestAfterEachErr.class);

    @BeforeEach
    void setUp() {
        logger.info("setUp");
    }

    @AfterEach
    void tearDown() throws Exception {
        throw new RuntimeException("Ooops in finalize");
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
