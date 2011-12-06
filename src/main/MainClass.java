package main;

import java.util.Date;

import main.controller.*;
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
		// Initialize model.
		Model model = MySQLModel.getInstance();
		// Initialize view.
		View view = new View(model);
		// Initialize controller.
		Controller controller = new Controller(model, view);
	}

}
