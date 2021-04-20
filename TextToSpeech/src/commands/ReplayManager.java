package commands;

import java.util.ArrayList;
import java.util.List;

import model.Document;

public class ReplayManager {
	
	private boolean recordingStatus;
	private List<List<String>> recordList;
	private Document doc;
	private boolean playing;
	private double volume;
	private int rate;
	private int pitch;

	public ReplayManager() {
		recordingStatus = false;
		playing = false;
		recordList = new ArrayList<>();
	}
	
	public void replay() {
		playing = true;
		List<String> out = new ArrayList<>();
		for( List<String> content: recordList) {
			for(String word: content) {
				out.add(word);
			}
		}
		doc.setVolRatePitchDoc(volume, rate, pitch);
		doc.playPartContents(out);
	}
	
	public void startRecording() {
		recordingStatus = true;
	}
	
	public void stopAudio() {
		if(playing) {
			doc.stopPlayingContents();
			playing = false;
		}
	}
	public void endRecording() {
		if(recordingStatus) {
			//doc.stopPlayingContents();
			recordingStatus = false;
			recordList.clear();
		}
	}
	
	public boolean isActiveRecording() {
		return recordingStatus;
	}
	
	public void addContentsToRecList(List<String> con) {
		recordList.add(con);
	}
	
	public void setDoc(Document doc) {
		this.doc = doc;
	}
	
	public void setVolRatePitch(double speechVolume, int speechRate, int speechPitch) {
		this.volume = speechVolume;
		this.rate = speechRate;
		this.pitch = speechPitch;
	}

}
