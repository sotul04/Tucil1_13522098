import javax.swing.*;
import java.awt.Color;

public class IntegerInput extends JPanel {

    private JSpinner spinner;
    private int min;

    public IntegerInput(int min) {
        this.min = min;
        SpinnerNumberModel model = new SpinnerNumberModel(min, min, null, 1);
        spinner = new JSpinner(model);
        JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) spinner.getEditor();
        editor.getTextField().setHorizontalAlignment(JTextField.CENTER);
        editor.getTextField().setColumns(2);
        this.add(spinner);
        this.setBackground(new Color(0x888888));
    }

    public int getInputNumber() {
        return (int) spinner.getValue();
    }

    public boolean isValidValue() {
        return getInputNumber() >= min; 
    }
}
