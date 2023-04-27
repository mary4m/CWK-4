
// Force.java
package cwk4;

public class Force {
    private final String ref;
    private final String name;
    private final int strength;
    private final int activationFee;

    public Force(String ref, String name, int strength, int activationFee) {
        this.ref = ref;
        this.name = name;
        this.strength = strength;
        this.activationFee = activationFee;
    }

    public String getRef() {
        return ref;
    }

    public String getName() {
        return name;
    }

    public int getStrength() {
        return strength;
    }

    public int getActivationFee() {
        return activationFee;
    }

    @Override
    public String toString() {
        return "Force [ref=" + ref + ", name=" + name + ", strength=" + strength + ", activationFee=" + activationFee + "]";
    }
}
