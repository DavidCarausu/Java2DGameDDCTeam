package inputs;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Main.Game;
import entities.Player;
import gamestates.Gamestate;
import gamestates.playing;

public class MouseInputs extends MouseAdapter {
    private Player player;
    private Game game;
    private int mouseX, mouseY;

    public MouseInputs(Game game) {
        if (game == null)
            throw new IllegalArgumentException("Game cannot be null");
        this.game = game;
        if (game.getPlayer() == null)
            throw new IllegalArgumentException("Player cannot be null");
        this.player = game.getPlayer();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(game.getGameState()==Gamestate.PLAYING)
        {
            game.getPlayer().updateMousePosition(e.getX(), e.getY());

        }
//        switch (game.gamestate) {
//            case PLAYING:
//                game.getPlaying().mouseMoved(e);
//                break;
//
//            case MENU:
//                break;
//
//            default:
//                break;
//        }
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {}
    @Override
    public void mousePressed(MouseEvent e)
    {}
    @Override
    public void mouseReleased(MouseEvent e)
    {}
}