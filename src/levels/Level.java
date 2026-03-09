package levels;

import javax.swing.*;

import entities.Player;
import levels.*;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.ct.GameCT.*;
import static utils.ct.LevelManagerCT.tiles;

public class Level {

    public static int[][] lvlData;
    public static int[][] lvlLiftsData;
    public static int nrTilesRow = 0;
    public static int nrTilesCol = 0;
    public static int nrLiftsLv = 0;
    public elevator[] liftVector = null;

    private Rectangle NEXTLEVEL=null;
    private Rectangle PREVLEVEL=null;

    public static BufferedImage[] levelTilesvector;
    public static BufferedImage[] levelLiftTilesVector;
    public static BufferedImage levelBackgroundSprite;

    public Level(int[][] lvlData, int[][] lvlLiftsData) {
        this.lvlData = lvlData;
        // Reset the static counter for each new level
        nrLiftsLv = 0;

        if (lvlLiftsData != null) {
            this.lvlLiftsData = lvlLiftsData;
            adaufareLifturi();
            System.out.println("Nr of lifts: " + nrLiftsLv);
        } else {
            System.out.println("LvlLiftsData is null");
        }
    }
    public void setNextLevel(Rectangle next) {
        this.NEXTLEVEL = next;
    }

    public void setPrevLevel(Rectangle prev) {
        this.PREVLEVEL = prev;
    }

    public Rectangle getNextLevel() {
        return NEXTLEVEL;
    }

    public Rectangle getPrevLevel() {
        return PREVLEVEL;
    }

    public void LevelRowColSet(int ROW, int COL) {
        nrTilesRow = ROW;
        nrTilesCol = COL;
        levelTilesvector = new BufferedImage[nrTilesRow * nrTilesCol];
        levelLiftTilesVector = new BufferedImage[nrTilesRow * nrTilesCol];
    }

    public BufferedImage[] getLevelTilesvector() {
        if (levelTilesvector != null) {
            return levelTilesvector;
        } else
            System.out.println("LevelTilesvector is null");
        return null;
    }

    public BufferedImage[] getLevelLiftsTilesvector() {
        if (levelLiftTilesVector != null) {
            return levelLiftTilesVector;
        } else
            System.out.println("LevelTilesvector is null");
        return null;
    }

    public int GetNrLifts() {
        return nrLiftsLv;
    }


    public void SetBackgroundSprite(BufferedImage levelBackgroundSprite) {
        this.levelBackgroundSprite = levelBackgroundSprite;
    }

    public BufferedImage GetBackgroundSprite() {
        return levelBackgroundSprite;
    }

    public static void setLvlData(int[][] lvlData) {
        Level.lvlData = lvlData;
    }


    public static int[][] getLvlData() {
        if (lvlData != null)
            return lvlData;
        else {
            System.out.println("LvlData is null");
            return null;
        }

    }

    public static int[][] getLvlLiftsData() {
        if (lvlLiftsData != null)
            return lvlLiftsData;
        else {
            System.out.println("LvlData is null");
            return null;
        }

    }
    // In Level.java, modify the adaufareLifturi() method:

    public void adaufareLifturi() {
        // Reset counter at the beginning
        nrLiftsLv = 0;
        int[][] vv = new int[2][99];

        for (int i = 0; i < lvlLiftsData.length; i++) {
            for (int j = 0; j < lvlLiftsData[0].length; j++) {
                if (lvlLiftsData[i][j] == 0) {
                    vv[0][nrLiftsLv] = i + 2; // ROW (cu offset dacă e nevoie)
                    vv[1][nrLiftsLv] = j;     // COL
                    nrLiftsLv++;
                }
            }
        }

        liftVector = new elevator[nrLiftsLv];
        if (liftVector == null) {
            System.out.println("LiftVector is null");
            return;
        }

        for (int i = 0; i < nrLiftsLv; i++) {
            int row = vv[0][i]; // ROW
            int col = vv[1][i]; // COL
            System.out.println("Lift " + i + " at row " + row + " col " + col);
            liftVector[i] = new elevator(row, col, this);

            // căutăm minY (tile 25 în jos)
            int j = 0;
            while ((row + j) < lvlLiftsData.length && lvlLiftsData[row + j][col] != 25) {
                j++;
            }
            if ((row + j) >= lvlLiftsData.length) {
                System.out.println("Eroare: Nu s-a găsit tile 25 pentru liftul " + i);
                continue;
            }
            liftVector[i].adaugareMinY(row + j - 1);

            // căutăm maxY (tile 27 în sus)
            int k = row;
            while (k >= 0 && lvlLiftsData[k][col] != 27) {
                k--;
            }
            if (k < 0) {
                System.out.println("Eroare: Nu s-a găsit tile 27 pentru liftul " + i);
                continue;
            }
            liftVector[i].adaugareMaxY(k);
        }

        // Initialize buttons based on current level
        initializeElevatorButtons();
    }


