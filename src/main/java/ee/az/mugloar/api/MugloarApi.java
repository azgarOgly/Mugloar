package ee.az.mugloar.api;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import ee.az.mugloar.api.model.Adventure;
import ee.az.mugloar.api.model.Game;
import ee.az.mugloar.api.model.Item;
import ee.az.mugloar.api.model.Message;
import ee.az.mugloar.api.model.Reputation;
import ee.az.mugloar.api.model.ShoppingResponse;
import ee.az.mugloar.utils.DecryptionUtils;
import ee.az.mugloar.utils.HttpUtils;
import ee.az.mugloar.utils.SerializationUtils;

public class MugloarApi {
	private static Logger logger = Logger.getLogger(MugloarApi.class);

	private static String SERVER = "https://www.dragonsofmugloar.com/";
	private static String GAME_START = SERVER + "api/v2/game/start";
	private static String REPUTATION = SERVER + "api/v2/%s/investigate/reputation";
	private static String MESSAGES = SERVER + "api/v2/%s/messages";
	private static String ADVENTURE = SERVER + "api/v2/%s/solve/%s";
	private static String SHOP = SERVER + "api/v2/%s/shop";
	private static String BUY = SERVER + "api/v2/%s/shop/buy/%s";

	public static Game startGame() {
		try {
			String json = HttpUtils.post(GAME_START);
			return SerializationUtils.getGame(json);
		} catch (Exception e) {
			throw new RuntimeException("Failed to start game", e);
		} 
	}

	public static Reputation getReputation(String gameId) {
		try {
			Map<String, String> params = new HashMap<>();
			params.put("gameId", gameId);
			String json = HttpUtils.post(String.format(REPUTATION, gameId), params);
			return SerializationUtils.getReputation(json);
		} catch (Exception e) {
			throw new RuntimeException("Failed to get reputation", e);
		} 
	}

	public static Collection<Message> getMessages(String gameId) {
		try {
			Map<String, String> params = new HashMap<>();
			params.put("gameId", gameId);
			String json = HttpUtils.get(String.format(MESSAGES, gameId), params);
			Collection<Message> messages = SerializationUtils.getMessages(json);
			
			for (Message m : messages) {
				if (m.getEncrypted() != null) {
					logger.debug("Recieved encrypted message " + m);
				}
			}
			messages = DecryptionUtils.decrypt(messages);
			
			return messages;
		} catch (Exception e) {
			throw new RuntimeException("Failed to get messages", e);
		} 
	}

	public static Adventure solveAdventure(String gameId, String adId) {
		try {
			Map<String, String> params = new HashMap<>();
			params.put("gameId", gameId);
			params.put("adId", adId);
			String json = HttpUtils.post(String.format(ADVENTURE, gameId, adId), params);
			return SerializationUtils.getAdventure(json);
		} catch (Exception e) {
			throw new RuntimeException("Failed to get adventure", e);
		} 
	}
	
	public static Collection<Item> getShopOffers(String gameId) {
		try {
			Map<String, String> params = new HashMap<>();
			params.put("gameId", gameId);
			String json = HttpUtils.get(String.format(SHOP, gameId), params);
			return SerializationUtils.getShoppingItems(json);
		} catch (Exception e) {
			throw new RuntimeException("Failed to get shopping items", e);
		} 
	}

	public static ShoppingResponse buyItem(String gameId, String itemId) {
		try {
			Map<String, String> params = new HashMap<>();
			params.put("gameId", gameId);
			params.put("itemId", itemId);
			String json = HttpUtils.post(String.format(BUY, gameId, itemId), params);
			return SerializationUtils.getShoppingResponse(json);
		} catch (Exception e) {
			throw new RuntimeException("Failed to get shopping items", e);
		} 
	}
}
