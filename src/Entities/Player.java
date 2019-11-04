package Entities;

import edu.utc.game.Game;
import edu.utc.game.GameObject;
import edu.utc.game.Math.Vector2f;
import edu.utc.game.Texture;
import org.lwjgl.glfw.GLFW;
import Game.MainGame;

public class Player extends GameObject {
	private Vector2f pos;
	private Vector2f direction;
	private Texture texture;
	private float speed;
	private float weight;
	private float jumpability;

	public Player(Vector2f origin) {
		this.hitbox.setBounds((int) origin.x, (int) origin.y, 30, 30);
		this.pos = origin;
		this.texture = new Texture("res/Textures/kirby.png");
		this.direction = new Vector2f(0, 0);
		this.speed = .04f;
		this.weight = 1f;
		this.jumpability = 30f;
	}

	@Override
	public void setColor(float r, float g, float b) {
		super.setColor(r, g, b);
	}

	@Override
	public void update(int delta) {
		direction = new Vector2f(0, 0);

		if (Game.ui.keyPressed(GLFW.GLFW_KEY_A)) {
			direction.x -= 10;
		}
		if (Game.ui.keyPressed(GLFW.GLFW_KEY_D)) {
			direction.x += 10;
		}
		if (Game.ui.keyPressed(GLFW.GLFW_KEY_SPACE)) {
			direction.y -= jumpability;
		}

		fall();
		fitToBounds(delta);
		move(delta);
		adjustHitBox();
	}

	@Override
	public void draw() {
		texture.draw(this);
	}

	private void fall() {
		direction.y += MainGame.GRAVITY * weight;
	}

	private void move(int delta) {
		pos.x += direction.x * delta * speed;
		pos.y += direction.y * delta * speed;
	}

	private void adjustHitBox() {
		hitbox.x = (int) pos.x;
		hitbox.y = (int) pos.y;
	}

	private void fitToBounds(int delta) {
		int newX = (int) (direction.x * delta * speed) + hitbox.x;
		int newY = (int) (direction.y * delta * speed) + hitbox.y;
		if (newX < 0 || newX + hitbox.width > Game.ui.getWidth()) {
			direction.x = 0;
		}
		if (newY < 0 || newY + hitbox.height > Game.ui.getHeight()) {
			direction.y = 0;
		}
	}
}
