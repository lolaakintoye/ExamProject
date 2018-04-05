/* CS 342: Project 3: Saving and Restoring Exam and Exam Components
 * Name: Charlotte Norman
 * NetID: cnorma4
 */

import java.util.*;

public class MCMAAnswer extends MCAnswer {
	public MCMAAnswer(String answer, double creditIfSelected) {
		super(answer, creditIfSelected);
	}
	
	public MCMAAnswer(Scanner scn) {
		super(scn);
	}
}
