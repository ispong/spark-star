package com.isxcode.star.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
public class CommandUtils {

    /**
     * execute command to log file
     *
     * @param command     command
     * @param logPath     log file path
     * @param waitingTime waiting timeMillis
     * @return exit code
     */
    public static int executeCommand(String command, String logPath, long waitingTime) throws IOException {

        String[] cmd = {"-c", command};
        CommandLine cmdLine = CommandLine.parse("/bin/sh");
        cmdLine.addArguments(cmd, false);

        DefaultExecutor executor = new DefaultExecutor();

        // set log file path
        FileOutputStream fileOutputStream = new FileOutputStream(logPath, true);
        PumpStreamHandler streamHandler = new PumpStreamHandler(fileOutputStream, fileOutputStream, null);
        executor.setStreamHandler(streamHandler);

        // set watchdog for waiting
        ExecuteWatchdog watchdog = new ExecuteWatchdog(waitingTime);
        executor.setWatchdog(watchdog);

        // execute command
        return executor.execute(cmdLine);

    }

    /**
     * execute command to log file
     *
     * @param command command
     * @param logPath log file path
     * @return exit code
     */
    public static int executeCommand(String command, String logPath) throws IOException {

        return executeCommand(command, logPath, 60000);
    }

    /**
     * execute command to log file
     *
     * @param command     command
     * @param waitingTime waiting timeMillis
     * @return result string
     */
    public static String executeBackCommand(String command, long waitingTime) throws IOException {

        String[] cmd = {"-c", command};
        CommandLine cmdLine = CommandLine.parse("/bin/sh");
        cmdLine.addArguments(cmd, false);

        DefaultExecutor executor = new DefaultExecutor();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        PumpStreamHandler streamHandler = new PumpStreamHandler(byteArrayOutputStream, byteArrayOutputStream, null);
        executor.setStreamHandler(streamHandler);

        // set watchdog for waiting
        ExecuteWatchdog watchdog = new ExecuteWatchdog(waitingTime);
        executor.setWatchdog(watchdog);
        executor.execute(cmdLine);

        byte[] bytes = byteArrayOutputStream.toByteArray();
        return new String(bytes, 0, bytes.length);
    }

    /**
     * execute command to log file
     *
     * @param command command
     * @return command result str
     */
    public static String executeBackCommand(String command) throws IOException {

        return executeBackCommand(command, 60000);
    }

    /**
     * execute command to log file
     *
     * @param command     command
     * @param waitingTime waiting timeMillis
     * @return exit code
     */
    public static int executeNoBackCommand(String command, long waitingTime) throws IOException {

        String[] cmd = {"-c", command};
        CommandLine cmdLine = CommandLine.parse("/bin/sh");
        cmdLine.addArguments(cmd, false);

        DefaultExecutor executor = new DefaultExecutor();

        // set watchdog for waiting
        ExecuteWatchdog watchdog = new ExecuteWatchdog(waitingTime);
        executor.setWatchdog(watchdog);
        return executor.execute(cmdLine);
    }

    /**
     * execute command to log file
     *
     * @param command command
     * @return exit code
     */
    public static int executeNoBackCommand(String command) throws IOException {

        return executeNoBackCommand(command, 60000);
    }

}

