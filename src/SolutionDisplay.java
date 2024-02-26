import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import operations.Bordered;
import operations.Game;
import operations.MatrixSolution;
import operations.Sekuens;
import operations.Solution;

public class SolutionDisplay extends JFrame{
    
    SolutionDisplay (Game game, int time, String currentPath) {
        ImageIcon icon = new ImageIcon("../src/logo.png");
        
        int maksLengthSekuen = game.getMaxSequenceLength();
        
        int vertical_space = game.wasFound? 22+22*(6+game.solution.length) : 25+22*3;
        int horizontal_space = maksLengthSekuen*20 + 20*5;
        int solution_length = game.solution.length > 9 ? game.solution.length*20 : 9*20;
        
        Dimension matrixDimension = new Dimension(game.matriks.col*MatrixSolution.rectangle_length,game.matriks.row*MatrixSolution.rectangle_length);
        Dimension resultDimension = new Dimension(solution_length, vertical_space+20);
        Dimension sekuensDimension = new Dimension(horizontal_space, 24*game.sekuen.length+24);
        
        MatrixSolution matrixResult = new MatrixSolution(game);
        Solution resultSolution = new Solution(game, time);
        Sekuens sekuens = new Sekuens(game);
        
        //matrixResult.setBounds(10,15,(int)matrixDimension.getWidth(), (int)matrixDimension.getHeight());
        matrixResult.setPreferredSize(matrixDimension);
        //resultSolution.setBounds(20+(int)matrixDimension.getWidth(),10, (int)resultDimension.getWidth(), (int)resultDimension.getHeight());
        resultSolution.setPreferredSize(resultDimension);
        sekuens.setPreferredSize(sekuensDimension);
        //sekuens.setBounds(20+(int)matrixDimension.getWidth()+20+(int)resultDimension.getWidth(),10,(int)sekuensDimension.getWidth(),(int)sekuensDimension.getHeight());

        int frame_width = (int) matrixDimension.getWidth() + (int) sekuensDimension.getWidth()+ (int) resultDimension.getWidth();
        int frame_height = (int) matrixDimension.getHeight();
        frame_height += 160;

        JButton saveOption = new JButton("Save");
        saveOption.setBounds(frame_width/2-50, frame_height-100, 100, 20);
        saveOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();

                fileChooser.setCurrentDirectory(new File(currentPath + "/test/output"));

                int response = fileChooser.showSaveDialog(null);

                if (response == JFileChooser.APPROVE_OPTION) {
                    String abs_path = fileChooser.getSelectedFile().getAbsolutePath();
                    String extension = abs_path.substring(abs_path.lastIndexOf("."));
                    System.out.println(extension);
                    if (!extension.equals(".txt")) {
                        JOptionPane.showMessageDialog(null, "Ekstensi File harus .txt", "Extension error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        game.saveSolution(time, abs_path);
                        JOptionPane.showMessageDialog(null, "Berhasil menyimpan solusi", "Saving Succeed", JOptionPane.INFORMATION_MESSAGE);
                    }
                }

            }
        });

        Bordered matrixGame = new Bordered(matrixResult, "Matrix");
        //matrixGame.setBounds(10,15,(int)matrixGame.getPreferredSize().getWidth(), (int)matrixGame.getPreferredSize().getHeight());
        Bordered resultGame = new Bordered(resultSolution, "Solution");
        Bordered sekuensGame = new Bordered(sekuens, "Sekuens");

        Container container = this.getContentPane();

        BoxLayout boxLayout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(boxLayout);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

        matrixGame.setAlignmentX(Component.LEFT_ALIGNMENT);
        resultGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        sekuensGame.setAlignmentX(Component.RIGHT_ALIGNMENT);

        topPanel.add(matrixGame);
        topPanel.add(resultGame);
        topPanel.add(sekuensGame);
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(300,70));
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));

        saveOption.setAlignmentY(Component.CENTER_ALIGNMENT);
        saveOption.setAlignmentX(Component.CENTER_ALIGNMENT);

        bottomPanel.add(saveOption);
        bottomPanel.setBackground(new Color(0x333333));
        bottomPanel.setOpaque(true);
        bottomPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(topPanel);
        this.add(bottomPanel);

        this.getContentPane().setBackground(new Color(0x333333));
        this.setVisible(true);
        this.pack();
        this.setIconImage(icon.getImage());
        this.setTitle("Solution");

    }
}
