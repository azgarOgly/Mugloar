package ee.az.mugloar.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ee.az.mugloar.api.model.Adventure;
import ee.az.mugloar.api.model.Item;
import ee.az.mugloar.api.model.Message;

public class GameLogic {

	private static Adventure currentAdventure;
	
	public static Message selectAdventure(Collection<Message> messages) {
		if (messages == null || messages.isEmpty()) {
			throw new RuntimeException("Cannot make best choisce of empty list of options");
		}
		
		List<Message> options = new ArrayList<Message>(messages.size());
		List<Message> acceptables = new ArrayList<Message>(messages.size());
		options.addAll(messages);
		Collections.sort(options, new MathMessageComparator()); // sort by probability*reward 
		
		for (Message m : options) {
			RiskLevel rl = RiskLevel.getRiskLevel(m.getProbability());
			if (rl.isAcceptable()) {
				acceptables.add(m);
			}
		}
		
		if (!acceptables.isEmpty()) {
			if (acceptables.size() > 1) {
				Message first = acceptables.get(0);
				Message second = acceptables.get(1);
				if (first.getExpiresIn() > 2 && first.getExpiresIn() < 2) { 
					return second; // if second best expires soon and first can wait
				} else {
					return first;
				}
			}
			return acceptables.get(0);
		}

		Collections.sort(options, new CowardlyMessageComparator()); // resort least risk first
		return options.get(0); // first on the list
	}
	
	static class MathMessageComparator implements Comparator<Message> {
		@Override
		public int compare(Message m0, Message m1) {
			RiskLevel rl0 = RiskLevel.getRiskLevel(m0.getProbability());
			RiskLevel rl1 = RiskLevel.getRiskLevel(m1.getProbability());
			if (m1.getReward() == null || rl1 == null) {
				return -1;
			}
			Integer i0 = -1;
			Integer i1 = -1;
			try {
				i0 = (Integer.valueOf(m0.getReward()) * rl0.getScore());
			} catch (NumberFormatException e) {}
			try {
				i1 = (Integer.valueOf(m1.getReward()) * rl1.getScore());
			} catch (NumberFormatException e) {}
			return i1.compareTo(i0);
		}
	}
	
	static class CowardlyMessageComparator implements Comparator<Message> {
		@Override
		public int compare(Message m0, Message m1) {
			RiskLevel rl0 = RiskLevel.getRiskLevel(m0.getProbability());
			RiskLevel rl1 = RiskLevel.getRiskLevel(m1.getProbability());
			if (m1.getReward() == null || rl1 == null) {
				return -1;
			}
			Integer i0 = (Integer.valueOf(rl0.getScore()));
			Integer i1 = (Integer.valueOf(rl1.getScore()));
			return i1.compareTo(i0);
		}
	}
	
	static class GreedyMessageComparator implements Comparator<Message> {
		@Override
		public int compare(Message m0, Message m1) {
			if (m1.getReward() == null) {
				return -1;
			}
			return m1.getReward().compareTo(m0.getReward());
		}
	}
	
	public static Item selectItem(Collection<Item> items) {
		if (currentAdventure.getLives() < 7) {
			Item healingPotion = getHealingPotion(items);
			if (healingPotion != null && healingPotion.getCost() <= currentAdventure.getGold()) {
				return healingPotion;
			}
		} else {
			if (currentAdventure.getGold() < 100) {
				return null;
			}
		}
		
		List<Item> affordables = new ArrayList<>();
		for (Item i : items) {
			if (i != null && i.getCost() <= currentAdventure.getGold()) {
				affordables.add(i);
			}
		}
		
		if (affordables.isEmpty()) {
			return null;
		}
		
		List<Item> options = new ArrayList<>();
		Collections.sort(affordables, new ItemByPriceComparator());
		int price = affordables.get(0).getCost();
		for (Item i : affordables) {
			if (i.getCost() == price) {
				options.add(i); // items of equally high price
			}
		}
		
		int index = (int)(Math.random() * options.size());
		return options.get(index);
	}

	public static class ItemByPriceComparator implements Comparator<Item> {
		@Override
		public int compare(Item i0, Item i1) {
			if (i0 == null && i1 == null) {
				return 0;
			} else if (i0 == null) {
				return 1;
			} else if (i1 == null) {
				return -1;
			}
			return Integer.valueOf(i1.getCost()).compareTo(i0.getCost());
		}
	}
	
	private static Item getHealingPotion(Collection<Item> items) {
		for (Item i : items) {
			if ("hpot".equals(i.getId())) {
				return i;
			}
		}
		return null;
	}
	
	public static Adventure getCurrentAdventure() {
		return currentAdventure;
	}
	public static void setCurrentAdventure(Adventure currentAdventure) {
		GameLogic.currentAdventure = currentAdventure;
	}
}
