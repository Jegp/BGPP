package main.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.model.Customer;
import main.view.*;

/**
 * A controller for the customer container.
 */
public class CustomerController {
	
	private CustomerContainer container;
	
	public CustomerController(final CustomerContainer container) {
		this.container = container;
		
		// Adds a listener to the search button
		container.addListenerToSearchButton(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
	      CustomerWindow window = new CustomerWindow(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						System.out.println("Search!");
						
					}
				});
      }
			
		});
		
		final Customer[] customers = Customer.getAll();
		
		// Show the customers in the array and add a listener for mouse events in the table.
		container.showCustomers(customers, new MouseListener() {

			// On click: Edit customer in a new window (if the row exists)
      public void mouseClicked(MouseEvent e) {
	      int selectedRow = container.getCustomerTable().getSelectedRow();
	      if (selectedRow >= 0) {
	      	
	      	// Initialize the window
	      	new CustomerWindow(customers[selectedRow],

  	      	// Define the action listener for the delete button
      			new ActionListener() {
						
							public void actionPerformed(ActionEvent arg0) {
								System.out.println("Delete!");
							}
						}, 
						// And define the action listener for the save button
						new ActionListener() {
							
							public void actionPerformed(ActionEvent e) {
								System.out.println("Save!");
								
							}
					});
	      }
      }
      // No need for these...
      public void mouseEntered(MouseEvent e)  {}
      public void mouseExited(MouseEvent e)   {}
      public void mousePressed(MouseEvent e)  {} 
      public void mouseReleased(MouseEvent e) {}
			
		});
	}
	
}
