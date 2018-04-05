/* CS 342: Project 3: Saving and Restoring Exam and Exam Components
 * Name: Charlotte Norman
 * NetID: cnorma4
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