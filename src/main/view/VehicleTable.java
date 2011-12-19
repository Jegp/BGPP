package main.view;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.*;
import main.model.Vehicle;
/**
 * A table displaying vehicles in the database
 */
public class VehicleTable extends JTable {
	
	private Vehicle[] vehicles;
	private VehicleTableModel vehicleTableModel;
	
	/**
	 * Constructor
	 * @param vehicles array of vehicles to display in the table
	 */
	public VehicleTable(Vehicle[] vehicles) {
		
		if (vehicles != null) {
		this.vehicles = vehicles;
		} else vehicles = new Vehicle[0];
		vehicleTableModel = new VehicleTableModel(vehicles);
		setModel(vehicleTableModel);
		setRowHeight(20);
		setToolTipText("Click on row to edit.");
		
	}
	
	/**
	 * redraw the table
	 * @param vehicles the array of vehicles to update the table with
	 */
	public void updateTable(Vehicle[] vehicles) {
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		vehicleTableModel = new VehicleTableModel(vehicles);
		setModel(vehicleTableModel);
	}
	
	/**
	 * table model managing the data of the vehicle table
	 * @author sunedebel
	 *
	 */
	class VehicleTableModel extends AbstractTableModel {
		
		private final String[] columnNames = {"ID", "Description", "Manufactor", "Model", "Class"};
		private Object[][] data;
		
		/**
		 * constructor
		 * @param vehicles the array of vehicles containing data
		 */
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
		
		/**
		 * get the number of columns
		 * @ return number of columns
		 */
		@Override
		public int getColumnCount() {
			return columnNames.length;
		}
		
		/**
		 * get the number of rows
		 */
		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return data.length;
		}
		
		/**
		 * get the data at a specific location in the table
		 */
		@Override
		public Object getValueAt(int arg0, int arg1) {
			// TODO Auto-generated method stub
			return data[arg0][arg1];
		}
		
		/**
		 * get the name of the column at specific column
		 * @ param n the columnnumber
		 */
		public String getColumnName(int n) {
			return columnNames[n];
		}
		
		/**
		 * get the array containing the data
		 * @return the array of vehicles
		 */
		public Vehicle[] getVehicles() {
			return vehicles;
		}
		
	}
	

}
