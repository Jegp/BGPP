package main.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import main.model.Vehicle;

/**
 * the main window of the vehicle section
 */
public class VehicleContainer extends JPanel	
{
	JButton addVehicleBtn;
	JButton deleteBtn;
	JScrollPane pane;
	/**
	 * constructor
	 */
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
		
		add(south, BorderLayout.SOUTH);
		add(west, BorderLayout.WEST);
		
		setVisible(true);		
	}
	
	
	
	/**
	 * add and actionlistener to the delete button
	 * @param dbl the actionlistener
	 */
	public void addDeleteBtnListener(ActionListener dbl) {
		deleteBtn.addActionListener(dbl);
	}
	/**
	 * add an actionlistener to the add vehicle button
	 * @param vbl the actionlistener
	 */
	public void addCreateVehicleBtnListener(ActionListener cvbl) {
		addVehicleBtn.addActionListener(cvbl);
		
	}
	
	/**
	 * add a table showing the vehicles in the database to the window
	 * @param table the table to display
	 */
	public void addTable(VehicleTable table) {
		pane = new JScrollPane(table);
		pane.setPreferredSize(new Dimension(1200, 600));
		JPanel centerPanel = new JPanel();
		centerPanel.add(pane);
		add(centerPanel, BorderLayout.CENTER);
	}
	
	/**
	 * add actionlistener to the delete button
	 * @param e
	 */
	public void addDeleteVehicleBtnListener(ActionListener e) {
		deleteBtn.addActionListener(e);
	}

	/**
	 * disable the delete button
	 */
	public void disableDeleteButton() {
		deleteBtn.setEnabled(false);
	}

	/**
	 * enable the delete button
	 */
	public void enableDeleteButton() {
		deleteBtn.setEnabled(true);	
	}
		
}
