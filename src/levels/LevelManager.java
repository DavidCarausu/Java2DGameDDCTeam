package levels;

import Main.Game;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.ct.LevelManagerCT.*;
import static utils.ct.LoadSaveCt.*;


public class LevelManager {
    private static Level level1 = null, level2 = null, level3 = null;
    private Game game;
    private static BufferedImage levelSprite; //asta este pentru a taia dupa sub Bufferele
    private static BufferedImage OptimizareaCica;


    public LevelManager(Game game) {
        this.game = game;
        //importOutSideSprites();
        switch (CurrentLevel.getLevelNumber()) {
            case LevelNumber.LEVEL_1:

                level1 = new Level(LoadSave.loadCSV(LEVEL_1_CSV), LoadSave.loadCSV(LIFT1));
                level1.SetBackgroundSprite(LoadSave.GetLevel1BackgroundSprite());
                OptimizareaCica = LoadSave.GetLevel1BackgroundSprite();
                level1.setNextLevel(new Rectangle(137 * 128, 750 - 128, 4 * 128, 2 * 128));


                break;
            case LevelNumber.LEVEL_2:

                level2 = new Level(LoadSave.loadCSV(LEVEL_2_CSV), LoadSave.loadCSV(LIFT2));
                level2.SetBackgroundSprite(LoadSave.GetLevel2BackgroundSprite());
                //                  OptimizareaCica = LoadSave.GetLevel1BackgroundSprite();
                    level2.setNextLevel(new Rectangle(15730+4*128+13,2694-132,4*128,2*128));
                break;

            case LevelNumber.LEVEL_3:

                level3 = new Level(LoadSave.loadCSV(LEVEL_3_CSV), LoadSave.loadCSV(LIFT3));
                level3.SetBackgroundSprite(LoadSave.GetLevel3BackgroundSprite());
                break;

            default:
                break;

        }
        importOutSideSprites();


    }

    private void importOutSideSprites() {
        int x = 0, y = 0;

        switch (CurrentLevel.getLevelNumber()) {
            case LEVEL_1:
                levelSprite = LoadSave.GetLevel1Tiles();

                x = levelSprite.getWidth() / tiles;
                y = levelSprite.getHeight() / tiles;

                level1.LevelRowColSet(x, y);

                //System.out.print(y);
                BufferedImage[] level1vectorTiles = level1.getLevelTilesvector();
                BufferedImage[] level1LiftTiles = level1.getLevelLiftsTilesvector();

                for (int j = 0; j < y; j++) {            // linii - de sus în jos
                    for (int i = 0; i < x; i++) {        // coloane - de la stânga la dreapta
                        level1vectorTiles[i + j * x] = levelSprite.getSubimage(i * tiles, j * tiles, tiles, tiles);
                    }
                }

                break;
            //!!!!!!!!!
            case LEVEL_2:
                levelSprite = LoadSave.GetLevel2Tiles();

                x = levelSprite.getWidth() / tiles;
                y = levelSprite.getHeight() / tiles;

                level2.LevelRowColSet(x, y);

                //System.out.print(y);
                BufferedImage[] level2vectorTiles = level2.getLevelTilesvector();
                BufferedImage[] level2LiftTiles = level2.getLevelLiftsTilesvector();

                for (int j = 0; j < y; j++) {            // linii - de sus în jos
                    for (int i = 0; i < x; i++) {        // coloane - de la stânga la dreapta
                        level2vectorTiles[i + j * x] = levelSprite.getSubimage(i * tiles, j * tiles, tiles, tiles);
                    }
                }
                break;
            case LEVEL_3:
                levelSprite = LoadSave.GetLevel3Tiles();

                x = levelSprite.getWidth() / tiles;
                y = levelSprite.getHeight() / tiles;

                level3.LevelRowColSet(x, y);
                BufferedImage[] level3vectorTiles = level2.getLevelTilesvector();
                BufferedImage[] level3LiftTiles = level2.getLevelLiftsTilesvector();

                for (int j = 0; j < y; j++) {            // linii - de sus în jos
                    for (int i = 0; i < x; i++) {        // coloane - de la stânga la dreapta
                        level3vectorTiles[i + j * x] = levelSprite.getSubimage(i * tiles, j * tiles, tiles, tiles);
                    }
                }
                break;
            default:
                break;
        }


    }

