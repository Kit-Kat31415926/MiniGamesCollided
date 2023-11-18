import java.util.ArrayList;
import java.util.Scanner;

public class Player {
	private String name;
	private int currentHp;
	private int hp;
	private int atk;
	private int def;
	private int gold;
	private int gemstones;
	private ArrayList<String> inventory;
	private final String[] directions = {"North", "East", "South", "West"};
	private String choice;
	private int intChoice;

	/**
	 * Create new player
	 */
	public Player(String name) {
		this.name = name;
		hp = 150;
		currentHp = 150;
		atk = 7;
		def = 0;
		gold = 150;
		gemstones = 1;
		inventory = new ArrayList<String>();
	}

	/**
	 * Get player name
	 * 
	 * @return - Player's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get player health
	 * 
	 * @return - Player's hp stat
	 */
	public int getHp() {
		return hp;
	}

	public int getCurrentHp() {
		return currentHp;
	}

	/**
	 * Get player attack
	 * 
	 * @return - Player's atk stat
	 */
	public int getAtk() {
		return atk;
	}

	/**
	 * Get player defense
	 * 
	 * @return - Player's def stat
	 */
	public int getDef() {
		return def;
	}

	/**
	 * Get player gold
	 * 
	 * @return - Player's gold in inventory
	 */
	public int getGold() {
		return gold;
	}

	/**
	 * Get player gemstones
	 * 
	 * @return - Player's gemstones in inventory
	 */
	public int getGemstones() {
		return gemstones;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void setCurrentHp(int currentHp) {
		this.currentHp = currentHp;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public void setGemstones(int gemstones) {
		this.gemstones = gemstones;
	}

	public void addItem(String item) {
		inventory.add(item);
	}

	public void changeStats(String type, String amount) {
		int amt = Integer.parseInt(amount);
		switch (type) {
		case "hp":
			hp += amt;
			currentHp += amt;
			break;
		case "atk":
			atk += amt;
			break;
		case "def":
			def += amt;
			break;
		default:
			break;
		}
	}

	/**
	 * Sets the player's choice for the current action
	 * 
	 * @param options - list of valid options
	 * @param scan    - Scanner to take user input
	 */
	public void setChoice(String[] options, Scanner scan) {
		boolean valid = false;
		while (!valid) {
			choice = scan.next();
			boolean contains = false;
			for (int i = 0; i < options.length; i++) {
				if (choice.equalsIgnoreCase(options[i])) {
					contains = true;
				}
			}
			if (contains) {
				valid = true;
			} else {
				Main.print("\nInvalid option. Please try again.\n");
			}
		}
	}

	/**
	 * Sets the player's choice for the current action
	 * @param min - Minimum integer that is allowed (inclusive)
	 * @param max - Maximum integer that is allowed (inclusive)
	 * @param scan - Scanner to take user input
	 */
	public void setIntChoice(int min, int max, Scanner scan) {
		boolean valid = false;
		while (!valid) {
			try {
				intChoice = scan.nextInt();
				if (intChoice >= min && intChoice <= max) {
					valid = true;
				} else {
					Main.print("\nInvalid option. Please try again.\n");
				}
				scan.nextLine();
			} catch (Exception e) {
				Main.print("\nInvalid option. Please try again.\n");
				scan.nextLine();
			}
		}
	}

	/**
	 * Sets player's choice manually
	 * 
	 * @param choice - user's choice
	 */
	public void setChoice(String choice) {
		this.choice = choice;
	}

	/**
	 * Sets player's choice manually
	 * 
	 * @param choice - user's choice
	 */
	public void setIntChoice(int choice) {
		this.intChoice = choice;
	}

	/**
	 * Returns user's choice for the current action
	 * 
	 * @return choice - the user's choice
	 */
	public String getChoice() {
		return choice;
	}
	
	public int getIntChoice() {
		return intChoice;
	}

	/**
	 * Returns direction based on user's choice
	 * 
	 * @return - North, East, South, or West
	 */
	public String getDirectionName() {
		return directions[intChoice - 1];
	}

	public void printStats() {
		Main.print("\n\tHP:         " + getCurrentHp() + "/" + getHp());
		Main.print("\n\tAttack:     " + getAtk());
		Main.print("\n\tDefense:    " + getDef());
	}

	public void printInventory() {
		Main.print("\n\tGold:       " + getGold());
		Main.print("\n\tGemstones:  " + getGemstones());
		Main.print("\n\tItems:");
		for (String s : inventory) {
			Main.print("\n\t\t" + s);
		}
		if (inventory.size() == 0) {
			Main.print("\n\t\tNothing to see here~");
		}
	}
	
	public ArrayList<String> getInventory() {
		return inventory;
	}
	
	public int getInventoryLength() {
		return inventory.size();
	}

	public boolean checkLottery() {
		return inventory.contains("Lottery Ticket");
	}

	public void loseItem(String s) {
		inventory.remove(s);
	}
}