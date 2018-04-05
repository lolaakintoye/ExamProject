/* CS 342: Project 4: Developing Applications Using Exam-Related Classes
 * Name: Daisy Arellano
 * NetID: darell3
 */

import java.io.*;
import java.util.*;

abstract public class Answer
{	
	protected Answer() {}
	
	public Answer(Scanner scn) {}
	
	abstract public void print();
	
	abstract public double getCredit(Answer rightAnswerObj);
	
	abstract public void save(PrintWriter pw);
}
