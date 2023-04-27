
package cwk4;

public class Starship {
    private String reference;
    private int strength;

    public Starship(String reference, int strength) {
        this.reference = reference;
        this.strength = strength;
    }

    public String getReference() {
        return reference;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    @Override
    public String toString() {
        return "Starship [reference=" + reference + ", strength=" + strength + "]";
    }
}
