import java.util.Scanner;

public class TicTacToe implements Game {
	private String[][] board = { { "1", "2", "3" }, { "4", "5", "6" }, { "7", "8", "9" } };
	public int turn = 1;
	Player opp, you;
	Scanner scan;

	/**
	 * Create new TicTacToe board
	 */
	public TicTacToe(Player you, Scanner scan) {
		this.you = you;
		this.scan = scan;
		Main.print("Tic Tac Toe");
	}

	public boolean play() {
		opp = new Player("Tic Tac Toe Master");

		Main.print("You are X. Opponent is O.\n\nDeciding the first player...\n\n");
		scan.nextLine();
		displayBoard();
		String result = "";
		// 0 for player's turn and 1 for opponent's turn to decide who starts
		boolean yourTurn = (int) (Math.random() * 2) % 2 == 0;
		if (yourTurn) {
			yourTurn();
		} else {
			oppTurn();
		}
		result = checkWinner();
		switch (result) {
		case "X":
			return true;
		case "O":
			return false;
		case "draw":
			Main.print(
					"\n\nYou ended in a draw. What a shame! You are about to go about on your way when your opponent halts you in \nyour tracks. \"We go again.\" With no other choice, you clear the Tic Tac Toe board begrudgingly.\n");
			scan.nextLine();
			board = new String[][]{ { "1", "2", "3" }, { "4", "5", "6" }, { "7", "8", "9" } };

			return play();
		default:
			Main.print("\n\nError error!");
			return false;
		}
	}

	public void yourTurn() {
		Main.print("\nYour turn!\n\nChoose a spot:\n");
		you.setIntChoice(1, 9, scan);
		while (!updateBoard(you.getIntChoice(), "X")) {
			Main.print("\nInvalid spot! Please try again.\n");
			you.setIntChoice(1, 9, scan);
		}
		displayBoard();
		turn++;
		if (checkWinner() == null) {
			oppTurn();
		}
	}

	public void oppTurn() {
		Main.print("\n\nYour opponent has made their move!\n");
		scan.nextLine();
		opp.setIntChoice(optimalChoice());
		updateBoard(opp.getIntChoice(), "O");
		displayBoard();
		turn++;
		if (checkWinner() == null) {
			yourTurn();
		}
	}

	private int optimalChoice() {
		int choice = (int) (Math.random() * 10);
		choice = choice % 2 == 1 ? choice : choice + 1;
		if (turn == 1) {
			return choice;
		} else if (turn == 2) {
			if (board[1][1].equals("5")) {
				return 5;
			}
			while (!board[(choice - 1) / 3][(choice - 1) % 3].equals(choice + "")) {
				choice = (int) (Math.random() * 10);
				choice = choice % 2 == 1 ? choice : choice + 1;
			}
			return choice;
		}
		choice = winningMove();
		if (winningMove() != -1) {
			return choice;
		}
		choice = block();
		if (choice != -1) {
			return choice;
		}
		choice = (int) (Math.random() * 9) + 1;
		while (board[(choice - 1) / 3][(choice - 1) % 3].equals("X")
				|| board[(choice - 1) / 3][(choice - 1) % 3].equals("O")) {
			choice = (int) (Math.random() * 9) + 1;
		}
		return choice;
	}

