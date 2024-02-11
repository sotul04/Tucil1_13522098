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

    // public void setVisited(boolean val,int i, int j) {
    //     this.isVisited[i][j] = val;
    // }

    // public boolean getVisited(int i, int j){
    //     return this.isVisited[i][j];
    // }

    @Override
    public String toString() {
        String temp = "Matrix{\nTokens:\n";
        for (int i = 0 ; i < row; i++){
            for (int j = 0 ; j < col; j++){
                temp += getToken(i, j);
                if (j != col-1){
                    temp+=" ";
                }
            }
            temp += "\n";
        }
        temp += "row: "+Integer.toString(row)+", col: "+Integer.toString(col)+"}";
        return temp;
    }
}
