package com.netcracker.hotelbe.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

public class SimpleLogger {
    private Logger logger;
    private Class clazz;

    public SimpleLogger(Class clazz) {
        logger = LogManager.getLogger(clazz);
        this.clazz = clazz;
    }

    public void trace(String message) {
        if (logger.isTraceEnabled()) {
            logger.trace(message);
        }
    }
    public void trace(String message, Throwable throwable) {
        if (logger.isTraceEnabled()) {
            logger.trace(message, throwable);
        }
    }

    public void info(String message) {
        if (logger.isInfoEnabled()) {
            logger.info(message);
        }
    }

    public void info(String message, Throwable throwable) {
        if (logger.isInfoEnabled()) {
            logger.info(message, throwable);
        }
    }

    public void warn(String message) {
        if (logger.isEnabledFor(Priority.WARN)) {
            logger.warn(message);
        }
    }

    public void warn(String message, Throwable throwable) {
        if (logger.isEnabledFor(Priority.WARN)) {
            logger.warn(message, throwable);
        }
    }

    public void error(String message) {
        if (logger.isEnabledFor(Priority.ERROR)) {
            logger.error(message);
        }
    }

    public void error(String message, Throwable throwable) {
        if (logger.isEnabledFor(Priority.ERROR)) {
            logger.error(message, throwable);
        }
    }
}
