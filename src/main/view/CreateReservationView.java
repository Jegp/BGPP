package main.view;

import javax.swing.*;

import main.model.Customer;
import main.model.Period;
import main.model.Vehicle;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class CreateReservationView extends JFrame{

	/**
	 * the reservations user.
	 */
	private Customer customerInput;
	private Date startDateInput;
	private Date endDateInput;
	private Vehicle vehicleInput;
	
	private Customer customer;
	private Period period;
	private Vehicle vehicle;
	
	private JTextField customerTextField;
	private JTextField startDateTextField;
	private JTextField endDateTextField;
	private JTextField vehicleTextField;
	
	public final JLabel customerLabel = new JLabel("Customer:");
	public final JLabel startDateLabel = new JLabel("Start:");
	public final JLabel endDateLabel = new JLabel("End:");
	public final JLabel vehicleLabel = new JLabel("Vehicle:");
	
	private JButton saveButton			= new JButton("Save"); 
	private JButton editButton			= new JButton("Edit");
	private CancelButton cancelButton 	= new CancelButton(this);
	
	public CreateReservationView() {		
		customerTextField 	= new JTextField(10);
		startDateTextField 	= new JTextField(10);
		endDateTextField	= new JTextField(10);
		vehicleTextField	= new JTextField(10);

		editButton.setVisible(false);
		
		setLayout();
	}
	
	public CreateReservationView(Customer customer, Period period, Vehicle vehicle) {
		this.customer	= customer;
		this.period		= period;
		this.vehicle	= vehicle;
		
		//date -> string
		SimpleDateFormat dateFormatYYYYMMDD	= new SimpleDateFormat("YYYYMMDD");
		String periodStartToString 			= dateFormatYYYYMMDD.format(period.start);
		String periodEndToString			= dateFormatYYYYMMDD.format(period.end);
		
		customerTextField 	= new JTextField(customer.firstName + customer.lastName);
		startDateTextField 	= new JTextField(periodStartToString);
		endDateTextField	= new JTextField(periodEndToString);
		vehicleTextField	= new JTextField(vehicle.model);
		
		editButton.setVisible(true);
		
		setLayout();
	}
	
	public void setLayout() {
		setLayout(new BorderLayout());
		
		JPanel northPanel = new JPanel(new GridLayout(4,2));
		JPanel 
		JPanel southPanel = new JPanel(new FlowLayout());
		JPanel eastPanel  = new JPanel(new GridLayout(4,1));
		
		//arrange north panel with JLabels and JTextFIelds
		northPanel.add(customerLabel);
			northPanel.add(customerTextField);
		northPanel.add(startDateLabel);
			northPanel.add(startDateTextField);
		northPanel.add(endDateLabel);
			northPanel.add(endDateTextField);
		northPanel.add(vehicleLabel);
			northPanel.add(vehicleTextField);
		
		//arrange south panel with buttons
		southPanel.add(saveButton);
		southPanel.add(editButton);
		southPanel.add(cancelButton);
		
		add(northPanel, BorderLayout.NORTH);
		add(southPanel, BorderLayout.SOUTH);
				
		setVisible(true);
		pack();
	}
	
	public void submitReservation() {
		
	}
}
