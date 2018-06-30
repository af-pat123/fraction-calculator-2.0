package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int numerator;
        int denominator;
        String operation;
        char operator;
        Fraction answerFract;
        Fraction secondFract;
        Fraction firstFract;
        String firstPart;
        String lastPart;
        int space;
        int slash;
        Boolean equals;
        Scanner input = new Scanner(System.in);

        System.out.println("This program is a fraction calculator");
        System.out.println("It will add, subtract, multiply and divide fractions until you type Q to quit.");
        System.out.println("Valid operations are of the form '[FRAC] [OPERATION] [FRAC]'.");
        System.out.println("[FRAC] can be either a single integer or two integers seperated by '/' ");
        System.out.println("[OPERATION] can be + ,- ,/ ,* or =");


        /*loops until user wants to quit, anytime the operation is seen to be invalid the program goes back to the
        start of this loop*/
        while(true){
            System.out.print("Enter an operation(q to quit):");
            operation=input.nextLine();;

            if(("Q".equals(operation))||("q".equals(operation))){
                input.close();
                System.exit(0);
            }

            space=findSpacing(operation);

            if(space==0){
                System.out.println("Invalid operation. Must be [FRAC] [OPERATION] [FRAC]");
                continue;
            }

            //splits the string into the first fraction the operator and the second fraction
            firstPart=operation.substring(0,space);
            operator=operation.charAt(space+1);
            lastPart=operation.substring(space+3);

            //first fraction entered is an integer
            if(isNumber(firstPart)){
                numerator=Integer.parseInt(firstPart);
                firstFract= new Fraction(numerator);
            }

            else{
                slash=findSlash(firstPart);
                //if a slash was found and what preceeds and succeeds the slash are integers create a fraction
                if(slash!=0 && isNumber(firstPart.substring(0,slash)) && isNumber(firstPart.substring(slash+1))){
                    numerator=Integer.parseInt(firstPart.substring(0,slash));
                    denominator=Integer.parseInt(firstPart.substring(slash+1));
                    firstFract = new Fraction(numerator,denominator);
                }
                else{
                    System.out.println("Invalid operation. Must be [FRAC] [OPERATION] [FRAC]");
                    continue;
                }
            }

            if(!validOperator(operator)){
                System.out.println("Invalid operation. Must be [FRAC] [OPERATION] [FRAC]");
                continue;
            }

            //second fraction entered was an integer
            if(isNumber(lastPart)){
                numerator=Integer.parseInt(lastPart);
                secondFract= new Fraction(numerator);
            }
            else{
                slash=findSlash(lastPart);
                if(slash!=0 && isNumber(lastPart.substring(0,slash)) && isNumber(lastPart.substring(slash+1))){
                    numerator=Integer.parseInt(lastPart.substring(0,slash));
                    denominator=Integer.parseInt(lastPart.substring(slash+1));
                    secondFract = new Fraction(numerator,denominator);
                }
                else{
                    System.out.println("Invalid operation. Must be [FRAC] [OPERATION] [FRAC]");
                    continue;
                }
            }

            //display operation
            System.out.print(firstFract.toString());
            System.out.print(" " + operator + " ");
            System.out.print(secondFract.toString());

            switch (operator) {
                case '-':;
                    answerFract=secondFract.subtract(firstFract);
                    System.out.println(" = " + answerFract.toString());
                    break;
                case '+':;
                    answerFract=secondFract.add(firstFract);
                    System.out.println(" = " + answerFract.toString());
                    break;
                case '/':;
                    if(secondFract.getNumerator()==0){
                        System.out.println(" = Undefined");
                    }
                    else{
                        answerFract=secondFract.divide(firstFract);
                        System.out.println(" = " + answerFract.toString());
                    }
                    break;
                case '*':;
                    answerFract=secondFract.multiply(firstFract);
                    System.out.println(" = " + answerFract.toString());
                    break;
                case '=':;
                    equals=secondFract.equals(firstFract);
                    System.out.println(" is " + equals);
                    break;


            }



        }


    }

    //returns the postion of the first ' ' in the string
    public static int findSpacing(String input){
        boolean spaceFound=false;
        int spacePosition=0;
        int i=0;

        //loop through the string to find the first space
        do{
            if(input.charAt(i)==' '&&spaceFound==false){
                spaceFound=true;
                spacePosition=i;
            }
            i++;
        }while(i<input.length()&&spaceFound==false);

        //if a space is not found or the spacing of the input is invalid return 0
        if((spacePosition+3)>input.length()||spaceFound==false || input.charAt(spacePosition+2)!=' '){
            return 0;
        }


        return spacePosition;

    }

    //finds the position of the '/' in the string
    public static int findSlash(String input){
        boolean slashFound=false;
        int slashPosition=0;
        int i=0;
        //loop through the string to find the '/' and record the position if found
        do{
            if(input.charAt(i)=='/'){
                slashFound=true;
                slashPosition=i;
            }
            i++;
        }while(i<input.length()&&slashFound==false);

        if(slashFound==false){
            return 0;
        }
        return slashPosition;
    }

    public static boolean validOperator(char operator){
        if (operator=='+'||operator=='-'||operator=='*'||operator=='/'||operator=='='){
            return true;
        }

        return false;

    }



    //check if string is an integer
    public static boolean isNumber(String str){
        try {
            Integer.parseInt(str);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }
}