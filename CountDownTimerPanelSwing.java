package project1;

import javax.swing.*;
import javax.xml.soap.Text;

import project1.CountDownTimer;
import sun.jvm.hotspot.memory.Space;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
/**
 * Write a description  here.
 *
 * @author Ferguson & Nguyen
 * @version Sep 22, 2021
 */
public class CountDownTimerPanelSwing extends JPanel {

    private CountDownTimer watch;
    private Timer javaTimer;

    private JButton startButton, stopButton, saveButton, loadButton, addButton, stringInputButton, continueButton;;
    private JTextField hourField, minField, secondField, addSecondsField, newStringField;

    private JLabel lblTime;

    public CountDownTimerPanelSwing() {

        // create the game object as well as the GUI1024 Frame
        watch = new CountDownTimer();
        javaTimer = new Timer(1000, new TimerListener());

        setLayout(new GridLayout(9, 2));
        setBackground(Color.pink);

        // Code goes on...
        hourField = new JTextField();
        add(hourField);
        add(new JLabel("Hours:"));

        minField = new JTextField();
        add(minField);
        add(new JLabel("Minutes:"));

        secondField = new JTextField();
        add(secondField);
        add(new JLabel("Seconds:"));

        startButton = new JButton("Start");
        add(startButton);

        startButton.addActionListener(new ButtonListener());
        // Code goes on...
        stopButton = new JButton("Stop");
        add(stopButton);
        stopButton.addActionListener(new ButtonListener());

        saveButton = new JButton("Save");
        add(saveButton);
        saveButton.addActionListener((new ButtonListener()));

        loadButton = new JButton("Load");
        add(loadButton);
        loadButton.addActionListener(new ButtonListener());

        addButton = new JButton("Add");
        add(addButton);
        addSecondsField = new JTextField();
        add(addSecondsField);

        addButton.addActionListener(new ButtonListener());

        stringInputButton = new JButton("New");
        add(stringInputButton);
        newStringField = new JTextField("0:0:0");
        add(newStringField);
        stringInputButton.addActionListener(new ButtonListener());

        continueButton = new JButton("Continue");
        add(continueButton);
        continueButton.addActionListener((new ButtonListener()));

        lblTime = new JLabel();
        lblTime.setText(watch.toString());
        add(new JLabel(""));
        add(lblTime);
        add(new JLabel("Time:"));

    }

    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            if (event.getSource() == stopButton) {
                javaTimer.stop();
            }

            if (event.getSource() == startButton) {
                int min, sec, milli;
                try {
                    min = Integer.parseInt(hourField.getText());
                    sec = Integer.parseInt(minField.getText());
                    milli = Integer.parseInt(secondField.getText());
                    watch = new CountDownTimer(min, sec, milli);
                    javaTimer.start();
                } catch (NumberFormatException io) {
                    JOptionPane.showMessageDialog(null, "Enter an integer in all fields");
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Error in field");
                }
            }

            if (event.getSource() == addButton) {
                int inputSec;
                inputSec = Integer.parseInt(addSecondsField.getText());
                watch.add(inputSec);
                secondField.setText(String.format("%d", watch.getSeconds()));
            }

            if (event.getSource() == saveButton) {
                JFileChooser fileChooser = new JFileChooser();
                JTextArea textArea = new JTextArea(20, 30);
                fileChooser.setDialogTitle("Specify a file to save");
                int userSelection = fileChooser.showSaveDialog(null);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    watch.save(fileToSave.getAbsolutePath());
                    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
                }
            }

            if (event.getSource() == loadButton) {
                JTextArea textArea = new JTextArea(20, 30);
                JFileChooser fileChooser = new JFileChooser();
                int status = fileChooser.showOpenDialog(null);
                if (status == JFileChooser.APPROVE_OPTION) {
                    File fileToOpen = fileChooser.getSelectedFile();
                    System.out.println("Selected file: " + fileToOpen.getAbsolutePath());
                    try {
                        watch.load(fileToOpen.getAbsolutePath());
                    } catch (IllegalArgumentException e) {
                        JOptionPane.showMessageDialog(null, "Enter a valid file");
                    }
                }
            }

            if (event.getSource() == stringInputButton) {
                watch = new CountDownTimer(newStringField.getText());
                hourField.setText(String.format("%d", watch.getHours()));
                minField.setText(String.format("%d", watch.getMinutes()));
                secondField.setText(String.format("%d",watch.getSeconds()));
            }

            if (event.getSource() == continueButton) {
                javaTimer.start();
            }
            lblTime.setText(watch.toString());
        }
    }

    private class TimerListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {
                watch.sub(1);
                lblTime.setText(watch.toString());
                if (watch.getSeconds() == 0 && watch.getMinutes() == 0 && watch.getHours() == 0) {
                    JOptionPane.showMessageDialog(null, "Happy New Year");
                }
            }
            catch (Exception exception) {

            }
        }
    }
}