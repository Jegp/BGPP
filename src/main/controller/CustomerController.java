package main.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.JButton;

import main.model.Customer;
import main.view.*;

/**
 * A controller for the customer container.
 */
public class CustomerController {

	/**
	 * The container to use when the customer table is updated.
	 */
	private CustomerContainer container;

	/**
	 * Creates a controller for a given CustomerContainer by initializing it with an array 
	 * of all available customers and set's up the relevant listeners.
	 */
	public CustomerController(CustomerContainer container) {
		// Store the container for use later on
		this.container = container;

		// Retrieve the customers to show - initially we just want a list over all available customers
		Customer[] customers = Customer.getAll();
		
		// Show the table
		showCustomerTable(customers);
	
		// Adds a listener to the search button
		container.setActionListenerToSearchButton(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				final CustomerWindow window = new CustomerWindow();
				
				window.addActionListenerToSubmitButton(new ActionListener() {

					public void actionPerformed(ActionEvent e) {						
						// And assign fields
						HashMap<String, String> fields = new HashMap<String, String>();
						String firstName = window.fieldFirstName.getText();
						String lastName  = window.fieldLastName.getText();
						String email     = window.fieldEmail.getText();
						String phone     = window.fieldPhone.getText();
						String address   = window.fieldAddress.getText();
						
						if (firstName.length() > 0)
							fields.put("firstName", firstName);
						
						if (lastName.length() > 0)
							fields.put("lastName", lastName);
						
						if (email.length() > 0)
							fields.put("email", email);
						
						if (phone.length() > 0)
							fields.put("phone", phone);
							
						if (address.length() > 0)
							fields.put("address", address);
						
						// Retrieve the customers that match the search criteria
						Customer[] customers = Customer.searchWhere(fields);
						
						// Show them
						showCustomerTable(customers);
						
						// And finally close the open window
						window.dispose();
					}
				});
			}

		});
	}
	
	/**
	 * Shows the given array of customers and set's the appropriate listeners up.
	 */
	private void showCustomerTable(final Customer[] customers) {
		// Show the customers in the array and add a listener for mouse events in the table.
		container.showCustomers(customers);
		
		// If the customers array is empty, don't bother setting the listener, otherwise move on!
		if (customers != null) {
			// Retrieve the table
			CustomerTable table = container.getCustomerTable();
			
			// Set the listener for the table
			table.addMouseListener(new MouseListener() {
	
				// On click: Edit customer in a new window (if the row exists)
				public void mouseClicked(MouseEvent e) {
					int selectedRow = container.getCustomerTable().getSelectedRow();
					if (selectedRow >= 0) {
	
						// Initialize the window
						final CustomerWindow window = new CustomerWindow(customers[selectedRow]);
						
						// Define the action listener for the delete button
						window.addActionListenerToDeleteButton(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								System.out.println("Delete!");
							}
						});
	
						// And define the action listener for the save button
						window.addActionListenerToSubmitButton(new ActionListener() {
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

}
