package levels;

import entities.Player;
import utils.LoadSave;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import static utils.ct.GameCT.*;

public class elevator {
    //private int[][] csv = null;
    private static BufferedImage liftSprite;

    public static BufferedImage[] liftSubImageVector;
    private final int x;
    public Rectangle HBActivationLift;
    public Rectangle HBLIFT;
    private Rectangle[] HBActivationLiftButtons = null;

    private int nrBut;

    private int minY;  // Minimum Y position (bottom)
    private int maxY;  // Maximum Y position (top)
    private boolean movingUp;
    private boolean movingDown;
    public boolean playerOnElevator;
    public final int ACTIVATION_KEY = KeyEvent.VK_E; // Key to activate elevator
    private int XX, YY;
    public int width = 2 * TILES_SIZE;
    public int height = 3 * TILES_SIZE;
    public int inaltime = 67;
    public int speed = 2;
    public Level level;
    public int liftStartRow;


    public elevator(int x, int y, Level level) {

        this.level = level;
        this.x = x;
        int hitboxX = y * TILES_SIZE;
        int hitboxY = (x - 2) * TILES_SIZE;// ultima linie vizibilă a liftului
        System.out.println("hitboxX = " + hitboxX + ", hitboxY = " + hitboxY);
        this.HBActivationLift = new Rectangle(hitboxX, hitboxY, width, height);
        this.HBLIFT = new Rectangle(hitboxX, hitboxY + TILES_SIZE * 3 - inaltime, width, inaltime);

        this.speed = speed;
        this.liftStartRow = x;

        this.movingUp = false;
        this.movingDown = false;
        this.playerOnElevator = false;

        importOutSideSprites();
    }

    public void adaugareMinY(int minY) {
        this.minY = (minY - 2) * TILES_SIZE;
    }

    public void update(Player player) {
        // Verifică dacă playerul e pe lift SAU lângă butoane
        playerOnElevator = false;

        // Verifică dacă playerul e pe platforma liftului
        if (HBActivationLift.intersects(player.getHitbox())) {
            playerOnElevator = true;
        }

        // Verifică dacă playerul e lângă butoane
        if (HBActivationLiftButtons != null) {
            for (int i = 0; i < HBActivationLiftButtons.length; i++) {
                if (HBActivationLiftButtons[i].intersects(player.getHitbox())) {
                    playerOnElevator = true;
                    break;
                }
            }
        }

        // Mișcă liftul în sus
        if (movingUp && HBActivationLift.y > maxY) {
            HBActivationLift.y -= speed;
            HBLIFT.y -= speed;

            // Mută playerul doar dacă e pe platforma liftului
            if (HBActivationLift.intersects(player.getHitbox())) {
                player.moveY(-speed);
            }
        }

        // Mișcă liftul în jos
        else if (movingDown && HBActivationLift.y < minY) {
            HBActivationLift.y += speed;
            HBLIFT.y += speed;

            // Mută playerul doar dacă e pe platforma liftului
            if (HBActivationLift.intersects(player.getHitbox())) {
                player.moveY(speed);
            }
        }

        // Opriri la limite
        if (HBActivationLift.y <= maxY) {
            HBActivationLift.y = maxY;
            movingUp = false;
        }
        if (HBActivationLift.y >= minY) {
            HBActivationLift.y = minY;
            movingDown = false;
        }
    }

    private void importOutSideSprites() {
        liftSprite = LoadSave.GetLiftAtls();
        if (liftSprite == null)
            System.out.println("liftSprite is null");
        try {
            int x = liftSprite.getWidth() / TILES_SIZE;
            int y = liftSprite.getHeight() / TILES_SIZE;

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

    public void render(Graphics g, int xLevelOffset, int yLevelOffset) {
        int tileWidth = TILES_SIZE;
        int tileHeight = TILES_SIZE;

        // presupunem că liftul are 2 coloane și 3 rânduri (3x2)
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 2; col++) {
                int idx = row * 3 + col;
                int drawX = HBActivationLift.x + col * tileWidth - xLevelOffset;
                int drawY = HBActivationLift.y + row * tileHeight - yLevelOffset;
                g.drawImage(liftSubImageVector[idx], drawX, drawY, tileWidth, tileHeight, null);
            }
        }

        // opțional: desenare hitbox pentru debugging
        g.setColor(Color.RED);
        g.drawRect(HBActivationLift.x - xLevelOffset, HBActivationLift.y - yLevelOffset, HBActivationLift.width, HBActivationLift.height);
        g.drawRect(HBLIFT.x - xLevelOffset, HBLIFT.y - yLevelOffset, HBLIFT.width, HBLIFT.height);

        // Hitbox butoane
        g.setColor(Color.GREEN);
        if (HBActivationLiftButtons != null) {
            for (Rectangle r : HBActivationLiftButtons) {
                if (movingDown == false && movingUp == false)
                    g.drawImage(liftSubImageVector[8], r.x - xLevelOffset, r.y - yLevelOffset, tileWidth, tileHeight, null);
                else if (movingDown == true && movingUp == false)

                    g.drawImage(liftSubImageVector[5], r.x - xLevelOffset, r.y - yLevelOffset, tileWidth, tileHeight, null);
else if(movingDown==false&&movingUp==true)

                    g.drawImage(liftSubImageVector[2], r.x - xLevelOffset, r.y - yLevelOffset, tileWidth, tileHeight, null);
                    g.drawRect(r.x - xLevelOffset, r.y - yLevelOffset, r.width, r.height);

            }
        }
    }

