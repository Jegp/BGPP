package main.view;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.*;

public class ReservationTable extends AbstractTableModel
{
	private Date startDate;
	private Date endDate;
	private int numberOfVehicles;
	private boolean reservation = true;
	
	public ReservationTable(Date startDate, Date endDate)
	{
		this.startDate = startDate;
		this.endDate = endDate;
		
		/**
		 * Initialize calendar
		 */
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(startDate);
		
		int[] date = new int[10];
		Object[][] data = new Object[5][5];
		
		/**
		 * Initialize dates / columns
		 */
		for(int i = 0; i < 10; i++){
			date[i] = calendar.get(Calendar.DAY_OF_MONTH);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		/**
		 * Initializes reservations in the table
		 */
		for(int i = 0; i < 10; i++){
		 for(int j = 0; j < numberOfVehicles; j++){
		  if(reservation){
			  data[i][j] = "x";	  
		  }
		  else{
			  data[i][j] = "";
		  }
		 }
		}
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean isEditable()
	{
		return false;
	}
}
