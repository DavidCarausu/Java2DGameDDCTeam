package Main;

import PaooGame.Menu.FadedInBackground;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class GameWindow {
    private JFrame jframe;
    private GamePanel gamePanel;
    private FadedInBackground menu;

    public GameWindow() {
        jframe = new JFrame();
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setResizable(false);

        // Obținem dimensiunile ecranului
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // Setăm dimensiunile ferestrei (sau le luăm din panel)
        int windowWidth = 1280; // sau gamePanel.getWidth() dacă e disponibil
        int windowHeight = 720; // sau gamePanel.getHeight()

        // Calculăm poziția pentru "jumate de ecran mai sus și în stânga"
        int x = (screenWidth - windowWidth) / 4; // 1/4 din spațiul rămas (spre stânga)
        int y = (screenHeight - windowHeight) / 4; // 1/4 din spațiul rămas (spre sus)

        // Setăm poziția și dimensiunea ferestrei
        jframe.setBounds(x, y, windowWidth, windowHeight);

        jframe.setVisible(true);
    }

    public void AddMenuPanel(FadedInBackground menu) {
        if (menu != null)
            //jframe.getContentPane().remove(menu);

        this.menu = menu;
    }

    public void AddGamePanel(GamePanel gamepanel) {
        if (gamepanel != null) jframe.getContentPane().remove(gamepanel);

        this.gamePanel = gamepanel;
    }

    //0 pentru Menu
    //1 pentru game
    public void CarePanelPeFrame(boolean a) {
        jframe.getContentPane().removeAll();
        if (a) {
//            if (gamePanel != null) jframe.getContentPane().remove(gamePanel);
            jframe.add(gamePanel);

            jframe.addWindowFocusListener(new WindowFocusListener() {
                @Override
                public void windowGainedFocus(WindowEvent e) {
                    // Optional: logica când fereastra câștigă focus
                }

                @Override
                public void windowLostFocus(WindowEvent e) {
                    gamePanel.getGame().windowFocusLost();  // Apelează windowFocusLost din Game
                }
            });
        }
        else
        jframe.add(menu);
        jframe.revalidate();
        jframe.repaint();
        jframe.pack();
        //
    }

}
