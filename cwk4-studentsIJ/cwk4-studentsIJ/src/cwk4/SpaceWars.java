package cwk4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;
import java.io.FileInputStream;
import java.io.ObjectInputStream;


public class SpaceWars implements WIN {
    private final String admiral;
    private int warChest;
    private List<Force> UFFDock;
    private final List<Force> ASFFleet;
    private final List<Force> destroyedForces;
    private List<Battle> battles;

    public SpaceWars(String admiral) {
        this.admiral = admiral;
        this.warChest = 1000; // Set initial war chest value
        this.UFFDock = new ArrayList<>();
        this.ASFFleet = new ArrayList<>();
        this.destroyedForces = new ArrayList<>();
        this.battles = new ArrayList<>();

        setupForces();
        setupBattles();

    }

    public String toString() {
        StringBuilder s = new StringBuilder("Admiral: " + admiral + "\n");
        s.append("War Chest: ").append(warChest).append("\n");
        s.append("Defeated: ").append(isDefeated() ? "Yes" : "No").append("\n");
        s.append("Active Star Fleet Forces: ").append(ASFFleet.isEmpty() ? "No forces" : "").append("\n");
        for (Force f : ASFFleet) {
            s.append(f.toString()).append("\n");
        }
        return s.toString();
    }

    public boolean isDefeated() {

        return warChest <= 0 && ASFFleet.isEmpty();
    }

    public int getWarchest() {

        return warChest;
    }

    public String getAllForces() {
        return getASFleet() + "\n" + getForcesInDock() + "\n" + getDestroyedForces();
    }

    public boolean isInUFFDock(String ref) {

        return UFFDock.stream().anyMatch(force -> force.getRef().equals(ref));
    }

    public String getForcesInDock() {
        StringBuilder s = new StringBuilder("\n\n************ Forces available in UFFleet Dock********\n");
        for (Force f : UFFDock) {
            s.append(f.toString()).append("\n");
        }
        return s.toString();
    }

    public String getDestroyedForces() {
        StringBuilder s = new StringBuilder("\n***** Destroyed Forces ****\n");
        for (Force f : destroyedForces) {
            s.append(f.toString()).append("\n");
        }
        return s.toString();
    }

    public String getForceDetails(String ref) {
        for (Force f : UFFDock) {
            if (f.getRef().equals(ref)) {
                return f.toString();
            }
        }
        for (Force f : ASFFleet) {
            if (f.getRef().equals(ref)) {
                return f.toString();
            }
        }
        return "\nNo such force";
    }

    public int activateForce(String ref) {
        Force force = UFFDock.stream().filter(f -> f.getRef().equals(ref)).findFirst().orElse(null);
        if (force == null) {
            return -1;
        }

        int activationFee = force.getActivationFee();
        if (warChest < activationFee) {
            return 2;
        }

        UFFDock.remove(force);
        ASFFleet.add(force);
        warChest -= activationFee;
        return 0;
    }

    public boolean isInASFleet(String ref) {

        return ASFFleet.stream().anyMatch(force -> force.getRef().equals(ref));
    }

    public String getASFleet() {
        StringBuilder s = new StringBuilder("\n****** Forces in the Active Star Fleet******\n");
        for (Force f : ASFFleet) {
            s.append(f.toString()).append("\n");
        }
        return s.toString();
    }

    public boolean recallForce(String ref) {
        Force force = ASFFleet.stream().filter(f -> f.getRef().equals(ref)).findFirst().orElse(null);
        if (force != null) {
            ASFFleet.remove(force);
            UFFDock.add(force);
        }
        return false;
    }

    public boolean isBattle(int num) {
        return num >= 0 && num < battles.size();
    }

    public String getBattle(int num) {
        if (isBattle(num)) {
            return battles.get(num).toString();
        }
        return "No such battle";
    }

    public String getAllBattles() {
        StringBuilder s = new StringBuilder("\n************ All Battles ************\n");
        for (Battle b : battles) {
            s.append(b.toString()).append("\n");
        }

        final String fname = "battles.txt";

        try {
            FileInputStream myFileObject = new FileInputStream(fname);
            BufferedReader reader = new BufferedReader(new InputStreamReader(myFileObject));

            String battle;
            while ((battle = reader.readLine()) != null) {
                System.out.println(battle);
            }

            reader.close();
        } catch (Exception ex) {
            System.out.println("File read error");

        }
        return s.toString();
    }


    public int doBattle(int battleNo) {
        if (!isBattle(battleNo)) {
            return -1;
        }
            Battle battle = battles.get(battleNo);
            int battleNumber = battle.getNumber();
            int battleStrength = battle.getStrength();
            String battleType = battle.getType();
            String battleEnemy = battle.getEnemy();


        //Battle battle = battles.get(battleNo);
        Force suitableForce = null;
        for (Force f : ASFFleet) {
            if (battle.isForceSuitable(f)) {
                suitableForce = f;
                break;
            }
        }

        if (suitableForce == null) {
            warChest -= battle.getLosses();
            return 1;
        }

        if (battle.isForceStrongEnough(suitableForce)) {
            warChest += battle.getGains();
            return 0;
        } else {
            warChest -= battle.getLosses();
            ASFFleet.remove(suitableForce);
            destroyedForces.add(suitableForce);
            if (isDefeated()) {
                return 3;
            }
            return 2;
        }
    }

    private void setupForces() {
        // Add code to initialize forces and add them to the UFFDock
        UFFDock = new ArrayList<>();

        Force force1 = new Force("F1", "Wings", 100, 50);
        Force force2 = new Force("F2", "Starship", 150, 70);
        Force force3 = new Force("F3", "WarBird", 200, 100);
        UFFDock.add(force1);
        UFFDock.add(force2);
        UFFDock.add(force3);
    }



    private void setupBattles() {
        // Add code to initialize battles and add them to the battles list
        battles = new ArrayList<>();

            Battle battle1 = new Battle(1, "Type1", "Enemy1", 100, 20, 50);
            Battle battle2 = new Battle(2, "Type2", "Enemy2", 150, 30, 60);
            Battle battle3 = new Battle(3, "Type3", "Enemy3", 200, 40, 70);

            battles.add(battle1);
            battles.add(battle2);
            battles.add(battle3);
    }


    public void saveGame(String fname) {
        // Add code to save the game using object serialization
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fname);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fname);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + fname);
        }
    }


    public SpaceWars restoreGame(String fname) {
        // Add code to restore the game using object serialization
        SpaceWars restoredGame = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(fname);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            restoredGame = (SpaceWars) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fname);
        } catch (IOException e) {
            System.err.println("Error reading from file: " + fname);
        } catch (ClassNotFoundException e) {
            System.err.println("SpaceWars class not found");
        }
        return restoredGame;

    }

   // @Override
    public String Battle(String ref) {
        return "Battle";
    }


    private void readBattles() {
        String fname = "battles.txt";
        battles = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fname));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int Number = Integer.parseInt(parts[0].trim());
                String Type = parts[1].trim();
                String Enemy = parts[2].trim();
                int Strength = Integer.parseInt(parts[3].trim());
                int Losses = Integer.parseInt(parts[4].trim());
                int Gains = Integer.parseInt(parts[5].trim());


                Battle battle = new Battle(Number, Type, Enemy, Strength, Losses, Gains);
                battles.add(battle);
            }

            reader.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fname);
        } catch (IOException e) {
            System.err.println("Error reading from file: " + fname);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing number from file: " + fname);
        }
    }


}