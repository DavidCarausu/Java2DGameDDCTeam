package inputs;

import Main.Game;
import Main.GamePanel;
import gamestates.CurentGameState;
import gamestates.Gamestate;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyboardInputs extends KeyAdapter {
    private GamePanel gamePanel;
    private Game game;

    public KeyboardInputs(Game game) {
        this.gamePanel = game.getGamePanel();
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode()==KeyEvent.VK_ENTER)
        {
            game.SwitchGameState();
        }
        switch (CurentGameState.getState()) {
            case PLAYING:
                game.getPlaying().keyPressed(e);
                break;

            case MENU:
               // game.getMenu().keyPressed(e);
                break;

            default:
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (CurentGameState.getState()) {
            case PLAYING:

                game.getPlaying().keyReleased(e);
                break;

            case MENU:

               // game.getMenu().keyReleased(e);
                break;

            default:
                break;
        }

    }
}
