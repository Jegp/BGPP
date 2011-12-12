package main.view;
import javax.swing.*;
import java.awt.event.*;

public class CancelBtn extends JButton {
	
	private JFrame fParent;
	
	public CancelBtn(JFrame fParent) {
		super("Cancel");
		this.fParent = fParent;
		addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				cancel(); 
			}
		});
		
	}
	
	public void cancel() {
		fParent.dispose();
	}

}
