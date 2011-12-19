package main.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.*;

import main.model.Customer;

/**
 * A container with information about customers as well as 
 * methods to edit and delete customers.
 */
public class CustomerContainer extends JPanel {
	
	/**
	 * The delete button.
	 */
	public final JButton buttonDelete;
	
	/**
	 * The search button.
	 */
	public final JButton buttonSearch;
	
	private CustomerTable customerTable;
	
	private JScrollPane pane; 
	
	/**
	 * Creates a new container and initializes it with a list of all available customers.
	 */
	public CustomerContainer() { 
		// Set the layout
		setLayout(new BorderLayout());
		
		// Create the delete button and disable it.
		buttonDelete = new JButton("Delete");
		buttonDelete.setEnabled(false);

		// Create the search button
		buttonSearch = new JButton("Search for customers");
		buttonSearch.setPreferredSize(new Dimension(200, 50));
		
		// Create a wrapper panel and westpanel for esthetic reasons
		JPanel wrapper = new JPanel(new GridLayout(1, 2));
		JPanel west = new JPanel();
		
		// Add the button
		wrapper.add(buttonSearch); wrapper.add(buttonDelete);
		
		// Add the wrapper panel
		add(wrapper, BorderLayout.SOUTH);
		add(west, BorderLayout.WEST);
	}
	
	/**
	 * Adds a listener to the search button.
	 */
	public void setActionListenerToSearchButton(ActionListener listener) {
		buttonSearch.addActionListener(listener);
	}
	
	/**
	 * Reset the table with the given customers.
	 * @param customers  An array of customers to show.
	 */
	public CustomerTable showCustomers(Customer[] customers) {
		// Remove the old pane if it exists
		if (pane != null) {
			pane.removeAll();
			remove(pane);
		}
		
		// Define the table model
		customerTable = new CustomerTable(customers);
		
		// Enclose the table in a new scroll pane
		pane = new JScrollPane(customerTable);
		
		// Insert the new center element.
		pane.setPreferredSize(new Dimension(1200, 600));
		add(pane, BorderLayout.CENTER);
		
		// Validate
		validate();
		
		// Return the table
		return customerTable;
	}
	
	/**
	 * Retrieve the table with customers.
	 */
	public CustomerTable getCustomerTable() {
		return customerTable;
	}
	
}
