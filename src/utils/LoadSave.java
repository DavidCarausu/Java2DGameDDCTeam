package utils;

import Main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import static levels.Level.nrTilesCol;
import static levels.Level.nrTilesRow;
import static utils.ct.LevelManagerCT.tiles;
import static utils.ct.LoadSaveCt.*;


public class LoadSave {


    public static int[][] loadCSV(String path) {
        ArrayList<Object> rows = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(",");
                int[] row = new int[tokens.length];
                for (int i = 0; i < tokens.length; i++) {
                    row[i] = Integer.parseInt(tokens[i].trim());

                }
                rows.add(row);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rows.toArray(new int[0][0]);
    }

    public static BufferedImage GetFULLPlayerAtlas() {

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(PLAYER_FULL));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }

    public static BufferedImage GetGun() {

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(GUN));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }

    public static BufferedImage GetPlayerAtlas() {

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(PLAYER_SPRITE));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }

    public static BufferedImage GetPlayerAtlasHead() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(PLAYER_HEAD));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }

    public static BufferedImage GetPlayerAtlasHand() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(PLAYER_HAND));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }

    public static BufferedImage GetLevel1Tiles() {

        BufferedImage sd = null;

        try {
            sd = ImageIO.read(new File(LEVEL_1_TILES));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sd;
    }

    public static BufferedImage GetLevel2Tiles() {

        BufferedImage sd = null;

        try {
            sd = ImageIO.read(new File(LEVEL_2_TILES));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sd;
    }

    public static BufferedImage GetLevel3Tiles() {

        BufferedImage sd = null;

        try {
            sd = ImageIO.read(new File(LEVEL_3_TILES));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sd;
    }

    public static BufferedImage GetLevel1BackgroundSprite() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(LEVEL1_BackGroundSprite));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }
//fds
    public static BufferedImage GetLevel2BackgroundSprite() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(LEVEL2_BackGroundSprite));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }

    public static BufferedImage GetLevel3BackgroundSprite() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(LEVEL3_SPRITE));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }

    public static BufferedImage GetLiftAtlas() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(LIFT1));


        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    } public static BufferedImage GetLiftAtls() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(LIFT1SPRITE));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }



}
