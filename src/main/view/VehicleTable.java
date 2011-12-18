package main.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;
import main.model.Vehicle;

public class VehicleTable extends JTable {
	
	private Vehicle[] vehicles;
	private VehicleTableModel vehicleTableModel;
	
	public VehicleTable(Vehicle[] vehicles) {
		
		if (vehicles != null) {
		this.vehicles = vehicles;
		} else vehicles = new Vehicle[0];
		vehicleTableModel = new VehicleTableModel(vehicles);
		setModel(vehicleTableModel);
		setRowHeight(20);
		setToolTipText("Click on row to edit.");
		
	}
	
	public void updateTable(Vehicle[] vehicles) {
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		vehicleTableModel = new VehicleTableModel(vehicles);
		setModel(vehicleTableModel);
	}
	
	class VehicleTableModel extends AbstractTableModel {
		
		private final String[] columnNames = {"ID", "Description", "Manufactor", "Model", "Class"};
		private Object[][] data;
		
		public VehicleTableModel(Vehicle[] vehicles) {
			data = new Object[vehicles.length][5];
			
			for (int i = 0; i < vehicles.length; i++) {
				data[i][0] = vehicles[i].id;
				data[i][1] = vehicles[i].description;
				data[i][2] = vehicles[i].manufacturer;
				data[i][3] = vehicles[i].model;
				data[i][4] = vehicles[i].vehicleClass.description;
			}
		}
		

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return data.length;
		}

		@Override
		public Object getValueAt(int arg0, int arg1) {
			// TODO Auto-generated method stub
			return data[arg0][arg1];
		}
		
		public String getColumnName(int n) {
			return columnNames[n];
		}
		
		public Vehicle[] getVehicles() {
			return vehicles;
		}
		
	}
	

}
