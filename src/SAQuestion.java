/* CS 342: Project 4: Developing Applications Using Exam-Related Classes
 * Name: Daisy Arellano
 * NetID: darell3
 */

import java.io.*;
import java.util.*;

public class SAQuestion extends Question {	
	public SAQuestion(String question, double maxValue) {
		super(question, maxValue);
	}
	
	public SAQuestion(Scanner scn) {
		super(scn);
		
		this.questionType = "SAQuestion";
		this.rightAnswer = new SAAnswer(scn);
	}
	
	public SAAnswer getNewAnswer() { // Returns empty "Short Answer" answer object.
		SAAnswer ansObj = new SAAnswer(""); // Dummy Content
		
		return ansObj;
	}
	
	public SAAnswer getNewAnswer(String answer) { // Returns newly created "Short Answer" answer object that has content.
		SAAnswer ansObj = new SAAnswer(answer); 
		
		this.rightAnswer = ansObj; // Sets the right answer.
		
		return ansObj;
	}
	
	public void getAnswerFromStudent() {
		Scanner scn = ScannerFactory.getKeyboardScanner(); // The shared System.in scanner.
		
		SAAnswer ansObj; // "Multiple Choice Single Answer" Answer Object
		String answer; // Answer entered in by user.
		
		System.out.println("\n" + question);
		System.out.print("Enter your answer or SKIP to come back: ");
		answer = scn.nextLine(); 
		if(answer.contains("SKIP")){
			ansObj = new SAAnswer("SKIP");
		}else {
			ansObj = new SAAnswer(answer);
		}
		this.studentAnswer = ansObj;		
	}
	
	public double getValue() {
		double value = studentAnswer.getCredit(rightAnswer);
		
		return (value * maxValue);
	}
	
	public void save(PrintWriter pw) {
		pw.println(this.questionType);
		pw.println(this.maxValue);
		pw.println(this.question);
		
		this.rightAnswer.save(pw); // Calls for the answer to save its information.
	}
 }
