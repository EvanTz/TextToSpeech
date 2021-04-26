package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ReplayCommand implements ActionListener {

	private ReplayManager rm;
	private JButton playRecording;
	private JButton stopAudioButton;

	public ReplayCommand() {
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == playRecording) {
			playRecording.setText("Click Stop Audio");
			playRecording.setEnabled(false);
			playRec();
		}
		else if(arg0.getSource() == stopAudioButton) {
			playRecording.setText("Replay Recorded Actions");
			playRecording.setEnabled(true);
			stopSound();
		}
	}
	
	public void playRec() {  // public for testing
		rm.replay();
	}
	
	public void stopSound() {  // public for testing
		rm.stopAudio();
	}

	public void setReplayManager(ReplayManager rm) {
		this.rm = rm;
	}
	
	public void setPlayRecButton(JButton playButton) {
		this.playRecording = playButton;
	}
	
	public void setStopAudioButton(JButton stopAudioButton) {
		this.stopAudioButton = stopAudioButton;
	}

}
