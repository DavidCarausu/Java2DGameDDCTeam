package Main;

import entities.Player;
import inputs.MouseInputs;
import inputs.KeyboardInputs;

import javax.swing.*;
import java.awt.*;

import static utils.ct.GameCT.*;


public class GamePanel extends JPanel {
    private Game game;

    private Player player; // Referință corectă către Player

   // private MouseInputs mouseInputs;

    public GamePanel(Game game) {
        this.game = game;
        if(game.getPlayer()==null)
            System.out.println("Player is null");
        else
        this.player = game.getPlayer(); // Setează Player-ul din Game
        setFocusable(true);

        // Inițializează MouseInputs cu Player-ul
        MouseInputs mouseInputs = new MouseInputs(game);

        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);



        // Inițializează KeyboardInputs cu GamePanel
  // Corect: Transmite GamePanel


        setPanelSize();
    }

    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void updateGame() {
        // Actualizare logică de joc
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);  // Redă jocul
    }

    public Game getGame() {
        return game;
    }
}
