import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        EventQueue.invokeLater(() -> {
            Calculator window = new Calculator();
            window.frame.setVisible(true);
        });
    }

    /*public Calculator() {
        initialize();
    }*/

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

        JButton cButton = new JButton("C");
        cButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) { textField.setText(null); }
        });
        cButton.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(cButton, BorderLayout.EAST);
    }


}
