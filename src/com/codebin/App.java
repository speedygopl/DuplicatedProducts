package com.codebin;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class App {
    private JButton buttonRUN;
    private JPanel PanelMain;
    private JButton selectFileButton;
    private JLabel selectFileLabel;
    private final JFileChooser openFileChooser;
    protected static String path;

    // constructor sets specification of Swing GUI application, sets action for "Select File" button and
//    sets action for "RUN" button
    public App() {
        openFileChooser = new JFileChooser();
        openFileChooser.setCurrentDirectory(new File("c:\\users\\damazy\\Downloads"));
        openFileChooser.setFileFilter(new FileNameExtensionFilter("CSV files", "csv"));

        buttonRUN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JOptionPane.showMessageDialog(null, startProgram());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
        selectFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int resultValue = openFileChooser.showOpenDialog(null);
                if (resultValue == JFileChooser.APPROVE_OPTION) {
                    try {
                        ImageIO.read(openFileChooser.getSelectedFile());
                        selectFileLabel.setText("File successfully loaded !!!");
                    } catch (IOException ioe) {
                        selectFileLabel.setText("Failed to load the file !!!");
                    }
                } else {
                    selectFileLabel.setText("No file chosen !!!");
                }
                path = openFileChooser.getSelectedFile().getAbsolutePath();
            }
        });
    }

    // method that starts methods in Program.java after push RUN button
    public String startProgram() throws IOException {
        Program program = new Program();
        return program.writeResultToFile();
    }

    // main mathod that Run application
    public static void main(String[] args) {
        JFrame frame = new JFrame("Powielone Produkty");
        frame.setContentPane(new App().PanelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
