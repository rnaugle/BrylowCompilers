
public class Scanner {

	//static int[][] nextState = new int[95][51];
	static int[][] nextState;
	static String[] states;

	public static void main(String[] args) {
		java.io.BufferedReader reader;
		makeNextState();
		stateName();
		

		try{
			//empty into array
			reader = new java.io.BufferedReader(new java.io.FileReader(args[0]));
			int fileSize = 0;
			
			//counting number of characters in file
			int test;
			while((test = reader.read())!= -1){
				if(test != 13){
					fileSize++;
					
				}
				
				
			}
			int[] file = new int[fileSize];
			reader.close();
			reader = new java.io.BufferedReader(new java.io.FileReader(args[0]));
			int index = 0;
			int ch;
			while((ch = reader.read())!= -1){
				if(ch != 13){
					file[index] = ch;
					index++;
				}
				
			}
			reader.close();
			
			
			int counter = 0;
			int c = file[counter];
			boolean peeked = false;
			char peek = ' ';
			int state=0;
			boolean done;
			String token_value;
			int charClass;
			int lastFinalState;

			while (counter  < fileSize)
			{
				//start at state 0 for each token with a false flag and empty string
				
				state  = 0;
				peeked = false;
				done = false;
				token_value = "";
				charClass = nextState.length;
				lastFinalState = 0;
				int sinceLastFinal = 0;
				c= file[counter];

				while (!done)
				{
					if(peeked){
						//c = peek;
					}else{
						//c = reader.read();
						//counter++;
					}
					c= file[counter];
					char cc = (char)c;
					token_value+=cc;
					charClass = getCharClass(cc);
					//unspecified characters throw a general error
					//unless in a string or comment
					if(charClass == -1){
						if(state == 13 || state == 95){
							;
						}else{
							state = 98;
						}
					}else{
						state = nextState[state][charClass];
					}
					
					if(state == 63 || state == 65 || state == 72 || state == 84 || state == 89 ||state == 94){
						//peek = read
						//if peeked character is letter or number
						//then reader.reset
						//else last final state = charClass, put peeked character back
						if(counter+1 >= fileSize){
							System.out.println(states[state]);
							done = true;
							counter++;
							token_value = "";
							sinceLastFinal = 0;
						}else if(file[counter+1] > (int)'A' && file[counter+1] < (int)'Z'){
							state = lastFinalState;
							System.out.println(states[state] +  token_value.substring(0,token_value.length()-sinceLastFinal) + ")");
							state = 0;
							counter-=sinceLastFinal;
							counter++;
							sinceLastFinal = 0;
							token_value = "";
							done = true;
							
							
						}else if(file[counter+1] > (int)'a' && file[counter+1] < (int)'z'){
							state = lastFinalState;
							System.out.println(states[state] +  token_value.substring(0,token_value.length()-sinceLastFinal) + ")");
							state = 0;
							counter-=sinceLastFinal;
							counter++;
							sinceLastFinal = 0;
							token_value = "";
							done = true;
							
						}else if(file[counter+1] > (int)'0' && file[counter+1]  <(int)'9'){
							state = lastFinalState;
							System.out.println(states[state] + token_value.substring(0,token_value.length()-sinceLastFinal) + ")");
							state = 0;
							counter-=sinceLastFinal;
							counter++;
							sinceLastFinal = 0;
							token_value = "";
							done = true;
						}else if(file[counter+1] == (int)'_'){
							state = lastFinalState;
							System.out.println(states[state] + token_value.substring(0,token_value.length()-sinceLastFinal) + ")");
							state = 0;
							counter-=sinceLastFinal;
							counter++;
							sinceLastFinal = 0;
							token_value = "";
							done = true;
						}else{
							lastFinalState = state;
							counter++;
							sinceLastFinal++;
							sinceLastFinal = 0;
							System.out.println(states[state]);
							token_value = "";
							done = true;
							
						}
						
						//peeked = true;
						
					}else if(state == 60){
						if(counter+1 >= fileSize){
							System.out.println(states[state]);
							done = true;
							counter++;
							token_value = "";
						}else if(file[counter+1] == (int)'l' || file[counter+1] == (int)'i'){
							counter++;
							sinceLastFinal++;
						}else if(file[counter+1] > (int)'A' && file[counter+1] < (int)'Z'){
							state = lastFinalState;
							System.out.println(states[state] +  token_value.substring(0,token_value.length()-sinceLastFinal) + ")");
							state = 0;
							counter-=sinceLastFinal;
							sinceLastFinal = 0;
							counter++;
							token_value = "";
							done = true;
							
							
						}else if(file[counter+1] > (int)'a' && file[counter+1] < (int)'z'){
							state = lastFinalState;
							System.out.println(states[state] + token_value.substring(0,token_value.length()-sinceLastFinal) + ")");
							state = 0;
							counter-=sinceLastFinal;
							counter++;
							sinceLastFinal = 0;
							token_value = "";
							done = true;
							
						}else if(file[counter+1] > (int)'0' && file[counter+1]  <(int)'9'){
							state = lastFinalState;
							System.out.println(states[state] + token_value.substring(0,token_value.length()-sinceLastFinal) + ")");
							state = 0;
							counter-=sinceLastFinal;
							counter++;
							sinceLastFinal = 0;
							token_value = "";
							done = true;
						}else if(file[counter+1] == (int)'_'){
							state = lastFinalState;
							System.out.println(states[state] + token_value.substring(0,token_value.length()-sinceLastFinal) + ")");
							state = 0;
							counter-=sinceLastFinal;
							counter++;
							sinceLastFinal = 0;
							token_value = "";
							done = true;
						}else{
							lastFinalState = state;
							counter++;
							sinceLastFinal++;
							sinceLastFinal = 0;
							System.out.println(states[state]);
							token_value = "";
							done = true;
						}
						
					
						
					
					}else if(state == -1){
						state = lastFinalState;
						String maybe;
						maybe = isRes(token_value.substring(0,token_value.length()-sinceLastFinal));
						if(maybe.equals("")){
							if( state == 14 || state ==97){
								;
							}
							else if(states[state].equals("STRING_LITERAL(")){
								System.out.println(states[state] + token_value.substring(1,token_value.length()-sinceLastFinal-1) + ")");
							}
							else if(states[state].charAt(states[state].length()-1) == '('){
								System.out.println(states[state] + token_value.substring(0,token_value.length()-sinceLastFinal) + ")");
							}else if(state != 14 && state != 100){
								System.out.println(states[state]);
							}
							
						}else{
							System.out.println(maybe);
						}
						counter-=sinceLastFinal;
						counter++;
						state=0;
						token_value = "";
						done = true;
						
						
						
						
					//}else if(state == 43 || state == 44 || state == 45){
					//cant remember why i made this
						//this may be for spaces, but these are corresponding columns,not cases
					}else if(state == 0){
						lastFinalState = 0;
						sinceLastFinal = 0;
						counter++;
						token_value = "";
						done = true;
					//if state is final
					}else if(!states[state].equals("") || state == 14 || state ==97){
						lastFinalState = state;
						counter++;
						sinceLastFinal = 0;
						sinceLastFinal++;
						if(counter+1 >= fileSize){
							String maybe;
							sinceLastFinal--;
							maybe = isRes(token_value.substring(0,token_value.length()-sinceLastFinal));
							if( state == 14 || state ==97){
								;
							}
							else if(maybe.equals("")){
								if(states[state].equals("STRING_LITERAL(")){
									System.out.println(states[state] + token_value.substring(1,token_value.length()-sinceLastFinal-1) + ")");
								}
								else if(states[state].charAt(states[state].length()-1) == '('){
									System.out.println(states[state] + token_value.substring(0,token_value.length()-sinceLastFinal) + ")");
								}else if(state != 14 && state != 100){
									System.out.println(states[state]);
								}
								
							}else{
								System.out.println(maybe);
							}
							counter++;
							state=0;
							token_value = "";
							done = true;
						}
						
						
					}else{
						counter++;
						sinceLastFinal++;
						if(counter >= fileSize){
							if(state == 95 || state == 96){
								state = 104;
								done = true;
							}else if(state == 34){
								state = 103;
								done = true;
							}else{
								state = lastFinalState;
								String maybe;
								maybe = isRes(token_value.substring(0,token_value.length()-sinceLastFinal));
								if( state == 14 || state ==97){
									;
								}
								else if(maybe.equals("")){
								
									if(states[state].equals("STRING_LITERAL(")){
										System.out.println(states[state] + token_value.substring(1,token_value.length()-sinceLastFinal-1) + ")");
									}
									else if(states[state].charAt(states[state].length()-1) == '('){
										System.out.println(states[state] + token_value.substring(0,token_value.length()-sinceLastFinal) + ")");
									}else if(state != 14 && state != 100){
										System.out.println(states[state]);
									}
									
								}else{
									System.out.println(maybe);
								}
								counter-=sinceLastFinal;
								//counter++;
								state=0;
								token_value = "";
								done = true;
							}
							
						}
					}
				}

				//TODO: initialize output stream
			}
			
			if(state == 104 || state == 103){
				
					System.out.println(states[state]);
				
			}
			System.out.println("EOF");
			
			
			//check if in incomplete state

			reader.close();
		}
		catch (Exception e){
			System.out.println("Error reading file.");}

	}
	


