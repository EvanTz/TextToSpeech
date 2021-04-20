package commands;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ActionRecording implements ActionListener{
	private ReplayManager rm;
	private JButton activateRecording;
	private JButton playRecording;
	private JButton stopRecording;
	private JButton stopAudioButton;

	public ActionRecording() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == activateRecording) {
			activateRec();
		}
		else if(e.getSource() == playRecording) {
			playRec();
		}
		else if(e.getSource() == stopRecording & rm.isActiveRecording()) {
			endRec();
		}
		else if(e.getSource() == stopAudioButton) {
			stopSound();
		}
		
	}
	
	public void activateRec() {  // public for testing
		rm.startRecording();
		activateRecording.setText("Recording Actions");
		activateRecording.setBackground(Color.RED);
	}
	
	public void playRec() {  // public for testing
		playRecording.setText("Click Stop Audio");
		playRecording.setEnabled(false);
		rm.replay();
	}
	
	public void endRec() {  // public for testing
		rm.endRecording();
		activateRecording.setText("Record Actions");
		activateRecording.setBackground(Color.ORANGE);
	}
	
	public void stopSound() {  // public for testing
		playRecording.setText("Replay Recorded Actions");
		playRecording.setEnabled(true);
		rm.stopAudio();
	}

	public void setReplayManager(ReplayManager rm) {
		this.rm = rm;
	}
	
	public void setActivateRecButton(JButton activateButton) {
		this.activateRecording = activateButton;
	}
	
	public void setPlayRecButton(JButton playButton) {
		this.playRecording = playButton;
	}
	
	public void setStopRecButton(JButton stopButton) {
		this.stopRecording = stopButton;
	}
	
	public void setStopAudioButton(JButton stopAudioButton) {
		this.stopAudioButton = stopAudioButton;
	}
}
