package VFX;

import edu.utc.game.GameObject;

public class Wallpaper extends GameObject  {
	public Wallpaper(int x, int y, int width, int height) {
		this.hitbox.setBounds(x, y, width, height);
	}

	public void setWidth(int x) {
		this.hitbox.x = x;
	}
}
