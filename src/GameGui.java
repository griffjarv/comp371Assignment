import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.HashMap;
public class GameGui {


    //Game GameClass = new Game();

    private final int FORTRESSSTRENGTH = 1500;
    private final int GRIDSIZE = 10;

    private final int TOTALNUMBEROFCELLS = 4;
    private final int TOTALNUMBEROFTANKS = 5;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the Tank Game \n");

        System.out.println("Please select an option from the menu: ");
        System.out.println("Press 'S' to start game.");
        System.out.println("Press 'I' for Instructions" );



        HashMap<Integer, Integer> damageWithCells = new HashMap<>();
        damageWithCells.put(1, 1);
        damageWithCells.put(2, 2);
        damageWithCells.put(3, 5);
        damageWithCells.put(4, 20);

        Game FortressGame = new Game(FORTRESSSTRENGTH, GRIDSIZE);

        Random rand = new Random();
        int low = 1;
        int high = 4;
        int low2 = 6;
        int high2 = 8;
        int high3 = 8;
        int low3 = 6;
        int high4 = 3;
        int high5 = 5;
        int row = rand.nextInt(high - low) + low;
        int col = rand.nextInt(high - low) + low;
        int row2 = rand.nextInt(high - low) + low;
        int col2 = rand.nextInt(high2 - low2) + low2;
        int row3 = rand.nextInt(high2 - low2) + low2;
        int col3 = rand.nextInt(high4 - low) + low;
        int row4 = rand.nextInt(high3 + 1 - low3) + low3;
        int col4 = rand.nextInt(low2 + 1 - low3) + low3;
        int row5 = rand.nextInt(high3 - low3) + low3;
        int col5 = rand.nextInt(high5 - high) + high;

        ArrayList<Point> coords1 = new ArrayList<>();
        add4Points(row, col, coords1);

        ArrayList<Point> coords2 = new ArrayList<>();
        add4Points(row2, col2, coords2);

        ArrayList<Point> coords3 = new ArrayList<>();
        add4Points(row3, col3, coords3);

        ArrayList<Point> coords4 = new ArrayList<>();
        add4Points(row4, col4, coords4);

        ArrayList<Point> coords5 = new ArrayList<>();
        add4Points(row5, col5, coords5);

        ArrayList<ArrayList<Point>> coordsList = new ArrayList<>();
        coordsList.add(coords1);
        coordsList.add(coords2);
        coordsList.add(coords3);
        coordsList.add(coords4);
        coordsList.add(coords5);


        for(int i = 0; i< TOTALNUMBEROFTANKS; i++) {

           ArrayList<Point> coords = coordsList.get(i);
           try {
               FortressGame.addSiegeTank(TOTALNUMBEROFCELLS, coords, damageWithCells);
           }
           catch (Exception e) {
               System.out.println(e.getMessage());
           }

        }
        //creates grid of FOG
        for (int i = 0; i< GRIDSIZE; i++){
            for (int j = 0; j< GRIDSIZE; j++){
                FortressGame.getGameMap().setCharAt(i, j, FortressGame.FOG);
            }
        }



        //COLUMN AND ROW HEADERS
        String [] alpha = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};
        String numbers = "  0 1 2 3 4 5 6 7 8 9";






        public void createGrid() {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    grid[i][j] = "~";
                    System.out.print(grid[i][j] + " ");
                }
                System.out.println();
            }
        }

        String MenuChoice;
        MenuChoice = input.next().toLowerCase();
        /*if (!MenuChoice.equals("s") || !MenuChoice.equals("i")) {
            System.out.print("Invalid Error, please re-enter an option");
        } else {*/
            switch (MenuChoice) {


                case "s":
                    System.out.println("Here is your map");

                    System.out.println("Game Map");

                    while(!FortressGame.isGameFinished()) {

                        System.out.println( numbers);
                        for (int i = 0; i < GRIDSIZE; i++) {
                            System.out.print(alpha[i] + " ");
                            for (int j = 0; j < GRIDSIZE; j++) {
                                System.out.print(FortressGame.getGameMap().getCharAt(i, j));
                            }
                        }



                        boolean validInput = false;

                        while (validInput) {
                            System.out.println("Enter shot in form <Letter><Number> e.g. B8");
                            String nums = input.next();

                            try{
                                if (nums.length() != 2) {
                                    throw new Exception("Invalid input");
                                }
                                String[] parts = nums.split("");
                                int li = Character.getNumericValue(parts[0].charAt(0));
                                li = li - 10;
                                int lp = Integer.parseInt(parts[1]);
                                validInput = true;

                            }
                            catch (Exception e) {
                              System.out.println(e.getMessage());
                            }
                        }


                        if (FortressGame.makePlayerMove(new Point(li, lp))) {
                            System.out.println("You hit a tank");
                        } else {
                            System.out.println("You missed your shot");

                        }
                        //computer
                        FortressGame.makeEnemyMove();
                        System.out.println("Your Fortress remaining health is " + FortressGame.getFortressStrength());


                    }
                   if (isPlayerWin()){
                       System.out.println("YOU WIN");
                   } else {
                       System.out.println("YOUR FORTRESS WAS DESTROYED");
                   }

                    System.out.println( numbers);
                    for (int i = 0; i < GRIDSIZE; i++) {
                        System.out.print(alpha[i] + " ");
                        for (int j = 0; j < GRIDSIZE; j++) {
                            System.out.print(FortressGame.getFinalMap().getCharAt(i, j));
                        }
                    }
                    break;
                case "i":
                    System.out.println("");

                default:
                    System.out.println("invalid option");
            }

        }

    private static void add4Points(int row, int col, ArrayList<Point> coords1) {
        coords1.add(new Point(row, col));
        coords1.add(new Point(row +1, col));
        coords1.add(new Point(row, col +1));
        coords1.add(new Point(row-1, col));
    }


}

