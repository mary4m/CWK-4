package cwk4;

public class WarBird {
    private String reference;
    private int strength;

    public WarBird(String reference, int strength) {
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
        return "WarBird [reference=" + reference + ", strength=" + strength + "]";
    }
}
