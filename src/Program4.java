
public class Program4 {
	Stm program =
			new PrintStm(new LastExpList(new OpExp(
					new NumExp(87),
					OpExp.MINUS,
					new NumExp(1))));
}
