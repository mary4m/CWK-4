package cwk4;

import java.util.Scanner;

/**
 * Provide a command line user interface
 * 
 * @author A.A.Marczyk
 * @version 06/10/23
 */
public class GameUI
{
    private final Scanner myIn = new Scanner(System.in);

    private void playGame()
    {
        int choice;
        String admiralName;
        int result = -1;
        System.out.println("Enter admiral's name");
        String s = myIn.nextLine();
        SpaceWars gp = new SpaceWars(s);
        choice = 100;
        while (choice != 0 )
        {
            choice = getMenuItem();
            if (choice == 1)  //All forces
            {
                System.out.println(gp.getAllForces());
            }
            else if (choice == 2) //List all battles
            {
                System.out.println(gp.getAllBattles());
            }
            else if (choice == 3) //get Force
            {
                System.out.println("Enter Force reference");
                myIn.nextLine();
                String ref = (myIn.nextLine()).trim();
                System.out.println(gp.getForceDetails(ref));
            } 
            else if (choice == 4) //activate Force
            {
                System.out.println("Enter Force reference");
                myIn.nextLine();
                String ref = (myIn.nextLine()).trim();
                int activationResult = gp.activateForce(ref);
                System.out.println(activation(activationResult));

            }
            else if (choice == 5) //List ASFleet
            {
                System.out.println(gp.getASFleet());
            }
            else if (choice == 6) //engage in a battle
            {

                System.out.println("Enter Force reference to engage in a battle");
                myIn.nextLine();
                String ref = (myIn.nextLine()).trim();
                String battleResult = gp.Battle(ref);
                System.out.println(battleResult);
            }
            
            else if (choice == 7) //recall force
            {
                System.out.println("Enter Force reference to recall");
                myIn.nextLine();
                String ref = (myIn.nextLine()).trim();
                boolean recallResult = gp.recallForce(ref);
                if (recallResult) {
                    System.out.println("Force has been recalled.");
                } else {
                    System.out.println("Failed to recall force.");
                }
            }
            else if (choice==8) //view game state
            {
                System.out.println(gp);
            }
                // Uncomment after task 3.5
             else if (choice == 9) // Task 3.5 only
             {
                 System.out.println("Write to file");
                 gp.saveGame("olenka.txt");
             }
             else if (choice == 10) // Task 3.5 only
             {
                 System.out.println("Restore from file");
                 gp = gp.restoreGame("olenka.txt");
                 System.out.println(gp.toString());
             }
        }  
        System.out.println("Thank-you");
    }
    
    private int getMenuItem()
    {   
        int choice = 100;  
        System.out.println("Main Menu");
        System.out.println("0. Quit");
        System.out.println("1. List all forces");
        System.out.println("2. List all battles"); 
        System.out.println("3. View details of a force");
        System.out.println("4. Activate a force into the Active Star  fleet");
        System.out.println("5. List forces in Active Star Fleet");
        System.out.println("6. Engage in a battle");
        System.out.println("7. Recall a force");
        System.out.println("8. View the state of the game");

        //For Task 3.5 only
         System.out.println("9. Save this game");
         System.out.println("10. Restore a game");
       
        
        while (choice < 0 || choice  > 10)
        {
            System.out.println("Enter the number of your choice");
            choice =  myIn.nextInt();
        }
        return choice;        
    } 
    
    private String activation(int code)
    {
        return switch (code) {
            case 0 -> "force is activated";
            case 1 -> "force is not in the UFFDock";
            case 2 -> "not enough money";
            case 3 -> "no such force";
            default -> "Error";
        };
    }
    

    
    public static void main(String[] args)
    {
        GameUI myGame = new GameUI();
        myGame.playGame();
    }
}