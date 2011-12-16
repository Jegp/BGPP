package main.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import main.controller.Controller.createActionListenerToCreateButton;
import main.model.Vehicle;
import main.model.VehicleClass;
import main.view.CreateVehicleView;
import main.view.EditVehicleView;
import main.view.VehicleContainer;
import main.view.VehicleTable;

public class VehicleController {
	
	private VehicleContainer vehicleContainer;
	private VehicleTable vehicleTable;
	private Vehicle[] vehicles;
	
	
	public VehicleController(VehicleContainer vehicleContainer) {
		
		this.vehicleContainer = vehicleContainer;
		
		
		vehicles = Vehicle.getAll();
		vehicleTable = new VehicleTable(vehicles);
		
		vehicleTable.addMouseListener(new EditVehicleListener());
		vehicleContainer.addTable(vehicleTable);		
		
		this.vehicleContainer.addCreateVehicleBtnListener(new addVehicleBtnListener());
		
		
		
		
		
	}
	
	class addVehicleBtnListener implements ActionListener {
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
        		}
        	}    		
    		});
    	}
	}
	
	class EditVehicleListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			
			
			int selectedRow = vehicleTable.getSelectedRow();
			
			new EditVehicleView(vehicles[selectedRow]);
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	public void updateTable() {
		vehicleTable.updateTable(Vehicle.getAll());
	}

}
