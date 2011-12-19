package main.controller;

import java.awt.event.*;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import main.model.Reservation;
import main.model.Vehicle;
import main.model.VehicleClass;
import main.view.*;

/**
 * Controller of event flow in the vehilce part of the system.
 */
public class VehicleController {
	
	private VehicleContainer vehicleContainer;
	private VehicleTable vehicleTable;
	private Vehicle[] vehicles;
	
	/**
	 * constructor
	 * @param vehicleContainer the window to control
	 */
	public VehicleController(VehicleContainer vehicleContainer) {
		
		this.vehicleContainer = vehicleContainer;
		
		
		vehicles = Vehicle.getAll();
		vehicleTable = new VehicleTable(vehicles);
		
		vehicleTable.addMouseListener(new EditVehicleListener());
		vehicleContainer.addTable(vehicleTable);		
		
		this.vehicleContainer.addCreateVehicleBtnListener(new AddVehicleBtnListener());
		this.vehicleContainer.addDeleteVehicleBtnListener(new DeleteVehicleBtnListener());
	}
	
	/**
	 * A class to define action for the "add vehicle" button
	 * @author sunedebel
	 *
	 */
	class AddVehicleBtnListener implements ActionListener {
		/**
		 * opens a window for creating vehicles and add an actionlistener to the save button.
		 */
    	public void actionPerformed(ActionEvent e) {
    		final CreateVehicleView createVehicleView = new CreateVehicleView();
    		
    		createVehicleView.addSaveVehicleListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
        		String description = createVehicleView.getNewDescription();
        		String manufactorer = createVehicleView.getNewManufactorer();
        		String model = createVehicleView.getNewModel();
        		
       
        		if (description.equals("") || manufactorer.equals("") || model.equals("")) {
    				JOptionPane.showMessageDialog(createVehicleView, "Please fill out all boxes",
        			"Insufficient information", JOptionPane.ERROR_MESSAGE);
        		} else {
        			int selectedVehicleClassID = createVehicleView.getNewVehicleClassID();
            		VehicleClass vehicleClass = VehicleClass.getWhereId(selectedVehicleClassID);
            		Vehicle v = new Vehicle(description, manufactorer, model, vehicleClass);
        			Vehicle.save(v);
        			vehicleTable.updateTable(Vehicle.getAll());
        			createVehicleView.kill();
        			vehicles = Vehicle.getAll();
        		}
        	}    		
    		});
    	}
	}
	
	/**
	 * an actionlistener for the vehicletable
	 * @author sunedebel
	 *
	 */
	class EditVehicleListener implements MouseListener {
		/**
		 * opens an edit window, fills out the information and add actionlistener to the save button.
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
			
			if (e.getClickCount() == 2) {
				final Vehicle selectedVehicle = vehicles[vehicleTable.getSelectedRow()];
			
			
			
				final EditVehicleView editVehicleView = new EditVehicleView(selectedVehicle);
			
				editVehicleView.addSaveActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						String description = editVehicleView.getNewDescription();
						String manufactorer = editVehicleView.getNewManufactorer();
						String model = editVehicleView.getNewModel();
	        		
	       
						if (description.equals("") || manufactorer.equals("") || model.equals("")) {
							JOptionPane.showMessageDialog(editVehicleView, "Please fill out all boxes",
									"Insufficient information", JOptionPane.ERROR_MESSAGE);
						} else {
							int selectedVehicleClassID = editVehicleView.getNewVehicleClassID();
							VehicleClass vehicleClass = VehicleClass.getWhereId(selectedVehicleClassID);
							Vehicle v = new Vehicle(description, manufactorer, model, vehicleClass);
							Vehicle.update(v, selectedVehicle.id);
							
							if (Vehicle.getAll() != null) {
							vehicleTable.updateTable(Vehicle.getAll());
							} else {
								vehicleTable.updateTable(new Vehicle[0]);
							}
							editVehicleView.kill();
							vehicles = Vehicle.getAll();
						}
				}
				});
			}
			
		}
		/**
		 * not used
		 */
		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		/**
		 * not used
		 */
		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		/**
		 * not used
		 */
		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		/**
		 * enables the delete button of the vehicleContaner when a vehicle in the table is selected
		 */
		@Override
		public void mouseReleased(MouseEvent arg0) {
			if (vehicleTable.getSelectedRowCount() == 1) {
				vehicleContainer.enableDeleteButton();
			} else {
				vehicleContainer.disableDeleteButton();
			}
			
		}
		
	}
	
	/**
	 * an actionlistener for the delete button
	 * @author sunedebel
	 *
	 */
	class DeleteVehicleBtnListener implements ActionListener {
		/**
		 * deletes a vehicle from the database and updates the table.
		 * Only lets the user delete if the vehicle is not involved in any future reservations
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			if (vehicleTable.getSelectedRow() >= 0) {
				HashMap<String, String> fields = new HashMap<String, String>();				
				fields.put("vehicle", "" + vehicles[vehicleTable.getSelectedRow()].id);
				Reservation[] relevantReservations = Reservation.searchWhere(fields);
				boolean hasFutureReservations = false;
				if (relevantReservations != null)
				for (Reservation r : relevantReservations) {
					if (r.period.end.after(new Date()))
						hasFutureReservations = true;
				}
				
				
				
				if (!hasFutureReservations) {
					Vehicle v = vehicles[vehicleTable.getSelectedRow()];
					Vehicle.delete(v.getTable(), v.id);
					if (Vehicle.getAll() != null) {
						vehicleTable.updateTable(Vehicle.getAll());
						vehicles = Vehicle.getAll();
					} else {				
						vehicleTable.updateTable(new Vehicle[0]);
					}				 	
				} else {
						JOptionPane.showMessageDialog(vehicleContainer, "That vehicle has a future reservation",
						"Future Reservation", JOptionPane.ERROR_MESSAGE);						
				}
			}
			
		}
		
	}

}
