import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Calculator {

    public JFrame frame;
    private JTextField textField;
    private enum Action {
        NONE, ADD, SUBTRACT, MULTIPLY, DIVIDE, SQUAREROOT, INVERT, LOG, PERCENT
    }

    private Action currentAction = Action.NONE;
    private boolean eraseBefore = false;
    private Double firstNumber, secondNumber = null;

    private Double getFromDisplay() {
        Double value = null;
        String text = textField.getText();
        if (text != null && text.length() > 0) {
            return Double.parseDouble(text.replace(',', '.'));
        }
        return null;
    }

    private void setDisplay(Double value) {
        if (value != null) {
            String text = value.toString().replace('.', ',');
            textField.setText(text);
        }
    }

    private void pressedDigit(String digit) {
        if (eraseBefore) {
            textField.setText(digit);
            eraseBefore = false;
        }
        else executeAction();
    }

    private void pressedAction(Action action) {
        this.currentAction = action;
        if (firstNumber == null) {
            firstNumber = getFromDisplay();
            eraseBefore = true;
        } else
            executeAction();
    }

    private void executeAction() {
        if (firstNumber != null) {
            Double secondNumber = getFromDisplay();
            if (secondNumber != null) {
                if (Action.ADD.equals(currentAction))
                    firstNumber += secondNumber;
                else if (Action.SUBTRACT.equals(currentAction))
                    firstNumber -= secondNumber;
                else if (Action.MULTIPLY.equals(currentAction))
                    firstNumber *= secondNumber;
                else if (Action.DIVIDE.equals(currentAction))
                    if (secondNumber != 0)
                        firstNumber /= secondNumber;
                else if (Action.SQUAREROOT.equals(currentAction))
                    firstNumber = Math.sqrt(firstNumber);
                else if (Action.INVERT.equals(currentAction))
                    firstNumber = Math.pow(firstNumber, -1);
                else if (Action.LOG.equals(currentAction))
                    firstNumber = Math.log(firstNumber);
                else if (Action.PERCENT.equals(currentAction))
                    if (secondNumber != 0)
                        firstNumber = (firstNumber / secondNumber) * 100;
            }
        }

        setDisplay(firstNumber);
        firstNumber = null;
        eraseBefore = true;
    }

    public Calculator() {
        initialize();
    }

    private void initialize() {
        JFrame frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        panel.setLayout(new BorderLayout(0, 0));

        JTextField textField = new JTextField();
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(textField);
        textField.setColumns(10);

        // adding panel with numbers
        JPanel digitPanel = new JPanel();
        digitPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        frame.getContentPane().add(digitPanel, BorderLayout.CENTER);
        digitPanel.setLayout(new GridLayout(4, 3, 5, 10));

        JButton cButton = new JButton("C");
        cButton.addActionListener(arg0 -> textField.setText(null));
        cButton.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(cButton, BorderLayout.EAST);

        JButton button_7 = new JButton("7");
        button_7.addActionListener(e -> pressedDigit("7"));
        digitPanel.add(button_7);

        JButton button_8 = new JButton("8");
        button_7.addActionListener(e -> pressedDigit("8"));
        digitPanel.add(button_8);

        JButton button_9 = new JButton("9");
        button_9.addActionListener(e -> pressedDigit("9"));
        digitPanel.add(button_9);

        JButton button_4 = new JButton("4");
        button_4.addActionListener(e -> pressedDigit("4"));
        digitPanel.add(button_4);

        JButton button_5 = new JButton("5");
        button_5.addActionListener(e -> pressedDigit("5"));
        digitPanel.add(button_5);

        JButton button_6 = new JButton("6");
        button_6.addActionListener(e -> pressedDigit("6"));
        digitPanel.add(button_6);

        JButton button_1 = new JButton("1");
        button_1.addActionListener(e -> pressedDigit("1"));
        digitPanel.add(button_1);

        JButton button_2 = new JButton("2");
        button_2.addActionListener(e -> pressedDigit("2"));
        digitPanel.add(button_2);

        JButton button_3 = new JButton("3");
        button_3.addActionListener(e -> pressedDigit("3"));
        digitPanel.add(button_3);

        JButton button_0 = new JButton("0");
        button_0.addActionListener(e -> pressedDigit("0"));
        digitPanel.add(button_0);

        JButton commaButton = new JButton(".");
        commaButton.addActionListener(e -> pressedDigit("."));
        digitPanel.add(commaButton);

        JButton logButton = new JButton("log(x)");
        logButton.addActionListener(e -> pressedAction(Action.LOG));
        digitPanel.add(logButton);

        // adding new panel with action buttons
        JPanel actionPanel = new JPanel();
        actionPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        frame.getContentPane().add(actionPanel, BorderLayout.EAST);
        actionPanel.setLayout(new GridLayout(0, 2, 5, 5));

        JButton addButton = new JButton("+");
        addButton.addActionListener(e -> pressedAction(Action.ADD));
        actionPanel.add(addButton);

        JButton subtractButton = new JButton("-");
        subtractButton.addActionListener(e -> pressedAction(Action.SUBTRACT));
        actionPanel.add(subtractButton);

        JButton multiplyButton = new JButton("*");
        multiplyButton.addActionListener(e -> pressedAction(Action.MULTIPLY));
        actionPanel.add(multiplyButton);

        JButton divideButton = new JButton("/");
        divideButton.addActionListener(e -> pressedAction(Action.DIVIDE));
        actionPanel.add(divideButton);

        JButton sqrtButton = new JButton("\u221a");
        sqrtButton.addActionListener(e -> pressedAction(Action.SQUAREROOT));
        actionPanel.add(sqrtButton);

        JButton equalsButton = new JButton("=");
        equalsButton.addActionListener(e -> pressedAction(currentAction));
        actionPanel.add(equalsButton);

        JButton invertButton = new JButton("1/x");
        invertButton.addActionListener(e -> pressedAction(Action.INVERT));
        actionPanel.add(invertButton);

        JButton percentButton = new JButton("%");
        percentButton.addActionListener(e -> pressedAction(Action.PERCENT));
        actionPanel.add(percentButton);
    }


}
