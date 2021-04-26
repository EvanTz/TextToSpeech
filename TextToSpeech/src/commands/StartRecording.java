package commands;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class StartRecording implements ActionListener {

	private ReplayManager rm;
	private JButton activateRecording;

	public StartRecording() {
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		activateRec();
		activateRecording.setText("Recording Actions");
		activateRecording.setBackground(Color.RED);
	}

	public void activateRec() {  // public for testing
		rm.startRecording();
	}
	
	public void setReplayManager(ReplayManager rm) {
		this.rm = rm;
	}
	
	public void setActivateRecButton(JButton activateButton) {
		this.activateRecording = activateButton;
	}
	
}
