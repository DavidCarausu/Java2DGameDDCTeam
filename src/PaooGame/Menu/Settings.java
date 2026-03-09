package PaooGame.Menu;

import Main.GameWindow;
import utils.ct;

import java.awt.*;

public class Settings extends FadedInBackground {
    public Menu_Buttons backButton;
    public Menu_Buttons Sound_Button;
    public Menu_Buttons Control_Button;

    public Settings(GameWindow w) {

        setPreferredSize(new Dimension(ct.GameCT.GAME_WIDTH, ct.GameCT.GAME_HEIGHT));
        this.setLayout(null);
        this.setBounds(0, 0, ct.GameCT.GAME_WIDTH, ct.GameCT.GAME_HEIGHT);

        Label_extend settingsLabel = new Label_extend("Settings");
        settingsLabel.setBounds(10, 50, 100, 20);

        backButton = new Menu_Buttons("Back to main menu", Boolean.FALSE);
        backButton.setBounds(10, 10, 250, 50);

        Sound_Button = new Menu_Buttons("Sound Settings", Boolean.TRUE);
        Sound_Button.setBounds(utils.ct.MenuCT.BUTTON_X, utils.ct.MenuCT.BUTTON_Y_START, utils.ct.MenuCT.BUTTON_WIDTH, utils.ct.MenuCT.BUTTON_HEIGHT);

        Control_Button = new Menu_Buttons("Control Settings", Boolean.TRUE);
        Control_Button.setBounds(utils.ct.MenuCT.BUTTON_X, utils.ct.MenuCT.BUTTON_Y_START + utils.ct.MenuCT.BUTTON_SPACING, utils.ct.MenuCT.BUTTON_WIDTH, utils.ct.MenuCT.BUTTON_HEIGHT);

        Sound_Settings sound_settings_panel = new Sound_Settings();
        Contorl_Settings control_settings_pannel = new Contorl_Settings();

        adaugaActiuni(w, control_settings_pannel, sound_settings_panel);
        this.add(Control_Button);
        this.add(Sound_Button);
        this.add(settingsLabel);
        this.add(backButton);

    }

    public void adaugaActiuni(GameWindow w, Contorl_Settings control_settings_panel, Sound_Settings sound_settings_panel) {
        Control_Button.addActionListener(e -> {
            w.AddMenuPanel(control_settings_panel);
            w.CarePanelPeFrame(false);
        });
        control_settings_panel.Back.addActionListener(e -> {
            w.AddMenuPanel(this);
            w.CarePanelPeFrame(false);
        });
        Sound_Button.addActionListener(e -> {
            w.AddMenuPanel(sound_settings_panel);
            w.CarePanelPeFrame(false);
        });
        sound_settings_panel.backButton.addActionListener(e -> {
            w.AddMenuPanel(this);
            w.CarePanelPeFrame(false);
        });

    }
}
