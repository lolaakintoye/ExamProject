/* CS 342: Project 4: Developing Applications Using Exam-Related Classes
 * Name: Daisy Arellano
 * NetID: darell3
 */

import java.io.*;
import java.util.*;

public class SAAnswer extends Answer {
	protected String answer; // Actual text of an answer.
	
	public SAAnswer(String answer) {
		this.answer = answer;
	}
	
	public SAAnswer(Scanner scn) {
		answer = scn.nextLine();
	}
	
	public void print() {
		System.out.println(answer);
	}
	
	public double getCredit(Answer ansObj) {
		double credit  = 0.0; // Default or No Credit
		
		SAAnswer rightAnsObj = (SAAnswer)ansObj; // Type casting to a more specific answer type.
				
		if ((answer.toLowerCase()).equals((rightAnsObj.answer).toLowerCase())) { // Credit - Compares the two case insensitive answers.
			credit = 1.0; 
		}
		
		return credit;
	}
	
	public void save(PrintWriter pw) {
		pw.println(answer);
	}
}
