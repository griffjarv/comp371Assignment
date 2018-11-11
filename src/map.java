import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class map {
    public String[][] grid = new String[10][10];
    public String[][] userGrid = new String[10][10];
    public ArrayList<Integer> coors = new ArrayList<Integer>();
    public int fortressDamage = 1500;


    //creating grid only the user sees
    public void createUserGrid() {
        String[] alpha = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};
        System.out.println("Game Map");
        System.out.println("  0 1 2 3 4 5 6 7 8 9");
        for (int i = 0; i < userGrid.length; i++) {
            System.out.print(alpha[i] + " ");
            for (int j = 0; j < userGrid[i].length; j++) {
                userGrid[i][j] = "~";
                System.out.print(userGrid[i][j] + " ");
            }
            System.out.println();
        }
    }

    //create grid for the tanks to be on
    public void createBackgroundGrid() {

        String[] alpha = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};
        // System.out.println("Game Map");
        // System.out.println("  0 1 2 3 4 5 6 7 8 9");
        for (int i = 0; i < grid.length; i++) {
            //System.out.print(alpha[i] + " ");
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = "~";
                // System.out.print(grid[i][j] + " ");
            }
            // System.out.println();
        }
    }

    //hitting a tank
    //updating the map
    public void HitTank(int row, int col) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[row][col] = "X";
                userGrid[row][col] = "X";
            }
        }

        for (int i = 0; i < userGrid.length; i++) {
            for (int j = 0; j < userGrid[i].length; j++) {
                userGrid[row][col] = "X";
            }
        }

    }

    //shows updated map to the user
    public void showUpdatedMap() {

        System.out.println("Game Map");
        System.out.println("  A B C D E F G H I J");
        for (int i = 0; i < userGrid.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < userGrid[i].length; j++) {
                System.out.print(userGrid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("end of show updated map");

        /*
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("end of show updated map");
        */
    }

    //*IGNORE*
    public void ShowBackgroundMap() {
        System.out.println("Game Map");
        System.out.println("  A B C D E F G H I J");
        for (int i = 0; i < grid.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("end of show background map");
    }

    //updating map for missing a tank
    public void missedTank(int row, int col) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[row][col] = ".";
            }
        }

        for (int i = 0; i < userGrid.length; i++) {
            for (int j = 0; j < userGrid[i].length; j++) {
                userGrid[row][col] = ".";
            }
        }
    }

    //user entering coordinates
    public void writingCoordinates() {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter shot in form <Letter><Number> e.g. B8");
        String nums = input.next();
        String[] parts = nums.split("");
        String row = parts[0];
        String col = parts[1];
        int li = Character.getNumericValue(parts[0].charAt(0));
        li = li - 10;
        int lp = Integer.parseInt(col);


        if (CheckIfTank1IsHit(lp, li)) {
            HitTank(lp, li);
        } else if (CheckIfTank2IsHit(lp, li)) {
            HitTank(lp, li);
        } else if (CheckIfTank3IsHit(lp, li)) {
            HitTank(lp, li);
        } else if (CheckIfTank4IsHit(lp, li)) {
            HitTank(lp, li);
        } else if (CheckIfTank5IsHit(lp, li)) {
            HitTank(lp, li);
        } else {
            missedTank(lp, li);
        }
    }

    //placing random tanks on the map
    public void randomTank1() {
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


        //writing Tank T1
        grid[row][col] = "T1";
        grid[row + 1][col] = "T1";
        grid[row][col + 1] = "T1";
        grid[row - 1][col] = "T1";

        //writing secondTank
        grid[row2][col2] = "T2";
        grid[row2 + 1][col2] = "T2";
        grid[row2][col2 + 1] = "T2";
        grid[row2 - 1][col2] = "T2";

        //writing thirdTank
        grid[row3][col3] = "T3";
        grid[row3 + 1][col3] = "T3";
        grid[row3][col3 + 1] = "T3";
        grid[row3 - 1][col3] = "T3";

        //writing fourthTank
        grid[row4][col4] = "T4";
        grid[row4 + 1][col4] = "T4";
        grid[row4][col4 + 1] = "T4";
        grid[row4 - 1][col4] = "T4";

        //writing fifthTank
        grid[row5][col5] = "T5";
        grid[row5 + 1][col5] = "T5";
        grid[row5][col5 + 1] = "T5";
        grid[row5 - 1][col5] = "T5";

    }
/*
    public void getCoords() {
        int x, i;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j].equals("T")) {


                }
            }
        }

System.out.println(coors);
    }
*/

