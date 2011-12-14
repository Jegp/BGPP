package main.view;

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class ReservationContainer
	extends JPanel
	
{
	Date startDate;
	Date endDate;
	
	
	public ReservationContainer()
	{	
		GregorianCalendar calendar 		= new GregorianCalendar();
		startDate 						= new Date(); 
		endDate 						= new Date();
		calendar.add(Calendar.MONTH, 10);
		
		ReservationTable data 			= new ReservationTable(startDate, endDate);
		
		JTable visualData = new JTable(data);
		add(new JScrollPane(visualData));
		
		setVisible(true);		
	}
}
