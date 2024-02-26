import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import operations.Game;

public class GameGUI extends JFrame{

    Game newGame;

    GameGUI() {

        ImageIcon icon = new ImageIcon("../src/icon.png");
        this.setIconImage(icon.getImage());

        newGame = new Game();

        JPanel rightPanel = new JPanel();
        rightPanel.setBounds(10, 0, 363, 500);
        rightPanel.setBackground(new Color(0x888888));
        rightPanel.setLayout(null);
        rightPanel.setOpaque(true);

        JLabel rightTitle = new JLabel("Masukan Random");
        rightTitle.setFont(new Font("Times Roman", Font.BOLD, 25));
        JLabel nTokenInput = new JLabel("Jumlah token unik");
        JLabel tokenInput = new JLabel("Token");
        JLabel bufferSize = new JLabel("Ukuran Buffer");
        JLabel matrixDimension = new JLabel("Ukuran Matriks");
        JLabel widthSize = new JLabel("Width");
        JLabel heightSize = new JLabel("Height");
        JLabel nSequeces = new JLabel("Jumlah Sekuens");
        JLabel sequenceMaksSize = new JLabel("Ukuran maksimum Sekuens");

        rightTitle.setBounds(80,10,280,50);

        nTokenInput.setBounds(20,60,180,27);
        tokenInput.setBounds(20,95,180,27);
        bufferSize.setBounds(20,130,180,27);
        matrixDimension.setBounds(20,165,180,27);
        widthSize.setBounds(20,200,50,27);
        heightSize.setBounds(135,200,70,27);
        nSequeces.setBounds(20,240,180,27);
        sequenceMaksSize.setBounds(20,285,180,27);

        IntegerInput nTokIntegerInput = new IntegerInput(1);
        IntegerInput bufIntegerInput = new IntegerInput(2);
        IntegerInput widthIntegerInput = new IntegerInput(0);
        IntegerInput heightIntegerInput = new IntegerInput(0);
        IntegerInput nSeqIntegerInput = new IntegerInput(1);
        IntegerInput seqMaksIntegerInput = new IntegerInput(2);
        
        JTextField tokenTextField = new JTextField();
        tokenTextField.setBounds(204,95,120,30);

        JLabel errorLabel = new JLabel("  nToken doesn't match");
        errorLabel.setFont(new Font("Cambria", Font.ITALIC, 11));
        errorLabel.setBackground(Color.red);
        errorLabel.setOpaque(true);
        errorLabel.setVisible(false);
        errorLabel.setBounds(90,97,110,25);

        tokenTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validateInput();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateInput();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateInput();
            }

