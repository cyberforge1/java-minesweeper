import java.util.Random;

public class Board {
    private Cell[][] cells;
    private int width;
    private int height;

    public Board(int width, int height, int mines) {
        this.width = width;
        this.height = height;
        cells = new Cell[height][width];
        initializeCells();
        plantMines(mines);
        calculateAdjacentMines();
    }

    private void initializeCells() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    private void plantMines(int mines) {
        Random rand = new Random();
        while (mines > 0) {
            int r = rand.nextInt(height);
            int c = rand.nextInt(width);
            if (!cells[r][c].isMine()) {
                cells[r][c].setMine(true);
                mines--;
            }
        }
    }

    private void calculateAdjacentMines() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (cells[i][j].isMine()) {
                    for (int ii = Math.max(0, i - 1); ii <= Math.min(i + 1, height - 1); ii++) {
                        for (int jj = Math.max(0, j - 1); jj <= Math.min(j + 1, width - 1); jj++) {
                            if (!(ii == i && jj == j)) {
                                cells[ii][jj].setAdjacentMines(cells[ii][jj].getAdjacentMines() + 1);
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean openCell(int row, int col) {
        if (cells[row][col].isMine()) {
            cells[row][col].reveal();
            printBoard();
            return false;
        }
        recursiveOpen(row, col);
        return true;
    }

    private void recursiveOpen(int row, int col) {
        if (!cells[row][col].isRevealed() && cells[row][col].getAdjacentMines() == 0) {
            cells[row][col].reveal();
            for (int ii = Math.max(0, row - 1); ii <= Math.min(row + 1, height - 1); ii++) {
                for (int jj = Math.max(0, col - 1); jj <= Math.min(col + 1, width - 1); jj++) {
                    if (!(ii == row && jj == col)) {
                        if (!cells[ii][jj].isMine() && !cells[ii][jj].isRevealed()) {
                            recursiveOpen(ii, jj);
                        }
                    }
                }
            }
        } else if (!cells[row][col].isRevealed()) {
            cells[row][col].reveal();
        }
    }

    public void printBoard() {
        System.out.print("   ");
        for (int j = 0; j < width; j++) {
            System.out.print(" " + j + " ");
        }
        System.out.println();
        System.out.print("   ");
        for (int j = 0; j < width; j++) {
            System.out.print("---");
        }
        System.out.println();
        for (int i = 0; i < height; i++) {
            System.out.print(i + " |");
            for (int j = 0; j < width; j++) {
                System.out.print(cells[i][j].toString());
            }
            System.out.println("| " + i);
        }
        System.out.print("   ");
        for (int j = 0; j < width; j++) {
            System.out.print("---");
        }
        System.out.println();
    }

    public boolean checkWin() {
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                if (!cell.isRevealed() && !cell.isMine()) {
                    return false;
                }
            }
        }
        return true;
    }
}

