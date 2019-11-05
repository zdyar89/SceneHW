package Utilities;

import edu.utc.game.Sound;

public class SoundClip {
	private Sound sound;
	public boolean isLooping = false;
	public SoundClip(String path) {
		this.sound = new Sound("res/Sound/" + path + ".wav");
	}

	public void play() {
		sound.play();
	}
	public void loop() {
		sound.play();
		sound.setLoop(true);
		isLooping = true;
	}
	public void stop() {
		sound.stop();
		isLooping = false;
	}
}
