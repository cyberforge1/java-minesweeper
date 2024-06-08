import java.util.Scanner;

public class Game {
    private Board board;
    private boolean gameRunning;
    
    public Game(int width, int height, int mines) {
        this.board = new Board(width, height, mines);
    }
    
    public void start() {
        Scanner scanner = new Scanner(System.in);
        gameRunning = true;
        while (gameRunning) {
            System.out.println();
            System.out.println();           
            System.out.println();
            System.out.println("\tJava Minesweeper Game");
            System.out.println();
            System.out.println();
            board.printBoard();
            System.out.println();
            System.out.print("Enter coordinates (row, column): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            if (!board.openCell(row, col)) {
                System.out.println("Boom! You hit a mine.");
                gameRunning = false;
            } else if (board.checkWin()) {
                System.out.println("Congratulations! You Won!!!.");
                gameRunning = false;
            }
        }
        scanner.close();
    }
}

