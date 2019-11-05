package Utilities;

import java.util.ArrayList;
import java.util.List;

public class SoundManager {
	public static List<SoundClip> sounds = new ArrayList<>();

	public static void add(SoundClip sound) {
		sounds.add(sound);
	}

	public static void stop() {
		for (SoundClip sound : sounds) {
			sound.stop();
		}
		sounds.clear();
	}
}
