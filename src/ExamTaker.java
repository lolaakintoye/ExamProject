import java.io.*;
import java.util.*;

public class ExamTaker
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

			} catch(Exception e) { // Likely a "file not found" exception, but catches any just in case.
				System.out.println("File: " + savedAnsFileName);
				System.out.println("Error: That file does not exist.  Please enter a new filename: ");
			}
		}
		return answersPW;
	}

	public static void main(String args[]) {
		Scanner scn = ScannerFactory.getKeyboardScanner(); // For user input.
		Scanner examFileScanner; // Scanner containing the exam contents.
		PrintWriter examPW; // To save the reordered exam.


		String savedExamFileName = null; // The filename where the modified exam will be saved under.


		File examFile;
		File ansFile;
		Scanner savedExamScanner;
		Scanner savedAnswersScanner;

		Exam currExam; // Current Exam Object

		examFileScanner = getExamFileScanner();

		currExam = new Exam(examFileScanner); // Step 1


		System.out.println("\n~~~ Taking the Exam ~~~");

		//get answer from student for all questions if positon = -1
		currExam.getAnswerFromStudent(-1);  // Step 4

		//Confirm list of answers
		System.out.println("Review yours below:");


		PrintWriter answersPW = getAnsWriter();
		currExam.saveStudentAnswers(answersPW); // Step 5


		answersPW.close();


		System.out.println("Program will now end.");

		// Closes all the scanners.
		ScannerFactory.closeKeyboardScanner();

		return;
	}
}