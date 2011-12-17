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
	private Period		period;
	private int 		periodInDays;
	private int 		numberOfVehicles;
	
	public ReservationTable(Date startDate, Date endDate) {
		
		this.startDate				= startDate;
		this.endDate				= endDate;
		period 						= new Period(startDate, endDate);
		Reservation[] reservations 	= Reservation.getFromPeriod(period);
		periodInDays 				= period.getLengthInDays();
		Vehicle[] allVehicles 		= Vehicle.getAll();
		if(allVehicles == null) {
			numberOfVehicles		= 0;
		}
		else {
		numberOfVehicles 			= allVehicles.length;
		}
		
		date 						= new String[periodInDays + 1];
		data 						= new Object[numberOfVehicles][periodInDays + 1];
		
		//Initialize calendar
		GregorianCalendar calendar 	= new GregorianCalendar();
		calendar.setTime(startDate);
		int actualMonth 			= calendar.get(Calendar.MONTH) + 1;
		
	 	//Initialize dates
		date[0] = "Vehicle";
		//adds all the vehicles to the first column
		for(int j = 1; j < periodInDays + 1; j++) {
			date[j] = calendar.get(Calendar.DAY_OF_MONTH) + "" + "/" + actualMonth;
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			}
		
		System.out.println(calendar.get(Calendar.MONTH));
		
		// creates the first column to contain all vehicles ID
		for(int i = 0; i < numberOfVehicles; i++) {
		 data[i][0] = allVehicles[i].model;
		  if(reservations == null) {
		  }
		  else{
		    // finds reservations with the same vehicle ID
		  	for(int j = 0; j < reservations.length; j++) {
		  		if(allVehicles[i].id == reservations[j].vehicle.getId()) {
		  			// iterates over the reservations with the same vehicle ID, and checks if they are part of the column/date is a part of its period  
		  			for(int k = 1; k < reservations.length; k++) {
		  				// checks if the the current date is a part of the reservations period
		  				if(reservations[k].period.isIncluded(calendar.getTime())) {
		  					data[i][k] = "x";
		  				}
		  				// changes current day to the following to.
		  				calendar.add(Calendar.DAY_OF_MONTH, 1);
				  }
			  }
		   }
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