            private void validateInput() {
                String input = tokenTextField.getText();
                int nToken = nTokIntegerInput.getInputNumber();
                if (isTokenValid(input, nToken)) {
                    errorLabel.setVisible(false);
                } else {
                    errorLabel.setVisible(true);
                }
            }
        });


        nTokIntegerInput.setBounds(200,60,50,30);
        bufIntegerInput.setBounds(200,130,50,30);
        widthIntegerInput.setBounds(60,200,50,30);
        heightIntegerInput.setBounds(180,200,50,30);
        nSeqIntegerInput.setBounds(200,240,50,30);
        seqMaksIntegerInput.setBounds(200,285,50,30);

        rightPanel.add(tokenTextField);
        rightPanel.add(errorLabel);

        rightPanel.add(rightTitle);
        rightPanel.add(nTokenInput);
        rightPanel.add(tokenInput);
        rightPanel.add(bufferSize);
        rightPanel.add(matrixDimension);
        rightPanel.add(widthSize);
        rightPanel.add(heightSize);
        rightPanel.add(nSequeces);
        rightPanel.add(sequenceMaksSize);

        rightPanel.add(nTokIntegerInput);
        rightPanel.add(bufIntegerInput);
        rightPanel.add(widthIntegerInput);
        rightPanel.add(heightIntegerInput);
        rightPanel.add(nSeqIntegerInput);
        rightPanel.add(seqMaksIntegerInput);

        
        // Start Button

        JButton startButton = new JButton("Start");
        startButton.setBounds(130,340,80,30);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nTokIntegerInput.isValidValue() &&
                    bufIntegerInput.isValidValue() &&
                    widthIntegerInput.isValidValue() &&
                    heightIntegerInput.isValidValue() &&
                    nSeqIntegerInput.isValidValue() &&
                    seqMaksIntegerInput.isValidValue()) {
                        String input = tokenTextField.getText();
                        int nToken = nTokIntegerInput.getInputNumber();
                        if (isTokenValid(input, nToken)) {
                            errorLabel.setVisible(false);
                            String[] tokens = input.split(" ");
                            newGame.randomGenerate(
                                nTokIntegerInput.getInputNumber(),
                                tokens,
                                bufIntegerInput.getInputNumber(),
                                heightIntegerInput.getInputNumber(),
                                widthIntegerInput.getInputNumber(),
                                nSeqIntegerInput.getInputNumber(),
                                seqMaksIntegerInput.getInputNumber()
                            );
                            System.out.println();
                            newGame.showProperties();
                            long start = System.currentTimeMillis();
                            newGame.startSearch();
                            long end = System.currentTimeMillis();
                            int diff = (int)(end-start);
                            newGame.displaySolution(diff);

                            String currPath = System.getProperty("user.dir");

                            Path path = Paths.get(currPath);
                            Path parenPath = path.getParent();
                            currPath = parenPath.toString();

                            new SolutionDisplay(newGame, diff, currPath);

                        } else {
                            errorLabel.setVisible(true);
                        }
                    }
            }
        });

        JLabel fileErrorLabel = new JLabel("  File harus berekstensi .txt");
        fileErrorLabel.setVisible(false);
        fileErrorLabel.setBounds(20,455, 150, 20);
        fileErrorLabel.setBackground(Color.red);
        fileErrorLabel.setOpaque(true);
        fileErrorLabel.setFont(new Font("Cambria", Font.ITALIC, 13));

        rightPanel.add(fileErrorLabel);

        JButton selectFile = new JButton("Select File");
        selectFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                JFileChooser fileChooser = new JFileChooser();

                String currentPath = System.getProperty("user.dir");

                Path path = Paths.get(currentPath);
                Path parenPath = path.getParent();
                currentPath = parenPath.toString();

                fileChooser.setCurrentDirectory(new File(currentPath+"/test/input"));

                int response = fileChooser.showOpenDialog(null);

                if (response == JFileChooser.APPROVE_OPTION) {
                    String abs_path = fileChooser.getSelectedFile().getAbsolutePath();
                    String extension = abs_path.substring(abs_path.lastIndexOf("."));
                    if (!extension.equals(".txt")) {
                        fileErrorLabel.setOpaque(true);
                        fileErrorLabel.setVisible(true);
                        fileErrorLabel.setText("  File harus berekstensi .txt");
                    } else {
                        fileErrorLabel.setOpaque(false);
                        fileErrorLabel.setVisible(true);
                        fileErrorLabel.setText("  File terpilih: "+ abs_path.substring(abs_path.lastIndexOf("\\")+1));
                        newGame = new Game();
                        boolean fileFormatAccepted = newGame.readText(abs_path);
                        if (fileFormatAccepted) {
                            long start = System.currentTimeMillis();
                            newGame.startSearch();
                            long end = System.currentTimeMillis();
                            int diff = (int)(end-start);
                            newGame.displaySolution(diff);
                            new SolutionDisplay(newGame, diff, currentPath);
                        } else {
                            JOptionPane.showMessageDialog(null, "File tidak memuat data yang sesuai format!", "Format data error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }

            }
        }); 

        selectFile.setBounds(20,420,100,30);

        rightPanel.add(startButton);

        JLabel fileLabel = new JLabel("Masukan berupa File:");
        fileLabel.setBounds(20,390,120,30);

        rightPanel.add(fileLabel);
        rightPanel.add(selectFile);
        this.setDefaultCloseOperation(3);
        this.setSize(new Dimension(400,550));

        this.add(rightPanel);
        // this.add(scrollPane);
        this.setTitle("Breach Protocol Cyberpunk 2077");

        // this.setBackground(new Color(0x121212));
        this.getContentPane().setBackground(new Color(0x666666));
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
    }

    public boolean isTokenValid (String tokens, int length) {
        String regex = "^[a-zA-Z0-9]*$";
        Pattern pattern = Pattern.compile(regex);
        String token[] = tokens.split(" ");
        if (token.length != length){
            return false;
        }
        for (int i = 0; i < token.length; i++) {
            Matcher matcher = pattern.matcher(token[i]);
            if (!matcher.matches() || token[i].length() != 2) {
                return false;
            }
        }
        return true;
    }
    
}
