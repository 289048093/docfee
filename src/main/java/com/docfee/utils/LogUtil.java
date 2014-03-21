package com.docfee.utils;

import org.apache.log4j.FileAppender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;


/**
 * User: Jarvis.Li(李朝)
 * Date: 14-2-12
 * Time: 下午5:18
 */
public class LogUtil {
    /**
     * 私有化构造器
     */
    private LogUtil() {

    }

    /**
     * 获取InfoLogger
     *
     * @return
     */
    public static Logger getInfoLogger() {
        return Logger.getLogger("docfeeInfoLogger");
    }

    /**
     * 获取WarnLogger
     *
     * @return
     */
    public static Logger getWarnLogger() {
        return Logger.getLogger("docfeeWarnLogger");
    }

    /**
     * 获取ErrorLogger
     *
     * @return
     */
    public static Logger getErrorLogger() {
        return Logger.getLogger("docfeeErrorLogger");
    }

    /**
     * 获取DebugLogger
     *
     * @return
     */
    public static Logger getDebugLogger() {
        return Logger.getLogger("docfeeDebugLogger");
    }

    /**
     * 获取FatalLogger
     *
     * @return
     */
    public static Logger getFatalLogger() {
        return Logger.getLogger("docfeeFatalLogger");
    }

    /**
     * error log
     *
     * @param message
     */
    public static void error(String message) {
        getErrorLogger().error(message);
    }

    /**
     * error log
     *
     * @param throwable
     */
    public static void error(Throwable throwable) {
        getErrorLogger().error("", throwable);
    }

    /**
     * error log
     *
     * @param throwable
     */
    public static void error(String message, Throwable throwable) {
        getErrorLogger().error(message, throwable);
    }

    /**
     * debug log
     *
     * @param throwable
     */
    public static void debug(Throwable throwable) {
        getDebugLogger().debug("", throwable);
    }

    /**
     * debug log
     *
     * @param message
     */
    public static void debug(String message) {
        getDebugLogger().debug(message);
    }

    /**
     * debug log
     *
     * @param message
     * @param throwable
     */
    public static void debug(String message, Throwable throwable) {
        getDebugLogger().debug(message, throwable);
    }

    /**
     * info log
     *
     * @param throwable
     */
    public static void info(Throwable throwable) {
        getInfoLogger().info("", throwable);
    }

    /**
     * info log
     *
     * @param message
     */
    public static void info(String message) {
        getInfoLogger().info(message);
    }

    /**
     * info log
     *
     * @param message
     * @param throwable
     */
    public static void info(String message, Throwable throwable) {
        getInfoLogger().info(message, throwable);
    }

    /**
     * fatal log
     *
     * @param message
     */
    public static void fatal(String message) {
        getFatalLogger().fatal(message);
    }

    /**
     * fatal log
     *
     * @param throwable
     */
    public static void fatal(Throwable throwable) {
        getFatalLogger().fatal("", throwable);
    }

    /**
     * fatal log
     *
     * @param message
     * @param throwable
     */
    public static void fatal(String message, Throwable throwable) {
        getFatalLogger().fatal(message, throwable);
    }

    /**
     * warn log
     *
     * @param message
     */
    public static void warn(String message) {
        getWarnLogger().warn(message);
    }

    /**
     * warn log
     *
     * @param throwable
     */
    public static void warn(Throwable throwable) {
        getFatalLogger().warn(null, throwable);
    }

    /**
     * warn log
     *
     * @param message
     * @param throwable
     */
    public static void warn(String message, Throwable throwable) {
        getFatalLogger().warn(message, throwable);
    }

    public static boolean isDebugEnabled() {
        return Logger.getRootLogger().isDebugEnabled();
    }


    public static boolean isInfoEnabled() {
        return Logger.getRootLogger().isInfoEnabled();
    }


    public static void flushAllLogs() {
        try {
            Set<FileAppender> flushedFileAppenders = new HashSet<FileAppender>();
            Enumeration currentLoggers = LogManager.getLoggerRepository().getCurrentLoggers();
            while (currentLoggers.hasMoreElements()) {
                Object nextLogger = currentLoggers.nextElement();
                if (nextLogger instanceof Logger) {
                    Logger currentLogger = (Logger) nextLogger;
                    Enumeration allAppenders = currentLogger.getAllAppenders();
                    while (allAppenders.hasMoreElements()) {
                        Object nextElement = allAppenders.nextElement();
                        if (nextElement instanceof FileAppender) {
                            FileAppender fileAppender = (FileAppender) nextElement;
                            if (!flushedFileAppenders.contains(fileAppender) && !fileAppender.getImmediateFlush()) {
                                flushedFileAppenders.add(fileAppender);
                                //log.info("Appender "+fileAppender.getName()+" is not doing immediateFlush ");
                                fileAppender.setImmediateFlush(true);
                                currentLogger.info("FLUSH");
                                fileAppender.setImmediateFlush(false);
                            } else {
                                //log.info("fileAppender"+fileAppender.getName()+" is doing immediateFlush");
                            }
                        }
                    }
                }
            }
        } catch (RuntimeException e) {
            error("Failed flushing logs", e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        flushAllLogs();
        System.setProperty("web.root",FileUtil.getClassPathFilePath(""));
        error("hello");
    }
}
