/* CS 342: Project 4: Developing Applications Using Exam-Related Classes
 * Name: Charlotte Norman
 * NetID: cnorma4
 */

import java.io.*;
import java.util.*;

public class ExamBuilder 
{
	private static void printMenu() {
		System.out.println("\nPlease select a menu option below: ");
		System.out.println("\t a) Load a saved exam from a file.");
		System.out.println("\t b) Add questions interactively.");
		System.out.println("\t c) Remove questions interactively.");
		System.out.println("\t d) Reorder questions and/or answers.");
		System.out.println("\t e) Print the exam: to the screen or to a file suitable for hard-copy printing.");
		System.out.println("\t f) Save the exam using in a file format.");
		System.out.println("\t q) Quit.\n");
	}
	
	private static Scanner getExamFileScanner() { // Gets the file used to create an exam.
		Scanner scn = ScannerFactory.getKeyboardScanner(); // For user input.
		Scanner scanFile; // Reads the exam file that will contain the exam contents.
		File examFile; // Exam File
		String examFileName; // Exam Filename
		
		// Getting User Input for the Exam File
		
		System.out.print("Enter an exam filename: ");	 
		examFileName = scn.nextLine();
		
		while (true) { // Check if the file actually exists.  If not, get a new file input.
			try {			
				examFile = new File(examFileName); // Opens specified file.
				scanFile = new Scanner(examFile); 
				break;
				
			} catch(Exception e) { // Likely a "file not found" exception, but catches any just in case.
				System.out.println("File: " + examFileName);
				System.out.print("Error: That file does not exist.  Please enter a new filename: ");
			}
		}
				
		return scanFile;
	}
	
	private static PrintWriter getExamFileWriter() { // Gets the file used to save an exam.
		Scanner scn = ScannerFactory.getKeyboardScanner(); // For user input.
		PrintWriter savedFile; // To save the modified exam.
		String savedExamFileName; // The filename where the modified exam will be saved under.
		
		// Getting User Input to Create the Exam Print Writer
		
		System.out.print("Please enter the filename to save the exam under: ");
		savedExamFileName = scn.nextLine();
		
		while (true) { // Check if the file actually exists.  If not, get a new file input.
			try {		
				savedFile = new PrintWriter(savedExamFileName); 
				break;
				
			} catch(Exception e) { // Likely a "file not found" exception, but catches any just in case.
				System.out.println("File: " + savedExamFileName);
				System.out.println("Error: That file does not exist.  Please enter a new filename: ");
			}
		}
		
		return savedFile;
	}
	
	private static Exam createExam() { // Creates a new exam from scratch.
		Scanner scn = ScannerFactory.getKeyboardScanner(); // For user input.
		Exam currExam; // Exam 
		String title; // Exam Title
		
		System.out.print("Please enter a title for the exam: ");
		title = scn.nextLine();
		
		currExam = new Exam(title);
		
		return currExam;
	}
	
	private static Exam loadExam(Exam currExam) { // Creates a new exam from file.
		if (currExam != null) {
			System.out.println("Cannot load an exam because another exam is being modified.");
			return currExam;
		}
		
		return new Exam(getExamFileScanner()); // Exam
	}
	
