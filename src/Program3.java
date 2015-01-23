
public class Program3 {

	 // b := ( print ( 15 , 87-1 ) , 10 * 14 ) ; print ( b )
	//15 86
	//140
    Stm program =
	    new CompoundStm(
		new AssignStm(
		    "b",
		    new EseqExp(
			new PrintStm(
			    new PairExpList(
				new NumExp(15),
				new LastExpList(
				    new OpExp(
					new NumExp(87),
					OpExp.MINUS,
					new NumExp(1))))),
			new OpExp(
			    new NumExp(10), 
			    OpExp.TIMES,
			    new NumExp(14)))),
		new PrintStm(
		    new LastExpList(
			new IdExp("b"))));
}
