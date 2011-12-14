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

	private Customer[] customers;
	private CustomerTableModel tableModel;
	
	/**
	 * Creates a JTable showing a number of customers.
	 */
	public CustomerTable(final Customer[] customers) {
		this.customers = customers;
		tableModel = new CustomerTableModel(customers);
		setModel(tableModel);
		setRowHeight(20);
		setToolTipText("Click on row to edit.");
		
		// Adds a mouse listener 
		addMouseListener(new MouseListener() {
			// On click: Edit customer
      public void mouseClicked(MouseEvent arg0) {
	      int selectedRow = getSelectedRow();
	      if (selectedRow >= 0) {
	      	new CustomerWindow(customers[selectedRow]);
	      }
      }
      public void mouseEntered(MouseEvent arg0) {}
      public void mouseExited(MouseEvent arg0) {}
      public void mousePressed(MouseEvent arg0) {} 
      public void mouseReleased(MouseEvent arg0) {}
			
		});
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
		
		/**
		 * Returns the Customer array shown in the table model.
		 */
		public Customer[] getCustomers() {
			return customers;
		}
	
	  public int getRowCount() {
	    return data.length;
	  }
	
	  public Object getValueAt(int arg0, int arg1) {
	    return data[arg0][arg1];
	  }
	  
	}

}
