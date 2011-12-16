package main.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import main.model.Vehicle;
import main.model.VehicleClass;
import main.view.CreateVehicleView;
import main.view.VehicleContainer;

public class VehicleController {
	
	private VehicleContainer vehicleContainer;
	
	
	public VehicleController(VehicleContainer vehicleContainer) {
		
		this.vehicleContainer = vehicleContainer;
		
		vehicleContainer.addCreateVehicleBtnListener(new addVehicleBtnListener());
		
		
		
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
        			vehicleContainer.updateTable();
        			createVehicleView.kill();
        		}
        	}    		
    		});
    	}
	}

}
