package ee.az.mugloar.utils;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ee.az.mugloar.api.model.Adventure;
import ee.az.mugloar.api.model.Game;
import ee.az.mugloar.api.model.Item;
import ee.az.mugloar.api.model.Message;
import ee.az.mugloar.api.model.Reputation;
import ee.az.mugloar.api.model.ShoppingResponse;

public class SerializationUtils {
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	public static Game getGame(String json) {
		try {
			return objectMapper.readValue(json, Game.class);
		} catch (Exception e) {
			throw new RuntimeException("Failed to deserialize game", e);
		}
	}

	public static Reputation getReputation(String json) {
		try {
			return objectMapper.readValue(json, Reputation.class);
		} catch (Exception e) {
			throw new RuntimeException("Failed to deserialize reputation", e);
		}
	}

	public static Collection<Message> getMessages(String json) {
		try {
			return objectMapper.readValue(json, new TypeReference<ArrayList<Message>>() {});
		} catch (Exception e) {
			throw new RuntimeException("Failed to deserialize messages", e);
		}
	}

	public static Adventure getAdventure(String json) {
		try {
			return objectMapper.readValue(json, Adventure.class);
		} catch (Exception e) {
			throw new RuntimeException("Failed to deserialize adventure", e);
		}
	}

	public static Collection<Item> getShoppingItems(String json) {
		try {
			return objectMapper.readValue(json, new TypeReference<ArrayList<Item>>() {});
		} catch (Exception e) {
			throw new RuntimeException("Failed to deserialize shopping items", e);
		}
	}
	
	public static ShoppingResponse getShoppingResponse(String json) {
		try {
			return objectMapper.readValue(json, ShoppingResponse.class);
		} catch (Exception e) {
			throw new RuntimeException("Failed to deserialize shopping response", e);
		}
	}
}
