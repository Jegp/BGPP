package main.view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import main.controller.ReservationController;

import java.util.*;
import java.util.Timer;

public class ReservationContainer extends JPanel
	
{
	private Date startDate;
	private Date endDate;
	private JButton searchButton;
	private JButton createReservationButton;
	private ReservationController controller;
	
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
		
		createReservationButton			= new JButton("Create Reservation");
		createReservationButton.setPreferredSize(new Dimension(200, 50));
		searchButton					= new JButton("Search");
		
		ReservationTable data 			= new ReservationTable(startDate, endDate);
		JScrollPane scrollPane			= new JScrollPane(new JTable(data));
		scrollPane.setPreferredSize(new Dimension(1200, 600));
		
		centerPanel.add(scrollPane);
		northPanel.add(startOfPeriod);
		northPanel.add(endOfPeriod);
		southPanel.add(createReservationButton);
		southPanel.add(searchButton);
		
		setVisible(true);		
	}
	
	public JButton getCreateReservationButton() {
		return createReservationButton;
	}
	
	/**
	 * returns button
	 */
	public JButton getSearchButton() {
		return searchButton;
	}
}