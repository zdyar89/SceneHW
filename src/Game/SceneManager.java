package Game;

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
		game.setScene(mainMenu);
		game.gameLoop();
	}
}
