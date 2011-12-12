package main.view;

import java.awt.*;
import javax.swing.*;

public class ReservationContainer
	extends JPanel
{
	public ReservationContainer()
	{
		String[] columnNames = {"First Name", "Last Name","Sport","# of Years","Vegetarian"};
		Object[][] data = {
			{"Kathy", "Smith",
			"Snowboarding", new Integer(5), new Boolean(false)},
			{"John", "Doe",
			"Rowing", new Integer(3), new Boolean(true)},
			{"Sue", "Black",
			"Knitting", new Integer(2), new Boolean(false)},
			{"Jane", "White",
			"Speed reading", new Integer(20), new Boolean(true)},
			{"Joe", "Brown",
			"Pool", new Integer(10), new Boolean(false)}
		};
		
		JTable visualData = new JTable(data, columnNames);
		
		add(new JScrollPane(visualData));
		
		setVisible(true);		
	}
}
