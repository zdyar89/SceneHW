package edu.utc.game;

public interface Scene {
	String getName();
	Scene drawFrame(int delta);
	default void onKeyEvent(int key, int scancode, int action, int mods)  { };
	default void onMouseEvent(int button, int action, int mods)  { }

}
