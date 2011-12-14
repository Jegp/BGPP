package main.view;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import main.model.Period;
import main.model.Reservation;
import main.model.Vehicle;

import java.awt.*;
import java.util.*;

public class ReservationTable extends AbstractTableModel
{
	private Date	 	startDate;
	private Date 		endDate;	
	private String[]	date;
	private Object[][] 	data;
	private int 		periodInDays;
	private int 		numberOfVehicles;
	
	public ReservationTable(Date startDate, Date endDate) {
		
		Period period 				= new Period(startDate, endDate);
		Reservation[] reservations 	= Reservation.getFromPeriod(period);
		periodInDays 				= period.getLengthInDays();
		Vehicle[] allVehicles 		= Vehicle.getAll();
		numberOfVehicles 			= allVehicles.length;
		
		date 						= new String[periodInDays];
		data 						= new Object[numberOfVehicles][periodInDays];
		
		//Initialize calendar
		GregorianCalendar calendar 	= new GregorianCalendar();
		calendar.setTime(startDate);
		
	 	//Initialize dates
		for(int j = 0; j < periodInDays; j++){
			date[j] = calendar.get(Calendar.DAY_OF_MONTH) + "" + "/" + calendar.get(Calendar.MONTH);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			}
		
		for(int i = 0; i < numberOfVehicles; i++) {
			for(int j = 0; j < periodInDays; j++) {
				//if(reservations[i].period.isIncluded(calendar.getTime())) {
				//	data[i][j] = "x";
				//}
				//else{
				//	data[i][j] = "";
				//}
				//calendar.add(Calendar.DAY_OF_MONTH, j);
			}	
		}
	}

	public String getColumnName(int col) {
		return date[col].toString();
	}
	
	public int getColumnCount() {
		return date.length;
	}

	public int getRowCount() {
		return numberOfVehicles; //should be replaced with method from model.
	}

	public Object getValueAt(int arg0, int arg1) {
		return data[arg0][arg1];
	}
	
	public boolean isEditable()
	{
		return false;
	}
}
