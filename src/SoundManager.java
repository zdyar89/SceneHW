import edu.utc.game.Sound;

public class SoundManager {
	private Sound sound;
	public SoundManager(String path) {
		this.sound = new Sound("res/Sound/" + path);
	}

	public void play() {
		sound.play();
	}
}