	public static int getCharClass(char c)
	{ 
		//compare ascii values
		//return corresponding class
		if ((int)c >= (int)'0'&&(int)c <= (int)'9')
		{
			if((int)c == (int)'0')
				return 0;
			else if ((int)c >= (int)'1'&&(int)c <= (int)'7')
				return 1;
			else if ((int)c >= (int)'1'&&(int)c <= (int)'9')
				return 2;
			else
				return -1;
		}
		else if ((int)c==(int)'.')
			return 3;
		else if ((int)c >= (int)'A' && (int)c <= (int)'Z')
		{
			if((int)c == (int)'X')
				return 5;
			else if ((int)c >= (int)'A' && (int)c <= (int)'F')
				return 6;
			else
				return 8;
		}
		else if((int)c >= (int)'a' && (int)c <='z')
		{
			if((int)c == (int)'x')
				return 4;		
			else if((int)c == (int)'a'){
				return 9;
			}else if((int)c == (int)'c'){
				return 10;
			}else if((int)c == (int)'d'){
				return 11;
			}else if((int)c == (int)'e'){
				return 12;
			}else if((int)c == (int)'h'){
				return 13;
			}else if((int)c == (int)'i'){
				return 14;
			}else if((int)c == (int)'l'){
				return 15;
			}else if((int)c == (int)'n'){
				return 16;
			}else if((int)c == (int)'o'){
				return 17;
			}else if((int)c == (int)'p'){
				return 18;
			}else if((int)c == (int)'r'){
				return 19;
			}else if((int)c == (int)'s'){
				return 20;
			}else if((int)c == (int)'t'){
				return 21;
			}else if((int)c == (int)'u'){
				return 22;
			}else if((int)c == (int)'w'){
				return 23;
			}else if((int)c == (int)'y'){
				return 24;
			}else if((int)c == (int)'z'){
				return 25;
			}else if ((int)c >= (int)'a' && (int)c <= (int)'f'){
				return 26;			
			}else
				return 7;
			
		
		}else if((int)c == (int)','){
			return 27;
		}else if((int)c == (int)'&'){
			return 28;
		}else if((int)c == (int)'|'){
			return 29;
		}else if((int)c == (int)'^'){
			return 30;
		}else if((int)c == (int)'~'){
			return 31;
		}else if((int)c == (int)'+'){
			return 32;
		}else if((int)c == (int)'-'){
			return 33;
		}else if((int)c == (int)'*'){
			return 34;
		}else if((int)c == (int)'/'){
			return 35;
		}else if((int)c == (int)'!'){
			return 36;
		}else if((int)c == (int)'('){
			return 37;
		}else if((int)c == (int)')'){
			return 38;
		}else if((int)c == (int)'['){
			return 39;
		}else if((int)c == (int)']'){
			return 40;
		}else if((int)c == (int)'{'){
			return 41;
		}else if((int)c == (int)'}'){
			return 42;
		}else if((int)c == (int)'\n'){
			return 43;
		}else if((int)c == (int)' '){
			return 44;
		}else if((int)c == (int)'\t'){
			return 45;
		}else if((int)c == (int)'='){
			return 46;
		}else if((int)c == (int)'"'){
			return 47;
		}else if((int)c == (int)'<'){
			return 48;
		}else if((int)c == (int)'>'){
			return 49;
		}else if((int)c == (int)'_'){
			return 50;
		}else if((int)c == (int)';'){
			return 51;
		}
	
		else
			return -1;
	}
	
