import java.util.ArrayList;

/**
 * Creates a new Game object consisting of a Map, Fortress,
 * and some Tanks.
 *
 * @author Harshveer Singh
 */
public class Game {

//    Constants
    private final int SQUARE_MAP_GRID_SIZE = 10;
    private final int NUM_OF_TANKS = 5;
    private final int INITIAL_FORTRESS_STRENGTH = 1500;

//    Following characters represent state of each cell on Map
    private final char EMPTY = ' ';
    private final char FOG = '~';
    private final char SHOT_A_TANK = 'X';
    private final char MISSED_A_SHOT = '.';

//    Variables
    private Map gameMap;
    private Fortress fortress;
    private ArrayList<Tank> siegeTanks;

    private boolean playerTurn;
    private boolean gameFinished;

//    Constructor
    Game() {
        this.gameMap = new Map(SQUARE_MAP_GRID_SIZE);
        this.fortress = new Fortress(INITIAL_FORTRESS_STRENGTH);

        for (int i=0; i<NUM_OF_TANKS; i++) {
            this.siegeTanks.add(new Tank(getTankCoordinates()));
        }

        this.gameFinished = false; // Can't be true when game just started
        this.playerTurn = true;    // Player is given the first turn

    }
}
