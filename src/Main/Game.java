package Main;


import PaooGame.Menu.MenuPrincipal;
import entities.Player;
import gamestates.*;
import inputs.KeyboardInputs;
import inputs.MouseInputs;
import levels.CurrentLevel;
import levels.LevelManager;
import levels.LevelNumber;

import java.awt.*;

public class Game implements Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;

    private KeyboardInputs keyboardInputs;
    private MouseInputs mouseInputs;

    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    private static Player player;
    private LevelManager levelManager;

    //  public Gamestate gamestate = Gamestate.PLAYING;

    //private static menu Menu;
    private static MenuJoc menuJoc;
    private static MenuPrincipal menuPrincipal;
    private static playing Playing;


    public Game(GameWindow gameWindow) {
        initClasses1();
        gamePanel = new GamePanel(this);
        initClasses2();

        this.gameWindow = gameWindow;
        menuJoc = new MenuJoc(gameWindow);
        Playing = new playing(this);
        gameWindow.AddGamePanel(gamePanel);
        gameWindow.CarePanelPeFrame(true);
        gamePanel.requestFocusInWindow();
        startGameLoop();
    }



    private void initClasses1() {

        levelManager = new LevelManager(this);

        player = new Player(Player.l1x, Player.l1y);  // Creează player-ul
        player.loadLvlData(levelManager.getCurrentLevel().getLvlData());
        //Menu = new menu();


        Playing = new playing(this);

    }

    private void initClasses2() {
        keyboardInputs = new KeyboardInputs(this);
        mouseInputs = new MouseInputs(this);

        gamePanel.addKeyListener(keyboardInputs);

        gamePanel.addMouseListener(mouseInputs);
        gamePanel.addMouseMotionListener(mouseInputs);

        gamePanel.setFocusable(true);
        gamePanel.requestFocus();
    }

    public Player getPlayer() {
        return player;  // Returnează referința corectă către Player
    }

    private void startGameLoop() {
        menuJoc.adaugajoc(this);
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        switch (CurentGameState.getState()) {
            case MENU:
                menuJoc.update();
                break;
            case PLAYING:
                Playing.update();
                break;
            default:
                break;
        }
    }
    public boolean nPressed = false;
    public boolean isNPressed() { return nPressed; }
    public void render(Graphics g) {
        switch (CurentGameState.getState()) {
            case MENU:
                menuJoc.draw();
                break;
            case PLAYING:

                Playing.render(g);

                break;
            default:
                break;
        }
    }
    public void resetLevel() {
        levelManager = new LevelManager(this);

        int startX = 0;
        int startY = 0;

        switch (CurrentLevel.getLevelNumber()) {
            case LevelNumber.LEVEL_1:
                startX = Player.l1x;
                startY = Player.l1y;
                break;
            case LevelNumber.LEVEL_2:
                startX = Player.l2x;
                startY = Player.l2y;
                break;
            case LevelNumber.LEVEL_3:
                startX = Player.l3x;
                startY = Player.l3y;
                break;
        }

        player.setX(startX);
        player.setY(startY);
    }

    public void SwitchGameState() {
        if (CurentGameState.getState() == Gamestate.PLAYING)
            CurentGameState.setState(Gamestate.MENU);
        else {
            CurentGameState.setState(Gamestate.PLAYING);
        }
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;
        long previousTime = System.nanoTime();
        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();
        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                frames = 0;
                updates = 0;
            }
        }
    }

    public void windowFocusLost() {
        player.stopMoving();
    }

    public MenuJoc getMenu() {
        return menuJoc;
    }

    public playing getPlaying() {
        return Playing;
    }


    public static Rectangle getplayerhitbox() {
        return player.getHitbox();
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
    public Enum<Gamestate> getGameState() {
        return CurentGameState.getState();
    }

    public LevelManager getLevelManager() {
        return levelManager;
    }
}