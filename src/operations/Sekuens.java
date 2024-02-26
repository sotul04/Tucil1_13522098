package operations;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Sekuens extends JPanel{
    public Sekuens (Game game) {
        this.setLayout(null);
        JLabel sequenceLabel = new JLabel("  Sekuen");
        sequenceLabel.setForeground(Color.GREEN);
        sequenceLabel.setBackground(new Color(0x121212));
        sequenceLabel.setOpaque(true);
        JLabel weightLabel = new JLabel("  Bobot");
        weightLabel.setForeground(Color.GREEN);
        weightLabel.setBackground(new Color(0x121212));
        weightLabel.setOpaque(true);

        int maxSekLength = game.getMaxSequenceLength();
        int nSekuens = game.sekuen.length;

        int width = 21*maxSekLength*2;

        JPanel sekContainer = new JPanel();
        sekContainer.setBackground(new Color(0x333333));
        sekContainer.setOpaque(true);
        sekContainer.setLayout(new GridLayout(nSekuens+1, 2, 2,2));

        sekContainer.add(sequenceLabel);
        sekContainer.add(weightLabel);

        JLabel[] sekuensLabel = new JLabel[nSekuens];
        JLabel[] bobotLabel = new JLabel[nSekuens];

        for (int i = 0; i < nSekuens; i++) {
            sekuensLabel[i] = new JLabel("  "+game.sekuen[i].seqString);
            sekuensLabel[i].setForeground(Color.GREEN);
            sekuensLabel[i].setBackground(new Color(0x222222));
            sekuensLabel[i].setOpaque(true);
            bobotLabel[i] = new JLabel("  "+Integer.toString(game.sekuen[i].weight));
            bobotLabel[i].setForeground(Color.GREEN);
            bobotLabel[i].setBackground(new Color(0x222222));
            bobotLabel[i].setOpaque(true);
            sekContainer.add(sekuensLabel[i]);
            sekContainer.add(bobotLabel[i]);
        }

        sekContainer.setBounds(4,0,width,(1+nSekuens)*23);

        this.add(sekContainer);
        this.setBackground(new Color(0x232323));
    }
}
