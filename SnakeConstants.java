/**
 * Utility class defining constants used in the Snake game.
 */
public class SnakeConstants {

    // Directional constants
    public static final int LEFT = 0, TOP = 1, RIGHT = 3, DOWN = 4, NOMOVE = 5;

    // Movement result constants
    public static final int WALL_COLLISION = 10;
    public static final int SELF_COLLISION = 11;
    public static final int ATE_FOOD = 12;
    public static final int SNAKE_NORMAL = 13;
    public static final int INVALID_MOVE = 14;
}
