/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.minesweeper;

/**
 * @author Sarah.Q, Engtieng.O
 * Reference: Horstmann, C, (2021). Core java, vol I: Foundamentals, 12th
 * edition. HorstMann, Cay. Addison-Wesley Professional. 
 * Description: The program will get the matrix size (rows * columns) and the 
 * number of mine. It randomly assign the position of each mine then the it will
 * ask for the player's input. It will reveal the number of mine surrounding the
 * player's guessed position. If there's no mine surrounding it, it will reveal
 * the number of mine near each neighbor. The player win if they reveal all the 
 * safe spot and lose if they land on a mine.
 */

import java.util.*;


public class MineSweeper {
    public static void main(String[] args) {
        
        //Run Driver Test
        MineSweeperDriver.main(null);
        
        //Get user inputs
        System.out.println("Welcome to the minesweeper game! Proudly developed by me! Yay!");
        System.out.print("Kindly provide the number of rows: ");
        Scanner scan = new Scanner(System.in);
        int rows = scan.nextInt();
        System.out.print("Kindly provide the number of columns: ");
        int cols = scan.nextInt();
        
        // Create the matrix rows * cols
        int[][] matrix = new int [rows][cols];
        // Mark all of them as not visited
        boolean[][] visited = new boolean [rows][cols];
        
        //Get the number of mine 
        int mineRange = (rows * cols) + 1;
        System.out.print("Kindly provide the number of mines to be randomly "
                + "placed in the game. Note that it should be smaller than " 
                + mineRange + " : ");
        int mineNum = scan.nextInt();
        Random rand = new Random();
        
        //Randomly assign the position for each mine
        int placedMines = 0;
        while (placedMines < mineNum){
            int ranRow = rand.nextInt(rows);
            int ranCol = rand.nextInt(cols);
            if (matrix[ranRow][ranCol] != -1) {
                matrix[ranRow][ranCol] = -1;
                placedMines++;
            }
        }
        
        //Numbers in matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == -1)
                    continue;
                int count = 0;
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {
                        int ni = i + x;
                        int nj = j + y;
                        if (ni >= 0 && ni < rows && nj >= 0 && nj < cols && 
                                matrix[ni][nj] == -1) {
                            count++;
                        }
                    }
                }
                matrix[i][j] = count;
            }
        }

        //Loop here
        int freePos = (rows * cols) - mineNum;
        int openedCount = 0;
        boolean mineExploded = false;
        //Main Game Loop
        while(!mineExploded) {
            //get user guessed position
            System.out.println("Current situation: there are still " + 
                    (freePos - openedCount)+ " free positions");
            System.out.println("What row and column would you guess?");
            System.out.print("Provide a row between 1 and " + rows +": ");
            int guessRow = scan.nextInt();
            System.out.print("Provide a col between 1 and " + cols +": ");
            int guessCol = scan.nextInt();

    int r = guessRow - 1;
    int c = guessCol - 1;
    
    //if the position was previously guessed, they need to provide a new postion
    if (visited[r][c]) {
        System.out.println("You've already revealed this cell. Try again!");
        continue;
    }
    
    //if mine is landed the game will be over
    if (matrix[r][c] == -1) {
        mineExploded = true;
        visited[r][c] = true;
    } else { //if not it will continue
        openSafe(matrix, visited, r, c);
        openedCount = countOpened(visited);
        printBoard(matrix, visited);
    }
    
    //if all free positions are guessed, the user win
    if(openedCount == freePos) {
                System.out.println("You win!!! You safely uncovered all positions.");
                OpenAllAndPrint(matrix);
                break;
            }
    //print the message for when the mine exploded
    if (mineExploded) {
        System.out.println("Hahaha :) the mine exploded ### !");
        System.out.println("Final board:");
        OpenAllAndPrint(matrix);
        break;
    }
    } 
}
    
    //Method for opening safe spot
    static void openSafe(int[][] matrix, boolean[][] visited, int r, int c) {
        if (r < 0 || r >= matrix.length || c < 0 || c >= matrix[0].length || visited[r][c])
            return;
        
        int rows = matrix.length;
        int cols = matrix[0].length;
           
        //Open the current cell
        visited[r][c] = true;
        
        if (matrix[r][c] == 0) {
            // recursively reveal neighbors if current cell has no adjacent mines
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int ni = r+i;
                    int nj = c+j;
                    
                    if(i == 0 && j == 0) continue;
                    
                    if (ni >= 0 && ni < rows && nj >= 0 && nj < cols) {
                        if (!visited[ni][nj] && matrix[ni][nj] != -1) {
                            visited[ni][nj]=true;
                        }
                    openSafe(matrix, visited, r + i, c + j);
                }
            }
        }
    }
    }
    
    //Counting the opended spot
    static int countOpened(boolean[][] visited) {
        int count = 0;
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[i].length; j++) {
                if (visited[i][j]) count++;
            }
        }
        return count;
    }

    // Method for priting the game board
    static void printBoard(int[][] matrix, boolean[][] visited){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (visited[i][j]) {
                    if (matrix[i][j] == -1) {
                        System.out.print("# ");
                    } else {
                        System.out.print(matrix[i][j] + " ");
                    }
                } else {
                    System.out.print("* ");
                    }
                }
            System.out.println();
            }
        }
    
    // Priting the final board revealing all the spots
    static void OpenAllAndPrint(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == -1) {
                    System.out.print("# ");
                } else {
                    System.out.print(matrix[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
