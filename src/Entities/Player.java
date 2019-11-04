package Entities;

import edu.utc.game.Game;
import edu.utc.game.GameObject;
import edu.utc.game.Math.Vector2f;
import org.lwjgl.glfw.GLFW;

public class Player extends GameObject {
	private Vector2f location;

	public Player(Vector2f origin) {
		this.hitbox.setBounds((int) origin.x, (int) origin.y, 25, 25);
		location = origin;
		this.setColor(1, 1, 1);
	}

	public Vector2f getLocation() {
		return this.location;
	}

	@Override
	public void setColor(float r, float g, float b) {
		super.setColor(r, g, b);
	}

	@Override
	public void update(int delta) {
		if (Game.ui.keyPressed(GLFW.GLFW_KEY_A)) {
			this.location.x -= 10;
		}
		if (Game.ui.keyPressed(GLFW.GLFW_KEY_D)) {
			this.location.x += 10;
		}
		if (Game.ui.keyPressed(GLFW.GLFW_KEY_W)) {
			this.location.y -= 10;
		}
		if (Game.ui.keyPressed(GLFW.GLFW_KEY_S)) {
			this.location.y += 10;
		}
		this.hitbox.x = (int) this.location.x;
		this.hitbox.y = (int) this.location.y;
	}
}
