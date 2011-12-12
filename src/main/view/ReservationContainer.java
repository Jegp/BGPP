package main.view;

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class ReservationContainer
	extends JPanel
	
{
	//Date startDate;
	Date endDate;
	
	
	public ReservationContainer()
	{
		ReservationTable data = new ReservationTable(new Date(), endDate);
		
		JTable visualData = new JTable(data);
		add(new JScrollPane(visualData));
		
		setVisible(true);		
	}
}
