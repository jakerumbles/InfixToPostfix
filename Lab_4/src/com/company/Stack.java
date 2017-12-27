/*
Author: Jake Edwards
Date Started: 03/05/2017
Date Finished:

Purpose:
 */

package com.company;

public class Stack {

    StackNode top;

    public Stack() {
        top = null;
    }

    public Stack(char firstItem) {
        top = new StackNode(firstItem);
    }

    // pop an item off of the stack
    public Object pop() {
        if (top != null) {
            Object item = top.getOperand();

            top = top.next;
            return item;
        }

        System.out.println("Stack is empty");
        return null;

    }

    // push an item on the top of the stack
    public void push(char item) {
        StackNode stackNode = new StackNode(item);

        if (top == null) { //if list is empty
            top = stackNode;
        }
        else { //list is not empty
            stackNode.next = top;
            top = stackNode;
        }
    }

    // peek at the stack printing the top item of the stack or "Stack is empty" if the stack is null
    public char peek() {
        if (top != null) {
            return top.getOperand();
        }else {
            System.out.println("Stack is empty");
            return '!';
        }
    }

    public boolean isEmpty() {

        // is empty
        if (top == null) {
            return true;
        }else { // not empty
            return false;
        }
    }

    public void clear() {
        while (!isEmpty()) {
            pop();
        }
    }
}
