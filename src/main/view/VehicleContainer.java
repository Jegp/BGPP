package main.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class VehicleContainer extends JPanel	
{
	JButton addVehicleBtn;
	JButton deleteBtn;
	public VehicleContainer()
	{
		//Manage layouts
		setLayout(new BorderLayout());
		JPanel center = new JPanel();
		JPanel south = new JPanel();
		south.setLayout(new GridLayout());
		center.setLayout(new GridLayout());
		
		
		//Manage content
		addVehicleBtn = new JButton("Add Vehicle");
		deleteBtn = new JButton("Delete");
		south.add(addVehicleBtn);
		south.add(deleteBtn);
		
			
		String[] columnNames = {"ID No.", "Manufacter", "Model", "Class"};
		Object[][] data = {
				{"10", "Toyota", "Corolla", "2 doors"},{"11", "Suzuki", "Swift", "2 doors"}
		};		
		JTable vehicleTable = new JTable(data, columnNames);
		center.add(new JScrollPane(vehicleTable));
		add(south, BorderLayout.SOUTH);
		add(center, BorderLayout.NORTH);
		
		setVisible(true);		
	}
	
	public void addVehicleBtnListener(ActionListener vbl) {
		addVehicleBtn.addActionListener(vbl);
	}
	
	public void addDeleteBtnListener(ActionListener dbl) {
		deleteBtn.addActionListener(dbl);
	}

	public void addCreateVehicleBtnListener(ActionListener cvbl) {
		addVehicleBtn.addActionListener(cvbl);
		
	}
}
