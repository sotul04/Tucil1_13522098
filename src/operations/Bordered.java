package operations;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class Bordered extends JPanel{
    public Bordered (Component component, String Title) {

        // Creating a custom border
        Border customBorder = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        // Creating a TitledBorder with the custom border
        TitledBorder titledBorder = new TitledBorder(customBorder, "  "+Title);
        titledBorder.setTitleJustification(TitledBorder.LEFT);
        titledBorder.setTitlePosition(TitledBorder.BELOW_TOP);
        titledBorder.setTitleFont(new Font("Courier New", Font.BOLD, 20));
        titledBorder.setTitleColor(Color.GREEN);
        this.setBorder(titledBorder);

        this.add(component,BorderLayout.CENTER);

        Dimension border = new Dimension(component.getPreferredSize());

        border.setSize((int)border.getWidth() + 20, (int)border.getHeight()+45);

        this.setPreferredSize(border);

        this.setBackground(new Color(0x222222));

        this.setOpaque(true);

    }
}
