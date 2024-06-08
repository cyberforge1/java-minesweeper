public class Cell {
    private boolean isMine;
    private boolean isRevealed;
    private int adjacentMines;

    public Cell() {
        isMine = false;
        isRevealed = false;
        adjacentMines = 0;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void reveal() {
        isRevealed = true;
    }

    public int getAdjacentMines() {
        return adjacentMines;
    }

    public void setAdjacentMines(int count) {
        adjacentMines = count;
    }

    @Override
    public String toString() {
        if (!isRevealed) {
            return " - ";
        } else if (isMine) {
            return " * ";
        } else if (adjacentMines > 0) {
            return " " + adjacentMines + " ";
        } else {
            return "   ";
        }
    }
}

