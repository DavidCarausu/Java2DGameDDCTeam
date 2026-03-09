package utils;

import static utils.ct.Constants.PlayerConstants.*;

public class Constants {
    public static class Directions {
        public static final int left = 0;
        public static final int right = 1;
    }

    public static class PlayerConstants {


        public static int GetSpriteAmmount(int player_action) {
            switch (player_action) {
                case RUNNING: return 15;
                case IDLE: return 4;
                case ATTACK_1: return 4; // nr de animatii pistol(attack)
                default: return 1;
            }
        }
    }
}