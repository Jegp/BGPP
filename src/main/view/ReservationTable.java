package main.view;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.*;

public class ReservationTable extends AbstractTableModel
{
	public Date startDate;
	public Date endDate;
	public int numberOfVehicles;
	public boolean reservation = true;
	
	public String[] date = new String[10];
	public Object[][] data = new Object[10][numberOfVehicles];
	public ReservationTable(Date startDate, Date endDate)
	{
		this.startDate = startDate;
		this.endDate = endDate;
		
		/**
		 * Initialize calendar
		 */
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(startDate);
		
		/**
		 * Initialize dates / columns
		 */
		for(int i = 0; i < 10; i++){
			date[i] = calendar.get(Calendar.DAY_OF_MONTH) + "" + "/" + calendar.get(Calendar.MONTH);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		/**
		 * Initializes reservations in the table
		 */
		for(int i = 0; i < 10; i++){
		 for(int j = 0; j < numberOfVehicles - 1; j++){
		  if(reservation){
			  data[i][j] = "x";	  
		  }
		  else{
			  data[i][j] = "";
		  }
		 }
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
