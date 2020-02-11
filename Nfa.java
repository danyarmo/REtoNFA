//Daniel Yarmolenko
//CS 317
//Project
//RE to NFA
package com.project.main;

public class Nfa { //stores information about nfa

    public int startState; //stores number of nfa start state
    public int finalState; //stores number of nfa final state
    public Transition nfaTransitions; //stores transitions of nfa

    public Nfa(int startState, int finalState, Transition nfaTransitions){ //constructor

        this.startState = startState;
        this.finalState = finalState;
        this.nfaTransitions = nfaTransitions;


    }




}
