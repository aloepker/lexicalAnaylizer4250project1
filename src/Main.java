import java.io.*;
public class Main {
//variable calls
    public static int charClass, lexLen, nextToken;
    public static char nextChar;
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

    public static void lookup(char ch){
        lex = "";
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
    }
    public static void addChar(){
        lex = lex + nextChar;
    }
    public static void getChar() {
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
    public static void getNonBlank(){
        while (Character.isWhitespace(nextChar)) {
            getChar();
        }
    }
    public static void lex(){
        lexLen = 0;
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
            case DIGIT:
                addChar();
                getChar();
                while (charClass == DIGIT) {
                    addChar();
                    getChar();
                }
                nextToken = INT_LIT;
                break;
            case UNKNOWN:
                lookup(nextChar);
                getChar();
                break;
            case EOF:
                nextToken = EOF;
                lex = "EOF";
                break;
        }
        System.out.println("Next token is: "+nextToken+", Next lexeme is "+ lex +"\n");
        }
    public static void main(String[] args) {
        try {
            in_fp = new FileReader("front.in");
            getChar();
            do {
                lex();
            } while (nextToken != EOF);
        } catch (IOException e) {
            System.out.println("ERROR - cannot open front.in");
        }
    }
}