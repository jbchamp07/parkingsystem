/**
 * Info about this package doing something for package-info.java file.
 * @author Champetier
 * @Version 1.0.0
 * @
 */
package com.parkit.parkingsystem;


import com.parkit.parkingsystem.service.InteractiveShell;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type App.
 */
public abstract class App {

    /**
     * The Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger("App");

    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(final String[] args) {
        LOGGER.info("Initializing Parking System");
        InteractiveShell.loadInterface();
    }
}
