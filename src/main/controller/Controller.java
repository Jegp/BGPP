package main.controller;

import main.model.Model;
import main.view.CustomerContainer;
import main.view.ReservationContainer;
import main.view.VehicleContainer;
import main.view.View;
import java.awt.event.*;

/**
 * The Controller for the booking-system.
 */
public class Controller {
	
	/**
	 * The view 
	 */
	private View view;
	
	/**
	 * Creates a controller that handles the three windows: Reservation, Customer and Vehicle.
	 * @param model  The model to show data from.
	 * @param view  The view to control.
	 */
	public Controller(final Model model, View view) {
		// Store the view.
		this.view = view;
		
		// Get a reservation listener
		ListenerToReservation reservationListener = new ListenerToReservation();
		
		// Trigger it to initialize the view in the reservation screen
		reservationListener.actionPerformed(null);
		
		// Add a listener to the reservation button
		view.addActionListenerToReservationButton(reservationListener);
		
		// Add a listener to the customer button
		view.addActionListenerToCustomerButton(new ListenerToCustomer());
		
		// Add a listener to the vehicle button
		view.addActionListenerToVehicleButton(new ListenerToVehicle());		
		
		// Add a listener to close the connection to the database.
		view.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// Close the connection to the model
				model.close();
				
				// Exit
				System.exit(0);
			}
		});
	}
	
	/**
	 * A listener for the reservation container.
	 */
	class ListenerToReservation implements ActionListener {
    public void actionPerformed(ActionEvent e) {
  		// Define container
    	ReservationContainer container = new ReservationContainer();
    	
    	// Initialize controller for reservations
    	new ReservationController(container);
    	
    	//Update view
    	view.changeContainer(container);
    }
  }
        
	/**
	 * A listener for the customer container.
	 */
  class ListenerToCustomer implements ActionListener {
  	public void actionPerformed(ActionEvent e) {
  		// Define container
    	CustomerContainer container = new CustomerContainer();
    	
    	// Initialize controller for customers
    	new CustomerController(container);
    	
    	// Update view
    	view.changeContainer(container);
  	}
  }
  
  /**
	 * A listener for the vehicle container.
	 */
  class ListenerToVehicle implements ActionListener {
  	public void actionPerformed(ActionEvent e) {
  		// Define container
	  	VehicleContainer container = new VehicleContainer();
	  	
	  	// Initialize controller for the vehicles
	  	new VehicleController(container);
	  	
	  	// Update view
	  	view.changeContainer(container);
  	}
  }
  
}

