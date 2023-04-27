// Battle.java
package cwk4;

public class Battle {
        private final int Number;
        private final String Type;
        private final String Enemy;
        private final int Strength;
        private final int Losses;
        private final int Gains;

        public Battle(int Number, String Type, String Enemy, int Strength, int Losses, int Gains) {
            this.Number = Number;
            this.Type = Type;
            this.Enemy = Enemy;
            this.Strength = Strength;
            this.Losses = Losses;
            this.Gains = Gains;
        }

        // The rest of the Battle class implementation

    public int getNumber() {
        return Number;
    }

    public String getType() {
        return Type;
    }
    public String getEnemy() {
        return Enemy;
    }

    public int getStrength() {
        return Strength;
    }

    public int getLosses() {
        return Losses;
    }
    public int getGains() {
        return Gains;

    }
    public boolean isForceSuitable(Force force) {
        // Implement logic to determine if the given force is suitable for this battle
        return true; // Modify this line as needed
    }

    public boolean isForceStrongEnough(Force force) {
        // Implement logic to determine if the given force is strong enough for this battle
        return true; // Modify this line as needed
    }


    public String toString() {
        return "Battle [Number=" + Number + ", Type=" + Type + ", Enemy=" + Enemy + ", Strength=" + Strength + ",  Losses= "+ Losses +", Gains=" + Gains + "]";
    }
}
