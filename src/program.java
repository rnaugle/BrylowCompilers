
public class program {
	// b := 2 ; b:= 3 ; print ( c , b )
	
	Stm program =
		    new CompoundStm(
			new AssignStm(
			    "b",
			    new NumExp(2)),
			    new CompoundStm(
			    		new AssignStm(
			    				"b",
			    				new NumExp(3)),
			    				new PrintStm(
			    						new PairExpList(
			    								new IdExp("c"),
			    								new LastExpList(
			    										new IdExp("b"))))));
}
