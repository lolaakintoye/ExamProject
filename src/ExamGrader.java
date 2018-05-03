/* CS 342: Project 3: Saving and Restoring Exam and Exam Components
 * Name: Daisy Arellano
 * NetID: darell3
 */

import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;



public class ExamGrader extends JFrame{
	public static JTable table;
	public static JScrollPane tableContainer;
	public static TableModel tableModel;
	public static PrintWriter csvWriter;
	public static double examScore; // Final Exam Score
	public static Scanner examFileScanner; // Scanner containing the exam contents.
	public static Scanner ansFileScanner;
	public static File ansFile;
	public static Scanner nameScanner;
	public static String answerTitle;
	public static Scanner examScanner;
	public static File examFile; // The actual file object of the exam.
	private static String studentName;
	private static String examFileName;
	private static String ansFileName;
	private static String csvName;
	private static  ArrayList<String> colNames = new ArrayList<String>();
	private static ArrayList<String> data= new ArrayList<String>();
	public static Exam currExam; // Current Exam Object


	public static void main(String args[]) {

		Color cute = new Color(255, 204, 255);
		Color back = new Color(255, 255, 153);
		String[][] info = new String[1][];
		
		JFrame tableFrame = new JFrame();
		JFrame frame = new JFrame();
	    frame.setTitle("Exam Grader");
		JPanel panel_startup = new JPanel();
		JLabel name = new JLabel("<html>Starting up... <br/> Student Name: Daisy Arellano<br/>Student NetID: Darell3</html>");
		name.setFont(new Font("Courier New", Font.BOLD, 14));
		 
		JPanel panel_fileQ = new JPanel();
		JPanel panel_fileQ2 = new JPanel();
		JPanel panel_fileQ3 = new JPanel();
		JPanel panel_button = new JPanel();
	    JLabel examFileQ = new JLabel("Enter an exam filename: ");
	    JLabel studentFileQ = new JLabel("Please enter the filename to save the student answers under: ");
	    JLabel outputFileQ = new JLabel("Enter file where results should be stored: ");
	    JTextField examFileA = new JTextField(10);
	    JTextField studentFileA = new JTextField(10);
	    JTextField outputFileA = new JTextField(10);
	 
	 
	    JButton button = new JButton("submit");
	    panel_fileQ.add(examFileQ);
	    panel_fileQ.add(examFileA);
	    panel_fileQ2.add(studentFileQ);
	    panel_fileQ2.add(studentFileA);
	    panel_fileQ3.add(outputFileQ);
	    panel_fileQ3.add(outputFileA);
	    panel_button.add(button);
	    
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			    examFileName = examFileA.getText();
			    ansFileName = studentFileA.getText();
			    csvName = outputFileA.getText();
			    
			    while (true) { // Check if the file actually exists. If not, get a new file input.
					try {
						examFile = new File(examFileName); // Opens specified file.
						examFileScanner = new Scanner(examFile);
						ansFile = new File(ansFileName); // Opens specified file.
						ansFileScanner = new Scanner(ansFile);
						nameScanner = new Scanner(ansFile);
						csvWriter = new PrintWriter(csvName);
						break;

					} catch (Exception f) { // Likely a "file not found" exception, but catches any just in case.
						 JOptionPane.showMessageDialog(new JFrame(), "Sorry one of your names was incorrect. Restart and try again.", "ERROR",
							        JOptionPane.ERROR_MESSAGE);
						 
						 System.exit(0);
						
					}
				}
			    currExam = new Exam(examFileScanner); // Step 1   

				studentName = nameScanner.nextLine();
				//Title of the answer file
				answerTitle = nameScanner.nextLine();
				//save exam file name
				String temp = examFileName.toLowerCase();
				//save answer title
				answerTitle = answerTitle.toLowerCase();
				// check if files match
				
				if(answerTitle.equals(temp)) {
					System.out.println("Files Match");
				}
				else {
					System.out.println("Mismatched file please run program again with matching File.");
				}
				nameScanner.close();
				// Processing Exam File
				
				
				currExam.restoreStudentAnswers(ansFileScanner); 
				//System.out.println(scn.nextLine());
				examScore = currExam.getValue();
				if (examScore == -1.0) {
					System.out.println("There are no questions or answers to grade!");
				} else {
					currExam.reportQuestionValues();
					System.out.println("\n\nThe exam is finished! Your score is " + examScore + ".");
				}

				// scn.close();

				
				
				csvWriter.print("Students Name, Total Score, ");
				colNames.add("Student Name");
				colNames.add("Total Score");
				
				
				for (int i = 0; i < currExam.numQuestions(); i++) {
					csvWriter.print("Question " + (i + 1) + ",");
					colNames.add("Question " + (i + 1));
					
				}
				csvWriter.println();
				data.add(studentName);
				data.add(Double.toString(examScore));
				csvWriter.print(studentName + ", " + examScore);
				currExam.saveQuestionValue(csvWriter, data);
				csvWriter.close();
				//create table
				String[] stringData= new String[data.size()];
				stringData = data.toArray(stringData);
				info[0] = stringData;
				tableModel = new DefaultTableModel(info, colNames.toArray());
			    table = new JTable(tableModel);
			    int A = table.getWidth();
		        int B = table.getHeight();
			    table.setSize(A,B);
			    tableContainer = new JScrollPane(table);
			    tableFrame.add( tableContainer);
			    tableFrame.setSize(400,100);
			    tableFrame.setBackground(back);
			    tableFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    tableFrame.setVisible(true);
			    }
		});
	    
	    
	   JPanel panel_QFinal = new JPanel(new GridBagLayout());
	   GridBagConstraints gbc = new GridBagConstraints();
	   gbc.gridx = 0;
	   gbc.gridy = 0;
	   panel_QFinal.add( panel_fileQ, gbc);
	   gbc.gridy++;
	   panel_QFinal.add( panel_fileQ2,gbc);
	   gbc.gridy++;
	   panel_QFinal.add( panel_fileQ3,gbc);
	   gbc.gridy++;
	   panel_QFinal.add( panel_button,gbc);
	    
	    panel_startup.setBackground(cute);
	    panel_fileQ.setBackground(cute);
	    panel_fileQ2.setBackground(cute);
	    panel_fileQ3.setBackground(cute);
	    panel_QFinal.setBackground(cute);
	    panel_button.setBackground(cute);
	    
	    panel_startup.add(name);
	   
	    frame.add(panel_startup,BorderLayout.PAGE_START);
	    frame.add(panel_QFinal, BorderLayout.CENTER);   
	    frame.setSize(800,800);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	    tableFrame.setVisible(false);
		return;
	}
}
