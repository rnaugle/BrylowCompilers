
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
						}
						
						//peeked = true;
						
					}else if(state == 60){
						if(counter+1 >= fileSize){
							System.out.println(states[state]);
							done = true;
							counter++;
							token_value = "";
						}else if(file[counter+1] < (int)'l' && file[counter+1] < (int)'i'){
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
						}
						
						
					
					}else if(state == -1){
						state = lastFinalState;
						if(states[state].substring(states[state].length()-1,states[state].length()).equals("(")){
							System.out.println(states[state] + token_value.substring(0,token_value.length()-sinceLastFinal) + ")");
						}else{
							System.out.println(states[state]);
						}
						counter-=sinceLastFinal;
						state=0;
						token_value = "";
						done = true;
						
						
						
						
					//}else if(state == 43 || state == 44 || state == 45){
					//cant remember why i made this
						//this may be for spaces, but these are corresponding columns,not cases
						
					//if state is final
					}else if(!states[state].equals("")){
						lastFinalState = state;
						counter++;
						sinceLastFinal++;
						
					}else{
						counter++;
						sinceLastFinal++;
					}
				}

				//TODO: initialize output stream
			}

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
		   
		states = new String[]
				//0
				{"","INTEGER_LITERAL", "DECIMAL_LITERAL","BWAND","BWOR","XOR", "COMP", };
	}
	
	//makes 2d array for referencing next states
	public static void makeNextState()
	{
		//TODO create the array with next state references
		//-2 Illegal Token
		//-3 Invalid character in number.
		//this.nextState[0]=
		nextState = new int[][]{
//0		 0	1-9	1-7  .  x   X  A-F a-z  A-Z a   c   d   e    h   i   l   n   o   p  r   s   t   u    w  y   z  a-f  ,   &  |  ^  ~  +   -
		{24, 1,  1, 23, 39, 28, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 36, 39, 39, 39, 40, 39, 39, 39, 39, 39, 39, 22, 3, 4, 5, 6, 9, 10, 
//			*   /   !   (    )   [   ]   {   } \n  [space]   \t  =   "   <   >  _
			11, 12, 15, 16, 17, 18, 19, 20, 21, 0, 0, 0, 32, 34, 39, 30, 95},
			
//1		 0	1-9	1-7 .  x   X  A-F a-z  A-Z a   c   d   e    h   i   l   n   o   p  r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	   	{1, 1,  1, 2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -3},
			
//2		 0	1-9	1-7 .  x   X  A-F a-z  A-Z a   c   d   e    h   i   l   n   o   p  r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	   	{2, 2,  2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   >  _
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -3},
		
//3		 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &  |  ^  ~  +   -
	   	{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  7, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  [space]  \t   =   "   <   > _
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			
//4		 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	   	{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  8, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  _  \t   =   "   <   >
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			
//5		 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	   	{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  _  \t   =   "   <   >
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			
//6		 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	   	{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  _  \t   =   "   <   >
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			
//7		 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	   	{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  _  \t   =   "   <   >
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			
//8		 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	   	{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  _  \t   =   "   <   >
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			
//9		 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	   	{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  _  \t   =   "   <   >
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			
//10	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	   	{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  _  \t   =   "   <   >
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			
//11	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	   	{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  _  \t   =   "   <   >
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			
//12	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	   	{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  _  \t   =   "   <   >
			-1, 13, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			
//13	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	   	{13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 
//			*   /   !   (    )   [   ]   {   }   \n  _  \t   =   "   <   >
			13, 13, 13, 13, 13, 13, 13, 13, 13, 14, 13, 13, 13, 13, 13, 13},
			
//14	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	   	{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  _  \t   =   "   <   >
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			
//15	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
	    {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  _  \t   =   "   <   >
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 31, -1, -1, -1},
			
//16	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  _  \t   =   "   <   >
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			
//17	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  _  \t   =   "   <   >
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
				
//18	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  _  \t   =   "   <   >
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
					
//19	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  _  \t   =   "   <   >
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
						
//20	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  _  \t   =   "   <   >
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
							
//21	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  _  \t   =   "   <   >
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
								
//22	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  _  \t   =   "   <   >
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},

//23	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		 {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//			*   /   !   (    )   [   ]   {   }   \n  _  \t   =   "   <   >
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			
//24	 0	1-9	 1-7 .    x   X  A-F a-z  A-Z a   c   d   e   h   i   l   n   o   p   r   s   t   u    w  y   z  a-f  ,   &   |   ^   ~   +   -
		   	{-1, 26, -1, 25, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
//				*   /   !   (    )   [   ]   {   }   \n  _  \t   =   "   <   >
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
				
		//row 94
		// 0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e    h   i   l   n   o   p    r   s   t   u  w    y  z  a-f  ,   &  |  ^  ~  +   -
		{94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 
		// *   /   !   (    )   [   ]   {   } \n  _  \t  =   "   <   >  [space]
		94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94},
		//row 95
		// 0 1-9 1-7  .  x   X  A-F a-z  A-Z a   c   d   e    h   i   l   n   o   p    r   s   t   u  w    y  z  a-f  ,   &  |  ^  ~  +   -
		{95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, 95, -2, -2, -2, -2, -2, -2, -2, 
		// *   /   !   (    )   [   ]   {   } \n  _  \t  =   "   <   > [space]
		-2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2}
			

		};
		
	}

}