	private static Exam addQuestionsIA(Exam currExam) {
		Scanner scn = ScannerFactory.getKeyboardScanner(); // For user input.
		//String[] questionTypes = new String[] {"MCSAQuestion", "MCMAQuestion", "SAQuestion", "NumQuestion"}; // Array to hold question types.
		int userChoice; // The user choice to continue adding questions or not.
		double maxValue; // The maximum points a question is worth.
		String question; // Actual text of a question.
		String answer; // Actual text of an answer.
		int numAdded = 0; // Number of questions added to exam.
		int numAnswers; // Number of answers for question.
		double credit; // Full or partial credit for each multiple choice answer.
		boolean toContinue = true; // Will exit the loop once the user chooses to.
		
		if (currExam == null) { // If an exam was not previously loaded, then create a new one manually.
			currExam = createExam();
		}
		
		do {
			// Print Menu Options
			System.out.println("\n\t 1) Multiple Choice - Single Answer (MCSAQuestion)");
			System.out.println("\t 2) Multiple Choice - Multiple Answers (MCMAQuestion)");
			System.out.println("\t 3) Short Answer (SAQuestion)");
			System.out.println("\t 4) Numerical Answer (NumQuestion)");
			System.out.println("\t 5) Quit\n");
			System.out.println("What type of question do you want to add?  Enter in the number:");
			
			userChoice = Integer.parseInt(scn.nextLine()); // Gets the actual position of the question within the array.
			
			switch(userChoice) {
				case 1: // MCSAQuestion					
					System.out.println("\nPlease enter the question: ");
					question = scn.nextLine();
					
					System.out.println("\nPlease enter the max value for this question: ");
					maxValue = Double.parseDouble(scn.nextLine());
					
					System.out.println("\nPlease enter the total number of answers for this question: ");
					numAnswers = Integer.parseInt(scn.nextLine());
					
					MCSAQuestion mcsaQues = new MCSAQuestion(question, maxValue);
					MCSAAnswer mcsaAns; 
					
					for (int i = 0; i < numAnswers; i++) {
						System.out.println("\nPlease enter the answer for this question: ");
						answer = scn.nextLine();
						
						System.out.println("\nPlease enter the (partial) credit for this answer: ");
						credit = Double.parseDouble(scn.nextLine());
						
						mcsaAns = (MCSAAnswer)mcsaQues.getNewAnswer(answer, credit);
						mcsaQues.addAnswer(mcsaAns); // Adding answer to question.
					}
					
					currExam.addQuestion(mcsaQues);
					numAdded++;
					System.out.println("\nQuestion added.");
					
					break;
				case 2: // MCMAQuestion
					double baseCredit; // Base credit to balance the multiple answer credits.

					System.out.println("\nPlease enter the question: ");
					question = scn.nextLine();
					
					System.out.println("\nPlease enter the max value for this question: ");
					maxValue = Double.parseDouble(scn.nextLine());
					
					System.out.println("\nPlease enter the base credit for this question: ");
					baseCredit = Double.parseDouble(scn.nextLine());
					
					System.out.println("\nPlease enter the total number of answers for this question: ");
					numAnswers = Integer.parseInt(scn.nextLine());
					
					MCMAQuestion mcmaQues = new MCMAQuestion(question, maxValue, baseCredit);
					MCMAAnswer mcmaAns;
					
					for (int i = 0; i < numAnswers; i++) {
						System.out.println("\nPlease enter the answer for this question: ");
						answer = scn.nextLine();
						
						System.out.println("\nPlease enter the (partial) credit for this answer: ");
						credit = Double.parseDouble(scn.nextLine());
						
						mcmaAns = (MCMAAnswer)mcmaQues.getNewAnswer(answer, credit);
						mcmaQues.addAnswer(mcmaAns); // Adding answer to question.
					}
					
					currExam.addQuestion(mcmaQues);			
					numAdded++;
					System.out.println("\nQuestion added.");
					
					break;
				case 3: // SAQuestion
					System.out.println("\nPlease enter the question: ");
					question = scn.nextLine();
					
					System.out.println("\nPlease enter the max value for this question: ");
					maxValue = Double.parseDouble(scn.nextLine());
					
					System.out.println("\nPlease enter the answer for this question: ");
					answer = scn.nextLine();
					
					SAQuestion saQues = new SAQuestion(question, maxValue);
					saQues.getNewAnswer(answer);
					
					currExam.addQuestion(saQues);
					numAdded++;
					System.out.println("\nQuestion added.");
					
					break;
				case 4: // NumQuestion
					System.out.println("\nPlease enter the question: ");
					question = scn.nextLine();
					
					System.out.println("\nPlease enter the max value for this question: ");
					maxValue = Double.parseDouble(scn.nextLine());
					
					System.out.println("\nPlease enter the answer for this question: ");
					answer = scn.nextLine();
					
					System.out.println("\nPlease enter the answer tolerance for this question: ");
					Double tolerance = Double.parseDouble(scn.nextLine());
					
					NumQuestion numQues = new NumQuestion(question, maxValue, tolerance);
					numQues.getNewAnswer(Double.parseDouble(answer));
					
					currExam.addQuestion(numQues);
					numAdded++;
					System.out.println("\nQuestion added.");
					
					break;
				case 5:
					toContinue = false;
					break;
				default:
					System.out.println("\nPlease pick a valid question type or select Quit.");
					break;
			}
		} while (toContinue);
		
		
		System.out.println("\n" + numAdded + " questions have been removed from the exam.");
		
		return currExam;
	}
	
