/* CS 342: Project 3: Saving and Restoring Exam and Exam Components
 * Name: Charlotte Norman
 * NetID: cnorma4
 */

import java.io.*;
import java.util.*;

public class NumAnswer extends Answer {
	protected double answer; // Numerical answer.
	//protected double tolerance; // Allows for the student answer to be in an acceptable range in order to be correct.
	
	public NumAnswer(double answer) {
		this.answer = answer;
	}
	
	public NumAnswer(double answer, double tolerance) {
		this.answer = answer;
		//this.tolerance = tolerance;
	}
	
	public NumAnswer(Scanner scn) {
		answer = Double.parseDouble(scn.nextLine());		
		//tolerance = Double.parseDouble(scn.nextLine());
	}
	
	public void print() {
		System.out.println(answer);
	}
	
	public double getCredit(Answer ansObj) {
		double credit = 0.0; // Default or No Credit 
		
		NumAnswer rightAnsObj = (NumAnswer)ansObj; // Type casting to a more specific answer type.
		
		if (answer == rightAnsObj.answer) { // Credit - Compares the answers between the two.
			credit = 1.0; 
		}
		
		return credit;
	}
	
	public double getCredit(Answer ansObj, double tolerance) { // Added method to pass tolerance down to answer.
		double credit = 0.0; // Default or No Credit 
		
		NumAnswer rightAnsObj = (NumAnswer)ansObj; // Type casting to a more specific answer type.
		
		if (answer == rightAnsObj.answer || ((answer < (answer + tolerance)) && (answer > (answer - tolerance)))) { // Credit - Compares the answers between the two.
			credit = 1.0; 
		}
		
		return credit;
	}
	
	public void save(PrintWriter pw) {
		pw.println(this.answer);
	}
}
