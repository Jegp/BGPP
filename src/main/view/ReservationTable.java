package main.view;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import main.model.Period;
import main.model.Reservation;

import java.awt.*;
import java.util.*;

public class ReservationTable extends AbstractTableModel
{
	private Date startDate;
	private Date endDate;	
	private String[] date;
	private Object[][] data;
	private int periodInDays;
	private int numberOfVehicles;
	
	public ReservationTable(Date startDate, Date endDate) {
		
		Period period = new Period(startDate, endDate);
		Reservation[] reservations = Reservation.getFromPeriod(period);
		periodInDays = period.getLengthInDays();
		Vehicles[] allVehicles = Vehicles.getAll();
		
		numberOfVehicles = allVehicles.length();
		
		date 	= new String[periodInDays];
		data 	= new Object[periodInDays][numberOfVehicles];
		
		//Initialize calendar
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(startDate);
		
	 	//Initialize dates / columns
		for(int i = 0; i < periodInDays; i++) {
			date[i] = calendar.get(Calendar.DAY_OF_MONTH) + "" + "/" + calendar.get(Calendar.MONTH);

			for(int j = 0; j < numberOfVehicles; j++){
				if(reservations[i].period.isIncluded(date[i]));
			}

			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
	}

	public String getColumnName(int col){
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
