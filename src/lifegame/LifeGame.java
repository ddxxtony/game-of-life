/*
 improved by Luis Aguilar
 */
package lifegame;

import java.util.Random;

class Game {

    public int ms;
    public int matriz[][];
    public int auxiliarMatriz[][];

    Game(int ms) {
        this.ms = ms;
        this.matriz = new int[ms][ms];
        this.auxiliarMatriz = new int[ms][ms];
    }

    public void fillMatriz() {
        System.out.println("Initial generation");
        Random generator = new Random();
        for (int row = 0; row < ms; row++) {
            for (int col = 0; col < ms; col++) {
                double number = generator.nextDouble() * 1;
                matriz[row][col] = (int) Math.round(number);
                System.out.print(matriz[row][col]);
            }
            System.out.println("");
        }
    }

    public void startLifeCicle(int generations) {
        for (int generation = 0; generation < generations; generation++) {
            for (int row = 0; row < ms; row++) {
                for (int col = 0; col < ms; col++) {
                    auxiliarMatriz[row][col] = getNextCellValue(matriz, row, col);
                }
            }
            System.out.println("");
            System.out.println("Generación " + generation);
            copyAuxiliarMatrizIntoMatriz();
        }
    }

    public void copyAuxiliarMatrizIntoMatriz() {
        for (int row = 0; row < ms; row++) {
            for (int col = 0; col < ms; col++) {
                matriz[row][col] = auxiliarMatriz[row][col];
                System.out.print(matriz[row][col]);
            }
             System.out.println("");
        }
    }

    public int getMatrizValue(int[][] matriz, int row, int col) {
        if (row >= 0 && col >= -0 && col <= ms - 1 && row <= ms - 1) {
            return matriz[row][col];
        } else if ((row < 0 || row > ms - 1) && (col >= 0 && col <= ms - 1)) {
            return matriz[row == -1 ? ms - 1 : 0][col];
        } else if ((row >= 0 && row <= ms - 1) && (col < 0 || col >= ms - 1)) {
            return matriz[row][col == -1 ? ms - 1 : 0];
        } else if ((row < 0 || row > ms - 1) && (col < 0 || col >= ms - 1)) {
            return matriz[row == -1 ? ms - 1 : 0][col == -1 ? ms - 1 : 0];
        }
        return matriz[row][col];
    }

    public int getNextCellValue(int[][] matriz, int currentRow, int currentCol) {
        /////check neigbords
        int aliveCells = 0;
        int row = currentRow;
        int col = currentCol;
        
        //midlet cellss
        if (getMatrizValue(matriz, row - 1,col - 1 ) == 1) {
            aliveCells++;
        }
        if (getMatrizValue(matriz, row - 1,col ) == 1) {
            aliveCells++;
        }
        if (getMatrizValue(matriz, row - 1,col + 1 ) == 1) {
            aliveCells++;
        }

        //same row 
        if (getMatrizValue(matriz, row,col - 1 ) == 1) {
            aliveCells++;
        }
        if (getMatrizValue(matriz, row,col + 1 )== 1) {
            aliveCells++;
        }

        //under
        if (getMatrizValue(matriz, row + 1,col - 1 ) == 1) {
            aliveCells++;
        }
        if (getMatrizValue(matriz, row + 1,col) == 1) {
            aliveCells++;
        }
        if (getMatrizValue(matriz, row + 1,col + 1 ) == 1) {
            aliveCells++;
        }

        int currentCell = matriz[currentRow][currentCol];
        if (currentCell == 1 && aliveCells < 2) {
            return 0;
        }
        if (currentCell == 1 && aliveCells > 3) {
            return 0;
        }
        if (currentCell == 1 && (aliveCells >= 2 || aliveCells <= 3)) {
            return 1;
        }

        if (currentCell == 0 && aliveCells == 3) {
            return 1;
        }
        return 0;
    }
}

public class LifeGame {
    public static void main(String[] args) {
        Game game = new Game(12);
        game.fillMatriz();
        game.startLifeCicle(10);
    }
}
