/*
Author: Jake Edwards
Date Started: 03/05/2017
Date Finished:

Purpose:
 */

package com.company;

public class Queue {
    StackNode front;
    StackNode back;

    public Queue() {
        front = null;
        back = null;
    }

    public Queue(char firstItem) {
        front = new StackNode(firstItem);
        back = front;
    }

    // push an item on the top of the stack
    public void enqueue(char item) {
        StackNode stackNode = new StackNode(item);

        if (front == null) { //if queue is empty
            back = stackNode;
            front = back;
        }
        else { //list is not empty
            back.next = stackNode;
            back = back.next;
        }
    }

    // dequeue an item from the front of the queue
    public Object dequeue() {
        // if queue is not empty
        if (front != null) {
            Object item = front.getOperand();

            front = front.next;
            return item;
        }
        else {// queue is empty
            System.out.println("Queue is empty");
            return null;
        }
    }

    // peek at the stack printing the top item of the stack or "Stack is empty" if the stack is null
    public Object peek() {
        if (front != null) {
            return front.getOperand();
        }else {
            System.out.println("Stack is empty");
            return null;
        }
    }

    public boolean isEmpty() {

        // is empty
        if (front == null) {
            return true;
        }else { // not empty
            return false;
        }
    }

    // dequeue all items in the queue until it is empty
    public void clear() {
        while (!isEmpty()) {
            dequeue();
        }
    }
}
