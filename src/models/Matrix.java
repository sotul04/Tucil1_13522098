package models;

public class Matrix {
    String content[][];
    public int row;
    public int col;

    public Matrix() {
        content = new String[1000][1000];
        row = 0;
        col = 0;
    }

    public Matrix(int rows, int column) {
        content = new String[rows][column];
        row = rows;
        col = column;
    }

    public Matrix(Matrix mat) {
        this(mat.row, mat.col);
        this.content = mat.content;
    }

    public String getToken(int i, int j) {
        return content[i][j];
    }

    public void setToken(String token, int i, int j) {
        content[i][j] = token;
    }
}
