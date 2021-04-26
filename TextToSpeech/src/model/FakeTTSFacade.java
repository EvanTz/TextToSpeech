package model;

public class FakeTTSFacade extends TTSFacade {
	private String playedContents;
	private float volume;
	private float rate;
	private float pitch;
	private boolean isPlaying = false;
	
	public FakeTTSFacade() {
		//  Auto-generated constructor stub
	}
	
	public void play(String message) {
		playedContents = message;
		isPlaying = true;
		super.play(message);
	}
	
	public String getPlayedContents() {
		return playedContents;
	}
	
	public void setVolume(float volume) {
		this.volume = volume;
		super.setVolume(volume);
	}
	
	public void setPitch(float pitch) {
		this.pitch = pitch;
		super.setPitch(pitch);
	}
	
	public void setRate(float rate) {
		this.rate = rate;
		super.setRate(rate);
	}
	
	public float getVolume() {
		return volume;
	}
	
	public float getPitch() {
		return pitch;
	}
	
	public float getRate() {
		return rate;
	}

	public void stopPlay() {
		super.stopPlay();
		isPlaying = false;
	}
	
	public boolean getPlayingStatus() {
		return isPlaying;
	}
}
