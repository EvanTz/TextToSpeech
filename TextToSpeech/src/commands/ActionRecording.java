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
			activateRecording.setText("Recording Actions");
			activateRecording.setBackground(Color.RED);
		}
		else if(e.getSource() == playRecording) {
			playRecording.setText("Click Stop Audio");
			playRecording.setEnabled(false);
			playRec();
		}
		else if(e.getSource() == stopRecording & rm.isActiveRecording()) {
			endRec();
			activateRecording.setText("Record Actions");
			activateRecording.setBackground(Color.ORANGE);
		}
		else if(e.getSource() == stopAudioButton) {
			playRecording.setText("Replay Recorded Actions");
			playRecording.setEnabled(true);
			stopSound();
		}
		
	}
	
	public void activateRec() {  // public for testing
		rm.startRecording();
	}
	
	public void playRec() {  // public for testing
		rm.replay();
	}
	
	public void endRec() {  // public for testing
		rm.endRecording();
	}
	
	public void stopSound() {  // public for testing
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
