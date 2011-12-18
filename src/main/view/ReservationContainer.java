package main.view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import main.controller.ReservationController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Timer;

public class ReservationContainer extends JPanel {
	private Date startDate;
	private Date endDate;
	private JButton deleteButton;
	private JButton createReservationButton;
	private JButton updatePeriodButton;

	private ReservationController controller;
	private JTextField startPeriod;
	private JTextField endPeriod;
	private SimpleDateFormat dateFormat;
	private ReservationTable data;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel centerPanel;
	
	/**
	 * Creates a new container
	 */
	public ReservationContainer() {	
		startDate 						= new Date(System.currentTimeMillis());		//day - hours - seconds ect. 
		endDate 						= new Date(startDate.getTime() + 10L * 1 * 24 * 60 * 60 * 1000);
		
		setLayout(new BorderLayout());
		
		centerPanel						= new JPanel();
		JPanel northPanel 				= new JPanel();
		JPanel southPanel 				= new JPanel(new GridLayout());
		JPanel westPanel 				= new JPanel();
		JPanel eastPanel 				= new JPanel();
		
		add(centerPanel, BorderLayout.CENTER);
		add(northPanel,	BorderLayout.NORTH);
		add(southPanel,	BorderLayout.SOUTH);
		add(westPanel,	BorderLayout.WEST);
		add(eastPanel,	BorderLayout.EAST);
		
		dateFormat		= new SimpleDateFormat("dd/MM/yyyy");
		
		startPeriod		= new JTextField(dateFormat.format(startDate)); 
		endPeriod		= new JTextField(dateFormat.format(endDate)); 
		
		createReservationButton					= new JButton("Create Reservation");
		createReservationButton.setPreferredSize( new Dimension(200, 50));
		deleteButton							= new JButton("Delete");
		updatePeriodButton						= new JButton("Update");
		
		addTable();
		
		northPanel.add(startPeriod);
		northPanel.add(endPeriod);
		northPanel.add(updatePeriodButton);
		southPanel.add(createReservationButton);
		southPanel.add(deleteButton);
		
		setVisible(true);		
	}

	public JTable addTable() {
		if(scrollPane == null) {
			data 							= new ReservationTable(startDate, endDate);
			table							= new JTable(data);
			scrollPane						= new JScrollPane(table);
			scrollPane.setPreferredSize		( new Dimension(1200, 600));
			
			centerPanel.add(scrollPane);
		} else { 
			centerPanel.remove(scrollPane);
			data 							= new ReservationTable(startDate, endDate);
			table							= new JTable(data);
			scrollPane						= new JScrollPane(table);
			scrollPane.setPreferredSize		( new Dimension(1200, 600));
			
			centerPanel.add(scrollPane);
		}
			
		centerPanel.revalidate();
		centerPanel.repaint();
		return table;
	}
	
	public void updatePeriod() {
		try {
			startDate 	= dateFormat.parse(startPeriod.getText());
			endDate		= dateFormat.parse(endPeriod.getText());
		
		} catch (ParseException e) {
			System.out.println("invalid date");
			e.printStackTrace();
		}
	}
	
	public ReservationTable getData() {
		return data;
	}

	/**
	 * returns the button south button which should open a new JFrame to create a reservation
	 * @return
	 */
	public JButton getCreateReservationButton() {
		return createReservationButton;
	}
	
	/**
	 * returns a button from the reservation view
	 * @return JButton
	 */
	public JButton getDeleteButton() {
		return deleteButton;
	}
	
	/**
	 * returns the table which shows the reservations with the given period
	 * @return JTable
	 */
	public JTable getTable() {
		return table;
	}
	
	public JButton getUpdatePeriodButton() {
		return updatePeriodButton;
	}
	
	public String getStartPeriodTextField() {
		return startPeriod.getText();
	}
	
	public String getEndPeriodTextField() {
		return endPeriod.getText();
	}
}
