package main.controller;

import main.model.Model;
import main.model.Vehicle;
import main.model.VehicleClass;
import main.view.CustomerContainer;
import main.view.ReservationContainer;
import main.view.VehicleContainer;
import main.view.View;
import java.awt.event.*;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * The Controller for the booking-system.
 */
public class Controller 
{		
	private View view;
	private Model model;
	
	public Controller(Model model, View view) 
	{
		this.view = view;
		
		view.addActionListenerToReservationButton(new ListenerToReservation());
		
		view.addActionListenerToCustomerButton(new ListenerToCustomer());
		
		view.addActionListenerToVehicleButton(new ListenerToVehicle());		
	}
	
	class ListenerToReservation implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        view.changeContainer(new ReservationContainer());
        System.out.println("Reservation button...."); //remember to remove, only used for checking proper reactions
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
    
    
    
    class createActionListenerToCreateButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
		}
    	
    }
}

