import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

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

//    Following characters represent state of each cell on Map
    private static final char EMPTY = ' ';
    private static final char FOG = '~';
    private static final char SHOT_A_TANK = 'X';
    private static final char IS_TANK = 'T';
    private static final char MISSED_A_SHOT = '.';

//    Variables
    private Map gameMap;    // a map keeping track of where shots were by user
    private Map tanksMap;   // a map only knowing tank locations
    private Fortress fortress;

    private ArrayList<Tank> siegeTanks;

    private boolean playerTurn;
    private boolean gameFinished;

//    Constructor
    Game(int fortressStrength, int gridSize) {
        this.gameFinished = false; // Can't be true when game just started
        this.playerTurn = true;    // Player is given the first turn

        this.fortress = new Fortress(INITIAL_FORTRESS_STRENGTH);
        this.gameMap = new Map(gridSize);
        this.siegeTanks = new ArrayList<>();
    }

    public void addSiegeTank(int totalCells, ArrayList<Point> coords, HashMap<Integer, Integer> damageWithCells) throws Exception {
        siegeTanks.add(new Tank(totalCells, coords, damageWithCells));
    }

    public boolean isFortressStanding() {
        return fortress.isStanding();
    }

    public boolean stillSomeTankStanding() {
        return (!(siegeTanks.isEmpty()));
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

    public boolean makePlayerMove(Point coords) {
        boolean hit = false;

        if (!(gameFinished)) {

            if (playerTurn) {

                for (Tank tank : siegeTanks) {
                    hit = tank.destroyCell(coords);

                    if (hit) {

                        if (!(tank.isStanding())) {
                            siegeTanks.remove(tank);

                            if (siegeTanks.isEmpty()) {
                                gameFinished = true;
                            }
                        }

                        gameMap.setCharAt(coords.x, coords.y, SHOT_A_TANK);
                        playerTurn = false;
                        return hit;
                    }
                }
            }

            playerTurn = false;
        }
        return false;
    }

    public int makeEnemyMove() {
        int damage = 0;

        if (!(gameFinished)) {

            if (!(playerTurn)) {

                if (siegeTanks.isEmpty()) {
                    gameFinished = true;
                    return damage;
                }

                for (Tank tank : siegeTanks) {
                    if (tank.isStanding()) {
                        damage += tank.getDamageOutput();
                    }
                    else {
                        siegeTanks.remove(tank);
                    }
                }

                fortress.subtractStrength(damage);
                if (!(fortress.isStanding())) {
                    gameFinished = true;
                }
            }

            playerTurn = true;
        }
        return damage;
    }

    public Map finalMap() {
        for (int i=0; i<gameMap.size(); i++) {
            for (int j=0; j<gameMap.size(); j++) {
                // Order of following statements matters!
                if (Character.compare(gameMap.getCharAt(i, j), SHOT_A_TANK) == 0) {
                    continue;
                }
                else if (Character.compare(gameMap.getCharAt(i, j), MISSED_A_SHOT) == 0) {
                    continue;
                }
                else if (Character.compare(tanksMap.getCharAt(i, j), IS_TANK) == 0) {
                    gameMap.setCharAt(i, j, IS_TANK);
                }
                else if (Character.compare(gameMap.getCharAt(i, j), FOG) == 0) {
                    gameMap.setCharAt(i, j, EMPTY);
                }
            }
        }
        return gameMap;
    }
}
