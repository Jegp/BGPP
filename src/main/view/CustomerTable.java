package main.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;

import main.model.Customer;

/**
 * A JTable showing a number of customers.
 */
public class CustomerTable extends JTable {

	private CustomerTableModel tableModel;
	
	/**
	 * Creates a JTable showing a number of customers.
	 */
	public CustomerTable(final Customer[] customers) {
		// Set the table model
		tableModel = new CustomerTableModel(customers);
		setModel(tableModel);
		
		// Set the right row height
		setRowHeight(20);
		
		// Set the tooltip, so we know what to do with each row
		setToolTipText("Click on row to edit.");
	}

	/**
	 * A Table Model for a given array of customers.
	 */
	private class CustomerTableModel extends AbstractTableModel {
	
		/**
		 * The name of the columns.
		 */
		private final String[] columnNames = {"ID", "First name", "Last name", "Email", "Phone", "Address"};
		
		/**
		 * The data of the table.
		 */
		private final Object[][] data;
		
		/**
		 * Creates a new customer table with the given customers.
		 */
		public CustomerTableModel(Customer[] customers) {
			// If there aren't any customers, store an empty array.
			if (customers == null) {
				data = new Object[0][0];
				return;
			}
			
			// Create the data array
			data = new Object[customers.length][6];
			
			// Create the rows and columns
			for (int n = 0; n < customers.length; n++) {
				data[n][0] = customers[n].id;
				data[n][1] = customers[n].firstName;
				data[n][2] = customers[n].lastName;
				data[n][3] = customers[n].email;
				data[n][4] = customers[n].phone;
				data[n][5] = customers[n].address;
			}
		}
		
	  public int getColumnCount() {
	    return columnNames.length;
	  }
	
		public String getColumnName(int n){
			return columnNames[n];
		}
	
	  public int getRowCount() {
	    return data.length;
	  }
	
	  public Object getValueAt(int arg0, int arg1) {
	    return data[arg0][arg1];
	  }
	  
	}

}
