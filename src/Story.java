import java.util.ArrayList;
import java.util.Scanner;

public class Story {
	private final String[] adjectives = { "carnivorous", "chocolate", "screeching", "frightening", "gigantic", "flying",
			"handsome", "fearsome", "wicked", "dancing", "lonely", "hungry", "thirsty" , "wild"};
	private final String[] enemies = { "tarantula", "baby", "giant (Fee Fi Fo Fum)", "troll", "fairy", "elf", "beast",
			"Cookie Monster", "witch", "lion", "bandit" };
	private final int numChallenges = 4;
	// Potential Endings
	private final String[] verbs = { "wants to destroy all of humanity so that its children may reign over the Earth",
			"is about to start crying", 
			"wants to crush you to smithereens" };
	private final String[] warnings = { "to prevent the apocalype", 
			"to prevent a flood", 
			"to survive" };
	private final String[] badConsequences = { "The apocalype has arrived",
			"You are hit with a wave of tears", 
			"You died" };
	private final String[] goodConsequences = { "the arrival of the apocalypse", "a disastrous flood", "near death" };
	// -1 = game over; 0 = nothing; 1 = mermaid
	private final int[] endGame = { -1, 1, -1 };

	public void playLottery(Player p, Scanner scan, Store s) {
		if (p.checkLottery()) {
			Main.print(
					"\n\n\"By the way, I noticed you bought a lottery ticket.\" Choco brandishes a small velvet bag, \nshaking it vigorously so you can hear the objects within.");
			scan.nextLine();
			Main.print("\n\"Would you like to cash it in now?\" (y/n)\n");
			p.setChoice(new String[] { "y", "n" }, scan);
			if (p.getChoice().equalsIgnoreCase("y")) {
				s.gamble(p);
			}
		}
	}

	/**
	 * Player continues their journey and encounters an enemy! They must challenge
	 * the enemy to a minigame, or face untold dangers...
	 * 
	 * @param p    - Player object
	 * @param scan - Scanner object
	 * @return - true if the game goes on; false if game over
	 */
	public boolean onward(Player p, Scanner scan) {
		Main.print("Where would you like to head next?\n1. North\n2. East\n3. South\n4. West\n");
		p.setIntChoice(1, 4, scan);
		Main.print("\nYou head " + p.getDirectionName() + " to find a ");
		int adjIndex = (int) (Math.random() * adjectives.length);
		Main.print(adjectives[adjIndex] + " ");
		int enemyIndex = (int) (Math.random() * enemies.length);
		Main.print(enemies[enemyIndex] + "!");
		int actionIndex = (int) (Math.random() * verbs.length);
		Main.print("\n\nThe " + enemies[enemyIndex] + " " + verbs[actionIndex] + "!");
		Main.print("\n\nYou challenge the " + enemies[enemyIndex] + " to a game of ");
		int gameChooser = (int) (Math.random() * numChallenges);
		Game game;
		switch (gameChooser) {
		case 0:
			game = new TicTacToe(p, scan);
			break;
		case 1:
			game = new Guess(p, scan);
			break;
		case 2:
			game = new Fight(p, scan);
		case 3:
			game = new Fireball(p, scan);
		default:
			Main.print("Error error!");
			return false;
		}
		Main.print(" " + warnings[actionIndex] + "!\n");
		scan.nextLine();
		if (game.play()) {
			Main.print("\nWow! You won! Congratulations, you have prevented " + goodConsequences[actionIndex] + "!\n");
			scan.nextLine();
			Main.print("You pick up the loot the ");
			Main.print(adjectives[adjIndex] + " ");
			Main.print(enemies[enemyIndex] + " dropped.\n");
			scan.nextLine();
			Store winningStore = new Store("Winner's Store");
			winningStore.giveRandomItem(p);
			scan.nextLine();
			return true;
		} else {
			Main.print("\nOh no, you lost! " + badConsequences[actionIndex] + "...\n");
			scan.nextLine();
			switch (endGame[actionIndex]) {
				case -1:
					Main.print("You died.\n\nGAME OVER\n");
					return false;
				case 0:
					Main.print("Before anything worse can happen to you, you run away as fast as your legs can carry you. Your breaths \nbecome labored and your legs begin to throb in pain. But at least you're far from danger...for now.");
					return true;
				case 1:
					encounterMermaid(p, scan);
					Main.print("You blink and find yourself back where you had first encountered the crying " + enemies[enemyIndex] + ". Perhaps it was all just a dream?\n");
					scan.nextLine();
					return true;
				default:
					Main.print("Error error!");
					return false;
			}
		}
	}
	
