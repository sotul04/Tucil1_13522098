package operations;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;

public class Token  extends JLabel{
    
    private boolean visited;
    private boolean top;
    private String token;
    static final int border_size = 36;

    Token(boolean visited, boolean top, String token) {
        this.visited = visited;
        this.token = token;
        this.top = top;
        this.setText(this.token);
        this.setFont(new Font("Times Roman", Font.BOLD, 13));
        this.setForeground(Color.WHITE);
        this.setBackground(new Color(0x101010));
        this.setOpaque(true);
    }

    public boolean getVisited() {
        return visited;
    }

    public boolean getTop() {
        return top;
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        if (visited) {
            g2d.setPaint(Color.GREEN);
            g2d.setStroke(new BasicStroke(4));
            g2d.drawRect(5,5, border_size, border_size);
        }
        if (top) {
            g2d.setPaint(Color.RED);
            int[] xPoints = {border_size/2,border_size/2+6,border_size/2+12};
            int[] yPoints = {0,10,0};
            g2d.fillPolygon(xPoints, yPoints, 3);
        }
    }
}
