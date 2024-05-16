package org.example;

import java.io.IOException;
import java.util.logging.*;

public class AppLogger {
    public static void configure() {
        try {
            Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
            ConsoleHandler consoleHandler = new ConsoleHandler();
            FileHandler fileHandler = new FileHandler("app.log", true);
            logger.addHandler(consoleHandler);
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}