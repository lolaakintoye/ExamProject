//* CS 342: Project 4: Developing Applications Using Exam-Related Classes
 * Name: Daisy Arellano
 * NetID: darell3
 */

import java.io.*;
import java.util.*;
import java.text.*;
//import java.time.*;

public class Exam
{
	private String title; // Title of the exam.
	protected ArrayList<Question> questions; // Array list to hold Question objects (exam questions).
    private int numQuestions; // Total number of questions in an exam.    
	private double studentScore; // Actual Student Score 
	private double maxScore; // Total Possible Score
	private double examScore; // Exam Score
	
	private String[] questionTypes = new String[] {"mcsaquestion", "mcmaquestion", "saquestion", "numquestion"}; // Array to hold question types.
	private String fileLine; // The lines of the exam file.
		
	public Exam(String title) {
		this.title = title;
		questions = new ArrayList<Question>();
		numQuestions = 0;
		studentScore = 0.0;
		maxScore = 0.0;
		examScore = 0.0;
	}
	
	public Exam(Scanner scn) {
		questions = new ArrayList<Question>();
		numQuestions = 0;
		studentScore = 0.0;
		maxScore = 0.0;
		examScore = 0.0;

		// Reading in Exam Information
		
		title = scn.nextLine(); // Gets exam title from scanner.
		
		while (scn.hasNextLine()) { // Reads until EOF.			
			fileLine = scn.nextLine();
			
			while (fileLine.length() == 0) { // If the current line is empty (length 0) then skip to the next until it is not.
				fileLine = scn.nextLine();
			}
			
			for (int i = 0; i < questionTypes.length; i++) {
				if ((fileLine.toLowerCase().equals(questionTypes[i]))) { // If the line equals one of the question types, then create the question.			
					switch(fileLine.toLowerCase()) {
						case "mcsaquestion": // Creates MCSAQuestion object.
							this.addQuestion(new MCSAQuestion(scn)); // Adds question to exam.
							break;
						case "mcmaquestion": // Creates MCMAQuestion object.
							this.addQuestion(new MCMAQuestion(scn)); // Adds question to exam.
							break;
						case "saquestion": // Creates SAQuestion object.
							this.addQuestion(new SAQuestion(scn)); // Adds question to exam.
							break;
						case "numquestion": // Creates NumQuestion object.
							this.addQuestion(new NumQuestion(scn)); // Adds question to exam.
							break;
						default:
							break;				
					}
				}
			}				
		}
			
		System.out.println("\n\n" + numQuestions + " questions has been added to the exam.\n");	
	}
	
	public void print() {	
		int i = 0;
		
		System.out.println("\nExam: " + title + "\n"); // Prints exam title.
		
		for (Question q: questions) { // Prints Questions
			System.out.print("Question " + (i + 1) + ". ");
			q.print(); 
			i++; // To get the questions' positions in their current order.
		}
	}
	
	public void addQuestion(Question quesObj) {
		questions.add(quesObj);
		numQuestions++;				
	}
	
	public void reorderQuestions() { // Reordering all the questions.	
		Collections.shuffle(questions); 
	}
	
	public void reorderMCQuestions(int position) {
		if (position < 0) { // Reordering all the multiple choice questions' answers.
		
			for (Question q: questions) { 
				if (q instanceof MCQuestion) { // Checking if the question is a multiple choice type.
					((MCQuestion) q).reorderAnswers();
				}
			}
		}
		else if (position < numQuestions) { // Makes sure the position is in bounds.
											// Reordering just the applicable multiple choice question's answers.
			Question q = questions.get(position);
			
			if (q instanceof MCQuestion) {
				((MCQuestion) q).reorderAnswers(); // Checking if the question is a multiple choice type.
			}
		}
	}
	
	public void getAnswerFromStudent(int position) {		
		if (position < 0) { // Gets an answer for every question in the exam.	
			for (Question q: questions) { 
				q.getAnswerFromStudent();
			}
		}
		else if (position < numQuestions) { // Makes sure the position is in bounds.
											// Just gets an answer for a particular question.
			Question q = questions.get(position);
			q.getAnswerFromStudent();
		}		
	}
	
	public double getValue() {		
		for (Question q: questions) { // Goes through each question to get the two scores to sum up separately.
			studentScore += q.getValue();
			maxScore += q.getMaxValue();
		}
		
		if (studentScore == 0.0 && maxScore == 0.0) { // Error checking: in case there are no questions on the exam to grade.
			examScore = -1.0;
		}
		else {
			examScore = studentScore;
		}
		
		return examScore;
	}
	
	public void reportQuestionValues() {
		int i = 0;
		
		System.out.println("\n\n              +---- " + title + " Exam Score ----+                 ");
		System.out.println("+----            Question  | Question Score         ----+");
		
		for (Question q: questions) { // Goes through each question to print out relevant scoring information.
			System.out.println("+----           Question " + (i + 1) + " | " + new DecimalFormat("#.##").format(q.getValue()) + "                    ----+"); // Keeps it to two decimal places.
			i++;
		}
		
		System.out.println("+----      Your Score: " + examScore + " | Total Score: " + maxScore + "      ----+");
	}
	
	public void save(PrintWriter pw) {
		Date currentDate = new Date();
		
		pw.println(title);
		pw.println(currentDate.toString());
		
		for (Question q: questions) {
			pw.println("");
			q.save(pw);		
		}
	}
	
	public void saveStudentAnswers(PrintWriter pw) {
		Date currentDate = new Date();
		
		Scanner scn = ScannerFactory.getKeyboardScanner(); // The shared System.in scanner.
		pw.println(currentDate.toString());
		
		System.out.print("\nWhat is your name? ");
		pw.println(scn.nextLine());
		pw.println(title);
		
		for (Question q: questions) {
			pw.println("");
			q.saveStudentAnswer(pw);		
		}
	}
	
	public void restoreStudentAnswers(Scanner scn) {
		scn.nextLine(); // Gets the student name and discards.
		
		scn.nextLine(); // scan title and discards
		
		//scn.nextLine(); // scan title and discards
		
		for (Question q: questions) {
			//System.out.println(q);
			scn.nextLine();
			q.restoreStudentAnswer(scn);
		}
	}
	
	public int numQuestions() {
		return numQuestions;
	}
	
	public void saveQuestionValue(PrintWriter pw) {
		int i = 0;
		
		for (i = 0; i < questions.size(); i++) {
			pw.print("," + questions.get(i).getValue() );
		}
		//pw.print(questions.get(questions.size()).getValue());
	}
}
