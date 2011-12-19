package main.view;

import javax.swing.table.AbstractTableModel;

import main.model.Period;
import main.model.Reservation;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * A reservation table that displays a given set of reservations.
 */
public class ReservationTable extends AbstractTableModel
{
	private Date	 				startDate;
	private Date 					endDate;

	private Object[][] 				data;
	private Period					period;
	private SimpleDateFormat 		toString;
	
	private final String[] columnNames = {"ID", "Vehicle", "Start", "End", "Customer"};
	
	/**
	 *  a variable that can hold an array of reservations
	 */
	public Reservation[] 			reservations;
	
	/**
	 * a constructor that generates table by receiving a start day, and ending day of the reservation.
	 * @param startDate
	 * @param endDate
	 */
	public ReservationTable(Date startDate, Date endDate) {
		this.startDate	= startDate;
		this.endDate	= endDate;
		
		period 			= new Period(startDate, endDate);
		
		reservations	= Reservation.getFromPeriod(period);
		
		if (reservations == null) {
			data		= new Object[0][0];
		} else {
			data		= new Object[reservations.length][columnNames.length];
		}
		
		toString		= new SimpleDateFormat("dd/MM/yyyy");
		
		for(int i = 0; i < data.length; i++) {
			data[i][0] = reservations[i].id;
		if(reservations[i].vehicle == null) {
			data[i][1] = "No Vehicle ** This is an error";
		}
		else {
			data[i][1] = reservations[i].vehicle.model;
		}
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
