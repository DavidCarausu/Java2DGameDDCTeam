package utils;

import Main.Game;
import levels.CurrentLevel;
import levels.elevator;

import java.awt.*;

import static levels.Level.lvlData;
import static utils.ct.GameCT.*;

public class HelpMethods {

    public static boolean CanMoveHere(int x, int y, int height, int width, int[][] leveldata, elevator[] lifts) {
        if (!IsSolid(x, y, lifts))
            if (!IsSolid(x, y + width, lifts))
                if (!IsSolid(x + height, y, lifts))
                    if (!IsSolid(x + height, y + width, lifts))
                        return true;
        return false;
    }

    private static final elevator[] EMPTY_ELEVATOR_ARRAY = new elevator[0]; // reutilizabil
    private static Rectangle tempRect = new Rectangle(); // reutilizabil pentru calcule

    public static boolean CanMoveHere(int x, int y, int height, int width, int[][] leveldata) {
        return CanMoveHere(x, y, height, width, leveldata, EMPTY_ELEVATOR_ARRAY);
    }

    // Optimizare verificare coliziune - verifică doar dacă e necesar
    private static boolean IsSolid(int x, int y, elevator[] lifts) {
        // Verifică marginile mai întâi (mai rapid)
        if (IsMargin(x, y, lvlData)) return true;

        // Apoi tile-urile
        if (IsTile(x, y, lvlData, Game.getplayerhitbox())) return true;

        // În final lifturile (doar dacă sunt)
        if (lifts != null && lifts.length > 0) {
            return isLiftCollision(x, y, lifts);
        }

        return false;
    }
    private static boolean isLiftCollision(int x, int y, elevator[] lifts) {
        for (elevator lift : lifts) {
            if (lift != null && lift.HBLIFT != null) {
                if (x >= lift.HBLIFT.x && x < lift.HBLIFT.x + lift.HBLIFT.width &&
                        y >= lift.HBLIFT.y && y < lift.HBLIFT.y + lift.HBLIFT.height) {
                    return true;
                }
            }
        }
        return false;
    }
//fsdf

//    private static boolean IsSolid(int x, int y, elevator[] lifts) {
//        // Verifică coliziunea cu tile-urile normale
//        boolean tileCollision = IsTile(x, y, lvlData, Game.getplayerhitbox()) || IsMargin(x, y, lvlData);
//
//        // Verifică coliziunea cu lifturile
//        boolean liftCollision = false;
//        if (lifts != null) {
//            for (int i = 0; i < lifts.length; i++) {
//                if (lifts[i] != null && lifts[i].HBLIFT != null) {
//                    // Verifică dacă punctul (x,y) se află în interiorul hitbox-ului liftului
//                    if (x >= lifts[i].HBLIFT.x &&
//                            x < lifts[i].HBLIFT.x + lifts[i].HBLIFT.width &&
//                            y >= lifts[i].HBLIFT.y &&
//                            y < lifts[i].HBLIFT.y + lifts[i].HBLIFT.height) {
//                        liftCollision = true;
//                        break;
//                    }
//                }
//            }
//        }
//
//        return tileCollision || liftCollision;
//    }

    private static boolean IsLiftSolid(Rectangle playerHitbox, elevator[] lifts) {
        for (elevator lift : lifts) {
            if (lift == null) {
                System.out.println("dsafsdfsd");
                continue;
            }
            for (int i = 0; i < lifts.length; i++)
                return hasContact2(playerHitbox, lifts[i].HBLIFT);
        }

        return false;
    }

    public static boolean hasContact2(Rectangle player, Rectangle b) {
        return (player.x < b.x + b.width) &&            // stânga player < dreapta b
                (player.x + player.width > b.x) &&       // dreapta player > stânga b
                (player.y < b.y + b.height) &&           // sus player < jos b
                (player.y + player.height > b.y);        // jos player > sus b
    }

    private static boolean IsMargin(float x, float y, int[][] leveldata) {
        if (x < -1 || x >= lvlData[0].length * TILES_SIZE + 2)
            return true;
        if (y < -1 || y >= lvlData.length * TILES_SIZE + 2)
            return true;
        return false;
    }

    private static boolean IsTile(int x, int y, int[][] leveldata, Rectangle playerhitbox) {
        int j = x / TILES_SIZE;
        int i = y / TILES_SIZE;

        if (i < 0 || i >= leveldata.length || j < 0 || j >= leveldata[0].length)
            return true;
        switch (CurrentLevel.getLevelNumber()) {
            case LEVEL_1:
                int tileVal = leveldata[i][j];

                if (tileVal == -1 || tileVal == 26)
                    return false;
                if (tileVal == 12 || tileVal == 13)
                    return IsSpecialTileTrotuar(x, y, leveldata, playerhitbox);
                else if (tileVal == 2 || tileVal == 10)
                    return IsSpecialTileStep(x, y, leveldata, playerhitbox);
                else if (tileVal == 5 || tileVal == 15)
                    return IsSpecialTileMirrorStep(x, y, leveldata, playerhitbox);
                break;
            //!!!!!!!!!
            case LEVEL_2:
                 tileVal = leveldata[i][j];

                if (tileVal == -1 || tileVal == 26)
                    return false;
                if (tileVal == 10)
                    return IsSpecialTileTrotuar(x, y, leveldata, playerhitbox);
                else if (tileVal == 8 || tileVal == 1)
                    return IsSpecialTileStep(x, y, leveldata, playerhitbox);
                else if (tileVal == 4 || tileVal == 13)
                    return IsSpecialTileMirrorStep(x, y, leveldata, playerhitbox);
                break;
            case LEVEL_3:
                 tileVal = leveldata[i][j];

                if (tileVal == -1 || tileVal == 26)
                    return false;
                if (tileVal == 12 || tileVal == 13)
                    return IsSpecialTileTrotuar(x, y, leveldata, playerhitbox);
                else if (tileVal == 2 || tileVal == 10)
                    return IsSpecialTileStep(x, y, leveldata, playerhitbox);
                else if (tileVal == 5 || tileVal == 15)
                    return IsSpecialTileMirrorStep(x, y, leveldata, playerhitbox);
                break;
            //!!!!!!!!!
            default:
                break;

        }  return true;
        }

