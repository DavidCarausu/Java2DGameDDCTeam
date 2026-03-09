package gamestates;

import Main.Game;
import Main.GameWindow;
import PaooGame.Menu.FadedInBackground;
import PaooGame.Menu.Menu_Buttons;
import PaooGame.Menu.Settings;
import utils.ct;

import java.awt.*;

public class MenuJoc extends FadedInBackground {

    Menu_Buttons settingsButton;
    Menu_Buttons exitButton;
    Menu_Buttons ResumeButton;
    Menu_Buttons SaveButton;
    GameWindow w;
    Game game;

    // Menu_Buttons mainMenu; // ignorăm cum ai cerut

    public MenuJoc(GameWindow w) {

        setPreferredSize(new Dimension(ct.GameCT.GAME_WIDTH, ct.GameCT.GAME_HEIGHT));
        setLayout(null);
        setBounds(0, 0, ct.GameCT.GAME_WIDTH, ct.GameCT.GAME_HEIGHT);
        if (w == null)
            System.out.println("sfsdafasfdsadf");
        this.w = w;
        // Lista cu titlurile butoanelor și referințele
        String[] labels = {"Resume", "SaveGame", "Settings", "Exit Game"};
        Menu_Buttons[] buttons = new Menu_Buttons[labels.length];

        for (int i = 0; i < labels.length; i++) {

            buttons[i] = new Menu_Buttons(labels[i], Boolean.TRUE);
            buttons[i].setBounds(ct.MenuCT.BUTTON_X, utils.ct.MenuCT.BUTTON_Y_START + i * utils.ct.MenuCT.BUTTON_SPACING, utils.ct.MenuCT.BUTTON_WIDTH, utils.ct.MenuCT.BUTTON_HEIGHT);
            add(buttons[i]);
        }

        // Referințele individuale dacă vrei să le folosești ulterior
        ResumeButton = buttons[0];
        SaveButton = buttons[1];
        settingsButton = buttons[2];
        exitButton = buttons[3];

        Settings settingsPanel = new Settings(w);
        //this.settings=settingsPanel;// comentat pentru că nu e folosit
        adaugaActiuni(w, settingsPanel);

    }

    public void adaugajoc(Game game) {
        if (game != null)
            this.game = game;
        else
            System.err.println("Warning: Game reference is null in MenuJoc");
    }

    public void draw() {
        if (w != null) {
            w.AddMenuPanel(this);
            w.CarePanelPeFrame(false);
        } else {
            System.err.println("Warning: GameWindow reference is null in MenuJoc");
        }
    }

    public void update() {

    }

    public void adaugaActiuni(GameWindow w, Settings settingsPanel) {
        settingsButton.addActionListener(e -> {
            w.AddMenuPanel(settingsPanel);
            w.CarePanelPeFrame(false);
        });
        settingsPanel.backButton.addActionListener(e -> {
            w.AddMenuPanel(this);
            w.CarePanelPeFrame(false);
        });
        exitButton.addActionListener(e -> System.exit(0));
        ResumeButton.addActionListener(e -> {

            CurentGameState.setState(Gamestate.PLAYING);
            w.CarePanelPeFrame(true);
            game.getGamePanel().requestFocusInWindow();
        });
    }

}