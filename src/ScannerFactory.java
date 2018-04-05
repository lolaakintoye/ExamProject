/* CS 342: Project 3: Saving and Restoring Exam and Exam Components
 * Name: Charlotte Norman
 * NetID: cnorma4
 */

import java.util.*;

public class ScannerFactory {
	private static Scanner keyboardScanner;
	
	public static Scanner getKeyboardScanner() {
		if (keyboardScanner == null) { // Checks if a scanner has already been created to ensure there's only one instance.
			keyboardScanner = new Scanner(System.in);
		}
		
		return keyboardScanner;
	}
	
	public static void closeKeyboardScanner() { // Added new method to close the scanner at end of program execution.
		keyboardScanner.close();
	}
}
