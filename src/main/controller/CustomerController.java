	package main.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import main.model.Customer;
import main.model.Reservation;
import main.util.Log;
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
				
				window.buttonSubmit.addActionListener(new ActionListener() {

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
		CustomerTable table = container.showCustomers(customers);
		
		// If the customers array is empty, don't bother setting the listener, otherwise move on!
		if (customers != null) {
			// Set the listener for the table
			table.addMouseListener(new MouseAdapter() {
	
				// On click: Edit customer in a new window (if the row exists)
				public void mouseClicked(MouseEvent e) {
					int selectedRow = container.getCustomerTable().getSelectedRow();
					if (selectedRow >= 0) {
	
						// Initialize the window
						final CustomerWindow window = new CustomerWindow(customers[selectedRow]);
						
						// Define the action listener for the delete button
						window.buttonDelete.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								int customerId = window.customerId;
								if (customerId == 0) {
									Log.error("Unable to delete customer without id");
								} else {
									// Examine if there are any reservations by the
									// customer in the future. If so we can't
									// delete the customer.
									HashMap<String, String> fields = new HashMap<String, String>();
									fields.put("customer", customerId + "");
									
									// Do the search
									Reservation[] reservations = Reservation.searchWhere(fields);
									
									// Check if there's any reservations
									if (reservations != null) {
										Log.warning("Cannot delete customer with future reservations. Please delete the reservations first.");
									} else {
										Customer.delete(Customer.table, customerId);
									}
									
									// Dispose the window
									window.dispose();
								}

								// Close the window
								window.dispose();
							}
						});
	
						// And define the action listener for the save button
						window.buttonSubmit.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								int customerId = window.customerId;
								if (customerId != 0) {
									String firstName = window.fieldFirstName.getText();
									String lastName  = window.fieldLastName.getText();
									String email	 = window.fieldEmail.getText();
									String phone	 = window.fieldPhone.getText();
									String address	 = window.fieldAddress.getText();
									
									// A list of errors, if any.
									ArrayList<String> errorList = new ArrayList();
									
									// Check for empty fields
									if (firstName.isEmpty()) errorList.add("firstName");
									if (lastName.isEmpty())  errorList.add("lastName");
									if (email.isEmpty()) 	 errorList.add("email");
									if (phone.isEmpty()) 	 errorList.add("phone");
									if (address.isEmpty()) 	 errorList.add("address");
									
									if (errorList.size() > 0) {
										String message = "Unable to update customer with empty fields: ";
										for (int n = 0; n >= errorList.size(); n++) {
											// Add a field to the message (and a "," if we're not iterating over the last element
											message += errorList.get(n) + ((n < errorList.size()) ? ", " : "");
										}
										Log.error(message);
									} else {
										// Update the customer in the database.
										Customer customer = new Customer(firstName, lastName, email, phone, address);
										Customer.update(customer, customerId);
										
										// Update the customer table
										showCustomerTable(Customer.getAll());
									}
								} else {
									Log.warning("Unable to update a customer without an id. Please make sure the customer is stored in the database.");
								}
								
								// Close the window
								window.dispose();
							}
						});
					}
				}	
			});
		}
	}

}
