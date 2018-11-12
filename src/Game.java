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

//    Following characters represent state of each cell on Map
    public static final String EMPTY = " ";
    public static final String FOG = "~";
    public static final String SHOT_A_TANK = "X";
    public static final String IS_TANK = "T";
    public static final String MISSED_A_SHOT = ".";

    //    Variables
    private Grid gameMap;    // a map keeping track of where shots were by user
    private Fortress fortress;
    private ArrayList<Tank> siegeTanks;

    private boolean playerTurn;
    private boolean gameFinished;
    private boolean playerWin;

//    Constructor
    Game(int fortressStrength, int gridSize) {
        this.gameFinished = false; // Can't be true when game just started
        this.playerWin = false;
        this.playerTurn = true;    // Player is given the first turn

        this.fortress = new Fortress(fortressStrength);
        this.gameMap = new Grid(gridSize);
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

    public boolean isPlayerWin() {
        return playerWin;
    }

    public Grid getGameMap() {
        return gameMap;
    }

    public int getFortressStrength() {
        return fortress.getStrength();
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
                                playerWin = true;
                            }
                        }

                        gameMap.setCharAt(coords.x, coords.y, SHOT_A_TANK);
                        playerTurn = false;
                        return hit;
                    }
                    else {
                        gameMap.setCharAt(coords.x, coords.y, MISSED_A_SHOT);
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
                    playerWin = true;
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

                if (siegeTanks.isEmpty()) {
                    gameFinished = true;
                    playerWin = true;
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

    public Grid getFinalMap() {
        for (int i=0; i<gameMap.getSize(); i++) {
            for (int j=0; j<gameMap.getSize(); j++) {

                boolean isTank = false;
                for (Tank t : siegeTanks) {
                    ArrayList<Point> c = t.getCoords();
                    for (Point p : c) {
                        if (p.equals(new Point(i, j))) {
                            isTank = true;
                        }
                    }
                }

                // Order of following statements matters!
                if ((gameMap.getCharAt(i, j).equals(SHOT_A_TANK))) {
                    continue;
                }
                else if ((gameMap.getCharAt(i, j).equals(MISSED_A_SHOT))) {
                    continue;
                }
                else if (isTank) {
                    gameMap.setCharAt(i, j, IS_TANK);
                }
                else if ((gameMap.getCharAt(i, j).equals(FOG))) {
                    gameMap.setCharAt(i, j, EMPTY);
                }
            }
            System.out.println();
        }
        return gameMap;
    }
}
