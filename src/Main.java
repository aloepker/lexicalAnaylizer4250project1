import java.io.*;
import java.util.*;

public class Main {
//variable calls
    public static int charClass, lexLen, token=10, nextToken, i;
    public static char nextChar;
    public static char[] lexeme = new char[100];
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

    public static int lookup(char ch){
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
            default:
                addChar();
                nextToken = EOF;
                break;
        }
        return nextToken;
    }

    public static void addChar(){
        if (lexLen<= 98){
            lexeme[lexLen++] = nextChar;
            lexeme[lexLen] = 0;
        } else {
            System.out.println("Error - lexeme is too long\n");
        }
    }

    public static void getChar() {
        if ((nextChar = getc(in_fp)) = EOF) {
            if (isalpha(nextChar))
                charClass = LETTER;
            else if (isdigit(nextChar))
                charClass = DIGIT;
            else charClass = UNKNOWN;
        }
        else
            charClass = EOF;
    }

    public static void getNonBlank(){ //a function to call getChar until it returns a non-whitespace character
        while (Character.isWhitespace(nextChar)) {
            getChar();
        }
    }

    public static int lex(){
        lexLen = 0;
        getNonBlank();
        switch(charClass) {


        }

        return lexLen;
    }
    public static void main(String[] args) {
        System.out.println("\nThat's it! That's the melody to funky town!!\n");
        if ((in_fp = fopen("front.in", "r")) == NULL) {
            System.out.println("Error - Cannot open front.in\n");
        }else{
            getChar();
            do{lex();}while(nextToken != EOF);
        }
    }
}