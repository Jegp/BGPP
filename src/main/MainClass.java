package main;

import main.view.*;
import main.model.*;

/**
 * The main entry point for the booking-system.
 */
public class MainClass {
	
	public static void main(String[] args) {
		View view = new View();
		Database.initConnection();
	}

}
