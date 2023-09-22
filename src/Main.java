import java.io.*;
import java.util.Arrays;
//import static java.lang.Character.isAlphabetic;
//import static java.lang.Character.isDigit;
//import java.util.*;  not used

public class Main {
//variable calls
    public static int charClass, lexLen, nextToken; //token variable not used
    public static char nextChar;
    //public static char[] lexeme = new char[6]; //from 100
    public static String lex;
    public static FileReader in_fp;
    public static final int LETTER = 0;
    public static final int DIGIT = 1;
    public static final int UNKNOWN = 99;
    public static final int INT_LIT = 10;
    public static final int IDENT = 11;
    public static final int ASSIGN_OP = 20;
    public static final int ADD_OP = 21;
    public static final int SUB_OP = 22;
    public static final int MULT_OP = 23;
    public static final int DIV_OP = 24;
    public static final int LEFT_PAREN = 25;
    public static final int RIGHT_PAREN = 26;
    public static final int EOF = -1;

    public static void lookup(char ch){   // apparently when this function is called, it is not looking for return data.. so I changed it to void from int
        lex = "";    // experimental code!
        switch(ch){
            case '(':
                addChar();
                nextToken = LEFT_PAREN;
                break;
            case ')':
                addChar();
                nextToken = RIGHT_PAREN;
                break;
            case '+':
                addChar();
                nextToken = ADD_OP;
                break;
            case '-':
                addChar();
                nextToken = SUB_OP;
                break;
            case '*':
                addChar();
                nextToken = MULT_OP;
                break;
            case '/':
                addChar();
                nextToken = DIV_OP;
                break;
            case '=':
                addChar();
                nextToken = ASSIGN_OP;
                break;
            default:
                addChar();
                nextToken = EOF;
                break;
        }
        //return nextToken; not needed with void return type
    }

    public static void addChar(){
       // if (lexLen<= 98){
           // lexeme[lexLen++] = nextChar;
           // lexeme[lexLen] = 0;

       // } else {
        //    System.out.println("Error - lexeme is too long\n");
      //  }
        lex = lex + nextChar;
    }

    public static void getChar() {
      /*  if ((nextChar = get c(in_fp)) = EOF) {
            if (isAlphabetic(nextChar))
                charClass = LETTER;
            else if (isDigit(nextChar))
                charClass = DIGIT;
            else charClass = UNKNOWN;
        }
        else
            charClass = EOF;
       */
        try {
            int nextCharInt = in_fp.read();
            if (nextCharInt == -1) {
                charClass = EOF;
            } else {
                nextChar = (char) nextCharInt;
                if (Character.isLetter(nextChar)) {
                    charClass = LETTER;
                } else if (Character.isDigit(nextChar)) {
                    charClass = DIGIT;
                } else {
                    charClass = UNKNOWN;
                }
            }
        } catch (IOException e) {
            charClass = EOF;
        }


    }

    public static void getNonBlank(){ //a function to call getChar until it returns a non-whitespace character
        while (Character.isWhitespace(nextChar)) {
            getChar();
        }
    }

    public static void lex(){ //switching from into to void return type
        lexLen = 0;
        //this might be the ideal spot to add: lex="";
        lex="";
        getNonBlank();
        switch(charClass) {
            case LETTER:
                addChar();
                getChar();
                while (charClass == LETTER || charClass == DIGIT) {
                    addChar();
                    getChar();
                }
                nextToken = IDENT;
                break;
            /* Parse integer literals */
            case DIGIT:
                addChar();
                getChar();
                while (charClass == DIGIT) {
                    addChar();
                    getChar();
                }
                nextToken = INT_LIT;
                break;
            /* Parentheses and operators */
            case UNKNOWN:
                lookup(nextChar);
                getChar();
                break;
            /* EOF */
            case EOF:
                nextToken = EOF;
              //  lexeme[0] = 'E';
              //  lexeme[1] = 'O';
              //  lexeme[2] = 'F';
              //  lexeme[3] = 0;
                lex = "EOF";
                break;
        } /* End of switch */
        System.out.println("Next token is: "+nextToken+", Next lexeme is "+ lex +"\n"); //added tostring code    removed: Arrays.toString(lexeme)
        //return nextToken; not needed with void return type
    } /* End of function lex */
    public static void main(String[] args) {  // wrap in a try catch, see code translation
       /* System.out.println("\nThat's it! That's the melody to funky town!!\n");
        if ((in_fp = f open("front.in", "r")) == Null) {
            System.out.println("Error - Cannot open front.in\n");
        }else{
            getChar();
            do{lex();}while(nextToken != EOF);
        }*/
        try {
            in_fp = new FileReader("test.txt"); //from front.in
            getChar();
            do {
                lex();
            } while (nextToken != EOF);
        } catch (IOException e) {
            System.out.println("ERROR - cannot open test.txt"); //from front.in
        }
        //let's see if this bs works, lol
        System.out.println(":D  That's it!! That's the melody to funky town!!!");
    }
}