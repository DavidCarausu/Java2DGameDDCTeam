package PaooGame.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class Menu_Buttons extends JButton {
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color TEXT_COLOR_HOVER = Color.GRAY;
    private  Font BUTTON_FONT ;

    public Menu_Buttons(String text, Boolean a) {
        super(text);
        if(a)
        {
            BUTTON_FONT = new Font("Arial", Font.BOLD, 36);
        }
        else
        {
            BUTTON_FONT = new Font("Arial", Font.BOLD, 22);
        }
        setStyle();
        addHoverEffect();
    }


    private void setStyle() {
        // 1. Elimină tot ce face butonul vizibil
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setBorder(BorderFactory.createEmptyBorder()); // Elimină orice spațiu rezervat bordurii

        // 2. Setări text
        setFont(BUTTON_FONT);
        setForeground(TEXT_COLOR);
        setHorizontalAlignment(SwingConstants.CENTER); // Aliniere corectă a textului
    }

    private void addHoverEffect() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setForeground(TEXT_COLOR_HOVER); // Culoare la hover
                repaint(); // Forțează redesenarea
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setForeground(TEXT_COLOR); // Culoare normală
                repaint();
            }
        });
    }

    // 3. Dezactivează desenarea fundalului și a border-ului
    @Override
    protected void paintComponent(Graphics g) {
        // Nu desena fundalul
        if (getModel().isArmed()) {
            g.setColor(new Color(0, 0, 0, 0)); // Complete transparent când e apăsat
        } else {
            g.setColor(new Color(0, 0, 0, 0)); // Fundal complet transparent
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}