package main;

import javax.swing.JOptionPane;

import main.controller.*;
import main.util.Log;
import main.view.*;
import main.model.*;

/**
 * The main entry point for the booking-system.
 */
public class MainClass {
	
	/**
	 * Initializes the application by instantiating the Model-View-Controller (MVC).
	 * If no connection could be made the application display an error message and closes.
	 */
	public static void main(String[] args) {
		// Initialize the log with a file handler.
		// TODO: Uncomment
		//Log.initFileHandler("log.txt");
		
		// Model-View-Controller pattern
		// Initialize model.
		Model model = MySQLModel.getInstance();
		
		// Initialize view.
		View view = new View(model);
		
		// Examine connection and close if none could be found
		if (!model.isConnected()) {
			// Create message
			String message = "<html>No connection to the database could be made. <br />" +
							 "Please check your connection and restart the application.</html>";
			
			// Show message
			JOptionPane.showMessageDialog(view, message, "No connection found.", JOptionPane.ERROR_MESSAGE);
			
			// Quit!
			System.exit(0);
		}
		
		// Initialize controller.
		new Controller(model, view);
		
		// Log success!
		Log.info("Application initialized correct.");
	}

}
