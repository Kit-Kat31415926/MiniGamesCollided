import java.util.Scanner;

public class Fireball implements Game{
	private Player opp, p;
	private int pCharge, oppCharge;
	private Scanner scan;

	public Fireball(Player p, Scanner scan) {
		this.p = p;
		this.scan = scan;
		opp = new Player("Opponent");
		Main.print("Fireball");
	}
	
	public boolean play() {
		boolean play = true;
		while (play) {
			chooseMove();
			displayResults();
			if (checkWinner()) {
				play = false;
			}
		}
		return false;
	}

	private void chooseMove() {
		Main.print("What would you like to do?\n");
		Main.print("1. Fireball! [You have " + pCharge + " charges]\n2. Shield\n3. Charge\n");
		boolean valid = false;
		while (!valid) {
			p.setIntChoice(1, 3, scan);
			if (p.getIntChoice() == 1 && pCharge > 0) {
				valid = true;
			} else {
				Main.print("\nNot enough charges. Try again.\n\nWhat would you like to do?\n");
			}
		}
	}

	private void displayResults() {
		if (oppCharge > 0) {
			opp.setIntChoice((int) (Math.random() * 3) + 1);
		} else {
			opp.setIntChoice((int) (Math.random() * 2) + 2);			
		}
		Main.print("\nYou " + getMove(p) + "\n");
		if (p.getIntChoice() == 1) {
			pCharge--;
		}
		if (p.getIntChoice() == 3) {
			pCharge++;
		}
		Main.print("\nYour opponent " + getMove(opp) + "\n");
		if (opp.getIntChoice() == 1) {
			oppCharge--;
		}
		if (opp.getIntChoice() == 3) {
			oppCharge++;
		}
		scan.nextLine();
	}
	
	private String getMove(Player player) {
		switch (player.getIntChoice()) {
			case 1:
				return "attacked with a fireball!";
			case 2:
				return "defended with a shield!";
			case 3:
				return "charged up their next fireball!";
			default:
				return "";
		}
	}

	private boolean checkWinner() {
		if (p.getIntChoice() == 1 && (opp.getIntChoice() == 1 || opp.getIntChoice() == 3)) {
			Main.print("You burned your opponent!\n");
			return true;
		}
		if (opp.getIntChoice() == 1 && (p.getIntChoice() == 1 || p.getIntChoice() == 3)) {
			Main.print("Your opponent singed you!\n");
			return true;
		}
		return false;
	}

}
