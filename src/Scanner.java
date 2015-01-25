
public class Scanner {

	static int[][] nextState = new int[95][50];

	public static void main(String[] args) {
		java.io.BufferedReader reader;
		makeNextState();

		try{
			reader = new java.io.BufferedReader(new java.io.FileReader(args[0]));
			int c = reader.read();

			while (c  != -1)
			{
				//start at state 0 for each token with a false flag and empty string
				int state  = 0;
				boolean done = false;
				String token_value = "";
				int charClass = nextState.length;
				int lastFinalState = 0;

				while (!done)
				{
					char cc = (char)c;
					charClass = getCharClass(cc);
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
		}
		else
			return -1;
	}
	//makes 2d array for referencing next states
	public static void makeNextState()
	{
		//TODO create the array with next state references
		this.nextState[0]={24,1,1,-1,39,28,39}
		
	}

}
