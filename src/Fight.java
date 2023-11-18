import java.util.Scanner;

public class Fight implements Game {
	private Player opp, p;
	private Scanner scan;

	public Fight(Player p, Scanner scan) {
		this.p = p;
		this.scan = scan;
		Main.print("Fight");
	}

	@Override
	public boolean play() {
		opp = new Player("Monster");
		opp.setHp(170);
		opp.setCurrentHp(170);
		opp.setAtk(8);
		opp.setDef(3);
		// 0 for player's turn and 1 for opponent's turn to decide who starts
		boolean yourTurn = (int) (Math.random() * 2) % 2 == 0;
		if (yourTurn) {
			yourTurn();
		} else {
			oppTurn();
		}
		boolean result = checkWinner();
		return false;
	}

	private void yourTurn() {
		Main.print("Your turn!\n\nWhat would you like to do?\n");
		Main.print("1. Attack!\n2. Defend\n3. Heal\n");
		
	}

	private void oppTurn() {
		// TODO Auto-generated method stub

	}

	private boolean checkWinner() {
		// TODO Auto-generated method stub
		return false;
	}

}
