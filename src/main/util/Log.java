package main.util;

import java.util.logging.*;

/**
 * A simple logger using Java's Logger from package java.util.logging.
 */
public class Log {

	// The log's reference to java's logger.
	private static Logger log = Logger.getLogger(Log.class.getPackage().getName());
	
	/**
	 * Log an error.
	 * @param message  The error to log.
	 */
	public static void error(String message)	{
		log.log(Level.SEVERE, message);
	}
	
	/**
	 * Log a warning.
	 * @param message  The warning to log.
	 */
	public static void warning(String message) {
		log.log(Level.WARNING, message);
	}
	
	/**
	 * Log a configuration.
	 * @param message  The configuration to log.
	 */
	public static void config(String message) {
		log.log(Level.CONFIG, message);
	}
	
}
