package utils;

import java.awt.image.BufferedImage;

import static utils.ct.GameCT.TILES_SIZE;


public class ct {
    public class LoadSaveCt {
        public static final String PLAYER_SPRITE = "res/textures/Player_Sprite-Sheet.png";
        public static final String PLAYER_HAND = "res/textures/SpriteHandPistol-export.png";
        public static final String PLAYER_HEAD = "res/textures/sprite-head.png";
        public static final String PLAYER_FULL = "res/textures/Player_Sprite-Sheet_complet+mirror.png";

        public static final String GUN="res/textures/guns/Famas Sheet.png";

        public static final String LEVEL_1_CSV = "res/Level_1_csv/level1._Tiles.csv";
        public static final String LEVEL1_BackGroundSprite = "res/textures/Level_1_sprites/Level1_Sprite.png";
        public static final String LEVEL_1_TILES = "res/textures/Level_1_sprites/level1tiles+cacamaka.png";
        public static final String LIFT1 = "res/Level_1_csv/level1._lift.csv";
        public static final String LIFT1SPRITE = "res/textures/Level_1_sprites/lift.png";

        public static final String LEVEL_2_CSV = "res/lvl2_csv/lvl2csv.csv";
        public static final String LEVEL2_BackGroundSprite = "res/textures/level2/lvl2Background.jpg";
        public static final String LEVEL_2_TILES = "res/textures/level2/lvl2tiles.png";
        public static final String LIFT2 = "res/lvl2_csv/lvl2lift.csv";

        public static final String LEVEL_3_CSV = "res/lvl3_csv/l33_nivel.csv";
        public static final String LEVEL3_SPRITE = "res/textures/lvl3_poze/lvl3_Background_aspr.png";
        public static final String LEVEL_3_TILES = "res/textures/lvl3_poze/level3_tiles.png";
        public static final String LIFT3 = "res/lvl3_csv/l33_liift.csv";


    }

    public class Constants {
        public class Directions {
            public static final int left = 0;
            public static final int right = 1;
        }

        public class PlayerConstants {
            public static final int IDLE = 0;
            public static final int RUNNING = 1;
            public static final int ATTACK_1 = 2;
            public static final int JUMPING = 3;
            public static final int FALLING = 4;
            public static final int HIT = 5;
            public static final int CROUCH = 6;


        }
    }

    public class GameCT {
        public final static int TILES_DEFAULT_SIZE = 128;
        public final static float SCALE = 1.0f;
        public final static int TILES_IN_WIDTH = 15;
        public final static int TILES_IN_HEIGHT = 8;
        public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
        public final static int GAME_WIDTH = TILES_IN_WIDTH * TILES_SIZE;
        public final static int GAME_HEIGHT = TILES_IN_HEIGHT * TILES_SIZE;
    }

    public static class LevelManagerCT {

        public static int tiles = TILES_SIZE;

        //  private static BufferedImage[][] level1incarcat;

    }

    public static class MenuCT {

        public static final int BUTTON_WIDTH  =GameCT.GAME_WIDTH/2;
        public static final int BUTTON_HEIGHT = 80;

        public static final int BUTTON_X  = ( GameCT.GAME_WIDTH - BUTTON_WIDTH )/2;
        public static final int BUTTON_Y_START  = GameCT.GAME_HEIGHT/5+300;
        public static final int BUTTON_SPACING  = 75;

        public static final int SLIDER_WIDTH = BUTTON_WIDTH;
        public static final int SLIDER_HEIGHT = BUTTON_HEIGHT;
        public static final int SLIDER_X_START  = BUTTON_X;
        public static final int SLIDER_Y_START  = 650;
        public static final int SLIDER_SPACING  = BUTTON_SPACING;
        public static final int SLIDER_VOLUME_MAX = 100;
        public static final int SLIDER_VOLUME_INITIAL = 80;

        public static final int button_change_label_x_offset=30;
        public static final int button_change_label_y_startoffset=200;
        public static final int button_change_x_offset=180;
        public static final int button_change_y_offset=200;
        public static final int button_change_spacing=BUTTON_SPACING;
    }


}
