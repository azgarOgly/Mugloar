package ee.az.mugloar.api.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ShoppingResponse {
	private String shoppingSuccess; // Whether or not the purchase was successful
	private int gold; // Amount of gold left after the transaction.
	private int lives; // Amount of lives left after the transaction
	private int level; // Dragon level after transaction
	private int turn; // Current turn. Note the turn increases even if the purchase is unsuccessful.
	
	public String getShoppingSuccess() {
		return shoppingSuccess;
	}
	public void setShoppingSuccess(String shoppingSuccess) {
		this.shoppingSuccess = shoppingSuccess;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public int getLives() {
		return lives;
	}
	public void setLives(int lives) {
		this.lives = lives;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
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
