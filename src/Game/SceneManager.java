package Game;

import edu.utc.game.Scene;
import edu.utc.game.SimpleMenu;
import static Game.MainGame.game;

class SceneManager {
	static void Run() {
		game = new MainGame();
		game.registerGlobalCallbacks();
		SimpleMenu mainMenu = new SimpleMenu();
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
		SimpleMenu pauseMenu = new SimpleMenu();
		pauseMenu.addItem(new SimpleMenu.SelectableText(20, 20, 20, 20, "Continue", 1, 0, 0, 1, 1, 1), game);
		pauseMenu.addItem(new SimpleMenu.SelectableText(20, 60, 20, 20, "Exit Game", 1, 0, 0, 1, 1, 1), null);
		pauseMenu.select(0);
		changeScene(pauseMenu);
	}
}