    public static Level getCurrentLevel() {
        switch (CurrentLevel.getLevelNumber()) {
            case LevelNumber.LEVEL_1:
                return level1;
            case LevelNumber.LEVEL_2:
                return level2;
            case LevelNumber.LEVEL_3:
                return level3;
            default:
                return null;
        }
    }

    public void afisarareNivel(Graphics g, int xLevelOffset, int yLevelOffset, int[][] matrice_csv, BufferedImage[] levelvectorTiles) {


        int nrTilesRowLv1 = level1.nrTilesRow;
        int nrTilesColLv1 = level1.nrTilesCol;

        // BufferedImage[] level1vectorTiles = level1.getLevelTilesvector();
        if (levelvectorTiles == null) {
            System.out.println("level1vectorTiles is null");
            return;
        }

        for (int i = 0; i < matrice_csv.length; i++) {
            for (int j = 0; j < matrice_csv[0].length; j++) {

                if (matrice_csv[i][j] > -1 && matrice_csv[i][j] < nrTilesColLv1 * nrTilesRowLv1) {
                    g.drawImage(levelvectorTiles[matrice_csv[i][j]], j * tiles - xLevelOffset, i * tiles - yLevelOffset, tiles, tiles, null);
                }

            }
        }

    }

    private static BufferedImage level1Background, level2Background, level3Background;
    private static boolean backgroundsLoaded = false;

    // Încarcă toate imaginile o singură dată
    private void loadAllBackgrounds() {
        if (!backgroundsLoaded) {
            level1Background = LoadSave.GetLevel1BackgroundSprite();
            level2Background = LoadSave.GetLevel2BackgroundSprite();
            level3Background = LoadSave.GetLevel3BackgroundSprite();
            backgroundsLoaded = true;
        }
    }

    public void draw(Graphics g, int xLevelOffset, int yLevelOffset) {
        loadAllBackgrounds(); // apelează doar prima dată

        switch (CurrentLevel.getLevelNumber()) {
            case LEVEL_1:
                g.drawImage(level1Background, 0, 0, null);
                level1.render(g, xLevelOffset, yLevelOffset);
                break;
            case LEVEL_2:
                g.drawImage(level2Background, 0, 0, null); // fără încărcare repetată
                level2.render(g, xLevelOffset, yLevelOffset);
                break;
            case LEVEL_3:
                g.drawImage(level3Background, 0, 0, null);
                level3.render(g, xLevelOffset, yLevelOffset);
                break;
        }
    }


    public void update() {
        Level currentLevel = getCurrentLevel();
        currentLevel.update(game.getPlayer());

        Rectangle playerHitbox = game.getPlayer().getHitbox();

        // Trecere la nivelul următor
        if (currentLevel.getNextLevel() != null &&
                currentLevel.getNextLevel().intersects(playerHitbox) &&
                game.isNPressed()) {

            LevelNumber current = CurrentLevel.getLevelNumber();

            if (current == LevelNumber.LEVEL_1)
                CurrentLevel.setLevelNumber(LevelNumber.LEVEL_2);
            else if (current == LevelNumber.LEVEL_2)
                CurrentLevel.setLevelNumber(LevelNumber.LEVEL_3);

            game.resetLevel(); // asigură-te că reîncarcă levelul
        }

        // Trecere la nivelul anterior
        if (currentLevel.getPrevLevel() != null &&
                currentLevel.getPrevLevel().intersects(playerHitbox) &&
                game.isNPressed()) {

            LevelNumber current = CurrentLevel.getLevelNumber();

            if (current == LevelNumber.LEVEL_3)
                CurrentLevel.setLevelNumber(LevelNumber.LEVEL_2);
            else if (current == LevelNumber.LEVEL_2)
                CurrentLevel.setLevelNumber(LevelNumber.LEVEL_1);

            game.resetLevel();
        }
    }


    public void mapDraw(Graphics g) {

    }

}
