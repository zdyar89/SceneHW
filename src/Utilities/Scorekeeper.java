package Utilities;

public class Scorekeeper {
	private static Scorekeeper scorekeeper = null;
	public float score;

	private Scorekeeper() {
		score = 0;
	}

	public static Scorekeeper getInstance() {
		if (scorekeeper == null) {
			scorekeeper = new Scorekeeper();
		}
		return scorekeeper;
	}
}
