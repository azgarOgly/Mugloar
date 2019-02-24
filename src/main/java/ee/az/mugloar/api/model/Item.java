package ee.az.mugloar.api.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Item {
	private String id; // Item unique identifier
	private String name; // Item name
	private int cost; // Item cost in gold
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}

	public String toString() {
		   return ToStringBuilder.reflectionToString(this);
	}
}
