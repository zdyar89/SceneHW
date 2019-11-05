package VFX;

import Utilities.SoundClip;
import edu.utc.game.Game;
import edu.utc.game.Scene;
import edu.utc.game.SimpleMenu;
import edu.utc.game.Texture;
import org.lwjgl.opengl.GL11;

public class Background implements Scene {
	private SoundClip music;
	private Texture background;
	private Wallpaper one, two;

	public Background() {
		GL11.glClearColor(0f, 0f, 0f, 0f);
		background = new Texture("res/Textures/kirby.png");
		music = new SoundClip("kommSusserTod");
		one = new Wallpaper(0, Game.ui.getHeight()/2, Game.ui.getWidth(), Game.ui.getHeight() / 2);
		two = new Wallpaper(Game.ui.getWidth(), Game.ui.getHeight()/2, Game.ui.getWidth(), Game.ui.getHeight() / 2);
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

	public void onKeyEvent(int key, int scancode, int action, int mods)  { }
	public void onMouseEvent(int button, int action, int mods)  { }
}
