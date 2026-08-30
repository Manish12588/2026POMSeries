package com.qa.opencart.utils;

import com.qa.opencart.factory.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtil {
    public static Logger log = LogManager.getLogger(LogUtil.class);

    public static void info(String message){
        log.info(message);
    }
    public static void warn(String message){
        log.warn(message);
    }
    public static void error(String message){
        log.error(message);
    }
    public static void fatal(String message){
        log.fatal(message);
    }
}
