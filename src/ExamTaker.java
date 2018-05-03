/*
* Funmilola Akintoye
* fakint3
* Parners: Daisy Arellano, Charlotte Norman
* */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class ExamTaker {


    public static Scanner scanFile = null; // Reads the exam file that will contain the exam contents.
    public static String examFileName; // The filename of the exam file.
    public static File examFile; // The actual file object of the exam.
    public static String userName = null;
    public static  Exam currExam;
    public static String nameText = null;
    public  static String examText = null;
    public static String loadFile = null;
    public static CardLayout cardLayout;
    public static JPanel examPanel;
    //public static JTextArea examArea = null;
    public static void guiInterface() {


        JLabel name, exam;
        JTextField nameField, examField;
        JButton loadButton = new JButton("Load");
        JFrame frame = new JFrame();


        frame.setTitle("Exam Taker");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        //frame.setLayout(new CardLayout());

        JFrame examFrame = new JFrame();
        examFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        examFrame.setSize(500, 500);
        examFrame.setVisible(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setFont(new Font("Serif", Font.PLAIN, 16));
        mainPanel.setBackground(new Color(181, 221, 255));
        name = new JLabel("Name: ");
        nameField = new JTextField(10);

        exam = new JLabel("Load File: ");
        examField = new JTextField(10);

        JTextArea textArea = new JTextArea(10, 30);
        JTextArea examArea = new JTextArea(10, 30);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        mainPanel.add(name, gbc);
        gbc.gridy++;
        mainPanel.add(exam, gbc);
        gbc.gridy++;

        gbc.gridx++;
        gbc.gridy = 0;
        mainPanel.add(nameField, gbc);
        gbc.gridy++;
        mainPanel.add(examField, gbc);
        gbc.gridx = 1;
        gbc.gridy++;

        examPanel = new JPanel(new GridLayout(1,0));

        //examPanel.setLayout(new GridBagLayout());
        GridBagConstraints examConst = new GridBagConstraints();

        cardLayout = new CardLayout();

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                examText = examField.getText();
                nameText = nameField.getText();


                    try{
                        examFile = new File(examText);
                        scanFile = new Scanner(examFile);


                    }catch(Exception except){
                        System.out.println("File (" + examFile + ") not found.");
                    }

                currExam = new Exam(scanFile);
                currExam.print();

                currExam.guiPrint(examPanel, examArea);

                examPanel.add(examArea);
                examFrame.add(examPanel);
                examFrame.setVisible(true);




            }
        });
        mainPanel.add(loadButton, gbc);


        examPanel.setLayout(cardLayout);
       // examPanel.add(page);
        //examPanel.setVisible(true);
        cardLayout.show(examPanel, "Test Panel");



        frame.add(mainPanel, BorderLayout.CENTER);
       // frame.add(examPanel, BorderLayout.CENTER);

        frame.setVisible(true);





    }


    public static PrintWriter getAnsWriter() { // Gets the file used to create an exam.
        Scanner scn = ScannerFactory.getKeyboardScanner(); // For user input.
        PrintWriter answersPW; // To save the student answers.
        String savedAnsFileName = null; // The filename where the student answers will be saved under.
        System.out.println("\nPlease enter the filename to save the student answers under: ");

        while (true) { // Check if the file actually exists.  If not, get a new file input.
            try {
                savedAnsFileName = scn.nextLine();
                answersPW = new PrintWriter(savedAnsFileName);
                break;

            } catch (Exception e) { // Likely a "file not found" exception, but catches any just in case.
                System.out.println("File: " + savedAnsFileName);
                System.out.println("Error: That file does not exist.  Please enter a new filename: ");
            }
        }
        return answersPW;
    }

    public static void main(String args[]) {

        guiInterface();

        return;
    }
}