	public static String isRes(String s){
		if(s.equals("class") || s.equals("public") || s.equals("main") || s.equals("extends") || s.equals("void") || s.equals("int") || s.equals("boolean") || s.equals("if") || s.equals("while") || s.equals("String")){
			return s.toUpperCase();
		}else{
			return "";
		}
	}
	
	public static void stateName(){
		
		//TODO: initialize
		states = new String[]
				//0
				{"","INTEGER_LITERAL(", "DECIMAL_LITERAL(","BWAND","BWOR","XOR", "COMP", "AND", "OR", "PLUS", "MINUS", "STAR", "FORWARDSLASH", "", "", "BANG", "LPAREN", "RPAREN", "LSQUARE", "RSQUARE", "LBRACE",
				"RBRACE", "COMMA", "PERIOD", "INTEGER_LITERAL(", "", "OCTAL_LITERAL(", "HEXADECIMAL_LITERAL(", "ID(", "LESSTHAN", "GREATERTHAN", "NOTEQUAL", "ASSIGN", "EQUAL", "", "STRING_LITERAL(", 
				"ID(", "ID(", "NEW", "ID(", "ID(", "ID(", "ID(", "ID(", "ID(", "ID(", "ID(", "ID(", "ID(", "ID(", "ID(", "SYNCHRONIZED", "ID(", "ID(", "ID(", "", "", "", "", "", "PRINT", "", "", "PRINTLN"
				, "", "PRINTINT", "", "", "", "", "", "", "READINT", "", "", "", "", "", "", "", "", "", "", "", "THREADCREATE", "", "", "", "", "YIELD", "", "", "", "", "SLEEP", "", "", "","Illegal token.", "Invalid character in number."
				, "Invalid character in hex number.", "Invalid character in octal number.","SEMICOLON", "String not terminated at end of line.", "Comment not terminated at end of input."};
		}
	
