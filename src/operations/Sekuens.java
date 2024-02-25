package operations;

import java.awt.Color;

import javax.swing.JLabel;

public class Sekuens extends JLabel{
    public Sekuens (Game game) {
        int length = game.sekuen.length;
        String display = "<html>Sekuens:<br>";
        for (int i = 0; i < length; i++) {
            display += game.sekuen[i].seqString +" ["+Integer.toString(game.sekuen[i].weight)+"]";
            if (i != length-1) {
                display += "<br>";
            }
        }
        display += "</html>";
        this.setText(display);
        this.setForeground(Color.GREEN);
    }
}
