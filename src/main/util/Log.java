package main.util;

import java.io.IOException;
import java.util.logging.*;

/**
 * A simple logger using Java's Logger from package java.util.logging.
 */
public class Log {

	// The log's reference to java's logger.
	private static Logger log = Logger.getLogger(Log.class.getPackage().getName());
	
	/**
	 * Log a configuration.
	 * @param message  The configuration to log.
	 */
	public static void config(String message) {
		log.log(Level.CONFIG, message);
	}
	
	/**
	 * Log an error.
	 * @param message  The error to log.
	 */
	public static void error(String message)	{
		log.log(Level.SEVERE, message);
	}
	
	/**
	 * @return  The level of log-messages that's being displayed.
	 */
	public static Level getLevel() {
		return log.getLevel();
	}
	
	/**
	 * Log a information.
	 * @param message  The information to log.
	 */
	public static void info(String message) {
		log.log(Level.INFO, message);
	}
	
	/**
	 * Initializes the log by adding a file handler to the log.
	 */
	public static void initFileHandler(String filename) {
		try {
			FileHandler handler = new FileHandler(filename);
			log.addHandler(handler);
		} catch(IOException e) {
			error("Unable to store log.");
		}
	}
	
	/**
	 * Set the log-level. 
	 */
	public static void setLevel(Level level) {
		log.setLevel(level);
	}
	
	/**
	 * Log a warning.
	 * @param message  The warning to log.
	 */
	public static void warning(String message) {
		log.log(Level.WARNING, message);
	}
	
}