	//makes 2d array for referencing next states
	public static void makeNextState()
	{
		//TODO create the array with next state references
		//-2 Illegal Token 98
		//-3 Invalid character in number. 99
		//-4 Invalid character in hex number. 100
		//-5 Invalid character in octal number. 101
		//-6 String not terminated at end of line. 102
		//this.nextState[0]=
		nextState = new int[][]{
//0		 0	1-9	1-7  .  x   X  A-F a-z  A-Z a   c   d   e    h   i   l   n   o   p  r   s   t   u    w  y   z  a-f  ,   &  |  ^  ~  +   -
		{24, 1,  1, 23, 39, 28, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 36, 39, 39, 39, 40, 39, 39, 39, 39, 39, 39, 22, 3, 4, 5, 6, 9, 10, 
//			*   /   !   (    )   [   ]   {   } \n  [space]   \t  =   "   <   >  _
			11, 12, 15, 16, 17, 18, 19, 20, 21, 0, 0, 0, 32, 34, 39, 30, 98, 102},
			
//1		 0	1-9	1-7 .  x   X  A-F a-z  A-Z a   c   d   e    h   i   l   n   o   p  r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	   	{1, 1,  1, 2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 99, -1},
			
//2		 0	1-9	1-7 .  x   X  A-F a-z  A-Z a   c   d   e    h   i   l   n   o   p  r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	   	{2, 2,  2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   >  _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 99, -1},
		
//3		 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &  |  ^  ~  +   -
	   	{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  7, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			
//4		 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	   	{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  8, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			
//5		 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	   	{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			
//6		 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	   	{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			
//7		 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	   	{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			
//8		 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	   	{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			
//9		 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	   	{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			
//10	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	   	{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			
//11	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	   	{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			
//12	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	   	{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			95, 13, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			
//13	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	   	{13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			13, 13, 13, 13, 13, 13, 13, 13, 13, 14, 13, 13, 13, 13, 13, 13, 13,13},
			
//14	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	   	{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			
//15	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	    {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 31, -1, -1, -1, -1, -1},
			
//16	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			
//17	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
				
//18	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
					
//19	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
						
//20	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
							
//21	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
								
//22	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},

//23	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		 {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			
//24	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{26, 26, -1, 2, 25, 25, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 99, -1},
				
//25	 0	1-9	 1-7 .    x    X   A-F a-z  A-Z   a   c   d   e   h    i    l    n     o   p    r    s    t    u    w     y   z   a-f  ,   &   |   ^   ~   +   -
		{27, 27, 27, -1, 100, 100, 27, 100, 100, 27, 27, 27, 27, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 27, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 100, -1},
		
//26	 0	1-9	 1-7   .   x    X   A-F  a-z  A-Z   a    c    d    e    h    i    l    n     o   p    r    s    t    u    w     y   z   a-f  ,   &   |   ^   ~   +   -
		{26, 101, 26, -1, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 101, -1},
		
//27	 0	1-9	 1-7 .    x    X   A-F a-z  A-Z   a   c   d   e   h    i    l    n     o   p    r    s    t    u    w     y   z   a-f  ,   &   |   ^   ~   +   -
		{27, 27, 27, -1, 100, 100, 27, 100, 100, 27, 27, 27, 27, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 27, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 100, -1},
		
//28	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{39, 39, 39, -1, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 52, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39, -1},
		
//29	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//30	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//31	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//32	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 33, -1, -1, -1, -1, -1},
		
//33	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//34	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		34, 34, 34, 34, 34, 34, 34, 34, 34, 103, 34, 34, 34, 35, 34, 34, 34, -1},
		
//35	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//36	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{39, 39, 39, -1, 39, 39, 39, 39, 39, 39, 39, 39, 37, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39, -1},
		
//37	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{39, 39, 39, -1, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 38, 39, 39, 39, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39, -1},
		
//38	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{39, 39, 39, -1, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39, -1},
		
//39	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{39, 39, 39, -1, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39, -1},
		
//40	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w  y   z  a-f    ,   &   |   ^   ~   +   -
		{39, 39, 39, -1, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 41, 39, 39, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39, -1},
		
//41	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{39, 39, 39, -1, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 42, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39, -1},
		
//42	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{39, 39, 39, -1, 39, 39, 39, 39, 39, 39, 43, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39, -1},
		
//43	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{39, 39, 39, -1, 39, 39, 39, 39, 39, 39, 39, 39, 39, 44, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39, -1},
		
//44	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{39, 39, 39, -1, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 45, 39, 39, 39, 39, 39, 39, 39, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39, -1},
		
//45	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{39, 39, 39, -1, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 46, 39, 39, 39, 39, 39, 39, 39, 39, 39, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39, -1},
		
//46	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{39, 39, 39, -1, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 47, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39, -1},
		
//47	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{39, 39, 39, -1, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 48, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39, -1},
		
//48	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{39, 39, 39, -1, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 49, 39, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39, -1},
		
//49	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{39, 39, 39, -1, 39, 39, 39, 39, 39, 39, 39, 39, 50, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39, -1},
		
//50	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{39, 39, 39, -1, 39, 39, 39, 39, 39, 39, 39, 51, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39, -1},
		
//51	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{39, 39, 39, -1, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39, -1},
		
//52	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{39, 39, 39, -1, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 53, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39, -1},
		
//53	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{39, 39, 39, -1, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 54, 39, 39, 39, 39, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39, -1},
		
//54	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{39, 39, 39, 55, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39, -1},
		
//55	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 56, 66, 90, 73, -1, -1, 85, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//56	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 57, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//57	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 58, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//58	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 59, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//59	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 60, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//60 	0   1-9  1-7  .  x   X   A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p    r   s   t   u  w    y  z  a-f  ,   &  |  ^  ~  +   -
		{60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 62, 61, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, -1},
		
//61 	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p    r   s   t   u  w    y  z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 63, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//62 	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p    r   s   t   u  w    y  z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 64, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//63 	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p    r   s   t   u  w    y  z  a-f  ,   &  |  ^  ~  +   -
		{63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, -1},
		
//64 	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p    r   s   t   u  w    y  z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 65, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//65 	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p    r   s   t   u  w    y  z  a-f  ,   &  |  ^  ~  +   -
		{65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, -1},
		
//66 	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 67, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//67 	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, 68, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//68 	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 69, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//69 	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 70, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//70 	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 71, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//71 	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 72, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//72 	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, -1},
		
//73 	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 74, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//74 	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 75, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//75 	  0  1-9 1-7  .   x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 76, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
							
//76	  0  1-9 1-7  .   x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, 77, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},		
		
//77	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 78, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//78	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 79, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//79	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 80, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//80	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 81, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//81	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, 82, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//82	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 83, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
	
//83	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 84, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//84 	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e    h   i   l   n   o   p    r   s   t   u  w    y  z  a-f  ,   &  |  ^  ~  +   -
		{84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, -1},
		
//85	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 86, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//86	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  87, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//87	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  -1, 88, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//88	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 89, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//89	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, -1},
		
//90	 0   1-9  1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 91, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//91	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 92, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//92	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 93, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//93	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 94, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
				
//94 	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e    h   i   l   n   o   p    r   s   t   u  w    y  z  a-f  ,   &  |  ^  ~  +   -
		{94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, -1},
		
		//generic Illegal token.
//95 	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e    h   i   l   n   o   p    r   s   t   u  w    y  z  a-f  ,   &  |  ^  ~  +   -
		{95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 
// 		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		96, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95},
			
//96	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e    h   i   l   n   o   p    r   s   t   u  w    y  z  a-f  ,   &  |  ^  ~  +   -
		{95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95,
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		95, 97, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95},
		
//97	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e    h   i   l   n   o   p    r   s   t   u  w    y  z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//98 	 0  1-9  1-7  .  x   X   A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w    y  z   a-f  ,   &   |   ^   ~   +   -
		{98, 98, 98, -1, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, -1, -1, -1, -1, -1, -1, -1, 
//		 		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
				
//99 	 0  1-9  1-7  .  x   X   A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w    y  z   a-f  ,   &   |   ^   ~   +   -
		{99, 99, 99, -1, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, -1, -1, -1, -1, -1, -1, -1, 
//				*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},

//100 	 0  1-9  1-7  .  x   X   A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w    y  z   a-f  ,   &   |   ^   ~   +   -
		{100, 100, 100, -1, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, -1, -1, -1, -1, -1, -1, -1, 
//				*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},

//101 	 0  1-9  1-7  .  x   X   A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w    y  z   a-f  ,   &   |   ^   ~   +   -
		{101, 101, 101, -1, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, -1, -1, -1, -1, -1, -1, -1, 
//				*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},	
//102 	 0  1-9  1-7  .  x   X   A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w    y  z   a-f  ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//				*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}

		};
		
	}

}
