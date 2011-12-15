package main.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import main.model.Customer;

/**
 * A container with information about customers as well as 
 * methods to edit and delete customers.
 */
public class CustomerContainer extends JPanel {
	
	private JButton buttonSearch;
	
	private CustomerTable customerTable;
	
	private JScrollPane pane;
	
	private ActionListener tableListener;
	
	/**
	 * Creates a new container and initializes it with a list of all available customers.
	 */
	public CustomerContainer() { 
		// Set the layout
		setLayout(new BorderLayout());
		
		// Create the search button
		buttonSearch = new JButton("Search for customers");
		buttonSearch.setPreferredSize(new Dimension(200, 50));
		
		// Add the button
		add(buttonSearch, BorderLayout.SOUTH);
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
	public void showCustomers(Customer[] customers) {
		// Remove the old pane if it exists
		if (pane != null)
			remove(pane);
		
		// Define the table model
		customerTable = new CustomerTable(customers);
		
		// Enclose the table in a new scroll pane
		pane = new JScrollPane(customerTable);
		
		// Insert the new center element.
		add(pane, BorderLayout.CENTER);
		
		// Validate
		validate();
	}
	
	/**
	 * Retrieve the table with customers.
	 */
	public CustomerTable getCustomerTable() {
		return customerTable;
	}
	
}
