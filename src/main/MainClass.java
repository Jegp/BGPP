package main;

import java.util.prefs.*;

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
	 */
	public static void main(String[] args) {
		// Initialize the log with a file handler.
		Log.initFileHandler("log.txt");
		
		// Model-View-Controller pattern
		// Initialize model.
		Model model = MySQLModel.getInstance();
		// Initialize view.
		View view = new View(model);
		// Initialize controller.
		Controller controller = new Controller(model, view);
	}

}
