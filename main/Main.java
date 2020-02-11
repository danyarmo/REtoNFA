//Daniel Yarmolenko
//CS 317
//Project
//RE to NFA Converter
//Synopsis: This program reads an input file of Regular expressions (RE), assumes that each line
//has one RE and converts each RE into an Nondeterministic Finite Automaton (NFA). If the RE has correct syntax
//the output of the NFA will be represented by a list of transitions from one state to another in numerical order.
//If the syntax is incorrect for an RE then the program will send a message that it got an error for that RE.
package com.project.main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException{

            File inputFile = new File("C:\\Users\\danya\\Desktop\\CS_317_Project_Daniel_Yarmolenko\\src\\com\\project\\main\\input.txt"); //change path of input.txt here
            Scanner scanner = new Scanner(inputFile); //takes input of file
            Nfa[] nfaStack = new Nfa[100]; //array that holds nfa's (transitions in nfa are added in order of state number)

            boolean inputIncorrect; //flag used for printing error messages
            int nfaCounter; //keeps track of nfas in array, (kind of like a stack pointer to top)

            while (scanner.hasNextLine()) { //loop while there are more lines of RE
                String reString = scanner.nextLine(); //Save RE string
                System.out.println("\n" + reString);  //print RE on NextLine

                inputIncorrect = false; //assume input is correct until its not
                nfaCounter = 0; //initially there are no nfas

                for (int i = 0; i < reString.length(); i++) { //Loop through each character in RE

                    char reChar = reString.charAt(i); //save character in char variable

                    if (reChar == '&') { //if concatenation operand

                        if(nfaCounter < 2){ //if there are less than 2 nfas, then there are not enough operands

                            System.out.println("Invalid input detected: Not enough operands for '&'");
                            inputIncorrect = true; //input is incorrect
                            break; //break from for loop

                        }
                        nfaCounter--; //pop stack
                        Nfa nfa2 = nfaStack[nfaCounter]; //save top of stack as nfa2
                        nfaStack[nfaCounter] = null; //remove popped nfa

                        nfaCounter--; //pop stack
                        Nfa nfa1 = nfaStack[nfaCounter]; //save 2nd pop as nfa1
                        nfaStack[nfaCounter] = null; //remove popped nfa

                        Transition conFtoS = new Transition(nfa1.finalState, nfa2.startState, 'E'); //transition connects from nfa1 final to nfa2 start.

                        Transition traverseNull = nfa1.nfaTransitions; //used to check if null before traversing
                        Transition prev1 = null; //prev1 used as a reference to transitions for traversing

                        while (traverseNull != null) { //while loop will traverse to that last state of nfa1

                            prev1 = traverseNull; //save to prev1
                            traverseNull = traverseNull.next; //go to next and while loop will check if its null

                        }

                        prev1.next = conFtoS; //point last state of nfa1 to new transition that connects to start of nfa2
                        conFtoS.next = nfa2.nfaTransitions; //point to the rest of the transitions of nfa2

                        Nfa newNfa = new Nfa(nfa1.startState, nfa2.finalState, nfa1.nfaTransitions); //create new nfa, transitions start with nfa1 to be in order

                        nfaStack[nfaCounter] = newNfa; //add new nfa to array
                        nfaCounter++; //increment to keep track of stack

                    } else if (reChar == '|') { //if union operand
                        if(nfaCounter < 2){ //if there are less than 2 nfas, then there are not enough operands

                            System.out.println("Invalid input detected: Not enough operands for '|'");
                            inputIncorrect = true; //input is incorrect
                            break; //break from for loop
                        }

                        nfaCounter--; //pop top of stack
                        Nfa nfa2 = nfaStack[nfaCounter]; //save popped as nfa2
                        nfaStack[nfaCounter] = null; //remove what was popped

                        nfaCounter--; //pop top of stack again
                        Nfa nfa1 = nfaStack[nfaCounter]; //save 2nd popped as nfa1
                        nfaStack[nfaCounter] = null; //remove what was popped

                        int nextStateNum = nfa2.finalState + 1; //next available state number for new state

                        Transition uStartNfa1 = new Transition(nextStateNum, nfa1.startState, 'E'); //new start state transition to nfa1
                        Transition uStartNfa2 = new Transition(nextStateNum, nfa2.startState, 'E'); //new start state transition to nfa2

                        Transition uFinalNfa1 = new Transition(nfa1.finalState, nextStateNum + 1, 'E'); //new final state transition to nfa1
                        Transition uFinalNfa2 = new Transition(nfa2.finalState, nextStateNum + 1, 'E'); //new final state transition to nfa2

                        Transition traverseNull = nfa1.nfaTransitions; //used to check for null in while loops
                        Transition prev1 = null; //used to reference nfa1 transitions
                        Transition prev2 = null; //used to reference nfa2 transitions

                        while (traverseNull != null) { //traverse to final state of nfa1

                            prev1 = traverseNull; //save latest of nfa1 transition in prev1
                            traverseNull = traverseNull.next; //go to next and while loop will check if null

                        }

                        prev1.next = uFinalNfa1; // final of nfa1 points to new final state
                        uFinalNfa1.next = nfa2.nfaTransitions; //new final state points to start of nfa2 (so states are sorted least to greatest)

                        traverseNull = nfa2.nfaTransitions; //used to check for null in while loops

                        while (traverseNull != null) { //traverse to final state of nfa1

                            prev2 = traverseNull; //save latest of nfa2 transition in prev2
                            traverseNull = traverseNull.next; //go to next and while loop will check if null

                        }

                        prev2.next = uFinalNfa2; //point last state of nfa1 to new final state
                        uFinalNfa2.next = uStartNfa1; //point new final state to new start state that transitions to nfa1
                        uStartNfa1.next = uStartNfa2; //point new start state to new start state that transitions to nfa2


                        Nfa newNfa = new Nfa(nextStateNum, nextStateNum + 1, nfa1.nfaTransitions); //create new nfa, transitions start with nfa1 to be in order

                        nfaStack[nfaCounter] = newNfa; //add to array
                        nfaCounter++; //increment to keep track top of stack

                    } else if (reChar == '*') { //if keene operand
                        if(nfaCounter < 1 ){ //if no nfa for operator

                            System.out.println("Invalid input: No operand for '*'");
                            inputIncorrect = true;
                            break;

                        }
                        nfaCounter--; //pop stack
                        Nfa nfa2 = nfaStack[nfaCounter]; //save popped nfa to nfa2
                        nfaStack[nfaCounter] = null;

                        int nextStateNum = nfa2.finalState + 1; //next available

                        Transition keeneStarS = new Transition(nextStateNum, nfa2.startState, 'E'); //transition from new start to nfa2 start
                        Transition keeneStarF = new Transition(nfa2.finalState, nextStateNum, 'E'); //transition from nf2 final to new final/start

                        Transition traverseNull = nfa2.nfaTransitions; //used to check for null in while loops
                        Transition prev2 = null; //used to reference nfa2 transitions

                        while (traverseNull != null) { //traverse to final state of nfa2

                            prev2 = traverseNull; //save latest of nfa2 transition in prev2
                            traverseNull = traverseNull.next; //go to next and while loop will check if null

                        }
                        prev2.next = keeneStarF; //last transition of nfa2 points to new final
                        keeneStarF.next = keeneStarS; //new final(which is also the start) transition points to start of nfa2

                        Nfa newNfa = new Nfa(nextStateNum, nextStateNum, nfa2.nfaTransitions); //create new nfa, accepts nfa2.transitions to sort states.

                        nfaStack[nfaCounter] = newNfa; //add new nfa to array
                        nfaCounter++; //increment to track top of stack

                    } else if (reChar == 'a' || reChar == 'b' || reChar == 'c' || reChar == 'd' || reChar == 'e' || reChar == 'E') { //if its a symbol

                        if (nfaCounter == 0) { //if stack is empty edge case

                            Transition newTransition = new Transition(1, 2, reString.charAt(i));  //create new transition

                            Nfa newNfa = new Nfa(1, 2, newTransition); //create new nfa

                            nfaStack[nfaCounter] = newNfa; //add nfa to stack
                            nfaCounter++;

                        } else { //otherwise NFA stack is not empty
                            nfaCounter--; //go to prev nfa
                            int nextStateNum = nfaStack[nfaCounter].finalState + 1; //final state+1 is now new state
                            nfaCounter++; //add back

                            Transition newTransition = new Transition(nextStateNum, nextStateNum + 1, reString.charAt(i)); //create new transition with corresponding states

                            Nfa newNfa = new Nfa(nextStateNum, nextStateNum + 1, newTransition); //create new nfa

                            nfaStack[nfaCounter] = newNfa; //push new nfa to stack
                            nfaCounter++; //increment counter

                        }

                    } else {
                        //if any other character is read then its sends error
                        System.out.println("Oops, invalid character detected");
                        System.out.println("Valid inputs include: a, b, c, d, e, E (for empty), |, &, *");
                        inputIncorrect = true; //input is incorrect
                        break; //break from for loop

                    }

                }
                //Printing NFA after adding states into array

               if(!inputIncorrect && nfaCounter == 1) { //only print if input is correct and there is only 1 nfa

                   Nfa nfaPrint = nfaStack[0]; //access nfa

                   int startState = nfaPrint.startState; //store start state
                   int acceptState = nfaPrint.finalState; //store final state

                   Transition checker = nfaPrint.nfaTransitions; //used to traverse

                   while (checker != null) {

                       System.out.println("(q" + checker.state1 + ", " + checker.symbol + ")" + " -> q" + checker.state2); //print

                       checker = checker.next; //traverse

                   }

                   System.out.println("Start State: q" + startState); //print start and final state numbers at end
                   System.out.println("Final State: q" + acceptState);

               } else if(nfaCounter != 1){ //if more than 1 nfa then syntax is incorrect.

                   System.out.println("Invalid input: Please check RE syntax, there may be too many operands for '&' or '|' operators");

               }

            }

            scanner.close(); //close scanner after all lines read
    }

}
