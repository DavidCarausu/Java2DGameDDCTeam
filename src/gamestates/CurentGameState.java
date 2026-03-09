package gamestates;

public class CurentGameState {
    private static Gamestate state = Gamestate.MENU_INIT;

    public static Gamestate getState() {
        return state;
    }

    public static void setState(Gamestate newState) {
        state = newState;
    }
}
