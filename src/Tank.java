import java.util.HashMap;
import java.awt.Point;
import java.util.ArrayList;

/**
 * Siege tank which can shoot and deal damage corresponding to
 * the number of cells available.
 *
 * @author Harshveer Singh
 */
public class Tank {

    private int totalCells;
    private int remainingCells;
    private ArrayList<Point> coords;
    private HashMap<Integer, Integer> damageWithCells;

    /**
     * Constructor for creating a siege Tank
     *
     * @param totalCells an int, total number of Tank cells
     * @param coords an ArrayList of Point, contains tank cells
     *                    co-ordinates using Point
     * @param damageWithCells a Map, containing information about damage
     *                        a tank can do corresponding to remainingCells
     * @throws Exception each tank cell must have a co-ordinate and damage output
     */
    Tank(int totalCells, ArrayList<Point> coords, HashMap<Integer, Integer> damageWithCells) throws Exception {
        this.totalCells = totalCells;
        this.remainingCells = totalCells;
        this.coords = new ArrayList<>();
        this.damageWithCells = new HashMap<>();

        // clone input cells co-ordinates in our class variable coords
        if (totalCells == coords.size()) {
            this.coords.addAll(coords);
        }
        else {
            throw new Exception("Total number of cells("+totalCells+") do not" +
                    " match with provided total number of cells co-ordinates("+
                    coords.size()+") ! ");
        }

        if (totalCells == damageWithCells.size()) {
            this.damageWithCells.putAll(damageWithCells);
        }
        else {
            throw new Exception("Total number of cells("+totalCells+") did not" +
                    " receive corresponding damage! \nDamage received for "+
                    damageWithCells.size()+" cells");
        }

    }

    public int getTotalCells() {
        return totalCells;
    }

    public ArrayList<Point> getCoords() {
        return (ArrayList<Point>) coords.clone();
    }

    public HashMap<Integer, Integer> getDamageWithCells() {
        HashMap<Integer, Integer> copy = new HashMap<>();
        copy.putAll(damageWithCells);
        return copy;
    }

    public boolean isStanding() {
        return (remainingCells > 0);
    }

    public int getRemainingCells() {
        return remainingCells;
    }

    /**
     * total damage this tank's shot is able to do given
     * it's current condition(available number of cells)
     *
     * @return an int, damage this tank's shot can do
     */
    public int getDamageOutput() {
        if (isStanding()) {
            if (damageWithCells.containsKey(remainingCells)) {
                return damageWithCells.get(remainingCells);
            }
        }
        return 0;
    }

    /**
     * destroys one cell of this Tank
     *
     * @param cellCoord a Point, co-ordinates of cell to be destroyed
     * @return true if cell is found and destroyed, false otherwise
     */
    public boolean destroyCell(Point cellCoord) {
        boolean done = false;
        for (Point p : coords) {
            if (p.equals(cellCoord)) {
                if (coords.remove(p)) {
                    remainingCells = remainingCells - 1;
                    done = true;
                    break;
                }
            }
        }
        return done;
    }
}
