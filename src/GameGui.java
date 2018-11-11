
import com.sun.glass.ui.Menu;

import java.util.Scanner;

public class GameGui {


    //Game GameClass = new Game();

    public static void main(String[] args) {
        map Map = new map();
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the Tank Game \n");

        System.out.println("Please select an option from the menu: ");
        System.out.println("Press 'S' to start game.");
        System.out.println("Press 'I' for Instructions" );

        String MenuChoice;
        MenuChoice = input.next().toLowerCase();
        /*if (!MenuChoice.equals("s") || !MenuChoice.equals("i")) {
            System.out.print("Invalid Error, please re-enter an option");
        } else {*/
            switch (MenuChoice) {


                case "s":
                    System.out.println("Here is your map");
                    Map.createUserGrid();
                    Map.createBackgroundGrid();
                    Map.randomTank1();
                    //Map.ShowBackgroundMap();
                    Map.makeMove();
                    //System.out.println("To fire your weapon, enter the coordinates");
                   // Map.writingCoordinates();
                    break;
                case "i":
                    System.out.println("B selected");

                default:
                    System.out.println("invalid option");
            }

        }
    }

