/**
 * This is player's fortress with certain strength.
 *
 * @author Harshveer Singh
 */
public class Fortress {

    private int strength;

    Fortress(int strength) {
        this.strength = strength;
    }

    public boolean isStanding() {
        return (strength > 0);
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void subtractStrength(int strength) {
        this.strength = this.strength - strength;
    }

    public void addStrength(int strength) {
        this.strength = this.strength + strength;
    }

}
