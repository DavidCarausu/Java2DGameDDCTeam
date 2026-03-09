package PaooGame.Menu;

import Main.GameWindow;

import javax.swing.*;
import java.awt.*;

public class MenuActions {
    public MenuActions() {}
    public static void seteazaActiune(FadedInBackground a, FadedInBackground b, JButton aa, JButton bb, GameWindow w) {
        aa.addActionListener(e -> {

            w.AddMenuPanel(b);
            w.CarePanelPeFrame(false);
        });
        bb.addActionListener(e -> {
            w.AddMenuPanel(a);
            w.CarePanelPeFrame(false);
        });


    }
}
