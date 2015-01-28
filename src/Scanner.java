
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
			while(reader.read()!= -1){
				fileSize++;
			}
			int[] file = new int[fileSize];
			reader.close();
			reader = new java.io.BufferedReader(new java.io.FileReader(args[0]));
			int index = 0;
			int ch;
			while((ch = reader.read())!= -1){
				file[index] = ch;
				index++;
			}
			reader.close();
			
			
			int counter = 0;
			int c = file[counter];
			boolean peeked = false;
			char peek = ' ';
			int state;
			boolean done;
			String token_value;
			int charClass;
			int lastFinalState;

			while (c  != fileSize)
			{
				//start at state 0 for each token with a false flag and empty string
				
				state  = 0;
				peeked = false;
				done = false;
				token_value = "";
				charClass = nextState.length;
				lastFinalState = 0;
				int sinceLastFinal = 0;

				while (!done)
				{
					if(peeked){
						//c = peek;
					}else{
						//c = reader.read();
						//counter++;
					}
					char cc = (char)c;
					token_value+=cc;
					charClass = getCharClass(cc);
					state = nextState[state][charClass];
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
						}else if(file[counter+1] == (int)'l' && file[counter+1] == (int)'i'){
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
						if(states[state].charAt(states[state].length()-1) == '('){
							System.out.println(states[state] + token_value.substring(0,token_value.length()-sinceLastFinal) + ")");
						}else if(state != 14 && state != 100){
							System.out.println(states[state]);
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
					}else if(!states[state].equals("")){
						lastFinalState = state;
						counter++;
						sinceLastFinal++;
						if(counter+1 >= fileSize){
							if(states[state].charAt(states[state].length()-1) == '('){
								System.out.println(states[state] + token_value.substring(0,token_value.length()-sinceLastFinal) + ")");
							}else if(state != 14 && state != 100){
								System.out.println(states[state]);
							}
							
							counter++;
							state=0;
							token_value = "";
							done = true;
						}
						
					}else{
						counter++;
						sinceLastFinal++;
						if(counter+1 >= fileSize){
							state = lastFinalState;
							if(states[state].charAt(states[state].length()-1) == '('){
								System.out.println(states[state] + token_value.substring(0,token_value.length()-sinceLastFinal) + ")");
							}else if(state != 14 && state != 100){
								System.out.println(states[state]);
							}
							counter-=sinceLastFinal;
							counter++;
							state=0;
							token_value = "";
							done = true;
						}
					}
				}

				//TODO: initialize output stream
			}
			
			
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
		else if((int)c >= (int)'a' && (int)c >='z')
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
		}
	
		else
			return -1;
	}
	
	public static void stateName(){
		
		//TODO: initialize
		states = new String[]
				//0
				{"","INTEGER_LITERAL(", "DECIMAL_LITERAL(","BWAND","BWOR","XOR", "COMP", "AND", "OR", "PLUS", "MINUS", "STAR", "FORWARDSLASH", "", "", "BANG", "LPAREN", "RPAREN", "LSQUARE", "RSQUARE", "LBRACE",
				"RBRACE", "COMMA", "PERIOD", "INTEGER_LITERAL(", "", "OCTAL_LITERAL(", "HEXADECIMAL_LITERAL", "ID(", "LESSTHAN", "GREATERTHAN", "NOTEQUAL", "ASSIGN", "EQUAL", "", "STRING_LITERAL", 
				"ID(", "ID(", "NEW", "ID(", "ID(", "ID(", "ID(", "ID(", "ID(", "ID(", "ID(", "ID(", "ID(", "ID(", "ID(", "SYNCHRONIZED", "ID(", "ID(", "ID(", "", "", "", "", "", "PRINT", "", "", "PRINTLN"
				, "", "PRINTLN", "", "", "", "", "", "", "READINT", "", "", "", "", "", "", "", "", "", "", "", "THREADCREATE", "", "", "", "", "YIELD", "", "", "", "", "SLEEP","Illegal Token", "Invalid character in number."
				, ""};
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
			11, 12, 15, 16, 17, 18, 19, 20, 21, 0, 0, 0, 32, 34, 39, 30, 98},
			
//1		 0	1-9	1-7 .  x   X  A-F a-z  A-Z a   c   d   e    h   i   l   n   o   p  r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	   	{1, 1,  1, 2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 99},
			
//2		 0	1-9	1-7 .  x   X  A-F a-z  A-Z a   c   d   e    h   i   l   n   o   p  r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	   	{2, 2,  2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   >  _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 99},
		
