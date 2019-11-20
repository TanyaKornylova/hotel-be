package com.netcracker.hotelbe.utils;

import com.netcracker.hotelbe.service.ApartmentPriceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerEnabledWrapperImpl implements LoggerEnabledWrapper {

    private Logger logger;
    private Class clazz;

    public LoggerEnabledWrapperImpl(Class clazz) {
        this.clazz = clazz;
        logger = LogManager.getLogger(clazz);
    }

    @Override
    public void trace(String s, Object... objects) {
        if (logger.isTraceEnabled()) {
            logger.trace(s, objects);
        }
    }

    @Override
    public void debug(String s, Object... objects) {
        if (logger.isDebugEnabled()) {
            logger.debug(s, objects);
        }
    }

    @Override
    public void info(String s, Object... objects) {
        if (logger.isInfoEnabled()) {
            logger.info(s, objects);
        }
    }

    @Override
    public void warn(String s, Object... objects) {
        if (logger.isWarnEnabled()) {
            logger.warn(s, objects);
        }
    }

    @Override
    public void error(String s, Object... objects) {
        if (logger.isErrorEnabled()) {
            logger.error(s, objects);
        }
    }

    @Override
    public void fatal(String s, Object... objects) {
        if (logger.isFatalEnabled()) {
            logger.fatal(s, objects);
        }
    }
}
