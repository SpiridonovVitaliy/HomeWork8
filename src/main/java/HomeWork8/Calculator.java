package HomeWork8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {

    private double TEMP;
    private double START;
    private double SolveTEMP;
    private final JTextField jtfResult;
    private boolean addBool = false;
    private boolean subBool = false;
    private boolean divBool = false;
    private boolean mulBool = false;
    private boolean operatorClicked = false;
    private boolean justSolved = false;
    String display = "0";

    public Calculator() {
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(4, 4));
        JButton jbtNum7;
        p1.add(jbtNum7 = new JButton("7"));
        JButton jbtNum8;
        p1.add(jbtNum8 = new JButton("8"));
        JButton jbtNum9;
        p1.add(jbtNum9 = new JButton("9"));
        JButton jbtAdd;
        p1.add(jbtAdd = new JButton("+"));
        JButton jbtNum4;
        p1.add(jbtNum4 = new JButton("4"));
        JButton jbtNum5;
        p1.add(jbtNum5 = new JButton("5"));
        JButton jbtNum6;
        p1.add(jbtNum6 = new JButton("6"));
        JButton jbtSubtract;
        p1.add(jbtSubtract = new JButton("-"));
        JButton jbtNum1;
        p1.add(jbtNum1 = new JButton("1"));
        JButton jbtNum2;
        p1.add(jbtNum2 = new JButton("2"));
        JButton jbtNum3;
        p1.add(jbtNum3 = new JButton("3"));
        JButton jbtDivide;
        p1.add(jbtDivide = new JButton("/"));
        JButton jbtNum0;
        p1.add(jbtNum0 = new JButton("0"));
        JButton jbtDecimal;
        p1.add(jbtDecimal = new JButton("."));
        JButton jbtSolve;
        p1.add(jbtSolve = new JButton("="));
        JButton jbtMultiply;
        p1.add(jbtMultiply = new JButton("*"));

        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout());
        p2.add(jtfResult = new JTextField(20));
        jtfResult.setHorizontalAlignment(JTextField.RIGHT);
        jtfResult.setEditable(false);

        JPanel p3 = new JPanel();
        p3.setLayout(new GridLayout(1, 2));
        JButton jbtClear;
        p3.add(jbtClear = new JButton("C"));
        JButton jbtPlusMinus;
        p3.add(jbtPlusMinus = new JButton("+/-"));

        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(p2, BorderLayout.NORTH);
        p.add(p1, BorderLayout.CENTER);
        p.add(p3, BorderLayout.SOUTH);
        add(p);

        jbtNum1.addActionListener(new ListenToButton());
        jbtNum2.addActionListener(new ListenToButton());
        jbtNum3.addActionListener(new ListenToButton());
        jbtNum4.addActionListener(new ListenToButton());
        jbtNum5.addActionListener(new ListenToButton());
        jbtNum6.addActionListener(new ListenToButton());
        jbtNum7.addActionListener(new ListenToButton());
        jbtNum8.addActionListener(new ListenToButton());
        jbtNum9.addActionListener(new ListenToButton());
        jbtNum0.addActionListener(new ListenToButton());
        jbtDecimal.addActionListener(new ListenToButton());

        jbtAdd.addActionListener(new ListenToAdd());
        jbtSubtract.addActionListener(new ListenToSubtract());
        jbtMultiply.addActionListener(new ListenToMultiply());
        jbtDivide.addActionListener(new ListenToDivide());
        jbtSolve.addActionListener(new ListenToSolve());
        jbtClear.addActionListener(new ListenToClear());
        jbtPlusMinus.addActionListener(new ListenToPlusMinus());
    }

    class ListenToClear implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            jtfResult.setText("0");
            addBool = false;
            subBool = false;
            mulBool = false;
            divBool = false;
            operatorClicked = false;
            justSolved = false;
            START = 0;
            TEMP = 0;
            SolveTEMP = 0;
        }
    }

    class ListenToButton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            display = jtfResult.getText();
            JButton button = (JButton) e.getSource();
            String buttonLabel = button.getText();
            if (justSolved) {
                justSolved = false;
                jtfResult.setText(buttonLabel);
                operatorClicked = false;

            } else if ((operatorClicked)) {
                jtfResult.setText(buttonLabel);
                operatorClicked = false;
            } else if (display.equals("0")) {
                jtfResult.setText(buttonLabel);
            } else {
                jtfResult.setText(display + buttonLabel);
            }
        }
    }

    class ListenToPlusMinus implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            TEMP = Double.parseDouble(jtfResult.getText());
            TEMP = TEMP * -1;
            roundCheck(TEMP);
        }
    }

    class ListenToAdd implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            TEMP = Double.parseDouble(jtfResult.getText());
            solveMethod();
            START = TEMP;
            addBool = true;
            operatorClicked = true;
        }
    }

    class ListenToSubtract implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            TEMP = Double.parseDouble(jtfResult.getText());
            solveMethod();
            START = TEMP;
            subBool = true;
            operatorClicked = true;
        }
    }

    class ListenToMultiply implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            TEMP = Double.parseDouble(jtfResult.getText());
            solveMethod();
            START = TEMP;
            mulBool = true;
            operatorClicked = true;
        }
    }

    class ListenToDivide implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            TEMP = Double.parseDouble(jtfResult.getText());
            solveMethod();
            START = TEMP;
            divBool = true;
            operatorClicked = true;
        }
    }

    class ListenToSolve implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            SolveTEMP = Double.parseDouble(jtfResult.getText());
            solveMethodEquals();
        }
    }

    public void solveMethod() {
        if (addBool) {
            TEMP = TEMP + START;
        } else if (subBool) {
            TEMP = START - TEMP;
        } else if (mulBool) {
            TEMP = START * TEMP;
        } else if (divBool) {
            TEMP = START / TEMP;
        }

        roundCheck(TEMP);
        addBool = false;
        subBool = false;
        mulBool = false;
        divBool = false;
        justSolved = true;
    }

    public void solveMethodEquals() {
        if (addBool) {
            SolveTEMP = TEMP + SolveTEMP;
        } else if (subBool) {
            SolveTEMP = TEMP - SolveTEMP;
        } else if (mulBool) {
            SolveTEMP = TEMP * SolveTEMP;
        } else if (divBool) {
            SolveTEMP = TEMP / SolveTEMP;
        }

        roundCheck(SolveTEMP);
        addBool = false;
        subBool = false;
        mulBool = false;
        divBool = false;
        operatorClicked = false;
        justSolved = true;
    }

    public void roundCheck(double d) {
        if (d % 1 == 0) {
            int result = (int) d;
            jtfResult.setText(Integer.toString(result));
        } else {
            jtfResult.setText(Double.toString(d));
        }
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.pack();
        calc.setLocationRelativeTo(null);
        calc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calc.setVisible(true);
    }
}