	private int block() {
		// Check horizontal
		for (int i = 0; i < board.length; i++) {
			int count = 0;
			int empty = -1;
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j].equals("X")) {
					count++;
				} else {
					empty = i * 3 + j + 1;
				}
			}
			if (count == 2 && !board[(empty - 1) / 3][(empty - 1) % 3].equals("O")) {
				return empty;
			}
		}
		// Check vertical
		for (int i = 0; i < board.length; i++) {
			int count = 0;
			int empty = -1;
			for (int j = 0; j < board.length; j++) {
				if (board[j][i].equals("X")) {
					count++;
				} else {
					empty = j * 3 + i + 1;
				}
			}
			if (count == 2 && !board[(empty - 1) / 3][(empty - 1) % 3].equals("O")) {
				return empty;
			}
		}
		// Check diagonal
		if (board[0][0].equals(board[1][1]) && board[0][0].equals("X") && isEmpty(2,2)) {
			return 9;
		}
		if (board[0][0].equals(board[2][2]) && board[0][0].equals("X") && isEmpty(1,1)) {
			return 5;
		}
		if (board[1][1].equals(board[2][2]) && board[1][1].equals("X") && isEmpty(0,0)) {
			return 1;
		}
		// Check other diagonal
		if (board[0][2].equals(board[1][1]) && board[0][2].equals("X") && isEmpty(2,0)) {
			return 7;
		}
		if (board[0][2].equals(board[2][0]) && board[0][2].equals("X") && isEmpty(1,1)) {
			return 5;
		}
		if (board[1][1].equals(board[2][0]) && board[1][1].equals("X") && isEmpty(0,2)) {
			return 3;
		}
		return -1;
	}

	private int winningMove() {
		// Check horizontal
		for (int i = 0; i < board.length; i++) {
			int count = 0;
			int empty = -1;
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j].equals("O")) {
					count++;
				} else {
					empty = i * 3 + j + 1;
				}
			}
			if (count == 2 && !board[(empty - 1) / 3][(empty - 1) % 3].equals("X")) {
				return empty;
			}
		}
		// Check vertical
		for (int i = 0; i < board.length; i++) {
			int count = 0;
			int empty = -1;
			for (int j = 0; j < board.length; j++) {
				if (board[j][i].equals("O")) {
					count++;
				} else {
					empty = j * 3 + i + 1;
				}
			}
			if (count == 2 && !board[(empty - 1) / 3][(empty - 1) % 3].equals("X")) {
				return empty;
			}
		}
		// Check diagonal
		if (board[0][0].equals(board[1][1]) && board[0][0].equals("O") && isEmpty(2,2)) {
			return 9;
		}
		if (board[0][0].equals(board[2][2]) && board[0][0].equals("O") && isEmpty(1,1)) {
			return 5;
		}
		if (board[1][1].equals(board[2][2]) && board[1][1].equals("O") && isEmpty(0,0)) {
			return 1;
		}
		// Check other diagonal
		if (board[0][2].equals(board[1][1]) && board[0][2].equals("O") && isEmpty(2,0)) {
			return 7;
		}
		if (board[0][2].equals(board[2][0]) && board[0][2].equals("O") && isEmpty(1,1)) {
			return 5;
		}
		if (board[1][1].equals(board[2][0]) && board[1][1].equals("O") && isEmpty(0,2)) {
			return 3;
		}
		return -1;
	}
	
	public boolean isEmpty(int row, int col) {
		return !board[row][col].equals("X") && !board[row][col].equals("O");
	}

	public boolean updateBoard(int loc, String str) {
		if (!board[(loc - 1) / 3][(loc - 1) % 3].equals("X") && !board[(loc - 1) / 3][(loc - 1) % 3].equals("O")) {
			board[(loc - 1) / 3][(loc - 1) % 3] = str;
			return true;
		} else {
			return false;
		}
	}

	public String checkWinner() {
		// Check horizontally and vertically
		for (int i = 0; i < board.length; i++) {
			boolean equal = true;
			String symbol = board[i][0];
			// Horizontal
			for (int j = 0; j < board[i].length; j++) {
				if (!board[i][j].equals(symbol)) {
					equal = false;
				}
			}
			if (equal) {
				return symbol;
			}
			equal = true;
			symbol = board[0][i];
			// Vertical
			for (int j = 0; j < board.length; j++) {
				if (!board[j][i].equals(symbol)) {
					equal = false;
				}
			}
			if (equal) {
				return symbol;
			}
		}
		// Check diagonally
		if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
			return board[0][0];
		}
		if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])) {
			return board[0][2];
		}
		// Check for draw
		boolean draw = true;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (!board[i][j].equals("X") && !board[i][j].equals("O")) {
					draw = false;
				}
			}
		}
		if (draw) {
			return "draw";
		} else {
			return null;
		}
	}

	public void displayBoard() {
		Main.print("\n");
		for (int i = 0; i < board.length; i++) {
			Main.print("\t" + board[i][0]);
			for (int j = 1; j < board[i].length; j++) {
				Main.print(" | " + board[i][j]);
			}
			if (i != board.length - 1) {
				Main.print("\n\t- + - + -\n");
			}
		}
		Main.print("\n");
	}
}