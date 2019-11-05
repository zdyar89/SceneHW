package Game;

import VFX.Background;
import edu.utc.game.Scene;
import edu.utc.game.SimpleMenu;

import static Game.MainGame.game;

class SceneManager {

	static void run() {
		game = new MainGame();
		game.registerGlobalCallbacks();
		SimpleMenu mainMenu = new SimpleMenu("mMenu");
		mainMenu.addItem(new SimpleMenu.SelectableText(20, 20, 20, 20, "Launch Game", 1, 0, 0, 1, 1, 1), game);
		mainMenu.addItem(new SimpleMenu.SelectableText(20, 60, 20, 20, "Exit", 1, 0, 0, 1, 1, 1), null);
		mainMenu.select(0);
		changeScene(mainMenu);
	}

	static void changeScene(Scene scene) {
		game.setScene(scene);
		game.gameLoop();
	}

	static void pause() {
		SimpleMenu mainMenu = new SimpleMenu("mMenu");
		mainMenu.addItem(new SimpleMenu.SelectableText(20, 20, 20, 20, "Launch Game", 1, 0, 0, 1, 1, 1), game);
		mainMenu.addItem(new SimpleMenu.SelectableText(20, 60, 20, 20, "Exit", 1, 0, 0, 1, 1, 1), null);
		mainMenu.select(0);
		SimpleMenu pauseMenu = new SimpleMenu("pMenu");
		pauseMenu.addItem(new SimpleMenu.SelectableText(20, 20, 20, 20, "Continue", 1, 0, 0, 1, 1, 1), game);
		pauseMenu.addItem(new SimpleMenu.SelectableText(20, 60, 20, 20, "Main Menu", 0, 1, 0, 1, 1, 1), mainMenu);
		pauseMenu.addItem(new SimpleMenu.SelectableText(20, 100, 20, 20, "Exit Game", 0, 0, 1, 1, 1, 1), null);
		pauseMenu.addItem(new SimpleMenu.SelectableText(20, 140, 20, 20, String.valueOf(game.clickCount), 1, 1, 0, 1, 1, 1), game);
		pauseMenu.select(0);
		changeScene(pauseMenu);
	}

	static void end() {
		Background endBackground = new Background();
		changeScene(endBackground);
	}
}