    // Add this new method to handle level-specific elevator button initialization
    private void initializeElevatorButtons() {
        // Get current level from CurrentLevel class
        LevelNumber currentLevel = CurrentLevel.getLevelNumber();

        switch (currentLevel) {
            case LEVEL_1:
                if (nrLiftsLv >= 3) {
                    liftVector[0].adaugaButoane2();
                    liftVector[1].adaugaButoane2Mirror();
                    liftVector[2].adaugaButoane3();
                }
                break;

            case LEVEL_2:
                // Add level 2 specific elevator button initialization
                // Adjust based on your level 2 elevator layout:
                liftVector[0].adaugaButoane2();
                liftVector[1].adaugaButoane2Mirror();
                liftVector[2].adaugaButoane2();
                liftVector[3].adaugaButoane2Mirror();

                for (int i = 0; i < nrLiftsLv; i++) {
                    //liftVector[i].adaugaButoane2(); // Toate lifturile cu aceeași configurație

                    // Add appropriate button initialization for each elevator
                    // You need to determine what button method each elevator should use
                    // For now, using a generic approach:
                    if (i == 0 && nrLiftsLv > 0) {
                        // liftVector[0].adaugaButoane2(); // uncomment and adjust as needed
                    }
                    if (i == 1 && nrLiftsLv > 1) {
                        // liftVector[1].adaugaButoane2Mirror(); // uncomment and adjust as needed
                    }
                    // Add more specific configurations based on your level 2 design
                }
                break;

            case LEVEL_3:
                liftVector[0].adaugaButoane2();
                liftVector[1].adaugaButoane2();
                liftVector[2].adaugaButoane2();
                liftVector[3].adaugaButoane2();

                liftVector[4].adaugaButoane2Mirror();
                break;

            default:
                System.out.println("Unknown level for elevator initialization");
                break;
        }
    }
    public void afisarareNivel(Graphics g, int xLevelOffset, int yLevelOffset, int[][] matrice_csv, BufferedImage[] levelvectorTiles) {

        // BufferedImage[] level1vectorTiles = level1.getLevelTilesvector();
        if (levelvectorTiles == null) {
            System.out.println("level1vectorTiles is null");
            return;
        }

        for (int i = 0; i < matrice_csv.length; i++) {
            for (int j = 0; j < matrice_csv[0].length; j++) {

                if (matrice_csv[i][j] > -1 && matrice_csv[i][j] < nrTilesCol * nrTilesRow) {
                    g.drawImage(levelvectorTiles[matrice_csv[i][j]], j * tiles - xLevelOffset, i * tiles - yLevelOffset, tiles, tiles, null);
                }

            }
        }

    }

    private static final int TILE_CACHE_SIZE = 1000;
    private BufferedImage[] tileCache = new BufferedImage[TILE_CACHE_SIZE];
    private boolean[] tileCacheLoaded = new boolean[TILE_CACHE_SIZE];

    // Încarcă tile-urile doar când sunt necesare
    private BufferedImage getTile(int index) {
        if (index < 0 || index >= TILE_CACHE_SIZE) return null;

        if (!tileCacheLoaded[index]) {
            // Încarcă tile-ul doar prima dată când e necesar
            if (levelTilesvector != null && index < levelTilesvector.length) {
                tileCache[index] = levelTilesvector[index];
                tileCacheLoaded[index] = true;
            }
        }
        return tileCache[index];
    }

    public void render(Graphics g, int xLevelOffset, int yLevelOffset) {
        // Calculează doar tile-urile vizibile
        int startCol = Math.max(0, xLevelOffset / TILES_SIZE);
        int endCol = Math.min(lvlData[0].length, (xLevelOffset + GAME_WIDTH) / TILES_SIZE + 1);
        int startRow = Math.max(0, yLevelOffset / TILES_SIZE);
        int endRow = Math.min(lvlData.length, (yLevelOffset + GAME_HEIGHT) / TILES_SIZE + 1);

        // Renderează doar tile-urile vizibile
        for (int i = startRow; i < endRow; i++) {
            for (int j = startCol; j < endCol; j++) {
                int tileId = lvlData[i][j];
                if (tileId > -1 && tileId < nrTilesCol * nrTilesRow) {
                    BufferedImage tile = getTile(tileId);
                    if (tile != null) {
                        g.drawImage(tile, j * TILES_SIZE - xLevelOffset,
                                i * TILES_SIZE - yLevelOffset, TILES_SIZE, TILES_SIZE, null);
                    }
                }
            }
        }

        // Renderează lifturile
        if (liftVector != null) {
            for (elevator lift : liftVector) {
                if (lift != null) {
                    lift.render(g, xLevelOffset, yLevelOffset);
                }
            }
        }
    }


    public void update(Player player) {
        // Use the actual number of lifts for this level instance
        if (liftVector != null) {
            for(int i = 0; i < liftVector.length; i++) {
                if (liftVector[i] != null) {
                    liftVector[i].update(player);
                }
            }
        }
    }

    public elevator[] getLiftVector() {
        if(liftVector!=null)
            return liftVector;
        else {
            System.out.println("LiftVector is null");
            return null;
        }
    }
}
