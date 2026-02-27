import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculus extends JFrame implements ActionListener {
    private JTextField data;
    private double metaOne = 0;
    private String metaTwo = "";
    private boolean metaThree = true;

    public Calculus() {
        setTitle("Calculus");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        data = new JTextField();
        data.setEditable(false);
        data.setHorizontalAlignment(JTextField.RIGHT);
        data.setFont(new Font("Fairfax", Font.PLAIN, 20));
        add(data, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(5, 4, 5, 5));

        String[] things = {
                "C", "←", "√", "x²",
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String thing : things) {
            JButton button = new JButton(thing);
            button.setFont(new Font("Fairfax", Font.PLAIN, 18));
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);

        setSize(300, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String metaData = e.getActionCommand();

        try {
            if (metaData.matches("[0-9]")) {
                if (metaThree) {
                    data.setText(metaData);
                    metaThree = false;
                } else {
                    data.setText(data.getText() + metaData);
                }
            } else if (metaData.equals(".")) {
                if (!data.getText().contains(".")) {
                    if (metaThree) {
                        data.setText("0.");
                        metaThree = false;
                    } else {
                        data.setText(data.getText() + ".");
                    }
                }
            } else if (metaData.equals("C")) {
                data.setText("");
                metaOne = 0;
                metaTwo = "";
                metaThree = true;
            } else if (metaData.equals("←")) {
                String thing = data.getText();
                if (thing.length() > 0) {
                    data.setText(thing.substring(0, thing.length() - 1));
                }
            } else if (metaData.equals("√")) {
                double thing = Double.parseDouble(data.getText());
                data.setText(String.valueOf(Math.sqrt(thing)));
                metaThree = true;
            } else if (metaData.equals("x²")) {
                double thing = Double.parseDouble(data.getText());
                data.setText(String.valueOf(thing * thing));
                metaThree = true;
            } else if (metaData.matches("[+\\-*/]")) {
                if (!metaTwo.isEmpty() && !metaThree) {
                    doTheThing();
                }
                metaOne = Double.parseDouble(data.getText());
                metaTwo = metaData;
                metaThree = true;
            } else if (metaData.equals("=")) {
                if (!metaTwo.isEmpty() && !metaThree) {
                    doTheThing();
                    metaTwo = "";
                }
            }
        } catch (NumberFormatException ex) {
            data.setText("Ошибка");
            metaThree = true;
        } catch (ArithmeticException ex) {
            data.setText("Ошибка");
            metaThree = true;
        }
    }

    private void doTheThing() {
        double thingOne = Double.parseDouble(data.getText());
        double thingTwo = 0;

        switch (metaTwo) {
            case "+":
                thingTwo = metaOne + thingOne;
                break;
            case "-":
                thingTwo = metaOne - thingOne;
                break;
            case "*":
                thingTwo = metaOne * thingOne;
                break;
            case "/":
                if (thingOne == 0) {
                    throw new ArithmeticException("Ошибка");
                }
                thingTwo = metaOne / thingOne;
                break;
        }

        if (thingTwo == (long) thingTwo) {
            data.setText(String.valueOf((long) thingTwo));
        } else {
            data.setText(String.valueOf(thingTwo));
        }

        metaOne = thingTwo;
        metaThree = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Calculus();
            }
        });
    }
}