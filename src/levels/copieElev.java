package levels;

import entities.Player;
import utils.LoadSave;
import utils.ct;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import static utils.ct.GameCT.*;

public class copieElev {
    private int[][] csv = null;
    private static BufferedImage liftSprite;

    public static BufferedImage[] liftSubImageVector;
    private Rectangle hitbox;

    private int minY;  // Minimum Y position (bottom)
    private int maxY;  // Maximum Y position (top)
    private boolean movingUp;
    private boolean movingDown;
    private boolean playerOnElevator;
    private final int ACTIVATION_KEY = KeyEvent.VK_E; // Key to activate elevator
    private  int XX,YY;
    public int width=2* TILES_SIZE;
    public int height=TILES_SIZE;
    public int speed=2;

    public copieElev(int x, int y) {
        //  csv = LoadSave.loadCSV("elevator.csv");
        //  importOutSideSprites();
        this.hitbox = new Rectangle(y* TILES_DEFAULT_SIZE, x*TILES_SIZE, width, height);
        //System.out.println("Elevator created at " + x*TILES_SIZE + "," + y*TILES_SIZE);
        this.speed = speed;
        // this.minY = minY;
        // this.maxY = maxY;
        this.movingUp = false;
        this.movingDown = false;
        this.playerOnElevator = false;
    }

    public void adaugareMinY(int minY)
    {
        this.minY=minY*TILES_SIZE;
    }

    public void update(Player player) {
        // Verifică dacă playerul e pe lift
        playerOnElevator = hitbox.intersects(player.getHitbox());

        // Mișcă liftul în sus
        if (movingUp && hitbox.y > maxY) {
            hitbox.y -= speed;
            if (playerOnElevator) {
                player.moveY(-speed); // mută playerul în sus
            }
        }

        // Mișcă liftul în jos
        else if (movingDown && hitbox.y < minY) {
            hitbox.y += speed;
            if (playerOnElevator) {
                player.moveY(speed); // mută playerul în jos
            }
        }

        // Opriri la limite
        if (hitbox.y <= maxY) {
            hitbox.y = maxY;
            movingUp = false;
        }
        if (hitbox.y >= minY) {
            hitbox.y = minY;
            movingDown = false;
        }
    }

    private void importOutSideSprites() {
        liftSprite = LoadSave.GetLiftAtlas();
        try {
            int x = liftSprite.getWidth()/ ct.GameCT.TILES_IN_WIDTH;
            int y =  liftSprite.getHeight()/ ct.GameCT.TILES_IN_HEIGHT;
            XX=x;
            YY=y;
            liftSubImageVector = new BufferedImage[x * y];

            for (int j = 0; j < y; j++) {            // linii - de sus în jos
                for (int i = 0; i < x; i++) {        // coloane - de la stânga la dreapta
                    liftSubImageVector[i + j * x] = liftSprite.getSubimage(i * TILES_SIZE, j * TILES_SIZE, TILES_SIZE, TILES_SIZE);
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading sprites");
        }
    }

    public void handleKeyPress(int keyCode) {
        if (keyCode == ACTIVATION_KEY) {
            //System.out.println("Activating elevator");

            // Toggle direction when activation key is pressed
            if (hitbox.y == maxY) {
                movingDown = true;
                movingUp = false;
            } else if (hitbox.y == minY) {
                movingUp = true;
                movingDown = false;
            } else {
                // If somewhere in middle, continue current direction
                movingUp = !movingDown;
                movingDown = !movingUp;
            }
        }
    }

    public void render(Graphics g, int xLevelOffset, int yLevelOffset) {
        // contur roșu
        g.setColor(Color.RED);
        g.drawRect(hitbox.x - xLevelOffset, hitbox.y - yLevelOffset, hitbox.width, hitbox.height);
        //System.out.println(hitbox.x + " " + hitbox.y);
        // interior gri
        g.setColor(Color.GRAY);
        g.fillRect(hitbox.x - xLevelOffset, hitbox.y - yLevelOffset, hitbox.width, hitbox.height);

        // contur întunecat
        g.setColor(Color.DARK_GRAY);
        g.drawRect(hitbox.x - xLevelOffset, hitbox.y - yLevelOffset, hitbox.width, hitbox.height);

        // indicator verde dacă playerul e pe lift
        if (playerOnElevator) {
            g.setColor(new Color(0, 255, 0, 100));
            g.fillRect(hitbox.x - xLevelOffset, hitbox.y - yLevelOffset, hitbox.width, hitbox.height);
        }
    }



    // Getters
    public Rectangle getHitbox() {
        return hitbox;
    }

    public boolean isMoving() {
        return movingUp || movingDown;
    }

    public void adaugareMaxY(int j) {
        this.maxY=j*TILES_SIZE;
    }
}