        private static boolean IsSpecialTileMirrorStep ( int x, int y, int[][] leveldata, Rectangle playerhitbox){
            int resx = x % TILES_SIZE;
            int resy = y % TILES_SIZE;
            return (resy > 95 || resx < 94) && (resy > 66 || resx < 67) && (resy > 32 || resx < 32);
        }

        private static boolean IsSpecialTileTrotuar ( int x, int y, int[][] leveldata, Rectangle playerhitbox){
            int resy = y % TILES_SIZE;
            return resy > 95;
        }

        private static boolean IsSpecialTileStep ( int x, int y, int[][] leveldata, Rectangle playerhitbox){
            int resx = x % TILES_SIZE;
            int resy = y % TILES_SIZE;
            return (resy > 95 || resx > 35) && (resy > 62 || resx > 61) && (resy > 32 || resx > 94);
        }

        public static boolean IsEntityNotInAir (Rectangle hitbox,int[][] lvlData){
            return IsTile(hitbox.x, hitbox.y + hitbox.height + 1, lvlData, hitbox) ||
                    IsTile(hitbox.width + hitbox.x, hitbox.y + 1, lvlData, hitbox);
        }

    public static boolean IsStepRight(Rectangle hitbox, int[][] lvlData) {
        int x = hitbox.x / TILES_SIZE;
        int y = hitbox.y / TILES_SIZE;

        if (x + 1 >= lvlData[0].length || y + 1 >= lvlData.length || x < 0 || y < 0) {
            return false;
        }

        switch (CurrentLevel.getLevelNumber()) {
            case LEVEL_1:
                return lvlData[y][x + 1] == 10 || lvlData[y][x + 1] == 2 ||
                        lvlData[y][x + 1] == 13 || lvlData[y][x + 1] == 12 ||
                        lvlData[y + 1][x] == 10 || lvlData[y + 1][x] == 2 ||
                        lvlData[y + 1][x + 1] == 10 || lvlData[y + 1][x + 1] == 2;

            case LEVEL_2:
                return lvlData[y][x + 1] == 8 || lvlData[y][x + 1] == 1 ||
                        lvlData[y][x + 1] == 10 || lvlData[y + 1][x] == 8 ||
                        lvlData[y + 1][x] == 1 || lvlData[y + 1][x + 1] == 8 ||
                        lvlData[y + 1][x + 1] == 1;

            case LEVEL_3:
                // AJUSTEAZĂ cu tile-urile corecte pentru nivelul 3
                return lvlData[y][x + 1] == 2 || lvlData[y][x + 1] == 10 ||
                        lvlData[y][x + 1] == 8 || lvlData[y][x + 1] == 1 ||
                        lvlData[y + 1][x] == 2 || lvlData[y + 1][x] == 10 ||
                        lvlData[y + 1][x + 1] == 2 || lvlData[y + 1][x + 1] == 10;

            default:
                return false;
        }
    }


    public static boolean IsStepLeft(Rectangle hitbox, int[][] lvlData) {
        int x = hitbox.x / TILES_SIZE;
        int y = hitbox.y / TILES_SIZE;

        if (x + 1 >= lvlData[0].length || y + 1 >= lvlData.length || x < 0 || y < 0) {
            return false;
        }

        switch (CurrentLevel.getLevelNumber()) {
            case LEVEL_1:
                return lvlData[y][x] == 15 || lvlData[y][x] == 5 ||
                        lvlData[y][x - 1] == 15 || lvlData[y][x - 1] == 5 ||
                        lvlData[y][x - 1] == 13 || lvlData[y][x - 1] == 12 ||
                        lvlData[y + 1][x] == 15 || lvlData[y + 1][x] == 5 ||
                        lvlData[y + 1][x - 1] == 15 || lvlData[y + 1][x - 1] == 5;

            case LEVEL_2:
                return lvlData[y][x] == 4 || lvlData[y][x] == 13 ||
                        lvlData[y][x - 1] == 4 || lvlData[y][x - 1] == 13 ||
                        lvlData[y + 1][x] == 4 || lvlData[y + 1][x] == 13 ||
                        lvlData[y + 1][x - 1] == 4 || lvlData[y + 1][x - 1] == 13;

            case LEVEL_3:
                // AJUSTEAZĂ cu tile-urile corecte pentru nivelul 3
                return lvlData[y][x] == 5 || lvlData[y][x] == 15 ||
                        lvlData[y][x - 1] == 5 || lvlData[y][x - 1] == 15 ||
                        lvlData[y][x - 1] == 4 || lvlData[y][x - 1] == 13 ||
                        lvlData[y + 1][x] == 5 || lvlData[y + 1][x] == 15 ||
                        lvlData[y + 1][x - 1] == 5 || lvlData[y + 1][x - 1] == 15;

            default:
                return false;
        }
    }

}