//3		 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &  |  ^  ~  +   -
	   	{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  7, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			
//4		 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	   	{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  8, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			
//5		 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	   	{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			
//6		 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	   	{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			
//7		 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	   	{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			
//8		 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	   	{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			
//9		 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	   	{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			
//10	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	   	{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			
//11	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	   	{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			
//12	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	   	{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			95, 13, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			
//13	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	   	{13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			13, 13, 13, 13, 13, 13, 13, 13, 13, 14, 13, 13, 13, 13, 13, 13, 13},
			
//14	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	   	{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			
//15	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	    {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 31, -1, -1, -1, -1},
			
//16	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			
//17	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
				
//18	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
					
//19	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
						
//20	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
							
//21	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
								
//22	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},

//23	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		 {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			
//24	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{26, 26, -1, 2, 25, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 99},
				
//25	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{27, 27, 27, -1, 100, 100, 27, 100, 100, 27, 27, 27, 27, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 27, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 100},
		
//26	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{26, 101, 26, -1, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 101},
		
//27	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{27, 27, 27, -1, 100, 100, 27, 100, 100, 27, 27, 27, 27, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 27, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 100},
		
//28	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{39, 39, 39, -1, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 52, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39},
		
//29	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//30	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//31	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//32	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 33, -1, -1, -1, -1},
		
//33	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//34	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		34, 34, 34, 34, 34, 34, 34, 34, 34, 102, 34, 34, 34, 35, 34, 34, 34},
		
//35	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//36	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{39, 39, 39, -1, 39, 39, 39, 39, 39, 39, 39, 39, 37, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39},
		
//37	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{39, 39, 39, -1, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 38, 39, 39, 39, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39},
		
//38	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{39, 39, 39, -1, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39},
		
//39	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{39, 39, 39, -1, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39},
		
//40	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{39, 39, 39, -1, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 40, 39, 39, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39},
		
//41	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{39, 39, 39, -1, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 42, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39},
		
//42	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{39, 39, 39, -1, 39, 39, 39, 39, 39, 39, 43, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39},
		
//43	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{39, 39, 39, -1, 39, 39, 39, 39, 39, 39, 39, 39, 39, 44, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39},
		
//44	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{39, 39, 39, -1, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 45, 39, 39, 39, 39, 39, 39, 39, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39},
		
//45	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{39, 39, 39, -1, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 46, 39, 39, 39, 39, 39, 39, 39, 39, 39, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39},
		
//46	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{39, 39, 39, -1, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 47, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39},
		
//47	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{39, 39, 39, -1, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 48, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39},
		
//48	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{39, 39, 39, -1, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 49, 39, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39},
		
//49	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{39, 39, 39, -1, 39, 39, 39, 39, 39, 39, 39, 39, 50, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39},
		
//50	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{39, 39, 39, -1, 39, 39, 39, 39, 39, 39, 39, 51, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39},
		
//51	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{39, 39, 39, -1, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39},
		
//52	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{39, 39, 39, -1, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 53, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39},
		
//53	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{39, 39, 39, -1, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39},
		
//54	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{39, 39, 39, 55, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39},
		
//55	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 56, 66, 90, 73, -1, -1, 85, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//56	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 57, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//57	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 58, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//58	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 59, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//59	0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u  w  y   z  a-f    ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 60, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//60 	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e    h   i   l   n   o   p    r   s   t   u  w    y  z  a-f  ,   &  |  ^  ~  +   -
		{60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60},
		
//61 	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p    r   s   t   u  w    y  z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 63, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//62 	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p    r   s   t   u  w    y  z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 64, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//63 	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p    r   s   t   u  w    y  z  a-f  ,   &  |  ^  ~  +   -
		{63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63},
		
//64 	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p    r   s   t   u  w    y  z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 65, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//65 	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p    r   s   t   u  w    y  z  a-f  ,   &  |  ^  ~  +   -
		{65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65},
		
//66 	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 67, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//67 	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, 68, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//68 	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 69, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//69 	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 70, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//70 	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 71, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//71 	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 72, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//72 	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72},
		
//73 	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 74, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//74 	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 75, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//75 	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 76, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
							
//76	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 77, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},		
		
//77	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 78, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//78	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, 79, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//79	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 80, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//80	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 81, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//81	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, 82, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//82	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 83, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
	
//83	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 84, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//84 	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e    h   i   l   n   o   p    r   s   t   u  w    y  z  a-f  ,   &  |  ^  ~  +   -
		{84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84},
		
//85	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 86, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//86	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 87, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//87	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 88, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//88	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 89, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//89	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89},
		
//90	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 91, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//91	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 92, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//92	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 93, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
		
//93	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u   w   y   z  a-f  ,   &  |  ^  ~  +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 94, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
				
//94 	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e    h   i   l   n   o   p    r   s   t   u  w    y  z  a-f  ,   &  |  ^  ~  +   -
		{94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 
//		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94},
		
		//generic Illegal token.
//95 	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e    h   i   l   n   o   p    r   s   t   u  w    y  z  a-f  ,   &  |  ^  ~  +   -
		{95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, -1, -1, -1, -1, -1, -1, -1, 
// 		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			
		//95 	0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e    h   i   l   n   o   p    r   s   t   u  w    y  z  a-f  ,   &  |  ^  ~  +   -
				{95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, -1, -1, -1, -1, -1, -1, -1, 
//		 		*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
				96, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}
					
		};
		
	}

}
