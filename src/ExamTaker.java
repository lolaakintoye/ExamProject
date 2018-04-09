import java.io.*;
import java.util.*;

public class ExamTaker {
	public static void main(String[]args){
		System.out.println("Name: Funmilola Akintoye");
		System.out.println("netID: fakint3");
		System.out.println("Partners: Daisy Arellano, Charlotte Norman");
		System.out.println();

		//UNCOMMENT FOR USER TO GIVE FILENAME
//        System.out.print("Please enter input file: ");
//        Scanner userInput = new Scanner(System.in);
//        String filename = userInput.nextLine();
		// File file = new File(filename);

		File file = new File("src/sample_exam.txt");

		File outputFile = new File("src/reordered_exam.txt");
		File outputAnswers = new File("src/output_answers.txt");


		Scanner examScanner = null; //scan through files
		Scanner ansScanner = null; // scan user input
		PrintWriter pwExam = null; //write to exam
		PrintWriter pwAnswers = null; //write to student answers


		try {
			examScanner = new Scanner(file);
			ansScanner = ScannerFactory.getKeyboardScanner();
			pwExam = new PrintWriter(outputFile);
			pwAnswers = new PrintWriter(outputAnswers);
		}
		catch(FileNotFoundException e){
			System.out.println("FILENOTFOUND");
			e.printStackTrace();
		}

		//Exam exam = new Exam(examScanner);
		String userName = null;
		System.out.print("Please enter your name: ");
		userName = ansScanner.nextLine();
		Exam exam = new Exam(examScanner);
		exam.print();
		//System.out.println(userName);
		pwAnswers.println(userName);
		pwAnswers.println();
//		exam.reorderQuestions();
//		exam.save(pwExam);
//		exam.saveStudentAnswers(pwAnswers);
//
//
//		pwExam.close();
//		pwAnswers.close();
//		exam = null;
//
//
//		try{
//			examScanner = new Scanner(outputFile);
//
//		}catch(FileNotFoundException e){
//			System.out.println("FILE NOT FOUND");
//			e.printStackTrace();
//
//		}
//		exam = new Exam(examScanner);
//		exam.restoreStudentAnswers(ansScanner);
//
//
//		//fileScanner.close();
//
//
//
		pwAnswers.close();
		pwExam.close();
	}
}

