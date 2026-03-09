package PaooGame.Menu;

import Main.Game;
import Main.GameWindow;
import gamestates.CurentGameState;
import gamestates.Gamestate;
import utils.ct;

import java.awt.*;

public class MenuPrincipal extends FadedInBackground {

    Menu_Buttons settingsButton;
    Menu_Buttons exitButton;
    Menu_Buttons startButton;
    Menu_Buttons loadButton;


    // Menu_Buttons mainMenu; // ignorăm cum ai cerut

    public MenuPrincipal(GameWindow w) {
        setPreferredSize(new Dimension(ct.GameCT.GAME_WIDTH, ct.GameCT.GAME_HEIGHT));
        setLayout(null);
        setBounds(0, 0, ct.GameCT.GAME_WIDTH, ct.GameCT.GAME_HEIGHT);


        // Lista cu titlurile butoanelor și referințele
        String[] labels = {"Start New Game", "Load Career", "Settings", "Exit Game"};
        Menu_Buttons[] buttons = new Menu_Buttons[labels.length];

        for (int i = 0; i < labels.length; i++) {

            buttons[i] = new Menu_Buttons(labels[i], Boolean.TRUE);
            buttons[i].setBounds(ct.MenuCT.BUTTON_X, utils.ct.MenuCT.BUTTON_Y_START + i * utils.ct.MenuCT.BUTTON_SPACING, utils.ct.MenuCT.BUTTON_WIDTH, utils.ct.MenuCT.BUTTON_HEIGHT);
            add(buttons[i]);
        }

        // Referințele individuale dacă vrei să le folosești ulterior
        startButton = buttons[0];
        loadButton = buttons[1];
        settingsButton = buttons[2];
        exitButton = buttons[3];

        Settings settingsPanel = new Settings(w);
        //this.settings=settingsPanel;// comentat pentru că nu e folosit
        adaugaActiuni(w, settingsPanel);

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
        startButton.addActionListener(e -> {

            CurentGameState.setState(Gamestate.PLAYING);
            Game g=new Game(w);
            w.CarePanelPeFrame(true);


        });
    }
}
