import java.util.Scanner;

public class Guess implements Game {
	private int num;
	Scanner scan;
	Player p;

	/**
	 * Create new guessing game
	 */
	public Guess(Player p, Scanner scan) {
		this.p = p;
		this.scan = scan;
		Main.print("Guess the Number");
	}
	
	public boolean play() {
		Main.print("Choosing a number...\n");
		scan.nextLine();
		num = (int) (Math.random() * 100) + 1;
		Main.print("Pick a number from 1 to 100 (inclusive): \n");
		int count = 0;
		int guess = scan.nextInt();
		scan.nextLine();
		while (count < 10 && guess != num) {
			if (Math.abs(guess - num) < 3) {
				Main.print("\nVery very hot!!\n");
			} else if (Math.abs(guess - num) < 10) {
				Main.print("\nHot!\n");
			} else if (Math.abs(guess - num) < 25) {
				Main.print("\nWarm.\n");
			} else {
				Main.print("\nCold...\n");
			}
			scan.nextLine();
			count++;
			if (count < 10) {
				Main.print("Pick a number from 1 to 100 (inclusive): \n");
				guess = scan.nextInt();
				scan.nextLine();
			}
		}
		if (count == 10) {
			Main.print("You ran out of turns...\n");
			return false;
		} else {
			return true;
		}
	}
}