    public void handleKeyPress(int keyCode, Player player) {
        if (keyCode != ACTIVATION_KEY) // KeyEvent.VK_E
            return;

        boolean canActivate = false;

        // Verifică dacă playerul e pe lift
        if (HBActivationLift.intersects(player.getHitbox())) {
            canActivate = true;
        }
        // Verifică dacă playerul e lângă butoane
        else if (HBActivationLiftButtons != null) {
            for (Rectangle button : HBActivationLiftButtons) {
                if (button.intersects(player.getHitbox())) {
                    canActivate = true;
                    break;
                }
            }
        }

        if (!canActivate)
            return;

        // Activare lift bazat pe poziția curentă
        if (HBActivationLift.y >= minY - 5) { // aproape de jos
            movingUp = true;
            movingDown = false;
        } else if (HBActivationLift.y <= maxY + 5) { // aproape de sus
            movingDown = true;
            movingUp = false;
        } else { // în mijloc - continuă în direcția curentă sau default în jos
            if (!movingUp && !movingDown) {
                movingDown = true;
                movingUp = false;
            }
        }
    }


    void initilizareButoaneLift(int nrBut, int[][] locatii) {
        HBActivationLiftButtons = new Rectangle[nrBut];
        if (locatii != null)
            for (int i = 0; i < nrBut; i++) {
                int x = locatii[i][0];
                int y = locatii[i][1];
                HBActivationLiftButtons[i] = new Rectangle(x, y, TILES_SIZE, TILES_SIZE);
            }
    }

    public void adaugaButoane2() {
        nrBut = 2;
        //0 pentru x
        //1 pentru y
        int[][] loc = new int[2][2];
        loc[0][0] = HBActivationLift.x - TILES_SIZE;
        loc[0][1] = minY + 2 * TILES_SIZE;
        loc[1][0] = (HBActivationLift.x + 2 * TILES_SIZE);
        loc[1][1] = maxY + 2 * TILES_SIZE;
        initilizareButoaneLift(nrBut, loc);
        System.out.println("x: " + loc[0][0] + " y: " + loc[0][1] + " || x: " + loc[1][0] + " y: " + loc[1][1] + "  " + x);
    }   public void adaugaButoane3() {
        nrBut = 3;
        //0 pentru x
        //1 pentru y
        int[][] loc = new int[3][2];
        loc[0][0] = HBActivationLift.x - TILES_SIZE;
        loc[0][1] = minY + 2 * TILES_SIZE;
        loc[1][0] = (HBActivationLift.x + 2 * TILES_SIZE);
        loc[1][1] = maxY + 2 * TILES_SIZE;
        loc[2][0] = (HBActivationLift.x + 2* TILES_SIZE);
        loc[2][1] = maxY + 5 * TILES_SIZE;
        initilizareButoaneLift(nrBut, loc);
        //System.out.println("x: " + loc[0][0] + " y: " + loc[0][1] + " || x: " + loc[1][0] + " y: " + loc[1][1] + "  " + x);
    }

    public void adaugaButoane2Mirror() {
        nrBut = 2;
        //0 pentru x
        //1 pentru y
        int[][] loc = new int[2][2];
        loc[0][0] = (HBActivationLift.x + 2 * TILES_SIZE);
        loc[0][1] = minY + 2 * TILES_SIZE;
        loc[1][0] = HBActivationLift.x - TILES_SIZE;
        loc[1][1] = maxY + 2 * TILES_SIZE;
        initilizareButoaneLift(nrBut, loc);
        System.out.println("x: " + loc[0][0] + " y: " + loc[0][1] + " || x: " + loc[1][0] + " y: " + loc[1][1] + "  " + x);
    }

    // Getters
    public Rectangle getHBActivationLift() {
        return HBActivationLift;
    }

    public boolean isMoving() {
        return movingUp || movingDown;
    }

    public void adaugareMaxY(int j) {
        this.maxY = (j - 2) * TILES_SIZE;
    }


}