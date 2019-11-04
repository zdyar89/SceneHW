package Game;

import Entities.Player;
import Entities.Reticle;
import Utilities.Scorekeeper;
import Utilities.SoundClip;
import edu.utc.game.*;
import edu.utc.game.Math.Vector2f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import java.util.List;

public class MainGame extends Game implements Scene {
    public static MainGame game;

    public static void main(String[] args) {
        SceneManager.Run();
    }

    public static final float GRAVITY = 9.8f;
    private boolean gotClick;
    private Reticle marker;
    private Player player;
    private Scorekeeper scorekeeper;
    private SoundClip soundManager;
    private SimpleMenu pauseMenu;
	
    public MainGame() {
    	initUI(1280,720,"SceneHW");
        GL11.glClearColor(.9f, .9f, .9f, 0f);
        gotClick = false;
        player = new Player(new Vector2f(Game.ui.getWidth()/7, Game.ui.getHeight()/1.5f));
        marker = new Reticle();
        scorekeeper = Scorekeeper.getInstance();
        pauseMenu = new SimpleMenu();
        pauseMenu.addItem(new SimpleMenu.SelectableText(20, 20, 20, 20, "Continue", 1, 0, 0, 1, 1, 1), game);
        pauseMenu.addItem(new SimpleMenu.SelectableText(20, 60, 20, 20, "Exit Game", 1, 0, 0, 1, 1, 1), null);
        pauseMenu.select(0);
    }

    @Override
    public void onMouseEvent(int button, int action, int mods) {
        if (button==0 && action== GLFW.GLFW_PRESS)
        {
            Vector2f lastClick = new Vector2f(Game.ui.getMouseLocation().x, Game.ui.getMouseLocation().y);
            gotClick = true;
        }
    }

    @Override
    public void onKeyEvent(int key, int scancode, int action, int mods) {
        if (action == org.lwjgl.glfw.GLFW.GLFW_PRESS && key == GLFW.GLFW_KEY_BACKSPACE) {
            game.setScene(pauseMenu);
            game.gameLoop();
        }
    }
    
    public Scene drawFrame(int delta) {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        Vector2f coords = new Vector2f(Game.ui.getMouseLocation().x, Game.ui.getMouseLocation().y);

        /* Update */
        marker.setLocation(coords);
        player.update(delta);

        /* Draw */
        marker.draw();
        player.draw();

        gotClick = false;
        return this;
    }

    private <T extends GameObject> void update(List<T> gameObjects, int delta) {
        for (GameObject go : gameObjects) {
            go.update(delta);
        }
    }

    private <T extends GameObject> void draw(List<T> gameObjects) {
        for (GameObject go : gameObjects) {
            go.draw();
        }
    }

    private <T extends GameObject> void deactivate(List<T> objects) {
        objects.removeIf(o -> !o.isActive());
    }
}
