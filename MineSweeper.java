import java.util.*;
public class MineSweeper {
    public static void main(String[] args) {
        System.out.println("Welcome to the minesweeper game! Proudly developed by me! Yay!");
        System.out.print("Kindly provide the number of rows: ");
        Scanner scan = new Scanner(System.in);
        int rows = scan.nextInt();
        System.out.print("Kindly provide the number of columns: ");
        int cols = scan.nextInt();
        int[][] matrix = new int [rows][cols];
        boolean[][] visited = new boolean [rows][cols];
        
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
        
        //Randomly assign the position for each mine
        int placedMines = 0;
        while (placedMines < mineNum){
            int ranRow = rand.nextInt(rows - 1);
            int ranCol = rand.nextInt(cols - 1);
            if (matrix[ranRow][ranCol] != -1) {
                matrix[ranRow][ranCol] = -1;
                placedMines++;
            }
        }
        //Numbers in matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == -1)continue;
                int count = 0;
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {
                        int ni = i + x;
                        int nj = j + y;
                        if (ni >= 0 && ni < rows && nj >= 0 && nj < cols && matrix[ni][nj] == -1) {
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
            //get user inputs
            System.out.println("Current situation: there are still " + (freePos - openedCount)+ " free positions");
            System.out.println("What row and column would you guess?");
            System.out.print("Provide a row between 1 and " + rows +" :");
            int guessRow = scan.nextInt();
            System.out.print("Provide a col between 1 and " + cols +" :");
            int guessCol = scan.nextInt();
            
            if (matrix[guessRow-1][guessCol-1] == -1) {
                mineExploded = true;
            }
        
        
        System.out.println("Hahaha :) the mine exploded ### !");

    int r = guessRow - 1;
    int c = guessCol - 1;


    if (matrix[r][c] == -1) {
        mineExploded = true;
        visited[r][c] = true;
    } else {
        openSafe(matrix, visited, r, c);
        openedCount = countOpened(visited);
    }

    if (mineExploded) {
        System.out.println("Hahaha :) the mine exploded ### !");
    } else {
        System.out.println("You win!!! You safely uncovered all positions.");
    }

    System.out.println("Final board:");
    OpenAllAndPrint(matrix);
    } 
}

    static void openSafe(int[][] matrix, boolean[][] visited, int r, int c) {
        if (r < 0 || r >= matrix.length || c < 0 || c >= matrix[0].length || visited[r][c])
            return;

        visited[r][c] = true;
        if (matrix[r][c] == 0) {
            // recursively reveal neighbors if current cell has no adjacent mines
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    openSafe(matrix, visited, r + i, c + j);
                }
            }
        }
    }

    static int countOpened(boolean[][] visited) {
        int count = 0;
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[i].length; j++) {
                if (visited[i][j]) count++;
            }
        }
        return count;
    }


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
