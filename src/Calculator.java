import javax.swing.*;

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
}
