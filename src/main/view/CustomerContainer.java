package main.view;

import java.awt.*;

import javax.swing.*;

import main.model.Customer;

/**
 * A container with information about customers as well as 
 * methods to edit and delete customers.
 */
public class CustomerContainer extends JPanel {
	
	private JButton buttonSearch;
	
	public CustomerContainer() { 
		setLayout(new BorderLayout());
		
		Customer[] customers = Customer.getAll();
		CustomerTable table  = new CustomerTable(customers);
		JTable customerTable = new JTable(table);
		JScrollPane pane		 = new JScrollPane(customerTable);
		
		// Create the search button
		buttonSearch = new JButton("Search for customers");
		
		// Add the table and the button
		add(pane);
		add(buttonSearch, BorderLayout.SOUTH);
	}
	
	
	
}
