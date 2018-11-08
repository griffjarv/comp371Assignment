import java.util.ArrayList;

/**
 * Creates a new Fortress Defence Game consisting of a Map, Fortress,
 * and some Tanks.
 *
 * @author Harshveer Singh
 */
public class Game {

//    Constants
    private static final int SQUARE_MAP_GRID_SIZE = 10;
    private static final int NUM_OF_TANKS = 5;
    private static final int TANK_CELLS = 4;
    private static final int INITIAL_FORTRESS_STRENGTH = 1500;

    public static final int HIT_A_TANK = -1;

//    Following characters represent state of each cell on Map
    private static final char EMPTY = ' ';
    private static final char FOG = '~';
    private static final char SHOT_A_TANK = 'X';
    private static final char MISSED_A_SHOT = '.';

//    Variables
    private Map gameMap;
    private Fortress fortress;
    private ArrayList<Tank> siegeTanks;

    private boolean playerTurn;
    private boolean gameFinished;

    public boolean isFortressStanding() {
        return (fortress.strength() <= 0);
    }

    public boolean anyTanksAlive() {
        return (siegeTanks.size() > 0);
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(boolean playerTurn) {
        this.playerTurn = playerTurn;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public int makeMove(String[] coords) {
        if (!(gameFinished)) {
            int damage = 0;

            if (playerTurn) {
                fireResult = fireAt(coords);
                update tankcells if hit a tank;
                update gameMap;
                if (fortress.strength <= 0) {
                    gameFinished = true;
                }
                if (hit a tank) {
                    return HIT_A_TANK;
                }
                playerTurn = false;
            }
            else {
                for (Tank tank : siegeTanks) {
                    damage += tank.getDamageOutput();
                }
                if (damage == 0) {
                    gameFinished = true;
                }
                fortress.updateStrength(fortress.strength - damage);
                playerTurn = true;
            }

            return damage;
        }
    }

    public Map finalMap() {
        Map finalMap = gameMap;
        // remove fog from this finalMap
        return finalMap;
    }

//    Constructor
    Game() {
        this.gameMap = new Map(SQUARE_MAP_GRID_SIZE);
        placeTanksOnMap(NUM_OF_TANKS);
        this.fortress = new Fortress(INITIAL_FORTRESS_STRENGTH);
        this.gameFinished = false; // Can't be true when game just started
        this.playerTurn = true;    // Player is given the first turn

    }

    private void placeTanksOnMap(int numOfTanks) {
        for (int i=0; i<numOfTanks; i++) {
            this.siegeTanks.add(new Tank(randomTankCoordinates(TANK_CELLS)));
        }
    }

    private Map randomTankCoordinates(int tankCells) {
    }

}