	private static Exam removeQuestionsIA(Exam currExam) {
		if (currExam == null) { 
			System.out.println("\nThere is no exam available, please load a exam first.");
			return null;
		}
		
		Scanner scn = ScannerFactory.getKeyboardScanner(); // For user input.
		
		boolean toContinue = true; // Will exit the loop once the user chooses to.
		int questionNum; // The question number in the exam.
		int numRemoved = 0; // Number of questions removed from exam.
		char userInput; // The user choice to continue removing questions or not.
		
		while (toContinue) {
			if (currExam.questions.size() < 1) {
				System.out.println("\nThere are no more questions to delete.");
				return currExam;
			}
			
			currExam.print(); // Prints exam to see which questions are available to delete.
			
			System.out.print("Which question do you want to delete? Please enter the Question #: ");
			questionNum = Integer.parseInt(scn.nextLine());
			
			if (questionNum > 0 && questionNum <= currExam.questions.size()) { // Makes sure the selection is within range.
				currExam.questions.remove((questionNum - 1)); // Gets the actual position of the question within the array.
				numRemoved++;
				
				System.out.println("Question " + questionNum + " has been removed.");			
			}
			else {
				System.out.println("\nPlease enter a valid Question #.");
			}
			
			System.out.println("Do you want to continue? (Y/N)");
			userInput = scn.nextLine().charAt(0); // Gets the first character of the input string only.
			
			switch (userInput) {
				case 'Y':
				case 'y':
					break;
				default:
					toContinue = false;					
					break;
			}
		}
		
		System.out.println("\n" + numRemoved + " questions have been removed from the exam.");
		
		return currExam;
	}
	
	private static void reorderExam(Exam currExam) {
		if (currExam == null) {
			System.out.println("\nThere is no exam available, please load a exam first.");
			return;
		}
		
		currExam.reorderQuestions();
		currExam.reorderMCQuestions(-1); 
	}
	
	private static void printExam(Exam currExam) {
		if (currExam == null) {
			System.out.println("\nThere is no exam available, please load a exam first.");
			return;
		}
		
		currExam.print();
	}
	
	private static void saveExam(Exam currExam) {
		if (currExam == null) {
			System.out.println("\nThere is no exam available, please load a exam first.");
			return;
		}
		
		currExam.save(getExamFileWriter()); // Saves exam using a print writer.
	}
	
	public static void main(String args[]) {
		Scanner scn = ScannerFactory.getKeyboardScanner(); // For user input.
		boolean toContinue = true; // This will terminate the loop once the user decides to quit.
		char menuChoice; // Menu option that was entered/selected.
		 
		Exam currExam = null; // Current Exam Object
		
		// Printing Program Introduction
		
		System.out.println("Starting up...");
		System.out.println("Student Name: Charlotte Norman");
		System.out.println("Student NetID: cnorma4 \n");
			
		// Printing Menu Options and Collecting User's Choice	
		
		while (toContinue) {
			printMenu();
			
			System.out.print("Choice: ");
			menuChoice = scn.nextLine().charAt(0); // Gets the first character of the input string only.
			
			switch (menuChoice) {
				case 'a':
					currExam = loadExam(currExam);
					break;
				case 'b':
					currExam = addQuestionsIA(currExam);
					break;
				case 'c':
					currExam = removeQuestionsIA(currExam);
					break;
				case 'd':
					reorderExam(currExam);
					break;
				case 'e':
					printExam(currExam);
					break;
				case 'f':
					saveExam(currExam);
					break;
				case 'q': 
					System.out.println("\nApplication will now exit.");
					toContinue = false;
					break;
				default:
					System.out.println("\nUnable to understand menu selection.  Please try again.");
					break;
			}
		}
		
		// Closes all the scanners.
		
		scn.close();
		
		return;
	}
}