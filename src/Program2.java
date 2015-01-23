/**
 * Program.java
 *
 * A program to be interpreted.  Abstract Syntax Tree classes are
 *    defined in Straightline.java, and the interpreter is in
 *    Interpreter.java.  See Appel chapter 1 for details.
 */

public class Program2{

    // print ( 34 )
    Stm program =
	new PrintStm(new LastExpList(new NumExp(34)));

} // Program
