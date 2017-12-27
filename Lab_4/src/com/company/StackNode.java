/*
Author: Jake Edwards
Date Started: 02/20/2017
Date Finished: 03/03/2017

Purpose: Node class to be used by the ListOperators class
*/

package com.company;

public class StackNode {

    private char operand;
    StackNode next;

    // default constuctor
    public StackNode() {
        next = null;
    }

    // create StackNode object and assign the character to the operand variable;
    public StackNode(char operand) {
        this.operand = operand;
    }

    public void setOperand(char operand) {
        this.operand = operand;
    }

    public char getOperand() {
        return this.operand;
    }

    public double toDouble() {
        String s = String.valueOf(this.operand);
        return Double.parseDouble(s);
    }

}
