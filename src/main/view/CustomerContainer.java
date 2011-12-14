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
	
	CustomerTable customerTable;
	
	/**
	 * Creates a new container and initializes it with a list of all available customers.
	 */
	public CustomerContainer() { 
		// Set the layout
		setLayout(new BorderLayout());
		
		// Retrieve the customers and set the table model
		Customer[] customers = Customer.getAll();
		customerTable        = new CustomerTable(customers);
		
		// Enclose the table in a scroll pane
		JScrollPane pane     = new JScrollPane(customerTable);
		
		// Create the search button
		buttonSearch = new JButton("Search for customers");
		buttonSearch.setPreferredSize(new Dimension(200, 50));
		
		// Add the table and the button
		add(pane);
		add(buttonSearch, BorderLayout.SOUTH);
	}
	
	/**
	 * Adds a listener to the search button.
	 */
	public void addListenerToSearchButton(ActionListener listener) {
		buttonSearch.addActionListener(listener);
	}
	
}
