package ee.az.mugloar.api.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Reputation {
	@JsonProperty("people")
	private int reputationWithPeople; // Your reputation with people
	@JsonProperty("state")
	private int reputationWithState; // Your reputation with state
	@JsonProperty("underworld")
	private int reputationWithUnderworld; // Your reputation with underworld

	public int getReputationWithPeople() {
		return reputationWithPeople;
	}
	public void setReputationWithPeople(int reputationWithPeople) {
		this.reputationWithPeople = reputationWithPeople;
	}
	public int getReputationWithState() {
		return reputationWithState;
	}
	public void setReputationWithState(int reputationWithState) {
		this.reputationWithState = reputationWithState;
	}
	public int getReputationWithUnderworld() {
		return reputationWithUnderworld;
	}
	public void setReputationWithUnderworld(int reputationWithUnderworld) {
		this.reputationWithUnderworld = reputationWithUnderworld;
	}
	
	public String toString() {
	   return ToStringBuilder.reflectionToString(this);
	}
}
