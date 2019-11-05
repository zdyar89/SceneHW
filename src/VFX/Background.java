package VFX;

import Utilities.SoundClip;
import edu.utc.game.Game;
import edu.utc.game.Scene;
import edu.utc.game.SimpleMenu;
import edu.utc.game.Texture;
import org.lwjgl.opengl.GL11;
import static Game.MainGame.game;

public class Background implements Scene {
	private SoundClip music;
	private Texture background;
	private Wallpaper one, two;
	private SimpleMenu menu;

	public Background(Scene mainMenu) {
		GL11.glClearColor(0f, 0f, 0f, 0f);
		background = new Texture("res/Textures/kirby.png");
		music = new SoundClip("boom");
		one = new Wallpaper(0, Game.ui.getHeight()/2, Game.ui.getWidth(), Game.ui.getHeight() / 2);
		two = new Wallpaper(Game.ui.getWidth(), Game.ui.getHeight()/2, Game.ui.getWidth(), Game.ui.getHeight() / 2);
		menu = new SimpleMenu("EndMenu");
		menu.addItem(new SimpleMenu.SelectableText(20, 60, 20, 20, "Main Menu", 0, 1, 0, 1, 1, 1), mainMenu);
		menu.addItem(new SimpleMenu.SelectableText(20, 100, 20, 20, "Exit Game", 0, 0, 1, 1, 1, 1), null);
		menu.addItem(new SimpleMenu.SelectableText(200, 60, 20, 20, "Clicks: " + game.clickCount, 0, 0, 1, 1, 1, 1), null);
		menu.addItem(new SimpleMenu.SelectableText(200, 100, 20, 20, "Seconds passed: " + ((game.timePassed / 1000) % 60), 0, 0, 1, 1, 1, 1), null);
	}

	public String getName() {
		return "End";
	}

	public Scene drawFrame(int delta) {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		background.draw(one);
		background.draw(two);
		if (!music.isLooping) music.loop();
		adjustBackgrounds(one, two);
		Scene mainMenu = menu.draw(delta);
		if (mainMenu != null) {
			music.stop();
			return mainMenu;
		}
		return this;
	}

	private void adjustBackgrounds(Wallpaper one, Wallpaper two) {
		int width = Game.ui.getWidth();
		int scroll_speed = 4;
		one.getHitbox().x += scroll_speed;
		two.getHitbox().x += scroll_speed;
		if (one.getHitbox().x >= width) one.setWidth(-width);
		if (two.getHitbox().x >= width) two.setWidth(-width);
	}

	public void onKeyEvent(int key, int scancode, int action, int mods)  {
		if (action==org.lwjgl.glfw.GLFW.GLFW_PRESS)
		{
			if (key == org.lwjgl.glfw.GLFW.GLFW_KEY_UP)
			{
				menu.select((menu.selected+menu.items.size()-1)%menu.items.size());
			}
			else if (key == org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN)
			{
				menu.select((menu.selected+1)%menu.items.size());
			}
			else if (key == org.lwjgl.glfw.GLFW.GLFW_KEY_ENTER)
			{
				menu.go();
			}
		}
	}
	public void onMouseEvent(int button, int action, int mods)  { }
}
