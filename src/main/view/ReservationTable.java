package main.view;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import main.model.Period;
import main.model.Reservation;
import main.model.Vehicle;

import java.awt.*;
import java.security.AllPermission;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReservationTable extends AbstractTableModel
{
	private Date	 				startDate;
	private Date 					endDate;

	private Object[][] 				data;
	private Period					period;
	private SimpleDateFormat 		toString;
	private int						numberOfReservations;
	public Reservation[] 			reservations;
	
	private final String[] columnNames = {"ID", "Vehicle", "Start", "End", "Customer"};
	
	public ReservationTable(Date startDate, Date endDate) {
		
		this.startDate	= startDate;
		this.endDate	= endDate;
		
		period 			= new Period(startDate, endDate);
		
		reservations	= Reservation.getFromPeriod(period);
		
		if(reservations == null) {
			data		= new Object[0][0];
		} else {
			data		= new Object[numberOfReservations][columnNames.length];
		}
		
		toString		= new SimpleDateFormat("dd/MM/YYYY");
		
		for(int i = 0; i < numberOfReservations; i++) {
			data[i][0] = reservations[i].vehicle.id;
			data[i][1] = reservations[i].vehicle.manufacturer;
			data[i][2] = toString.format(reservations[i].period.start);
			data[i][3] = toString.format(reservations[i].period.end);
			data[i][4] = reservations[i].customer.firstName + " " + reservations[i].customer.lastName;
		}
	}
	
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.length;
	}

	public Object getValueAt(int arg0, int arg1) {
		return data[arg0][arg1];
	}
	
	public boolean isEditable()
	{
		return false;
	}
}
