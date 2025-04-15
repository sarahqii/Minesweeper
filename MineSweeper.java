/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.minesweeper;
import java.util.*;
/**
 *
 * @author qisarah
 */
public class MineSweeper {

    public static void main(String[] args) {
        System.out.println("Welcome to the minesweeper game! Proudly developed by me! Yay!");
        System.out.print("Kindly provide the number of rows: ");
        Scanner scan = new Scanner(System.in);
        int rows = scan.nextInt();
        System.out.print("Kindly provide the number of columns: ");
        int cols = scan.nextInt();
        int[][] matrix = new int [rows][cols];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        int mineRange = (rows * cols) + 1;
        System.out.print("Kindly provide the number of mines to be randomly placed in the game. Note that it should be smaller than " + mineRange + " : ");
        int mineNum = scan.nextInt();
        Random rand = new Random();
//        int ranRow = rand.nextInt(rows-1);
//        int ranCol = rand.nextInt(cols-1);
        for (int i = 0; i < mineNum; i++){
            int ranRow = rand.nextInt(rows-1);
            int ranCol = rand.nextInt(cols-1);
            matrix[ranRow][ranCol] = -1;
        }
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        
        int checkedPos = 0;
        int freePos = rows*cols - mineNum - checkedPos;
        
        System.out.println("Current situation: ther are still " + freePos + " free positions");
        System.out.println("What row and column would you guess?");
        System.out.print("Provide a row between 1 and " + rows +" :");
        int guessRow = scan.nextInt();
        System.out.print("Provide a col between 1 and " + cols +" :");
        int guessCol = scan.nextInt();
        
        if (matrix[guessRow-1][guessCol-1] == -1) {
            System.out.println("Hahaha :D the mine exploded ### !");
        } // check if it's not the top row
        else if (guessRow > 1) {
            //top
            //check if top has a mine
        }
        
    }
    
//    static boolean isHasMine(int [][] arr, int r, int c){
//        return arr[r-1][c-1] == -1;
//    }
}
