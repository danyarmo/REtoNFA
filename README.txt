Daniel Yarmolenko
Project - Regular Expression to Finite Automata
10/14/2019

Contact: daniel.yarmolenko@gmail.com

Synopsis: 

This program is a Regular Expression (RE) to a Nondeterminitic Finite Automaton (NFA) convertor. The program is
written in Java using IntelliJ. The program reads an input text (input.txt) file consisting a list of REs and converts each
RE into an NFA. The program assumes that each line in the input.txt file is one RE, so each line of an RE will have a
corresponding NFA in the ouput. If the RE has correct syntax then the output of the NFA will be represented by a list
of transitions from one state to another in numerical order. However, if the syntax is incorrect for an RE then the program 
will send a message that it got an error for that RE.

Build Instructions:

1.Extract files from CS_317_Project_Daniel_Yarmolenko.zip (you should get a folder called: CS_317_Project_Daniel_Yarmolenko)
2.Import the folder with all the files to IntelliJ. (click open and search for the folder extracted from the zip file)
3.Find the input.txt file (or other .txt file of RE inputs) and copy/past its file path to the file scanner as indicated in Main.java.
4.Run main by right clicking on Main.Java and clicking run, IntelliJ should compile it and run the program.
5.The output will be a list of transitions in numerical order with Start and Final states labeled at the bottom
of each corresponding NFA for each RE.

Files Included:

README.txt - Shows example of input and output and the same info. in this file
input.txt - Text file that contains a list of RE inputs as given from assignment
Main.Java - Main class contains the algorithm to build and print the NFAs.
Nfa.Java - Nfa is used as an object to store information about each NFA
Transition.Java - Transition object is used as an object to store information about each transition.

Example input: (input.txt)

ab|
aE|
ab&
a*
ab|*
a*b|
ab|*b&
ab&a*&ab|&b&
ab|*a&b&a*&*ab|&ab|&
ab&a*&ab|&*b&
ab&a*&ab|&*b&*
ab&a*&ab|&*b&*ab|*&b&

Output:

ab|
(q1, a) -> q2
(q2, E) -> q6
(q3, b) -> q4
(q4, E) -> q6
(q5, E) -> q1
(q5, E) -> q3
Start State: q5
Final State: q6

aE|
(q1, a) -> q2
(q2, E) -> q6
(q3, E) -> q4
(q4, E) -> q6
(q5, E) -> q1
(q5, E) -> q3
Start State: q5
Final State: q6

ab&
(q1, a) -> q2
(q2, E) -> q3
(q3, b) -> q4
Start State: q1
Final State: q4

a*
(q1, a) -> q2
(q2, E) -> q3
(q3, E) -> q1
Start State: q3
Final State: q3

ab|*
(q1, a) -> q2
(q2, E) -> q6
(q3, b) -> q4
(q4, E) -> q6
(q5, E) -> q1
(q5, E) -> q3
(q6, E) -> q7
(q7, E) -> q5
Start State: q7
Final State: q7

a*b|
(q1, a) -> q2
(q2, E) -> q3
(q3, E) -> q1
(q3, E) -> q7
(q4, b) -> q5
(q5, E) -> q7
(q6, E) -> q3
(q6, E) -> q4
Start State: q6
Final State: q7

ab|*b&
(q1, a) -> q2
(q2, E) -> q6
(q3, b) -> q4
(q4, E) -> q6
(q5, E) -> q1
(q5, E) -> q3
(q6, E) -> q7
(q7, E) -> q5
(q7, E) -> q8
(q8, b) -> q9
Start State: q7
Final State: q9

ab&a*&ab|&b&
(q1, a) -> q2
(q2, E) -> q3
(q3, b) -> q4
(q4, E) -> q7
(q5, a) -> q6
(q6, E) -> q7
(q7, E) -> q5
(q7, E) -> q12
(q8, a) -> q9
(q9, E) -> q13
(q10, b) -> q11
(q11, E) -> q13
(q12, E) -> q8
(q12, E) -> q10
(q13, E) -> q14
(q14, b) -> q15
Start State: q1
Final State: q15

ab|*a&b&a*&*ab|&ab|&
(q1, a) -> q2
(q2, E) -> q6
(q3, b) -> q4
(q4, E) -> q6
(q5, E) -> q1
(q5, E) -> q3
(q6, E) -> q7
(q7, E) -> q5
(q7, E) -> q8
(q8, a) -> q9
(q9, E) -> q10
(q10, b) -> q11
(q11, E) -> q14
(q12, a) -> q13
(q13, E) -> q14
(q14, E) -> q12
(q14, E) -> q15
(q15, E) -> q7
(q15, E) -> q20
(q16, a) -> q17
(q17, E) -> q21
(q18, b) -> q19
(q19, E) -> q21
(q20, E) -> q16
(q20, E) -> q18
(q21, E) -> q26
(q22, a) -> q23
(q23, E) -> q27
(q24, b) -> q25
(q25, E) -> q27
(q26, E) -> q22
(q26, E) -> q24
Start State: q15
Final State: q27

ab&a*&ab|&*b&
(q1, a) -> q2
(q2, E) -> q3
(q3, b) -> q4
(q4, E) -> q7
(q5, a) -> q6
(q6, E) -> q7
(q7, E) -> q5
(q7, E) -> q12
(q8, a) -> q9
(q9, E) -> q13
(q10, b) -> q11
(q11, E) -> q13
(q12, E) -> q8
(q12, E) -> q10
(q13, E) -> q14
(q14, E) -> q1
(q14, E) -> q15
(q15, b) -> q16
Start State: q14
Final State: q16

ab&a*&ab|&*b&*
(q1, a) -> q2
(q2, E) -> q3
(q3, b) -> q4
(q4, E) -> q7
(q5, a) -> q6
(q6, E) -> q7
(q7, E) -> q5
(q7, E) -> q12
(q8, a) -> q9
(q9, E) -> q13
(q10, b) -> q11
(q11, E) -> q13
(q12, E) -> q8
(q12, E) -> q10
(q13, E) -> q14
(q14, E) -> q1
(q14, E) -> q15
(q15, b) -> q16
(q16, E) -> q17
(q17, E) -> q14
Start State: q17
Final State: q17

ab&a*&ab|&*b&*ab|*&b&
(q1, a) -> q2
(q2, E) -> q3
(q3, b) -> q4
(q4, E) -> q7
(q5, a) -> q6
(q6, E) -> q7
(q7, E) -> q5
(q7, E) -> q12
(q8, a) -> q9
(q9, E) -> q13
(q10, b) -> q11
(q11, E) -> q13
(q12, E) -> q8
(q12, E) -> q10
(q13, E) -> q14
(q14, E) -> q1
(q14, E) -> q15
(q15, b) -> q16
(q16, E) -> q17
(q17, E) -> q14
(q17, E) -> q24
(q18, a) -> q19
(q19, E) -> q23
(q20, b) -> q21
(q21, E) -> q23
(q22, E) -> q18
(q22, E) -> q20
(q23, E) -> q24
(q24, E) -> q22
(q24, E) -> q25
(q25, b) -> q26
Start State: q17
Final State: q26
