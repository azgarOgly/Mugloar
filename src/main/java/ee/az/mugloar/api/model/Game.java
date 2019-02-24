package ee.az.mugloar.api.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Game {
	private String gameId; // Unique game ID for the new game started.
	private int lives; // How many lives player starts with
	private int gold; // Gold player starts with,
	private int level; // The initial dragon level
	private int score; // The initial game score
	private int highScore; // The current highest score
	private int turn; // The turn number
	
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	public int getLives() {
		return lives;
	}
	public void setLives(int lives) {
		this.lives = lives;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getHighScore() {
		return highScore;
	}
	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}
	public int getTurn() {
		return turn;
	}
	public void setTurn(int turn) {
		this.turn = turn;
	}
	
	public String toString() {
	   return ToStringBuilder.reflectionToString(this);
	}
}
