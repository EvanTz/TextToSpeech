package model;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TTSFacade {
	private VoiceManager vm;
	private Voice voice;
	private float pitch;
	private float rate;
	private float volume;
	private Thread speakerThread;
	private boolean finished;
	public TTSFacade() {
		//  Auto-generated constructor stub
		
		 
	}

	public void play(String message) {
		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
		vm = VoiceManager.getInstance();
		voice = vm.getVoice("kevin16");
		voice.setVolume(volume);
		voice.setPitch(pitch);
		voice.setRate(rate);
		finished = false;
		voice.allocate();
		voice.setOutputQueue(null);
		Runnable speaker = new Runnable(){
			public void run() {
				voice.speak(message);  
				voice.deallocate();
				stopPlay();
			}
        };   
       speakerThread =  new Thread(speaker);
       speakerThread.start();

	}
	
	public void setVolume(float volume) {
		this.volume= volume;
		
	}
	
	public void setPitch(float pitch) {
		this.pitch= pitch;
		
	}
	
	public void setRate(float rate) {
		this.rate = rate;
		
		
	}
	
	public void stopPlay() {
		finished = true;
		speakerThread.interrupt();
		voice.deallocate();
		
	}
	
}