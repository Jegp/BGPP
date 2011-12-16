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
		
		// Add a listener to the reservation button
		view.addActionListenerToReservationButton(new ListenerToReservation());
		
		// Add a listener to the customer button
		view.addActionListenerToCustomerButton(new ListenerToCustomer());
		
		// Add a listener to the vehicle button
		view.addActionListenerToVehicleButton(new ListenerToVehicle());		
		
		// Add a listener to close the connection to the database.
		view.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				// Close the connection to the model
				model.close();
				
				// Exit
				System.exit(0);
			}
		});
	}
	
	class ListenerToReservation implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	ReservationContainer container = new ReservationContainer();
        	
        	new ReservationController(container);
        	//Update view
        	view.changeContainer(container);
        	view.pack();
        }
    }
        
    class ListenerToCustomer implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
	    	CustomerContainer container = new CustomerContainer();
	    	
	    	// Initialize controller for customer
	    	new CustomerController(container);
	    	// Update view
	    	view.changeContainer(container);
	    	view.pack();
    	}
    }
    
    class ListenerToVehicle implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    	
    	VehicleContainer container = new VehicleContainer();
    	VehicleController vc = new VehicleController(container);
    	
    	view.changeContainer(container);
    	view.pack();
    	}
    }
    
    class MouseAdapter {
    	public void mouseClicked(MouseEvent e) {
    	System.out.println("mouse has been pressed!!!");
    	}
    }
}

