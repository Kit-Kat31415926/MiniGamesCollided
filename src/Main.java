import java.lang.Thread;
import java.util.Scanner;

class Main {
	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);

		// Introduction
		print("Welcome to PuzzleLand!\n\nWhat is your name?\n");
		Player you = new Player(scan.nextLine());
		Story story = new Story();

		print("\nAs you wander aimlessly under the rising sun, you suddenly feel a chill run down your spine.\n");
		print("\n<Press enter to continue>\n");
		scan.nextLine();
		print("You turn around, but there is nothing there. Strange, you think.\n");
		scan.nextLine();
		print("With a shrug, you continue on your way, swinging your arms in a carefree fashion, thoughts floating in and out of your \nmind before you have a chance to process them.\n");
		scan.nextLine();
		print("Suddenly, you feel a small hand on your shoulder, tugging you backward, and you whirl around, only to find a tiny \nfaerie waving at you enthusiastically.\n");
		scan.nextLine();
		print("\"Hello, stranger! I'm Choco. It's quite nice to meet you. Where are you headed?\" the faerie asks.\n");
		scan.nextLine();
		print("You ponder Choco's question for a moment before responding.\n");
		print("1. On a journey to save the world\n2. To the ends of the world!\n3. Nowhere\n4. None of your business >:(\n");
		String choice = scan.nextLine();
		switch (choice.trim()) {
		case "1":
			print("\nYour answer impresses Choco. \"Wow! I'm honored to meet such a noble hero!\"\n");
			break;
		case "2":
			print("\nYour answer shocks Choco. \"You know the planet is round, no? You'll be running in circles until \nyou're in your grave!\"\n");
			break;
		case "3":
			print("\n\"Oh how pitiful to roam the earth without a purpose. A tragedy indeed...\"\n");
			break;
		case "4":
			print("\n\"How rude! Hmph! I curse you with bad luck!\"\n");
			break;
		default:
			print("\nYou remain silent. \"Ah. The quiet type, are you?\" Choco squints their eyes at you, scrutinizing you carefully.\n");
			break;
		}
		scan.nextLine();
		// Buy first item from Choco
		print("\"Anyway, I have some great news for you! As my ever-loyal customer, I'm offering you a special \ndiscount of 50% off any item in my shop!\n");
		print("\n<Press enter to see what's available at Choco's store!>\n");
		scan.nextLine();
		Store chocoStore = new Store("Choco");
		chocoStore.displayItems();
		chocoStore.selectPurchases(you, scan);
		print(" Choco smiles mischievously. \"I wish \nyou all the best on your journey~\"");
		scan.nextLine();
		story.playLottery(you, scan, chocoStore);
		// Check player stats
		print("\n\n<Press s to see your statistics>\n");
		you.setChoice(new String[] { "s" }, scan);
		you.printStats();
		print("\n\n<Press i to see your inventory>\n");
		you.setChoice(new String[] { "i" }, scan);
		you.printInventory();
		Main.print("\n\n");
		// Begin journey
		boolean play = true;
		while (play) {
			play = story.onward(you, scan);
		}
		// End program
		scan.close();
	}

	/**
	 * Print given statement
	 * 
	 * @param msg - Message to be printed
	 */
	public static void print(String msg) {
		try {
			for (int i = 0; i < msg.length(); i++) {
				System.out.print(msg.substring(i, i + 1));
				Thread.sleep(5);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}