package main.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import main.model.Vehicle;


public class VehicleContainer extends JPanel	
{
	JButton addVehicleBtn;
	JButton deleteBtn;
	JScrollPane pane;
	
	
	
	public VehicleContainer()
	{
		//Manage layouts
		setLayout(new BorderLayout());
		JPanel south = new JPanel(new GridLayout(1, 2));
		JPanel west = new JPanel();
		
		
		
		//Manage content
		addVehicleBtn = new JButton("Add Vehicle");
		addVehicleBtn.setPreferredSize(new Dimension(200, 50));
		deleteBtn = new JButton("Delete");
		deleteBtn.setEnabled(false);
		
		south.add(addVehicleBtn);
		south.add(deleteBtn);
		
		Vehicle[] vehicles = Vehicle.getAll();
		
		
		
		add(south, BorderLayout.SOUTH);
		add(west, BorderLayout.WEST);
		
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
	
	public void addTable(VehicleTable table) {
		pane = new JScrollPane(table);
		pane.setPreferredSize(new Dimension(1200, 600));
		JPanel centerPanel = new JPanel();
		centerPanel.add(pane);
		add(centerPanel, BorderLayout.CENTER);
	}
	
	public void addDeleteVehicleBtnListener(ActionListener e) {
		deleteBtn.addActionListener(e);
	}



	public void enableDeleteButton() {
		deleteBtn.setEnabled(true);
		
	}
	
}
