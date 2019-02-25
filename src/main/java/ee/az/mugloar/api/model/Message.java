package ee.az.mugloar.api.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Message {
	private String adId; // The unique ID of the message
	private String message; // Free text description of the message
	private String reward; // Reward in gold which is granted for successfully solving the game.
	private int expiresIn; // The amount in turns in which the message will become unavailable for solving.
	private String probability; // XXX not in api documentation
	private String encrypted; // XXX not in api documentation
	
	public String getAdId() {
		return adId;
	}
	public void setAdId(String adId) {
		this.adId = adId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getReward() {
		return reward;
	}
	public void setReward(String reward) {
		this.reward = reward;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	public String getProbability() {
		return probability;
	}
	public void setProbability(String probability) {
		this.probability = probability;
	}
	public String getEncrypted() {
		return encrypted;
	}
	public void setEncrypted(String encrypted) {
		this.encrypted = encrypted;
	}
	public String toString() {
		   return ToStringBuilder.reflectionToString(this);
	}
}
