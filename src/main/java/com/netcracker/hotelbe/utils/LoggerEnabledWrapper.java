package com.netcracker.hotelbe.utils;

public interface LoggerEnabledWrapper {
    void trace(String s, Object... objects);

    void debug(String s, Object... objects);

    void info(String s, Object... objects);

    void warn(String s, Object... objects);

    void error(String s, Object... objects);

    void fatal(String s, Object... objects);
}
