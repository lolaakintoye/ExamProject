In order to run the program, type "make" into the command line while in the folder containing the java files. 
This will compile and create the appropriate java classes.


To Run ExamBuilder (Charlotte Norman):

When the program gets run, the user will be prompted to decide what to do in regards to creating and/or modifying an exam.  
There are lots of comments throughout both in the program and during the execution that will guide you on what to do.s

To Run ExamTaker (Funmilola Akintoye):

The user will be first prompted to enter a file. From that file, the program will extract the data and populate the exam.
Next, each question will be printed to the user to answer with an option to skip. 
If wanting to skip, enter SKIP (case sensitive).
If there is a question that was skipped, it will be presented back to you after all the questions.
There is an option to change your answers(Y/N, case sensitive).
If Y, then the user is asked to enter which questions they would like to change.
Enter the numbers and hit return key after each one then finally end with '#' sign.
The user will be prompted for the name and all will be saved to the file and ready for ExamGrader. 

To Run ExamGrader (Daisy Arellano):

The user will be prompted to enter a file, which should be the name of your exam. Next it will request another file which
should be the name of the answer file. Finally it will request the name of the file which you (the user) would like to upload
to. For our files you can type in "modified_exam.txt", "output_answers.txt", "Sample_output" respectively. This should end with
a print statement which tells you if your files match, and then a csv file which includes the headers and information 
(Student identity, total score, question scores). Additionally the console should print a table with your new scores.