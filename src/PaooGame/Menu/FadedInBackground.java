package PaooGame.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FadedInBackground extends JPanel {
    private float alpha = 0.0f;
    private Timer fadeTimer;
    private Image backgroundImage;

    public FadedInBackground() {
        // Încarcă imaginea de fundal
        backgroundImage = new ImageIcon("poze/fundal.jpg").getImage();


        // Configurează timer-ul pentru animație (30 FPS)
        fadeTimer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alpha += 0.02f;  // Viteza de fade-in (ajustabilă)
                if (alpha >= 1.0f) {
                    alpha = 1.0f;
                    fadeTimer.stop();  // Oprește animația după completare
                }
                repaint();  // Redesenează componenta
            }
        });
        fadeTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Desenează negru în fundal
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Aplică transparența pe imagine
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}