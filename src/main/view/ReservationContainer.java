package main.view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.util.*;
import java.util.Timer;

public class ReservationContainer extends JPanel
	
{
	Date startDate;
	Date endDate;
	JButton searchButton;
	JButton createReservation;
	
	/**
	 * Creates a new container
	 */
	public ReservationContainer()
	{	
		startDate 						= new Date(System.currentTimeMillis());		//day - hours - seconds ect. 
		endDate 						= new Date(startDate.getTime() + 10L * 1 * 24 * 60 * 60 * 1000);
		
		setLayout(new BorderLayout());
		
		JPanel centerPanel				= new JPanel();
		JPanel northPanel 				= new JPanel();
		JPanel southPanel 				= new JPanel(new GridLayout());
		JPanel westPanel 				= new JPanel();
		JPanel eastPanel 				= new JPanel();
		
		add(centerPanel, BorderLayout.CENTER);
		add(northPanel,	BorderLayout.NORTH);
		add(southPanel,	BorderLayout.SOUTH);
		add(westPanel,	BorderLayout.WEST);
		add(eastPanel,	BorderLayout.EAST);

		JTextField startOfPeriod		= new JTextField("Today", 10); //remake
		JTextField endOfPeriod			= new JTextField("Tomorrow", 10); //remake
		
		JButton createReservation		= new JButton("Create Reservation");
		JButton searchButton			= new JButton("Search");
		
		ReservationTable data 			= new ReservationTable(startDate, endDate);
		JScrollPane scrollPane			= new JScrollPane(new JTable(data));
		
		centerPanel.add(scrollPane);
		northPanel.add(startOfPeriod);
		northPanel.add(endOfPeriod);
		southPanel.add(createReservation);
		southPanel.add(searchButton);
		
		setVisible(true);		
	}
	
	/**
	 * Adds a listener to the search button.
	 */
	public JButton getSearchButton() {
		return searchButton;
	}
	
	/**
	 * Adds a listener to the create reservation button.
	 */
	public void setActionListenerToCreateButton(ActionListener listener) {
		createReservation.addActionListener(listener);
	}
}
