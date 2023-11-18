public class Boss {
	private String[] adjectives = { "Scary", "Egotistical", "Manical", "Psycho", "Crying", "Tired", "Greedy", "Lazy",
			"Playful", "Hungry" };
	private String[] nouns = { "Salamander", "Egg", "Mandrake", "Potato", "Cafeteria Lady", "Tiger", "Glutton", "Liar",
			"Pianist", "Hoarder" };
	private String name;
	private int level;
	private int hp;
	private int atk;
	private int def;

	/**
	 * Create new boss
	 * 
	 * @param last - If the boss is the final boss or not
	 */
	public Boss(boolean last) {
		if (last) {
			name = "The Final Boss";
			level = 6;
		} else {
			name = "The " + adjectives[(int) (Math.random() * 10) + 1] + " " + nouns[(int) (Math.random() * 10) + 1];
			level = (int) (Math.random() * 5) + 1;
		}
		hp = level * 100;
		atk = level * 5;
		def = level * 2;
	}

	/**
	 * Get boss name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get boss level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Get boss health
	 */
	public int getHP() {
		return hp;
	}

	/**
	 * Get boss attack
	 */
	public int getAtk() {
		return atk;
	}

	/**
	 * Get boss defense
	 */
	public int getDef() {
		return def;
	}
}