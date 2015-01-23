
public class Scanner {

	static int[][] nextState = new int[94][94];

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
		//TODO
		//compare ascii values
		//return corresponding class
		if ((int)c >= (int)'0'&&(int)c <= (int)'9')
			return 1;
		else
			return 0;
	}
	//makes 2d array for referencing next states
	public static void makeNextState()
	{
		//TODO create the array with next state references
	}

}
