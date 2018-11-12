import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Grid {

    public String[][] grid;

    Grid(int size){
        this.grid = new String[size][size];
    }

    public String getCharAt(int row, int col){
        return grid[row][col];
    }


    public void setCharAt(int row, int col, String input){
        grid[row][col] = input;
    }


}