	public void encounterMermaid(Player p, Scanner scan) {
		Main.print("You were swept away by the flood! Whooooosh~\n");
		scan.nextLine();
		Main.print(
				"The world starts spinning and you start to see twinkling lights at the edge of your vision. But before \nyou can make out what they are, the world turns black...\n");
		scan.nextLine();
		Main.print("...\n");
		scan.nextLine();
		Main.print("...\n");
		scan.nextLine();
		Main.print("...\n");
		scan.nextLine();
		Main.print(
				"Eventually, you open your eyes to find yourself deep under the ocean, with very little light. You blink. \nOnce. Twice. Then you jolt upward, startled. There is a mermaid (?) staring straight into the depths of your soul. She is the \nprettiest being you have ever encountered in your life and you unbiddenly wish time would stop so you could stare at her forever.\n");
		scan.nextLine();
		Main.print(
				"\"Why hello there, strange creature.\" The mermaid's (??) purple tail flicks up and down in excitement. To \nwhat do I owe the pleasure of your visit?\"\n");
		scan.nextLine();
		Main.print(
				"1. What in the heavens are you?\n2. I am but a passing traveler on the hunt for *grumble* (You have evidently not eaten in days. How embarassing.)\n3. Are you going to eat me?!\n4. Huh?\n");
		p.setIntChoice(1, 4, scan);
		Main.print(
				"\nYou are surprised to find that you aren't drowning, despite opening your mouth far below the surface of the \nwater. Instead, bubbles float from your lips, filling your vision with white. Perhaps it's some sort of sorcery? \n\nThe mermaid (???) casts you a strange glance at your answer. For a moment, you feel as if she is looking down on your very existence, \nbut the feeling passes, and you are left unnerved. \"How strange you are. Let me introduce myself. I am a mermaid—\" (Aha! So you \nwere right!) \"—someone a creature like you would usually never have the opportunity to lay your eyes upon, but here you are. My name \nis Princess.\"\n");
		scan.nextLine();
		Main.print(
				"\"So you're a princess?\" you ask, eyes glittering in amazement. You haven't met royalty in the duration \nof your extremely short lifespan.\n");
		scan.nextLine();
		Main.print("\"Is that not what I just said? Your comprehension must be severely lacking.\" You feel affronted, but a \nsense of calm overtakes it. Is this what they call brainwa—?\n");
		scan.nextLine();
		Main.print("?\n");
		scan.nextLine();
		Main.print("???\n");
		scan.nextLine();
		Main.print("\"Now, now,\" the mermaid murmurs as she swims in lazy circles around you. You are frozen in place. \"Let's \nplay...a little game, shall we? You gift me an object of your choice. If I like it, I'll reward you generously. If not, well. We'll \nsee.\" A wide grin splits the mermaid's face, and you shiver involuntarily.\n");
		scan.nextLine();
		Main.print("You don't really have a choice, do you?\n\n<Press i to see your inventory>\n");
		p.setChoice(new String[] {"i"}, scan);
		p.printInventory();
		if (p.getInventoryLength() == 0 && p.getGold() == 0 && p.getGemstones() == 0) {
			Main.print("\n\nOh no! What a disaster! You're utterly broke!\n");
			scan.nextLine();
			Main.print("The mermaid looks at you with pity. \"I suppose there's little use in playing this game. How boring.\"\n");
		} else {
			Main.print("\n\nWhat would you like to offer to the mermaid?\n");
			int count = 0;
			boolean gold = false;
			boolean gem = false;
			if (p.getGold() != 0) {
				gold = true;
				count++;
				Main.print(count + ". Gold\n");
			}
			if (p.getGemstones() != 0) {
				gem = true;
				count++;
				Main.print(count + ". Gemstone\n");
			}
			ArrayList<String> inventory = p.getInventory();
			for (String s : inventory) {
				count++;
				Main.print(count + ". " + s + "\n");
			}
			p.setIntChoice(1, count, scan);
			switch (p.getIntChoice()) {
				case 1:
					if (gold) {
						Main.print("\nHow much gold would you like to offer?\n");
						p.setIntChoice(1, p.getGold(), scan); 
						p.setGold(p.getGold() - p.getIntChoice());
						Main.print("\n\"A measly " + p.getIntChoice() + " gold?! The audacity!\" the mermaid shrieks.\n");
						break;
					}
				case 2:
					if (gem) {
						Main.print("\nHow many gemstones would you like to offer?\n");
						p.setIntChoice(1, p.getGemstones(), scan);
						p.setGemstones(p.getGemstones() - p.getIntChoice());
						Main.print("\n\"A measly " + p.getIntChoice() + " gemstone?! The audacity!\" the mermaid shrieks.\n");
						break;
					}
				default:
					String offering = inventory.get(p.getIntChoice() -(count - p.getInventoryLength()) - 1);
					if (offering.equals("Acorn")) {
						Main.print("\n\"Wow! What's this? Is it a pet? Can I eat it?\" the mermaid marveled. She turned the \nacorn over and over, observing every inch of it.\n");
						scan.nextLine();
						Main.print("\"Uhm, hello?\" you venture carefully.\n");
						scan.nextLine();
						Main.print("Here is your reward, my lovely little creature~\n\n");
						p.addItem("Silver Fork");
						Main.print("<You have gained a Silver Fork!>\n");
						scan.nextLine();
						Main.print("As if she could see your questioning gaze, the mermaid winks and blows you a kiss of bubbles. \"Safe travels, my precious~\"\n");
					} else {
						Main.print("\n\"Hmph, boring!\" the mermaid yawns. \"Ah well, I suppose a creature as insignificant as \nyou would be uneducated in the art of gifting.\"\n\n");
						p.addItem("Small Pebble");
						Main.print("<You have gained a Small Pebble!>\n");
					}
			}
		}
		scan.nextLine();		
	}
}
