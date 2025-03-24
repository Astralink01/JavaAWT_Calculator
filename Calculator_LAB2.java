/*

 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license

 */

 

package com.mycompany.calculator_lab2;

 

import java.awt.*;

import java.awt.event.*;

 

public class Calculator_LAB2 {

    private final Frame frame;

    private final TextField textField;

    private String currentInput = "";

    private double firstNum = 0;

    private String operator = "";

 

    public Calculator_LAB2() {

        frame = new Frame("Calculator");

        frame.setSize(400, 500);

        frame.setLayout(null);

        frame.setBackground(Color.DARK_GRAY);

 

        textField = new TextField();

        textField.setEditable(false);

        textField.setFont(new Font("SansSerif", Font.BOLD, 36));

        textField.setBackground(Color.BLACK);

        textField.setForeground(Color.WHITE);

        textField.setBounds(20, 50, 360, 60);

        frame.add(textField);

 

        String[] buttonLabels = {

                "7", "8", "9", "/",

                "4", "5", "6", "*",

                "1", "2", "3", "-",

                "0", ".", "=", "+",

                "C"

        };

 

        int x = 20, y = 130, width = 80, height = 60;

        for (String label : buttonLabels) {

            Button button = new Button(label);

            button.setFont(new Font("SansSerif", Font.BOLD, 24));

            button.setBackground(new Color(70, 130, 180));

            button.setForeground(Color.WHITE);

            button.setBounds(x, y, width, height);

            button.addActionListener(new ButtonClickListener(button));

            frame.add(button);

 

            x += 90;

            if (x > 300) {

                x = 20;

                y += 70;

            }

        }

 

        frame.addWindowListener(new WindowAdapter() {

            @Override

            public void windowClosing(WindowEvent e) {

                System.exit(0);

            }

        });

 

        frame.setVisible(true);

 

    }

 

    private class ButtonClickListener implements ActionListener {

        private final Button button;

 

 

        public ButtonClickListener(Button button) {

            this.button = button;

 

 

        }

 

        @Override

        public void actionPerformed(ActionEvent e) {

            String command = e.getActionCommand();

 

 

            button.setBackground(new Color(100, 149, 237));

            new Thread(() -> {

                try {

                    Thread.sleep(100);

                    button.setBackground(new Color(70, 130, 180));

                } catch (InterruptedException ignored) {

                }

            }).start();

 

            switch (command) {

                case "=" -> calculate();

                case "/", "*", "-", "+" -> {

                    if (!currentInput.isEmpty()) {

                        firstNum = Double.parseDouble(currentInput);

                        operator = command;

                        currentInput = "";

                        textField.setText("");

                    }

                }

                case "C" -> {

                    currentInput = "";

                    firstNum = 0;

                    operator = "";

                    textField.setText("");

                }

                default -> {

                    currentInput += command;

                    textField.setText(currentInput);

                }

            }

        }

 

        private void calculate() {

            if (currentInput.isEmpty()) return;

 

            double secondNum = Double.parseDouble(currentInput);

            double result = 0;

 

            switch (operator) {

                case "+" -> result = firstNum + secondNum;

                case "-" -> result = firstNum - secondNum;

                case "*" -> result = firstNum * secondNum;

                case "/" -> result = (secondNum != 0) ? firstNum / secondNum : Double.NaN;

            }

 

            textField.setText(Double.isNaN(result) ? "Error" : String.valueOf(result));

            currentInput = Double.isNaN(result) ? ""

 : String.valueOf(result);

        }

    }

 

    public static void main(String[] args) {

        new Calculator_LAB2();

    }

}
