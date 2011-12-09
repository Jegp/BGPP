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
		// TODO: Uncomment
		//Log.initFileHandler("log.txt");
		
		// Model-View-Controller pattern
		// Initialize model.
		Model model = MySQLModel.getInstance();
		// Initialize view.
		View view = new View(model);
		// Initialize controller.
		Controller controller = new Controller(model, view);
		
		
		// Tests
		Customer c1 = new Customer("Daniel", "Varab", "forestdotcom@hotmail.com", "112", "VejAlléen 10, 2200 Kbh C");
		Customer c2 = new Customer("Sune", "Debel", "sdeb@itu.dk", "114", "VejAlléen 12, 2200 Kbh C");
		//c1 = Customer.save(c1);
		//c2 = Customer.save(c2);
	}

}
