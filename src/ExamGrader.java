/* CS 342: Project 3: Saving and Restoring Exam and Exam Components
 * Name: Daisy Arellano
 * NetID: cnorma4
 */

import java.io.*;
import java.util.*;

import java.io.FileWriter;

import java.io.IOException;

import java.util.ArrayList;

import java.util.List;

public class ExamGrader 
{
	private static String studentName;
	private static String examFileName;
	private static String ansFileName;
	public static Scanner getExamFileScanner() { // Gets the file used to create an exam.
		Scanner scanUserInput = ScannerFactory.getKeyboardScanner(); // For user input.
		Scanner scanFile; // Reads the exam file that will contain the exam contents.
		//String examFileName; // The filename of the exam file.
		File examFile; // The actual file object of the exam.
		
		// Getting User Input for the Exam File
		
		System.out.print("Enter an exam filename: ");
		examFileName = scanUserInput.nextLine();
		
		// Processing Exam File 
			
		while (true) { // Check if the file actually exists.  If not, get a new file input.
			try {
				examFile = new File(examFileName); // Opens specified file.
				scanFile = new Scanner(examFile); 
				break;
				
			} catch(Exception e) { // Likely a "file not found" exception, but catches any just in case.
				System.out.println("File: " + examFileName);
				System.out.print("Error: That file does not exist.  Please enter a new filename: ");
				examFileName = scanUserInput.nextLine();
			}
		}
				
		return scanFile;
	}
	
	public static Scanner getAnsFileScanner(String ansFileName) { // Gets the file used to create an Answer File.
		Scanner savedAnswersScanner = ScannerFactory.getKeyboardScanner(); // For user input.
		//String ansFileName; // The filename of the exam file.
		File ansFile; // The actual file object of the exam.
		
		// Getting User Input for the Exam File
		
		// Processing Exam File 
		while (true) { // Check if the file actually exists.  If not, get a new file input.
			try {	
				ansFile = new File(ansFileName); // Opens specified file.
				savedAnswersScanner = new Scanner(ansFile); // Scanner to read student answers.
				break;
				
			} catch(Exception e) { // Likely a "file not found" exception, but catches any just in case.
				System.out.println("File: " + ansFileName);
				System.out.print("Error: That file does not exist.  Please enter a new filename: ");
				ansFileName = savedAnswersScanner.nextLine();
			}
		}
				
		return savedAnswersScanner;
	}
	
	
	public static void main(String args[]) {
		Scanner scn = ScannerFactory.getKeyboardScanner(); // For user input.
		Scanner examFileScanner; // Scanner containing the exam contents.
		Scanner examScanner;
		String csvName;
		Scanner csvScanner;
		String AnswerTitle;
		PrintWriter csvWriter;
		Exam currExam; // Current Exam Object
		double examScore; // Final Exam Score		
		
		// Printing Program Introduction
		
		System.out.println("Starting up...");
		System.out.println("Student Name: Daisy Arellano");
		System.out.println("Student NetID: darell3 \n");
			
		// Creating and Executing the Exam
		
		examFileScanner = getExamFileScanner();
		
		currExam = new Exam(examFileScanner); // Step 1	
		System.out.println("\nPlease enter the filename to save the student answers under: ");
		ansFileName = scn.nextLine();
		scn = getAnsFileScanner(ansFileName);
		//String FileLine;
				
		//System.out.println(scn.nextLine());
		studentName = scn.nextLine();
		//Title of the answer file
		AnswerTitle = scn.nextLine();
		//save exam file name
		String temp = examFileName.toLowerCase();
		//save answer title
		AnswerTitle = AnswerTitle.toLowerCase();
		// check if files match
		if(AnswerTitle.equals(temp)) {
			System.out.println("Files Match");
		}
		else {
			System.out.println("Mismatched file please run program again with matching File.");
		}
		scn.close();

		scn = getAnsFileScanner(ansFileName);
		//System.out.println();

		currExam.restoreStudentAnswers(scn); 
		
	    //currExam.print();
		
		examScore = currExam.getValue();
		
		if (examScore == -1.0) {
			System.out.println("There are no questions or answers to grade!");		
		}
		else {
			currExam.reportQuestionValues(); 		
			System.out.println("\n\nThe exam is finished! Your score is " + examScore + ".");
		}
		
		//scn.close();
		
		System.out.println("Enter file where results should be stored: ");
		
		scn = ScannerFactory.getKeyboardScanner(); // For user input.
		
		csvName = scn.nextLine();
		
		while (true) { // Check if the file actually exists.  If not, get a new file input.
			try {	
				//File csvFile = new File(csvName); // Opens specified file.
				csvWriter = new PrintWriter (csvName);
				break;
				
			} catch(Exception e) { // Likely a "file not found" exception, but catches any just in case.
				System.out.println("File: " + ansFileName);
				System.out.print("Error: That file does not exist.  Please enter a new filename: ");
			}
		}
		csvWriter.print("Students Name, Total Score, ");
		for(int i = 0; i < currExam.numQuestions(); i++) {
			csvWriter.print("Question " + (i+1) + ",");
		}
		csvWriter.println();
		csvWriter.print(studentName + ", " + examScore);
		currExam.saveQuestionValue(csvWriter);
		csvWriter.close ();  
		examFileScanner.close();
		ScannerFactory.closeKeyboardScanner();
		
		return;
	}
}
