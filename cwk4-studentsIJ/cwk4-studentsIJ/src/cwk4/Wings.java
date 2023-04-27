

package cwk4;

public class Wings {
    private String reference;
    private int strength;

    public Wings(String reference, int strength) {
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
        return "Wings [reference=" + reference + ", strength=" + strength + "]";
    }
}
