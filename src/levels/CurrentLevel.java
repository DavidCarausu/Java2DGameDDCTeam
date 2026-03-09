package levels;

public class CurrentLevel {
    private static LevelNumber levelNumber=LevelNumber.LEVEL_1;
    public static LevelNumber getLevelNumber() {
        return levelNumber;
    }
    public static void setLevelNumber(LevelNumber levelNumber) {
        CurrentLevel.levelNumber = levelNumber;
    }

}
