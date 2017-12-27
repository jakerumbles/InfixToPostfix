/*
Author: Jake Edwards
Date Started: 03/05/2017
Date Finished:03/09/2017

Purpose: Loop through a file of infix expressions and convert and print to postfix form

ADT's: Queue, Stack
Files used: p4a.dat, p4b.dat

Example input:

    S-1
    C*S+S*0
    ((((5-S)*2)-C)/3)
    3
    ((((E-Q))))
    Y+0*Y*Y

Example output:

    Infix: S-1
    Postfix: S1-
    Result: 0.0

    Infix: C*S+S*0
    Postfix: CS*S0*+
    Result: 0.0

    Infix: ((((5-S)*2)-C)/3)
    Postfix: 5S-2*C-3/
    Result: 0.0

    Infix: 3
    Postfix: 3
    Result: 0.0

    Infix: ((((E-Q))))
    Postfix: EQ-
    Result: 0.0

    Infix: Y+0*Y*Y
    Postfix: Y0Y*Y*+
    Result: 0.0
 */

package com.company;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    // to store the values for the variables from the p4b.dat file
    static int[] varValues;
    static char[] varIdentifier;

    // ADT's
    static Stack stack;
    static Queue queue, queue2; // queue2 for use in calculateResult()

    // in stack priority and in coming priority
    static int isp;
    static int icp;

    public static void main(String[] args) throws IOException{

        // read file p4b.dat and store the values in the Main class variables
        getVariables();

        // convert numbers to postfix and print data
        parseToPostfix();
    }

    // load the values of the variables in the p4b.dat file to class variables in the Main class
    public static void getVariables() throws IOException{
        File file = new File("C:\\Users\\edwar\\Documents\\Data Structures\\Take1\\Lab_4\\Assets\\p4b.dat");
        Scanner input = new Scanner(file);

        varValues = new int[11];
        varIdentifier = new char[11];

        for (int i = 0; i < varValues.length; i++) {
            varIdentifier[i] = (input.next().charAt(0));
            varValues[i] = input.nextInt();
        }

        input.close();
    }

    // convert and print the prefix expressions that have been derived from the file p4a.dat
    public static void parseToPostfix() throws IOException{
        File file = new File("C:\\Users\\edwar\\Documents\\Data Structures\\Take1\\Lab_4\\Assets\\p4a.dat");
        Scanner input = new Scanner(file);

        String infix; //store original infix expression from p4a.dat file
        String postfix = ""; //store parsed string that has been converted to a postfix expression
        //String postfixQueue = ""; // to hold the operators and operands while constructing the expression
        double result = 0;
        stack = new Stack();
        queue = new Queue();

        // loop through file till no more tokens
        while (input.hasNext()) {
            // load infix with line from file
            infix = input.next();

            // loop through each character in infix
            for (int i = 0; i < infix.length(); i++) {

                // test if token is a character
                if (testCharacter(infix.charAt(i))) {
                    char temp = infix.charAt(i);

                    // if the character is a letter add to postfix queue
                    if (Character.isLetter(temp) || Character.isDigit(temp)) {
                        queue.enqueue(temp);
                    }

                    // if the character is a closing parentheses:
                    // add all tokens to the postfix queue down to the '(', then pop that off
                    else if (temp == ')') {
                        while (stack.peek() != '(') {
                            queue.enqueue((char)stack.pop());
                        }
                        stack.pop();
                    }

                    // if token is '(' or an operator i.e. + - / * ^
                    else if (temp == '(' || temp == '^'
                                         || temp == '*'
                                         || temp == '/'
                                         || temp == '-'
                                         || temp == '+') {

                        // if stack is not empty
                        if (!stack.isEmpty()) {
                            setPriorities(temp);

                            while (isp >= icp && !stack.isEmpty()) {
                                queue.enqueue((char) stack.pop());
                                setPriorities(temp);
                            }
                            if (isp < icp) {
                                stack.push(temp);
                                setPriorities(temp);
                            }
                        }
                        else {
                            stack.push(temp);
                        }



                    }
                }
            }

            // while stack is not empty
            while (!stack.isEmpty()) {
                queue.enqueue((char) stack.pop());
            }

            // calculate result of postfix expression
            //result = calculateResult();

            // print the values after each line has been evaluated
            System.out.println("Infix: " + infix);
            System.out.print("Postfix: ");
            while (!queue.isEmpty()) {
                System.out.print(queue.dequeue());
            }
            System.out.println();
            System.out.println("Result: " + result);
            System.out.println();

            queue.clear();
        }


    }

    // sets the isp to the correct values allowing parseToPostfix() to function as intended
    public static void setPriorities(char token) {
        if (stack.isEmpty()) {
            isp = 0;
        }
        else {
            char top = stack.peek();

            // set isp using the local variable top
            if (top == '^') {
                isp = 3;
            }
            else if (top == '*' || top == '/') {
                isp = 2;
            }
            else if (top == '+' || top == '-') {
                isp = 1;
            }
            else if (top == '(') {
                isp = 0;
            }
        }


        // sets the icp to the correct value allowing parseToPostfix() to function as intended
        if (token == '^') {
            icp = 4;
        }
        else if (token == '*' || token == '/') {
            icp = 2;
        }
        else if (token == '+' || token == '-') {
            icp = 1;
        }
        else if (token == '(') {
            icp = 4;
        }
    }

    //precondition: method is passed a character
    //postcondition: method returns true if passed in value is a character
    public static boolean testCharacter(char character) {

        if (character == '^') {
            return true;
        }
        else if (character == '*') {
            return true;
        }
        else if (character == '/') {
            return true;
        }
        else if (character == '+') {
            return true;
        }
        else if (character == '-') {
            return true;
        }
        else if (Character.isLetter(character) || Character.isDigit(character)) {
            return true;
        }
        else if (character == '(') {
            return true;
        }
        else if (character == ')') {
            return true;
        }
        else {
            System.out.println("Item is not a character");
            return false;
        }
    }

    // had no end of trouble trying to match up the right data types here for variables and pass in arguments.
    // I can't get it to work so I've just commented out this part, but it's about 95% functional

