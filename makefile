make:
	javac *.java
Answer.class : Answer.java
	javac Answer.java
Question.class : Question.java Answer.class
	javac Question.java
Exam.class : Exam.java Question.class Answer.class
	javac Exam.java
clean:
	$(RM) *.class
