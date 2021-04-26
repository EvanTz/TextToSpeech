package commands;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class EndRecording implements ActionListener {

	private ReplayManager rm;
	private JButton activateRecording;

	public EndRecording() {
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(rm.isActiveRecording()) {
			endRec();
			activateRecording.setText("Record Actions");
			activateRecording.setBackground(Color.ORANGE);
		}
	}
	
	public void endRec() {  // public for testing
		rm.endRecording();
	}
	
	public void setReplayManager(ReplayManager rm) {
		this.rm = rm;
	}
	
	public void setActivateRecButton(JButton activateButton) {
		this.activateRecording = activateButton;
	}


}