/*
    // calculate result of postfix expression
    public static double calculateResult() {

        queue2 = new Queue();
        char temp;
        //StackNode left, right;
        double leftOp, rightOp;

        // while queue is not empty
        while (!queue.isEmpty()) {

            temp = (char)queue.dequeue();

            // if character is a digit, add to queue
            if (Character.isDigit(temp)) {

                queue2.enqueue(temp);
            }
            // if character is a letter, swap letter with according variable value and add number to the queue
            else if (Character.isLetter(temp)) {
                int count = 0;
                while (temp != (varIdentifier[count]) && count < varIdentifier.length) {
                    count++;
                }
                queue2.enqueue((char) (varValues[count]));
            }
            // else, the character must be an operator
            else {

                //left = (StackNode) queue2.dequeue();
                //right = (StackNode) queue2.dequeue();

                leftOp =
                queue.dequeue();
                rightOp =

                if (tempString.charAt(0) == '+') {
                    queue2.enqueue(Double.toString((leftOp + rightOp)));
                }
                else if (tempString.charAt(0) == '-') {
                    queue2.enqueue(Double.toString((leftOp - rightOp)));
                }
                else if (tempString.charAt(0) == '/') {
                    queue2.enqueue(Double.toString((leftOp / rightOp)));
                }
                else if (tempString.charAt(0) == '*') {
                    queue2.enqueue(Double.toString((leftOp * rightOp)));
                }
                else if (tempString.charAt(0) == '^') {
                    queue2.enqueue(Double.toString(Math.pow(leftOp, rightOp)));
                }
            }
        }

        return (double)queue2.dequeue();
    }*/
}
