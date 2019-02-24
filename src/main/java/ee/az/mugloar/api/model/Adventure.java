package ee.az.mugloar.api.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Adventure {
	private boolean success; // Whether the attempt to solve the message was successful.
	private int lives; // Amount of lives left after the attempt
	private int gold; // Amount of gold after the attempt
	private int score; // Score after the attempt
	private int highScore; // The current highest score
	private int turn; // Current turn number
	private String message; // Text explanation of what happened on the last turn.
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String toString() {
		   return ToStringBuilder.reflectionToString(this);
	}
}
