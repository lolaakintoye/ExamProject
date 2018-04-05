/* CS 342: Project 3: Saving and Restoring Exam and Exam Components
 * Name: Charlotte Norman
 * NetID: cnorma4
 */

import java.io.*;
import java.util.*;

abstract class MCQuestion extends Question {
	protected ArrayList<MCAnswer> answers; // Array list that will hold the Answer objects (question answers).
	
	protected int numAnswers; // Number of answers in question.
	
	public MCQuestion(String question, double maxValue) {
		super(question, maxValue);
		
		answers = new ArrayList<MCAnswer>();
		numAnswers = 0;		
	}
	
	public MCQuestion(Scanner scn) {
		super(scn);
		
		answers = new ArrayList<MCAnswer>();
	}
	
	public void print() {
		int i = 0;
		char c; 
		
		System.out.print(question); // Prints Question
		
		for (MCAnswer a: answers) { // Prints Answers
			c = (char)('A' + i); // Converting position number to a letter.
			System.out.print("\n\t" + c + ". ");
			a.print(); 
			i++; // To get the answers' positions in their current order.
		}
		
		System.out.println("\n");
	}
	
	protected void addAnswer(MCAnswer ansObj) {
		answers.add(ansObj);		
	}
	
	public void reorderAnswers() {
		Collections.shuffle(answers); 
	}
	
	public double getValue(MCAnswer ansObj) {
		double value = 0.0;
		
		return value;
	}
	
	public void save(PrintWriter pw) {
		pw.println(this.questionType);
		pw.println(this.maxValue);
		pw.println (this.question);		
	}
}
