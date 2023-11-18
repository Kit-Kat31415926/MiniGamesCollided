import java.util.Scanner;

public class Store {
	private String owner;
	private final String[] items = { "Uncomfortable Armor", "Rusty Sword", "Dented Shield", "Small Pebble", "Paper",
			"Pair of Sharp Scissors", "Spoon", "Fork", "Butter Knife", "Mushroom", "JACKPOT" };
	private final String[] specialItems = { "Acorn", "Lottery Ticket" };
	private final int[] price = { 100, 100, 100, 40, 50, 60, 40, 50, 60, 25, 0 };
	private final String[] statInc = { "20 hp", "3 atk", "5 def", "", "", "", "", "", "1 atk", "", "" };
	
	private final String[] lottery = { "50 Gold", "2 Gemstones", "Excalibur", "Acorn", "Small Pebble", "Paper",
			"Sharp Scissors", "Spoon", "Fork", "Butter Knife" };
	private final String[] lotteryStatInc = { "", "", "5 atk", "", "", "", "", "", "", "1 atk" };
	int item1, item2, item3;

	public Store(String name) {
		owner = name;
	}

	/**
	 * Displays the items in the store *cannot buy special items :(*
	 */
	public void displayItems() {
		Main.print("\n\n\t~          " + owner + "'s Store          ~\t\n");
		randomizeItems();
		String msg = String.format("%-25s%-25s%-25s\n", "Option 1", "Option 2", "Option 3");
		Main.print(msg);
		msg = String.format("\n%-25s%-25s%-25s", items[item1], items[item2], items[item3]);
		Main.print(msg);
		msg = String.format("\n%-25s%-25s%-25s", price[item1] + "G", price[item2] + "G", price[item3] + "G");
		Main.print(msg);
		Main.print("\n\n<Press r to spend one gemstone and reset the shop>");
		Main.print("\n<Press n to buy nothing :( >");
	}

	/**
	 * Chooses 3 random items to display in the store
	 */
	public void randomizeItems() {
		item1 = (int) (Math.random() * (items.length - 1));
		item2 = (int) (Math.random() * (items.length - 1));
		item3 = (int) (Math.random() * (items.length - 1));
	}

	public void selectPurchases(Player p, Scanner scan) {
		Main.print("\n\nWhat would you like to buy?\n");
		p.setChoice(new String[] { "1", "2", "3", "r", "n" }, scan);
		switch (p.getChoice().toLowerCase()) {
		case "1":
			if (p.getGold() >= price[item1]) {
				Main.print("\nYou purchase a " + items[item1] + " for " + price[item1] + "G.");
				p.setGold(p.getGold() - price[item1]);
				gainItem(p, item1, items, statInc);
				Main.print("\n\n\"Thank you for your purchase! Would you like to buy anything else?\" (y/n)\n");
				p.setChoice(new String[] {"y", "n"}, scan);
				if (p.getChoice().equals("y")) {
					displayItems();
					selectPurchases(p, scan);
				} else {
					Main.print("\n\"I hope you can make use of your new acquisitions in the near future.\"");
				}
			} else {
				Main.print("\nNot enough money to buy this item! (Go get some cash, you're broke)");
				selectPurchases(p, scan);
			}
			break;
		case "2":
			if (p.getGold() >= price[item2]) {
				Main.print("\nYou purchase a " + items[item2] + " for " + price[item2] + "G.");
				p.setGold(p.getGold() - price[item2]);
				gainItem(p, item2, items, statInc);
				Main.print("\n\n\"Thank you for your purchase! Would you like to buy anything else?\" (y/n)\n");
				p.setChoice(new String[] {"y", "n"}, scan);
				if (p.getChoice().equals("y")) {
					displayItems();
					selectPurchases(p, scan);
				} else {
					Main.print("\n\"I hope you can make use of your new acquisitions in the near future.\"");
				}
			} else {
				Main.print("\nNot enough money to buy this item! (Go get some cash, you're broke)");
				selectPurchases(p, scan);
			}
			break;
		case "3":
			if (p.getGold() >= price[item3]) {
				Main.print("\nYou purchase a " + items[item3] + " for " + price[item3] + "G.");
				p.setGold(p.getGold() - price[item3]);
				gainItem(p, item3, items, statInc);
				Main.print("\n\n\"Thank you for your purchase! Would you like to buy anything else?\" (y/n)\n");
				p.setChoice(new String[] {"y", "n"}, scan);
				if (p.getChoice().equals("y")) {
					displayItems();
					selectPurchases(p, scan);
				} else {
					Main.print("\n\"I hope you can make use of your new acquisitions in the near future.\"");
				}
			} else {
				Main.print("\nNot enough money to buy this item! (Go get some cash, you're broke)");
				selectPurchases(p, scan);
			}
			break;
		case "n":
			Main.print("\nYou purchase nothing.");
			Main.print("\n\n\"Thank you for your consideration.\"");
			break;
		case "r":
			if (p.getGemstones() >= 1) {
				p.setGemstones(p.getGemstones() - 1);
				Main.print("\nResetting store options...\n");
				displayItems();
				selectPurchases(p, scan);
				break;
			}
		default:
			Main.print("Error error!");
			selectPurchases(p, scan);
			break;
		}
	}

	private void gainItem(Player p, int i, String[] itemsArray, String[] itemsStats) {
		p.addItem(itemsArray[i]);
		if (!itemsStats[i].equals("")) {
			String[] newStat = itemsStats[i].split(" ");
			p.changeStats(newStat[1], newStat[0]);
			Main.print("\nYour " + newStat[1] + " has increased by " + newStat[0] + ".\n");
		}
	}

	private void gainItem(Player p, int i, String[] itemsArray) {
		p.addItem(itemsArray[i]);
	}

	/**
	 * Use player's lottery ticket! *Cannot win any special items :(*
	 * 
	 * @param p - Player who cashes in ticket
	 */
	public void gamble(Player p) {
		int item = (int) (Math.random() * lottery.length - 1);
		Main.print("\nYou won a " + lottery[item] + "!");
		gainItem(p, item, lottery, lotteryStatInc);
		p.loseItem("Lottery Ticket");
	}

	public void giveRandomItem(Player p) {
		int item = (int) (Math.random() * items.length);
		if (item == items.length - 1) {
			item = (int) (Math.random() * specialItems.length);
			Main.print("\nYou gained a " + specialItems[item] + "!");
			gainItem(p, item, specialItems);
		} else {
			Main.print("You gained a " + items[item] + "!\n");
			gainItem(p, item, items, statInc);
		}
	}
}
