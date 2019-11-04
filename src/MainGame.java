import Entities.Player;
import Entities.Reticle;
import edu.utc.game.*;
import edu.utc.game.Math.Vector2f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.opengl.GL11;

import java.util.List;

public class MainGame extends Game implements Scene {
    private static MainGame game;

    public static void main(String[] args) {
        game = new MainGame();
        game.registerGlobalCallbacks();
        SimpleMenu mainMenu = new SimpleMenu();
        mainMenu.addItem(new SimpleMenu.SelectableText(20, 20, 20, 20, "Launch Game", 1, 0, 0, 1, 1, 1), game);
        mainMenu.addItem(new SimpleMenu.SelectableText(20, 60, 20, 20, "Exit", 1, 0, 0, 1, 1, 1), null);
        mainMenu.select(0);
        game.setScene(mainMenu);
        game.gameLoop();
    }

    private boolean gotClick = false;
    private Reticle marker;
    private Player player;
    private Scorekeeper scorekeeper;
    private SoundClip soundManager;
    private SimpleMenu pauseMenu = new SimpleMenu();
	
    public MainGame() {
    	initUI(1280,720,"SceneHW");
        GL11.glClearColor(0f, 0f, 0f, 0f);
        player = new Player(new Vector2f(Game.ui.getWidth()/2, Game.ui.getHeight()/2));
        marker = new Reticle();
        scorekeeper = Scorekeeper.getInstance();
        GLFW.glfwSetMouseButtonCallback(Game.ui.getWindow(), clickback);
        GLFW.glfwSetKeyCallback(Game.ui.getWindow(), pauseback);
        pauseMenu.addItem(new SimpleMenu.SelectableText(20, 20, 20, 20, "Continue", 1, 0, 0, 1, 1, 1), game);
        pauseMenu.addItem(new SimpleMenu.SelectableText(20, 60, 20, 20, "Exit Game", 1, 0, 0, 1, 1, 1), null);
        pauseMenu.select(0);
    }

    private GLFWKeyCallback pauseback = new GLFWKeyCallback() {
        public void invoke(long window, int key, int scancode, int action, int mods) {
            if (scancode == GLFW.GLFW_KEY_BACKSPACE) {
                game.setScene(pauseMenu);
                System.out.println(key + scancode);
            }
        }
    };

    private GLFWMouseButtonCallback clickback = new GLFWMouseButtonCallback() {
        public void invoke(long window, int button, int action, int mods)
        {
            if (button==0 && action== GLFW.GLFW_PRESS)
            {
                Vector2f lastClick = new Vector2f(Game.ui.getMouseLocation().x, Game.ui.getMouseLocation().y);
                gotClick = true;
            }
        }
    };
    
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
