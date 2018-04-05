/* CS 342: Project 3: Saving and Restoring Exam and Exam Components
 * Name: Charlotte Norman
 * NetID: cnorma4
 */

import java.io.*;
import java.util.*;

public class ExamTester 
{
	public static Scanner getExamFileScanner() { // Gets the file used to create an exam.
		Scanner scanUserInput = ScannerFactory.getKeyboardScanner(); // For user input.
		Scanner scanFile; // Reads the exam file that will contain the exam contents.
		String examFileName; // The filename of the exam file.
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
	
	public static void main(String args[]) {
		Scanner scn = ScannerFactory.getKeyboardScanner(); // For user input.
		Scanner examFileScanner; // Scanner containing the exam contents.
		PrintWriter examPW; // To save the reordered exam.
		PrintWriter answersPW; // To save the student answers.
		
		String savedExamFileName = null; // The filename where the modified exam will be saved under.
		String savedAnsFileName = null; // The filename where the student answers will be saved under.
		
		File examFile;
		File ansFile;
		Scanner savedExamScanner;
		Scanner savedAnswersScanner;
		 
		Exam currExam; // Current Exam Object
		double examScore; // Final Exam Score		
		
		// Printing Program Introduction
		
		System.out.println("Starting up...");
		System.out.println("Student Name: Charlotte Norman");
		System.out.println("Student NetID: cnorma4 \n");
			
		// Creating and Executing the Exam
		
		examFileScanner = getExamFileScanner();
		
		currExam = new Exam(examFileScanner); // Step 1
				
		currExam.reorderQuestions(); // Step 2
		
		currExam.reorderMCQuestions(-1); 
		
		// Getting Exam File Name
		
		System.out.println("Please enter the filename to save the exam under: ");
		
		while (true) { // Check if the file actually exists.  If not, get a new file input.
			try {
				savedExamFileName = scn.nextLine();
				examPW = new PrintWriter(savedExamFileName); 
				break;
				
			} catch(Exception e) { // Likely a "file not found" exception, but catches any just in case.
				System.out.println("File: " + savedExamFileName);
				System.out.println("Error: That file does not exist.  Please enter a new filename: ");
			}
		}
		
		// Saving Exam	
		
		currExam.save(examPW); // Step 3
		
		System.out.println("\n~~~ Taking the Exam ~~~"); 
		
		currExam.getAnswerFromStudent(-1);  // Step 4
		
		// Getting Student Answers File Name
		
		System.out.println("\nPlease enter the filename to save the student answers under: ");
		
		while (true) { // Check if the file actually exists.  If not, get a new file input.
			try {
				savedAnsFileName = scn.nextLine();
				answersPW = new PrintWriter(savedAnsFileName); 
				break;
				
			} catch(Exception e) { // Likely a "file not found" exception, but catches any just in case.
				System.out.println("File: " + savedAnsFileName);
				System.out.println("Error: That file does not exist.  Please enter a new filename: ");
			}
		}
		
		currExam.saveStudentAnswers(answersPW); // Step 5
		
		examPW.close();
		answersPW.close();
		
		currExam = null; // Step 6 
		
		while (true) { // Check if the file actually exists.  If not, get a new file input.
			try {			
				examFile = new File(savedExamFileName); // Opens specified file.
				savedExamScanner = new Scanner(examFile); // Scanner to read updated exam file.
				break;
				
			} catch(Exception e) { // Likely a "file not found" exception, but catches any just in case.
				System.out.println("File: " + savedExamFileName);
				System.out.print("Error: That file does not exist.  Please enter a new filename: ");
				savedExamFileName = scn.nextLine();
			}
		}
		
		while (true) { // Check if the file actually exists.  If not, get a new file input.
			try {	
				ansFile = new File(savedAnsFileName); // Opens specified file.
				savedAnswersScanner = new Scanner(ansFile); // Scanner to read student answers.
				break;
				
			} catch(Exception e) { // Likely a "file not found" exception, but catches any just in case.
				System.out.println("File: " + savedAnsFileName);
				System.out.print("Error: That file does not exist.  Please enter a new filename: ");
				savedAnsFileName = scn.nextLine();
			}
		}
		
		currExam = new Exam(savedExamScanner); // Step 7
		
		currExam.restoreStudentAnswers(savedAnswersScanner); 
		
		examScore = currExam.getValue(); // Step 8
		
		if (examScore == -1.0) {
			System.out.println("There are no questions or answers to grade!");		
		}
		else {
			currExam.reportQuestionValues(); 		
			System.out.println("\n\nThe exam is finished! Your score is " + examScore + ".");
		}
		
		System.out.println("Program will now end.");
		
		// Closes all the scanners.
		
		examFileScanner.close();
		ScannerFactory.closeKeyboardScanner();
		
		return;
	}
}