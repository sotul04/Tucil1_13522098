package models;

public class Visited {
    public boolean visited[][];
    public int row;
    public int col;

    public Visited (int row, int col) {
        visited = new boolean[row][col];
        this.row = row;
        this.col = col;
    }

    public Visited (Visited vist) {
        this(vist.row, vist.col);
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                this.setVisited(vist.getVisited(i, j), i, j);
            }
        }
    }

    public void setVisited(boolean val, int row, int col) {
        this.visited[row][col] = val;
    }

    public boolean getVisited(int row, int col) {
        return this.visited[row][col];
    }
}
