package PaooGame.Menu;

import utils.ct;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class Contorl_Settings extends FadedInBackground {
    private final HashMap<String, KeyStroke> keyBindings = new HashMap<>();

    private Label_extend rightKeyLabel;
    private Menu_Buttons changeRightKeyButton;

    private Label_extend leftKeyLabel;
    private Menu_Buttons changeLeftKeyButton;

    private Label_extend jumpLabel;
    private Menu_Buttons changeJumpButton;

    private Label_extend changeWeaponsLabel;
    private Menu_Buttons changeWeaponsButton;

    private Label_extend reloadweaponLabel;
    private Menu_Buttons changeReloadweaponButton;

    public Menu_Buttons Back;





    public Contorl_Settings() {
        setPreferredSize(new Dimension(ct.GameCT.GAME_WIDTH , ct.GameCT.GAME_HEIGHT));
        setLayout(null);
        setBounds(0, 0, ct.GameCT.GAME_WIDTH, ct.GameCT.GAME_HEIGHT); // ajustează dimensiunile după nevoie

        //acelasi igitn tot meniul de setari pentru butonul de back si labelul lui
        Back = new Menu_Buttons("Back to Settings",Boolean.FALSE);
        Back.setBounds(10, 10, 250, 30);
        this.add(Back);

        Label_extend title = new Label_extend("Control PaooGame.Menu.Settings");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(10, 50, 300, 30);
        add(title);

        back_ground_image img=new back_ground_image();

        // Tasta implicită pentru mers la dreapta: D
        keyBindings.put("moveRight", KeyStroke.getKeyStroke(KeyEvent.VK_D, 0));
        keyBindings.put("moveLeft" , KeyStroke.getKeyStroke(KeyEvent.VK_A, 0));
        keyBindings.put("Jump", KeyStroke.getKeyStroke(KeyEvent.VK_W, 0));
        keyBindings.put("Change_Weapon", KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0));
        keyBindings.put("Reload_Weapon", KeyStroke.getKeyStroke(KeyEvent.VK_R, 0));

    // Etichetă + Buton pentru right_key
        //start
        //eticheta
        Label_extend rightKeyLabel = new Label_extend("Move Right: " + formatKeyStroke(keyBindings.get("moveRight")));
        rightKeyLabel.setBounds(utils.ct.MenuCT.button_change_label_x_offset, utils.ct.MenuCT.button_change_label_y_startoffset, 300, 30);
        add(rightKeyLabel);

        // Buton pentru a schimba tasta
        changeRightKeyButton = new Menu_Buttons("Change Right Key",Boolean.FALSE);
        changeRightKeyButton.setBounds(utils.ct.MenuCT.button_change_x_offset, utils.ct.MenuCT.button_change_y_offset, 260, 30);
        add(changeRightKeyButton);

        // Când apăsăm pe buton, ascultăm o tastă nouă
        changeRightKeyButton.addActionListener(e -> listenForKey("moveRight", rightKeyLabel, "Move Right"));
    //end RIGHT

    // Eticheta + Buton pentru left_key
        //start
        //eticheta
        Label_extend leftKeyLabel = new Label_extend( "Move Left: " + formatKeyStroke(keyBindings.get("moveLeft")) );
        leftKeyLabel.setBounds(utils.ct.MenuCT.button_change_label_x_offset, utils.ct.MenuCT.button_change_label_y_startoffset + utils.ct.MenuCT.button_change_spacing, 300, 30);
        add(leftKeyLabel);

        //Buton pentru a schimba tasta LEFT
        changeLeftKeyButton = new Menu_Buttons("Change Left Key",Boolean.FALSE);
        changeLeftKeyButton.setBounds(utils.ct.MenuCT.button_change_x_offset, utils.ct.MenuCT.button_change_y_offset+utils.ct.MenuCT.button_change_spacing, 260, 30);
        add(changeLeftKeyButton);
        changeLeftKeyButton.addActionListener(e -> listenForKey("moveLeft", leftKeyLabel, "Move Left"));
    //end LEFT

    // Etichetă + Buton pentru JUMP
        //start
        //eticheta JUMP
        Label_extend jumpKeyLabel = new Label_extend("Jump: " + formatKeyStroke(keyBindings.get("Jump")));
        jumpKeyLabel.setBounds(utils.ct.MenuCT.button_change_label_x_offset, utils.ct.MenuCT.button_change_label_y_startoffset + 2 * utils.ct.MenuCT.button_change_spacing, 300, 30);
        add(jumpKeyLabel);

        //buton pentru a schimba tasta JUMP
        JButton changeJumpKeyButton = new Menu_Buttons("Change Jump Key",Boolean.FALSE);
        changeJumpKeyButton.setBounds(utils.ct.MenuCT.button_change_x_offset, utils.ct.MenuCT.button_change_y_offset + 2 * utils.ct.MenuCT.button_change_spacing, 260, 30);
        add(changeJumpKeyButton);
        changeJumpKeyButton.addActionListener(e -> listenForKey("Jump", jumpKeyLabel,"Jump"));
    //end JUMP

    // Etichetă + Buton pentru Change_Weapon
        Label_extend changeWeaponKeyLabel = new Label_extend("Change Weapon: " + formatKeyStroke(keyBindings.get("Change_Weapon")));
        changeWeaponKeyLabel.setBounds(utils.ct.MenuCT.button_change_label_x_offset, utils.ct.MenuCT.button_change_label_y_startoffset + 3 * utils.ct.MenuCT.button_change_spacing, 300, 30);
        add(changeWeaponKeyLabel);

        JButton changeWeaponKeyButton = new Menu_Buttons("Change Weapon Key",Boolean.FALSE);
        changeWeaponKeyButton.setBounds(utils.ct.MenuCT.button_change_x_offset, utils.ct.MenuCT.button_change_y_offset + 3 * utils.ct.MenuCT.button_change_spacing, 260, 30);
        add(changeWeaponKeyButton);
        changeWeaponKeyButton.addActionListener(e -> listenForKey("Change_Weapon", changeWeaponKeyLabel,"Change Weapon"));
    //end CHANGE_WEAPON

    // Etichetă + Buton pentru Reload_Weapon
        Label_extend reloadWeaponKeyLabel = new Label_extend("Reload Weapon: " + formatKeyStroke(keyBindings.get("Reload_Weapon")));
        reloadWeaponKeyLabel.setBounds(utils.ct.MenuCT.button_change_label_x_offset, utils.ct.MenuCT.button_change_label_y_startoffset + 4 * utils.ct.MenuCT.button_change_spacing, 300, 30);
        add(reloadWeaponKeyLabel);

        JButton reloadWeaponKeyButton = new Menu_Buttons("Change Reload Key",Boolean.FALSE);
        reloadWeaponKeyButton.setBounds(utils.ct.MenuCT.button_change_x_offset, utils.ct.MenuCT.button_change_y_offset + 4 * utils.ct.MenuCT.button_change_spacing, 260, 30);
        add(reloadWeaponKeyButton);
        reloadWeaponKeyButton.addActionListener(e -> listenForKey("Reload_Weapon", reloadWeaponKeyLabel, "Reload Weapon"));
    //end RELOAD_WEAPON

    }

    // Ascultă următoarea tastă apăsată și o setează pentru acțiune
    private void listenForKey(String action, Label_extend labelToUpdate, String NameToPut) {
        labelToUpdate.setText("Press a key...");
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_PRESSED) {
                    KeyStroke ks = KeyStroke.getKeyStrokeForEvent(e);
                    keyBindings.put(action, ks);
                    labelToUpdate.setText(NameToPut + ": " + formatKeyStroke(ks));
                    KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(this);
                }
                return true;
            }
        });
    }

    // Format frumos pentru afișarea tastei
    private String formatKeyStroke(KeyStroke ks) {
        String keyText = KeyEvent.getKeyText(ks.getKeyCode());
        String modifiers = KeyEvent.getModifiersExText(ks.getModifiers());
        return modifiers.isEmpty() ? keyText : modifiers + " + " + keyText;
    }

    // Metodă publică pentru a obține tasta asociată unei acțiuni
    public KeyStroke getKeyForAction(String action) {
        return keyBindings.get(action);
    }

}