//checks if the coordinates entered hit Tank 1
    public boolean CheckIfTank1IsHit(int row, int col) {

        if (grid[row][col].equals("T1")) {
            System.out.println("YOU HIT TANK 1!");
            return true;
        } else {
            System.out.println("Missed Tank 1");
            return false;
        }
    }
    //checks if the coordinates entered hit Tank 2
    public boolean CheckIfTank2IsHit(int row, int col) {

        if (grid[row][col].equals("T2")) {
            System.out.println("YOU HIT TANK 2");
            return true;
        } else {
            System.out.println("Missed Tank 2");
            return false;
        }
    }

    //checks if the coordinates entered hit Tank 3
    public boolean CheckIfTank3IsHit(int row, int col) {

        if (grid[row][col].equals("T3")) {
            System.out.println("YOU HIT TANK 3");
            return true;
        } else {
            System.out.println("Missed Tank 3");
            return false;
        }
    }

    //checks if the coordinates entered hit Tank 4
    public boolean CheckIfTank4IsHit(int row, int col) {

        if (grid[row][col].equals("T4")) {
            System.out.println("YOU HIT TANK 4");
            return true;
        } else {
            System.out.println("Missed Tank 4");
            return false;
        }
    }

    //checks if the coordinates entered hit Tank 5
    public boolean CheckIfTank5IsHit(int row, int col) {

        if (grid[row][col].equals("T5")) {
            System.out.println("YOU HIT TANK 5");
            return true;
        } else {
            System.out.println("Missed Tank 5");
            return false;
        }
    }


    //showing map and accepting coordinates
    public void GamePlay() {

        showUpdatedMap();
        writingCoordinates();
    }

    //public char UpdateMapChar(char x, int row, int col){


    /*
        public int Damage(){
        if(CheckIfTank1IsHit()
        }
    */

    //Tank 1 Attacks
    public void EnemyTank1Attack() {
        int counter = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j].equals("T1")) {
                    counter++;
                }
            }
        }
        if (counter == 4) {
            fortressDamage = fortressDamage - 20;
            System.out.println("Fortress Damage is " +fortressDamage);
        } else if (counter == 3) {
            fortressDamage = fortressDamage - 5;
            System.out.println("Fortress Damage is " + fortressDamage);
        } else if (counter == 2) {
            fortressDamage = fortressDamage - 2;
            System.out.println("Fortress Damaage is " + fortressDamage);
        } else if (counter == 1) {
            fortressDamage = fortressDamage - 1;
            System.out.println("Fortress Damage is " + fortressDamage);
        } else if (counter == 0) {
            fortressDamage = fortressDamage - 0;
            System.out.println("Fortress Damage is " + fortressDamage);
        }
    }

    //Tank 2 Attacks
    public void EnemyTank2Attack() {
        int counter = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j].equals("T2")) {
                    counter++;
                }
            }
        }
        if (counter == 4) {
            fortressDamage = fortressDamage - 20;
            System.out.println("TANK 2 HIT YOUR FORTRESS.");
            System.out.println("FORTRESS HEALTH IS: " +fortressDamage);
        } else if (counter == 3) {
            fortressDamage = fortressDamage - 5;
            System.out.println("TANK 2 HIT YOUR FORTRESS.");
            System.out.println("FORTRESS HEALTH IS: " +fortressDamage);
        } else if (counter == 2) {
            fortressDamage = fortressDamage - 2;
            System.out.println("TANK 2 HIT YOUR FORTRESS.");
            System.out.println("FORTRESS HEALTH IS: " +fortressDamage);
        } else if (counter == 1) {
            fortressDamage = fortressDamage - 1;
            System.out.println("TANK 2 HIT YOUR FORTRESS.");
            System.out.println("FORTRESS HEALTH IS: " +fortressDamage);
        } else if (counter == 0) {
            fortressDamage = fortressDamage - 0;
            System.out.println("TANK 2 HIT YOUR FORTRESS.");
            System.out.println("FORTRESS HEALTH IS: " +fortressDamage);
        }
    }

    //Tank 3 Attacks
    public void EnemyTank3Attack() {
        int counter = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j].equals("T3")) {
                    counter++;
                }
            }
        }
        if (counter == 4) {
            fortressDamage = fortressDamage - 20;
            System.out.println("TANK 3 HIT YOUR FORTRESS.");
            System.out.println("FORTRESS HEALTH IS: " +fortressDamage);
        } else if (counter == 3) {
            fortressDamage = fortressDamage - 5;
            System.out.println("TANK 3 HIT YOUR FORTRESS.");
            System.out.println("FORTRESS HEALTH IS: " +fortressDamage);
        } else if (counter == 2) {
            fortressDamage = fortressDamage - 2;
            System.out.println("TANK 3 HIT YOUR FORTRESS.");
            System.out.println("FORTRESS HEALTH IS: " +fortressDamage);
        } else if (counter == 1) {
            fortressDamage = fortressDamage - 1;
            System.out.println("TANK 3 HIT YOUR FORTRESS.");
            System.out.println("FORTRESS HEALTH IS: " +fortressDamage);
        } else if (counter == 0) {
            fortressDamage = fortressDamage - 0;
            System.out.println("TANK 3 HIT YOUR FORTRESS.");
            System.out.println("FORTRESS HEALTH IS: " +fortressDamage);
        }
    }

    //Tank 4 Attacks
    public void EnemyTank4Attack() {
        int counter = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j].equals("T4")) {
                    counter++;
                }
            }
        }
        if (counter == 4) {
            fortressDamage = fortressDamage - 20;
            System.out.println("TANK 4 HIT YOUR FORTRESS.");
            System.out.println("FORTRESS HEALTH IS: " +fortressDamage);
        } else if (counter == 3) {
            fortressDamage = fortressDamage - 5;
            System.out.println("TANK 4 HIT YOUR FORTRESS.");
            System.out.println("FORTRESS HEALTH IS: " +fortressDamage);
        } else if (counter == 2) {
            fortressDamage = fortressDamage - 2;
            System.out.println("TANK 4 HIT YOUR FORTRESS.");
            System.out.println("FORTRESS HEALTH IS: " +fortressDamage);
        } else if (counter == 1) {
            fortressDamage = fortressDamage - 1;
            System.out.println("TANK 4 HIT YOUR FORTRESS.");
            System.out.println("FORTRESS HEALTH IS: " +fortressDamage);
        } else if (counter == 0) {
            fortressDamage = fortressDamage - 0;
            System.out.println("TANK 4 HIT YOUR FORTRESS.");
            System.out.println("FORTRESS HEALTH IS: " +fortressDamage);
        }
    }

    //Tank 5 Attacks
    public void EnemyTank5Attack() {
        int counter = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j].equals("T5")) {
                    counter++;
                }
            }
        }
        System.out.println(counter);
        if (counter == 4) {
            fortressDamage = fortressDamage - 20;
            System.out.println("TANK 5 HIT YOUR FORTRESS. ");
            System.out.println("FORTRESS HEALTH IS: " +fortressDamage);
        } else if (counter == 3) {
            fortressDamage = fortressDamage - 5;
            System.out.println("TANK 5 HIT YOUR FORTRESS. ");
            System.out.println("FORTRESS HEALTH IS: " +fortressDamage +"\n ");
        } else if (counter == 2) {
            fortressDamage = fortressDamage - 2;
            System.out.println("TANK 5 HIT YOUR FORTRESS.");
            System.out.println("FORTRESS HEALTH IS: " +fortressDamage);
        } else if (counter == 1) {
            fortressDamage = fortressDamage - 1;
            System.out.println("TANK 5 HIT YOUR FORTRESS.");
            System.out.println("FORTRESS HEALTH IS: " +fortressDamage);
        } else if (counter == 0) {
            fortressDamage = fortressDamage - 0;
            System.out.println("TANK 5 HIT YOUR FORTRESS.");
            System.out.println("FORTRESS HEALTH IS: " +fortressDamage);
        }
    }

    //Check if game is finished
    public boolean isGameFinished() {
        if (fortressDamage < 0 || checkIfTanksAreDestroyed()) {
            return true;
        } else {
            return false;
        }
    }

    //order of how the functions
    public void makeMove() {
        while (!isGameFinished()) {
            GamePlay();
            System.out.println("Enemy is firing.");
            EnemyTank1Attack();
            EnemyTank2Attack();
            EnemyTank3Attack();
            EnemyTank4Attack();
            EnemyTank5Attack();
        }
        }



        //Checks if tanks are destroyed
    public boolean checkIfTanksAreDestroyed() {
        int answer = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (!grid[i][j].equals("T1") && !grid[i][j].equals("T2") && !grid[i][j].equals("T3") && !grid[i][j].equals("T4") && !grid[i][j].equals("T5")) {
                    answer = 1;
                } else {
                    answer = 0;
                }
            }
        }
        if(answer ==  0){
            return true;
        } else {
            return false;
        }
    }
}