package main.view;

import javax.swing.table.*;

import main.model.Customer;

/**
 * A Table Model for a given array of customers.
 */
public class CustomerTable extends AbstractTableModel {

	String[] columnNames = {"ID", "First name", "Last name", "Email", "Phone", "Address"};
	
	Object[][] data;
	
	public CustomerTable(Customer[] customers) {
		data = new Object[customers.length][6];
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
