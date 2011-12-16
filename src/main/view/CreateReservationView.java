package main.view;

import javax.swing.*;

import main.model.Customer;
import main.model.Period;
import main.model.Reservation;
import main.model.Vehicle;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class CreateReservationView extends JFrame{
	
	private Customer customer;
	private Date startDate;
	private Date endDate;
	private Period period;
	private Vehicle vehicle;
	
	public final JLabel emptyLabel2		 = new JLabel("Customer:");
	public final JLabel emptyLabel1		 = new JLabel("Customer:");
	public final JLabel customerLabel	 = new JLabel("Customer:");
	public final JLabel startDateLabel 	 = new JLabel("Start:");
	public final JLabel endDateLabel 	 = new JLabel("End:");
	public final JLabel vehicleLabel 	 = new JLabel("Vehicle:");
	
	private JTextField startDateTextField;
	private JTextField endDateTextField;
	
	public final JButton customerCreateButton = new JButton("Create");
	public final JButton customerSearchButton = new JButton("Search");
	
	public final JButton saveButton			= new JButton("Save"); 
	public final JButton editButton			= new JButton("Edit");
	public final CancelButton cancelButton 	= new CancelButton(this);
	
	private String customerInput;
	private String startDateInput;
	private String endDateInput;
	private String vehicleInput;
	
	private JComboBox<String> customerDropDown;
	private JComboBox<String> vehicleDropDown;
	private String[] allCustomers;
	private String[] availableVehicles;
		
	private SimpleDateFormat dateFormat;
	private GregorianCalendar calendar;
	
	public CreateReservationView() {		
		startDateTextField 		= new JTextField(10);
		endDateTextField		= new JTextField(10);

		editButton.setVisible(false);
		
		getAllCustomersInArray();
		
		customerDropDown	= new JComboBox(allCustomers);
		vehicleDropDown 	= new JComboBox();
		
		setLayout();
	}
	
	public CreateReservationView(Customer customer, Period period, Vehicle vehicle) {
		this.customer	= customer;
		this.period		= period;
		this.vehicle	= vehicle;
		
		//date -> string
		dateFormat							= new SimpleDateFormat("MM/dd/yyyy");
		String periodStartToString 			= dateFormat.format(period.start);
		String periodEndToString			= dateFormat.format(period.end);
		
		startDateTextField 	= new JTextField(periodStartToString);
		endDateTextField	= new JTextField(periodEndToString);
		
		editButton.setVisible(true);
		
		setLayout();
	}
	
	public void setLayout() {
		setTitle("Create Reservation");
		setLayout(new BorderLayout());

		JPanel northPanel		= new JPanel(new GridLayout(1,2));
		JPanel withinNorthPanel = new JPanel(new GridLayout(1,2));	
		JPanel centerPanel 		= new JPanel(new GridLayout(4,2));
		JPanel southPanel		= new JPanel(new FlowLayout());
		JPanel eastPanel  		= new JPanel(new GridLayout(4,1));

		
		//arrange the north panel with JLabels, JTextFields and JButtons
		emptyLabel1.setVisible(false);
		emptyLabel2.setVisible(false);
		
		northPanel.add(emptyLabel1);
		northPanel.add(withinNorthPanel);
		withinNorthPanel.add(customerCreateButton);
		withinNorthPanel.add(customerSearchButton);
		
		//arrange center panel with JLabels and JTextFIelds
		centerPanel.add(customerLabel);
		centerPanel.add(customerDropDown);
		
		centerPanel.add(startDateLabel);
		centerPanel.add(startDateTextField);
		
		centerPanel.add(endDateLabel);
		centerPanel.add(endDateTextField);
		
		centerPanel.add(vehicleLabel);
		centerPanel.add(vehicleDropDown);
		
		//arrange south panel with buttons
		southPanel.add(saveButton);
		southPanel.add(editButton);
		southPanel.add(cancelButton);
		
		add(northPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
				
		setResizable(false);
		pack();
		setVisible(true);
	}
	
	public void getAllVehiclesinArray() {
		Vehicle[] vehicles 			= Vehicle.getAll();
		Reservation[] reservations	= Reservation.getFromPeriod(period);
		availableVehicles			= new String[vehicles.length];
		
		//iterates through all vehicles
		for(int i = 0; i < vehicles.length; i++) {
		 //iterates through the specified reservations
		 for(int j = 0; j < reservations.length; j++) {
			 if(vehicles[i].id == reservations[j].id) {
				 int k = 0;
				 while(k < period.getLengthInDays() && !reservations[j].period.isIncluded(calendar.getTime())) {
					   calendar.add(Calendar.DAY_OF_MONTH, k);
					   k++;
					if(k == period.getLengthInDays() && !reservations[j].period.isIncluded(calendar.getTime())) {
					   availableVehicles[i]	= reservations[j].vehicle.model; 
				  }
				 }
			 }
			 else {
				 availableVehicles[i] = reservations[j].vehicle.model;
			 }
		 }
		}
		
		
	}
	
	public void getAllCustomersInArray() {
		Customer[] customers	= Customer.getAll();
		allCustomers			= new String[customers.length];
		
		for(int i = 0; i < customers.length; i++) {
			allCustomers[i] = customers[i].firstName + " " + customers[i].lastName;
		}	
	}
	
	public void submitInput() {
		customerInput		= allCustomers[customerDropDown.getSelectedIndex()];
		startDateInput		= startDateTextField.getText();
		endDateInput		= endDateTextField.getText();
		vehicleInput		= availableVehicles[vehicleDropDown.getSelectedIndex()];
	}
	
	public Period setPeriod() {
		try {
		startDate			= dateFormat.parse(startDateTextField.getText());	
		endDate				= dateFormat.parse(endDateTextField.getText());
		
		calendar			= new GregorianCalendar();
		calendar.setTime(startDate);
		
		period				= new Period(startDate, endDate);
		} catch (ParseException e) {
			System.out.println("Invalid calendar input");
			e.printStackTrace();
		}
		return period;

	}
	
	public JButton getCustomerCreateButton() {
		return customerCreateButton;
	}

	public JButton getCustomerSearchButton() {
		return customerSearchButton;
	}
}
