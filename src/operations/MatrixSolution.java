package operations;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class MatrixSolution extends JPanel{
    private int row;
    private int col;
    public static final int rectangle_length = 44;
    int[][] pos;
    int length;

    public MatrixSolution(Game game) {
        this.row = game.matriks.row;
        this.col = game.matriks.col;
        pos = new int[game.solution.maxsize][2];
        length = game.solution.length;
        for (int i = 0; i < length; i++) {
            pos[i][0] = game.solution.position[i][0];
            pos[i][1] = game.solution.position[i][1];
        }
        int main_row = row;
        if (row == 0 && col == 0) {
            main_row = 1;
        }
        this.setLayout(new GridLayout(main_row, col,1,1));
        int width = col*rectangle_length;
        int height = row*rectangle_length;
        this.setPreferredSize(new Dimension(width,height));
        Token[] tokens = new Token[row*col];
        this.setBackground(new Color(0x010101));
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                boolean isSeqeunce = false;
                int x;
                for (x = 0; x < game.solution.length;x++){
                    if(i == game.solution.position[x][0] && j == game.solution.position[x][1]) {
                        isSeqeunce = true;
                        break;
                    }
                }
                int index = i*col + j;
                tokens[index] = new Token(isSeqeunce, i==0 && isSeqeunce && x == 0,"    "+game.matriks.getToken(i, j));
                this.add(tokens[index]);
            }
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        boolean verticalDirection = true;
        g2d.setPaint(Color.GREEN);
        g2d.setStroke(new BasicStroke(2));
        for (int i = 1; i < length; i++) {
            int x1, y1, x2, y2;
            if (verticalDirection) {
                x1 = 1+(rectangle_length)*pos[i-1][1]+rectangle_length/2;
                x2 = x1;
                if (pos[i-1][0] < pos[i][0]){
                    y1 = 1+(rectangle_length)*pos[i-1][0]+rectangle_length-(row-pos[i-1][0]+1)/2;
                    y2 = 1+(rectangle_length)*pos[i][0];
                } else {
                    y1 = 1+(rectangle_length)*pos[i-1][0];
                    y2 = 1+(rectangle_length)*pos[i][0]+rectangle_length-(row-pos[i][0]+1)/2;
                }
                g2d.drawLine(x1, y1, x2, y2);
                verticalDirection = !verticalDirection;
            } else {
                y1 = 1+(rectangle_length)*pos[i-1][0]+rectangle_length/2;
                y2 = y1;
                if (pos[i-1][1] < pos[i][1]){
                    x1 = 1+(rectangle_length)*pos[i-1][1]+rectangle_length-(row-pos[i-1][1])/2;
                    x2 = 1+(rectangle_length)*pos[i][1];
                } else {
                    x1 = 1+(rectangle_length)*pos[i-1][1];
                    x2 = 1+(rectangle_length)*pos[i][1]+rectangle_length-(row-pos[i][1])/2;
                }
                g2d.drawLine(x1, y1, x2, y2);
                verticalDirection = !verticalDirection;
            }
        }
    }
}
