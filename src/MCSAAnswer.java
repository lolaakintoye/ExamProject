/* CS 342: Project 3: Saving and Restoring Exam and Exam Components
 * Name: Charlotte Norman
 * NetID: cnorma4
 */

import java.util.*;

public class MCSAAnswer extends MCAnswer {
	public MCSAAnswer(String answer, double creditIfSelected) {
		super(answer, creditIfSelected);
	}
	
	public MCSAAnswer(Scanner scn) {
		super(scn);	
	}
}
