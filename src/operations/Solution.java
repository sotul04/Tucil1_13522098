package operations;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Solution extends JPanel{
    public Solution (Game game, int time) {

        this.setLayout(null);
        JLabel weightLabel;
        JLabel[] tokensLabels;
        JLabel[] posLabels;
        this.setBackground(new Color(0x232323));
        JPanel bottomJPanel = new JPanel();
        bottomJPanel.setLayout(new BoxLayout(bottomJPanel, BoxLayout.Y_AXIS));
        bottomJPanel.setBackground(new Color(0x232323));

        JLabel timeElapse = new JLabel("Time: "+Integer.toString(time)+"ms");
        timeElapse.setForeground(Color.GREEN);
        timeElapse.setBackground(new Color(0x232323));
        timeElapse.setOpaque(true);
        
        if (game.wasFound){

            
            int length = game.solution.length;
            String []tokens = game.solution.buffString.split(" ");
            int[][] pos = new int[length][2];

            for (int i =0 ; i < length; i++) {
                pos[i][0] = game.solution.position[i][0];
                pos[i][1] = game.solution.position[i][1];
            }
    
            int weight = game.solution.weight;
            
            weightLabel = new JLabel("Poin: "+weight);
            weightLabel.setBackground(new Color(0x232323));
            weightLabel.setForeground(Color.green);
            weightLabel.setOpaque(false);
            weightLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

            JPanel bufferContainer = new JPanel();
            bufferContainer.setLayout(new GridLayout(1+length,2,2,2));
            bufferContainer.setBackground(new Color(0x333333));
            bufferContainer.setOpaque(true);

            JLabel tokenTitle = new JLabel("  Token");
            JLabel posTitle = new JLabel("  Posisi");

            tokenTitle.setForeground(new Color(0x00dd00));
            posTitle.setForeground(new Color(0x00dd00));

            tokenTitle.setBackground(new Color(0x131313));
            posTitle.setBackground(new Color(0x131313));

            tokenTitle.setOpaque(true);
            posTitle.setOpaque(true);

            tokenTitle.setHorizontalTextPosition(JLabel.CENTER);
            posTitle.setHorizontalTextPosition(JLabel.CENTER);

            bufferContainer.add(tokenTitle);
            bufferContainer.add(posTitle);
            
            tokensLabels = new JLabel[length];
            posLabels = new JLabel[length];

            for (int i = 0; i < length; i++) {
                tokensLabels[i] = new JLabel("  "+tokens[i]);
                tokensLabels[i].setForeground(Color.GREEN);
                tokensLabels[i].setBackground(new Color(0x222222));
                tokensLabels[i].setOpaque(true);
                posLabels[i] = new JLabel("  "+Integer.toString(pos[i][1]+1)+", "+Integer.toString(pos[i][0]+1));
                posLabels[i].setForeground(Color.GREEN);
                posLabels[i].setBackground(new Color(0x222222));
                posLabels[i].setOpaque(true);
                bufferContainer.add(tokensLabels[i]);
                bufferContainer.add(posLabels[i]);
            }

            int box_width = 120;
            int box_height = (length+1)*20;

            weightLabel.setBounds(7,0,100,15);
            bufferContainer.setBounds(5,23,box_width,box_height);

            this.add(weightLabel);
            this.add(bufferContainer);
            
            timeElapse.setBounds(7,33+box_height, 100,15);

            this.add(timeElapse);
        } else {
            JLabel noSolutionLabel = new JLabel("Tidak ada solusi");
            noSolutionLabel.setForeground(Color.GREEN);
            noSolutionLabel.setBackground(new Color(0x232323));
            noSolutionLabel.setOpaque(true);
            noSolutionLabel.setBounds(7,0,100,15);
            timeElapse.setBounds(7,15+10,100,15);
            this.add(noSolutionLabel);
            this.add(timeElapse);
        }
    }
}
