package PaooGame.Menu;

import Main.GameWindow;
import utils.ct;

import javax.swing.*;
import java.awt.*;

public class Sound_Settings extends FadedInBackground {
    public Menu_Buttons backButton;
//    TransparentSlider MasterVolumeSlider;
//    TransparentSlider MusicVolumeSlider;
//    TransparentSlider CreaturesVolumeSlider;
//

    public Sound_Settings() {

        setPreferredSize(new Dimension(ct.GameCT.GAME_WIDTH , ct.GameCT.GAME_HEIGHT));
        this.setLayout(null);
        this.setBounds(0, 0, ct.GameCT.GAME_WIDTH,ct.GameCT.GAME_HEIGHT);
    //Label uri
        Label_extend settingsLabel = new Label_extend("Sound Settings");
        Label_extend masterVolumeLabel = new Label_extend("Master Volume");
        Label_extend creaturesVolumeLabel = new Label_extend("Creatures Volume");
        Label_extend musicVolumeLabel = new Label_extend("Music Volume");

        settingsLabel.setBounds(10, 50, 200, 20);
        masterVolumeLabel.setBounds(utils.ct.MenuCT.SLIDER_X_START-200, utils.ct.MenuCT.SLIDER_Y_START, 300, 20);
        creaturesVolumeLabel.setBounds(utils.ct.MenuCT.SLIDER_X_START-200, utils.ct.MenuCT.SLIDER_Y_START+utils.ct.MenuCT.SLIDER_SPACING, 300, 20);
        musicVolumeLabel.setBounds(utils.ct.MenuCT.SLIDER_X_START-200, utils.ct.MenuCT.SLIDER_Y_START+2*utils.ct.MenuCT.SLIDER_SPACING, 300, 20);

        //butoane
        backButton = new Menu_Buttons("Back to main menu",Boolean.FALSE);
        backButton.setBounds(10, 10, 300, 30);

        //imag de fundal
        back_ground_image img= new back_ground_image();

        //slidere
//        MasterVolumeSlider= new TransparentSlider(JSlider.HORIZONTAL, 0, utils.ct.MenuCT.SLIDER_VOLUME_MAX, utils.ct.MenuCT.SLIDER_VOLUME_INITIAL);
//        MasterVolumeSlider.setBounds(utils.ct.MenuCT.SLIDER_X_START, utils.ct.MenuCT.SLIDER_Y_START, utils.ct.MenuCT.SLIDER_WIDTH, utils.ct.MenuCT.SLIDER_HEIGHT);
//
//        CreaturesVolumeSlider= new TransparentSlider(JSlider.HORIZONTAL, 0, utils.ct.MenuCT.SLIDER_VOLUME_MAX, utils.ct.MenuCT.SLIDER_VOLUME_INITIAL );
//        CreaturesVolumeSlider.setBounds(utils.ct.MenuCT.SLIDER_X_START,utils.ct.MenuCT.SLIDER_Y_START+utils.ct.MenuCT.SLIDER_SPACING, utils.ct.MenuCT.SLIDER_WIDTH, utils.ct.MenuCT.SLIDER_HEIGHT);
//
//        MusicVolumeSlider= new TransparentSlider(JSlider.HORIZONTAL, 0, utils.ct.MenuCT.SLIDER_VOLUME_MAX, utils.ct.MenuCT.SLIDER_VOLUME_INITIAL );
//        MusicVolumeSlider.setBounds(utils.ct.MenuCT.SLIDER_X_START,utils.ct.MenuCT.SLIDER_Y_START+2*utils.ct.MenuCT.SLIDER_SPACING, utils.ct.MenuCT.SLIDER_WIDTH, utils.ct.MenuCT.SLIDER_HEIGHT);
//
//        this.add(MusicVolumeSlider);
//        this.add(CreaturesVolumeSlider);
//        this.add(MasterVolumeSlider);

        this.add(backButton);

        this.add(settingsLabel);
        this.add(masterVolumeLabel);
        this.add(musicVolumeLabel);
        this.add(creaturesVolumeLabel);
    }
//    public void adaugaActiuni(GameWindow w, Sound_Settings sound_settings) {
//        Control_Button.addActionListener(e -> {
//            w.AddMenuPanel(sound_settings);
//            w.CarePanelPeFrame(false);
//        });
//        sound_settings.backButton.addActionListener(e->{w.AddMenuPanel(this);
//            w.CarePanelPeFrame(false);});
//
//    }
}