package PaooGame.Menu;

import javax.swing.*;
import java.awt.*;

public class Label_extend extends JLabel {
    private static final Font DEFAULT_FONT = new Font("Arial", Font.PLAIN, 18); // Font mai mare
    private static final Color DEFAULT_COLOR = new Color(255, 255, 255); // Gri închis
   // File FontFile= new File("C:\\Users\\david\\IdeaProjects\\paoo-proiect-doubledc\\Psychopod.ttf");
    // Constructor 1: Text personalizat
    public Label_extend(String text) {
        super(text);
        applyStyle();
        //sapt10 test message
    }

    // Aplică stilul
    private void applyStyle() {
        setFont(DEFAULT_FONT);
        setForeground(DEFAULT_COLOR);
        setOpaque(false); // Fundal transparent
    }
}
