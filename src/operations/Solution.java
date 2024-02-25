package operations;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Solution extends JLabel{
    public Solution (Game game, int time) {
        String display = "<html>";
        if (!game.wasFound) {
            display += "Tidak ada solusi<br>";
        } else {
            display += "Solusi:<br>"+game.solution.weight+"<br>"+game.solution.buffString+"<br>";
            for (int i = 0; i < game.solution.length; i++) {
                display += Integer.toString(game.solution.position[i][1]+1) +", "+ Integer.toString(game.solution.position[i][0]+1) +"<br>";
            }
        }
        display += "<br>"+Integer.toString(time)+"ms</html>";
        this.setText(display);
        this.setVerticalTextPosition(JLabel.CENTER);
        this.setForeground(Color.GREEN);
    }
}
