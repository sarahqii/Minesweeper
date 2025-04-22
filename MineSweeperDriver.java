/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.minesweeper;
import java.util.*;

/**
 * @author Sarah.Q, Engtieng.O
 * Description: Driver class for the MineSweeper game with 3 tests. 
 *  1. A 5x5 metric with 5 mines
 *  2. A 3X3 metric with 3 mines
 *  3. A 6X^ metric with 6 mines
 */


public class MineSweeperDriver {

    public static void main(String[] args) {
        System.out.println("===== Running Simulated MineSweeper Tests =====\n");

        // Run 3 different test cases
        runGame(1, 5, 5, 5,
            new int[][]{{0, 2}, {0, 3}, {0, 4}, {1, 2}, {4, 3}}, // mine positions
            new int[][]{{0, 0}, {4, 4}, {0, 4}, {4, 0}, {4, 2}, {3, 1}, {4, 3}} // moves
        );

        runGame(2, 3, 3, 3,
            new int[][]{{0, 0}, {1, 1}, {2, 2}}, // mine positions
            new int[][]{{0, 1}, {0, 2}, {2, 0}, {1, 2}, {2, 1}} // moves
        );

        runGame(3, 6, 6, 6,
            new int[][]{{0, 1}, {1, 2}, {2, 1}, {3, 3}}, // mine positions
            new int[][]{{0, 0}, {0, 3}, {1, 3}, {3, 0}, {3, 3}} // moves
        );
    }

    public static void runGame(int testNumber, int rows, int cols, int numMines, int[][] mines, int[][] moves) {
        System.out.println("=== Test Case " + testNumber + " ===\n");
        System.out.println("Welcome to the minesweeper game!");
        System.out.println("Proudly developed by me! Yay!");
        System.out.println("Kindly provide the number of rows: " + rows);
        System.out.println("Kindly provide the number of columns: " + cols);
        System.out.println("Kindly provide the number of mines to be randomly placed in the game. Note that it should be smaller than " + (rows * cols) + ": " + numMines);

        int[][] matrix = new int[rows][cols];
        boolean[][] visited = new boolean[rows][cols];

        // Place mines
        for (int[] pos : mines) {
            matrix[pos[0]][pos[1]] = -1;
        }

        // Generate hints
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == -1) continue;
                int count = 0;
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {
                        int ni = i + x, nj = j + y;
                        if (ni >= 0 && ni < rows && nj >= 0 && nj < cols && matrix[ni][nj] == -1) {
                            count++;
                        }
                    }
                }
                matrix[i][j] = count;
            }
        }

        int totalFree = rows * cols - numMines;
        int opened = 0;
        boolean exploded = false;

        for (int t = 0; t < moves.length; t++) {
            int r = moves[t][0];
            int c = moves[t][1];
            int remaining = totalFree - MineSweeper.countOpened(visited);

            System.out.println("Current situation: there are still " + remaining + " free positions.");
            System.out.println("What row and column would you guess?");
            System.out.println("Provide a row between 1 and " + rows + ": " + (r + 1));
            System.out.println("Provide a column between 1 and " + cols + ": " + (c + 1));

            if (matrix[r][c] == -1) {
                visited[r][c] = true;
                System.out.println("Hahaha :) the mine exploded ### !");
                MineSweeper.OpenAllAndPrint(matrix);
                exploded = true;
                break;
            } else {
                MineSweeper.openSafe(matrix, visited, r, c);
                MineSweeper.printBoard(matrix, visited);
            }

            opened = MineSweeper.countOpened(visited);
            if (opened == totalFree) {
                System.out.println("You win! All safe positions revealed.");
                break;
            }
        }

        if (!exploded && opened < totalFree) {
            System.out.println("Game ended before explosion, but not all positions revealed.");
            MineSweeper.OpenAllAndPrint(matrix);
        }

        System.out.println("\n=== End of Test " + testNumber + " ===\n");
    }
}