package main.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

import main.model.Customer;
import main.util.Log;

/**
 * A window that displays information about a given customer
 * as well as methods to manipulate that customer. This window is not meant 
 * for creating customers.
 */
public class CustomerWindow extends JFrame {
	
	private final JLabel textFirstName = new JLabel("First name:");
	private final JLabel textLastName  = new JLabel("Last name:");
	private final JLabel textEmail	   = new JLabel("Email:");
	private final JLabel textPhone     = new JLabel("Phone number:");
	private final JLabel textAddress   = new JLabel("Address:");
	private JLabel textHelpTip;
	
	public final JTextField fieldFirstName;
	public final JTextField fieldLastName;
	public final JTextField fieldEmail;
	public final JTextField fieldPhone;
	public final JTextArea fieldAddress;
	
	public final JButton buttonDelete;
	public final JButton buttonSubmit;
	public final CancelButton buttonCancel;
	
	/**
	 * The id of the customer.
	 */
	public final int customerId;
	
	/**
	 * Create a new window that searches for a given customer.
	 */
	public CustomerWindow() {
		// Set the title
		setTitle("Search for customers");
		
		// Set the elements to empty fields
		fieldFirstName	= new JTextField("");
		fieldLastName	= new JTextField("");
		fieldEmail		= new JTextField("");
		fieldPhone		= new JTextField("");
		fieldAddress	= new JTextArea("");
		customerId		= 0;
		
		// Set the help text
		textHelpTip = new JLabel("<html>It's possible to do wildcard searches <br />using the character '%'.</html>");
		
		// Create buttons
		buttonDelete = new JButton("");
		buttonDelete.setVisible(false);
		buttonSubmit = new JButton("Search");
		buttonCancel = new CancelButton(this);
		
		// Generate layout
		generateLayout();
	}
	
	/**
	 * Create a new window that can manipulate the given customer.
	 */
	public CustomerWindow(Customer customer) {
		// Close the window if the customer doesn't exists or hasn't been stored yet.
		if (customer == null) {
			Log.error("Cannot edit a non-existing customer.");
			dispose();
		} else if (customer.id == 0) {
			Log.error("Cannot edit a customer that does not exist in the database.");
			dispose();
		}
		
		// Set the title
		setTitle("Edit " + customer.firstName + " " + customer.lastName);
		
		// Set the elements to contain data of the customer
		fieldFirstName	= new JTextField(customer.firstName);
		fieldLastName	= new JTextField(customer.lastName);
		fieldEmail		= new JTextField(customer.email);
		fieldPhone		= new JTextField(customer.phone);
		fieldAddress	= new JTextArea(customer.address);
		customerId		= customer.id;
		
		// Create buttons
		buttonDelete = new JButton("Delete");
		buttonSubmit = new JButton("Save");
		buttonCancel = new CancelButton(this);
		
		// Generate the layout
		generateLayout();
	}
	
	/**
	 * Add an action listener to the submit button.
	 */
	public void addActionListenerToSubmitButton(ActionListener listener) {
		if (buttonSubmit != null)
			buttonSubmit.addActionListener(listener);
	}
	
	/**
	 * Add an action listener to the delete button.
	 */
	public void addActionListenerToDeleteButton(ActionListener listener) {
		if (buttonDelete != null)
			buttonDelete.addActionListener(listener);
	}
	
	/**
	 * Generate layout with the fields defined in the class.
	 */
	private void generateLayout() {
		// Set layout
		setLayout(new BorderLayout());
		
		// Add spaces
		JLabel space1 = new JLabel("");
		JLabel space2 = new JLabel("");
		JLabel space3 = new JLabel("");
		space1.setPreferredSize(new Dimension(10, 10));
		space2.setPreferredSize(new Dimension(10, 10));
		space3.setPreferredSize(new Dimension(10, 10));
		add(space1, BorderLayout.EAST); 
		add(space2, BorderLayout.WEST);
		add(space3, BorderLayout.NORTH);
		
		// Create center wrapper
		JPanel center = new JPanel();
		center.setLayout(new BorderLayout());
		
		// Create wrapping panels for use inside center
		JPanel northWrapper = new JPanel();
		JPanel southWrapper = new JPanel();
		
		// Set the layout - 3px horizontal and 6 px vertical distance
		northWrapper.setLayout(new GridLayout(5, 2, 3, 6));
		southWrapper.setLayout(new GridLayout(1, 2, 3, 6));

		// Add the elements to the pane
		northWrapper.add(textFirstName);	northWrapper.add(fieldFirstName);
		northWrapper.add(textLastName);		northWrapper.add(fieldLastName);
		northWrapper.add(textEmail);		northWrapper.add(fieldEmail);
		northWrapper.add(textPhone);		northWrapper.add(fieldPhone);
		northWrapper.add(new JLabel());		northWrapper.add(new JLabel()); // Add empty space
		southWrapper.add(textAddress);		southWrapper.add(new JScrollPane(fieldAddress)); // Add a scroll pane for the text area.
		textAddress.setPreferredSize(new Dimension(100, 60));
		
		// Add the wrappers to the center panel
		center.add(northWrapper, BorderLayout.NORTH);
		center.add(southWrapper, BorderLayout.SOUTH);
		
		// Add the center
		add(center, BorderLayout.CENTER);
		
		// Create a south wrapper
		JPanel south = new JPanel();
		south.setLayout(new BorderLayout());
		
		// Create inline wrappers and set their layout
		JPanel southDescription = new JPanel();
		JPanel southButtons     = new JPanel();	
		southDescription.setLayout(new BorderLayout());
		southButtons.setLayout(new GridLayout(1, 3, 3, 6));
		
		// Add the help
		if (textHelpTip != null) {
			southDescription.add(textHelpTip, BorderLayout.CENTER);
		
			// Add space
			JPanel space4 = new JPanel(); space4.setPreferredSize(new Dimension(10, 5));
			JPanel space5 = new JPanel(); space5.setPreferredSize(new Dimension(10, 5));
			southDescription.add(space4, BorderLayout.EAST);
			southDescription.add(space5, BorderLayout.WEST);
		}
		
		// Add space and buttons
		//southButtons.add(new JLabel()); southButtons.add(new JLabel()); southButtons.add(new JLabel());
		southButtons.add(buttonDelete); southButtons.add(buttonSubmit); southButtons.add(buttonCancel);
		
		// Add the wrappers to the south panel
		south.add(southDescription, BorderLayout.NORTH);
		south.add(southButtons, BorderLayout.SOUTH);
		
		// Add the south panel
		add(south, BorderLayout.SOUTH);
		
		// Force it to fix size
		setResizable(false);
		
		// Initialize
		setVisible(true);
		pack();
	}
	
}
