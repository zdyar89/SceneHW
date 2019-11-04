package Utilities;

import edu.utc.game.Sound;

public class SoundClip {
	private Sound sound;
	public SoundClip(String path) {
		this.sound = new Sound("res/Sound/" + path);
	}

	public void play() {
		sound.play();
	}
}
