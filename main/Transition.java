//Daniel Yarmolenko
//CS 317
//Project
//RE to NFA
package com.project.main;

public class Transition { //stores information about each transitions

        public int state1; //hold number of first state
        public int state2; //holds number of second state (transferred too here)
        public char symbol; //holds symbol used to transfer
        public Transition next; //points to next transitions (state 2) if <1 transition arrow

        public Transition(int state1, int state2, char symbol){ //constructor

            this.state1 = state1;
            this.state2 = state2;
            this.symbol = symbol;
            next = null;

        }

}
