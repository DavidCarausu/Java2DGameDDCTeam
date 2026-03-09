package gamestates;

import Main.Game;
import entities.Player;
import levels.*;
import utils.LoadSave;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static utils.ct.GameCT.*;
import static utils.ct.LoadSaveCt.*;

public class playing extends KeyAdapter implements Statemethods {

    private int xLevelOffset, yLevelOffset; // Offset-uri pentru cameră
    private int leftBorder = (int)(0.2 * GAME_WIDTH);
    private int rightBorder = (int)(0.4 * GAME_WIDTH);
    private int topBorder = (int)(0.2 * GAME_HEIGHT); // Marginea superioară
    private int bottomBorder = (int)(0.4 * GAME_HEIGHT); // Marginea inferioară

    private int lvlTilesWide = LoadSave.loadCSV(LEVEL_1_CSV)[0].length;
    private int lvlTilesHigh = LoadSave.loadCSV(LEVEL_1_CSV).length; // Înălțimea nivelului în tiles
    private int maxTilesOffsetX = lvlTilesWide - TILES_IN_WIDTH;
    private int maxTilesOffsetY = lvlTilesHigh - TILES_IN_HEIGHT; // Offset maxim pe verticală
    private int maxLvlOffsetX = maxTilesOffsetX * TILES_SIZE;
    private int maxLvlOffsetY = maxTilesOffsetY * TILES_SIZE; // Offset maxim Y (în pixeli)

    private Game game;
    private Player plr;
    private LevelManager levelManager;
    private LevelNumber currentLevel = null;

    private float cameraSpeed = 0.052f; // Ajustează această valoare pentru a controla viteza

    private void checkCloseToBorder() {

        updateCameraLimits();
        int playerX = (int) plr.getHitbox().x;
        int playerY = (int) plr.getHitbox().y;

        // Calcul diferențe față de offset-ul curent
        int diffX = playerX - xLevelOffset;
        int diffY = playerY - yLevelOffset;

        // Adjustare pe orizontală (stânga-dreapta)
        if (diffX > rightBorder) {
            xLevelOffset += (playerX - rightBorder - xLevelOffset) * cameraSpeed;
        } else if (diffX < leftBorder) {
            xLevelOffset += (playerX - leftBorder - xLevelOffset) * cameraSpeed;
        }

        // Adjustare pe verticală (sus-jos)
        if (diffY > bottomBorder) {
            yLevelOffset += (playerY - bottomBorder - yLevelOffset) * cameraSpeed;
        } else if (diffY < topBorder) {
            yLevelOffset += (playerY - topBorder - yLevelOffset) * cameraSpeed;
        }

        // Limitări pe orizontală
        if (xLevelOffset > maxLvlOffsetX) {
            xLevelOffset = maxLvlOffsetX;
        } else if (xLevelOffset < 0) {
            xLevelOffset = 0;
        }

        // Limitări pe verticală
        if (yLevelOffset > maxLvlOffsetY) {
            yLevelOffset = maxLvlOffsetY;
        } else if (yLevelOffset < 0) {
            yLevelOffset = 0;
        }

    }
    public playing(Game game) {
        this.game=game;
        this.plr=game.getPlayer();
        this.levelManager=game.getLevelManager();
        xLevelOffset=0;
        yLevelOffset = 0;
        updateCameraLimits();
    }

    private void updateCameraLimits() {
        LevelNumber newLevel = CurrentLevel.getLevelNumber();

        // Actualizează doar dacă s-a schimbat nivelul
        if (currentLevel != newLevel) {
            currentLevel = newLevel;

            String csvPath;
            switch (CurrentLevel.getLevelNumber()) {
                case LEVEL_1:
                    csvPath = LEVEL_1_CSV;
                    break;
                case LEVEL_2:
                    csvPath = LEVEL_2_CSV;
                    break;
                case LEVEL_3:
                    csvPath = LEVEL_3_CSV;
                    break;
                default:
                    csvPath = LEVEL_1_CSV;
                    break;
            }

            // Încarcă datele pentru nivelul curent
            int[][] currentLevelData = LoadSave.loadCSV(csvPath);
            if (currentLevelData != null && currentLevelData.length > 0) {
                lvlTilesWide = currentLevelData[0].length;
                lvlTilesHigh = currentLevelData.length;

                maxTilesOffsetX = Math.max(0, lvlTilesWide - TILES_IN_WIDTH);
                maxTilesOffsetY = Math.max(0, lvlTilesHigh - TILES_IN_HEIGHT);
                maxLvlOffsetX = maxTilesOffsetX * TILES_SIZE;
                maxLvlOffsetY = maxTilesOffsetY * TILES_SIZE;

            }
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        game.getPlayer().updateMousePosition(e.getX(), e.getY());
    }

    @Override
    public void update() {
        plr.update();
        levelManager.update();
        checkCloseToBorder();
//

    }

    @Override
    public void render(Graphics g) {

        levelManager.draw(g,xLevelOffset,yLevelOffset);
        plr.render(g,xLevelOffset,yLevelOffset);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

//    @Override
//    public void mouseMoved(MouseEvent e) {
//        if (game == null) {
//            return; // Early return if game is somehow null
//        }
//        mouseX = e.getX();
//        mouseY = e.getY();
//        if ( plr!= null) {
//            plr.updateMousePosition(mouseX, mouseY);
//        }
//    }
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
            case KeyEvent.VK_W:
                game.getPlayer().setJump(true);
                break;
            case KeyEvent.VK_A:
                game.getPlayer().setLeft(true);
                break;
            case KeyEvent.VK_S:
                game.getPlayer().setDown(true);
                break;
            case KeyEvent.VK_D:
                game.getPlayer().setRight(true);
                break;
            case KeyEvent.VK_E:
                //System.out.println("Tasta E apăsată");
                for (elevator lift : game.getLevelManager().getCurrentLevel().liftVector) {
                    //System.out.println("Verific player pe lift");
                    if (lift.getHBActivationLift().intersects(game.getPlayer().getHitbox())) {
                        System.out.println("Pl!!!!!!ft!");
                        lift.handleKeyPress(KeyEvent.VK_E,game.getPlayer());
                        break;
                    }
                    //else System.out.println("Player nu e pe lift!");
                }
                break;

            case KeyEvent.VK_N:
                 game.nPressed = true;

                break;


        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                game.getPlayer().setJump(false);
                break;
            case KeyEvent.VK_A:
                game.getPlayer().setLeft(false);
                break;
            case KeyEvent.VK_S:
                game.getPlayer().setDown(false);
                break;
            case KeyEvent.VK_D:
                game.getPlayer().setRight(false);
                break;
                case KeyEvent.VK_N:
                    game.nPressed = false;
                    break;
                    case KeyEvent.VK_E:
                elevator[] lifts = LevelManager.getCurrentLevel().getLiftVector();
                for (elevator lift : lifts) {
                    if (lift != null) {
                        lift.handleKeyPress(e.getKeyCode(), game.getPlayer());
                    }
                }

    }



    }

    @Override
    public void MouseInputs() {

    }


}
