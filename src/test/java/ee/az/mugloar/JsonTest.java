package ee.az.mugloar;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import ee.az.mugloar.api.model.Game;
import ee.az.mugloar.api.model.Reputation;

public class JsonTest {
	
	@Test
	public void testGameDeserialization() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String json = "{\"gameId\":\"xpd6qYti\",\"lives\":3,\"gold\":-1,\"level\":0,\"score\":1024,\"highScore\":4321,\"turn\":21}\n";
		Game game = objectMapper.readValue(json, Game.class);
		Assert.assertNotNull(game);
		Assert.assertEquals(game.getGameId(), "xpd6qYti");
		Assert.assertEquals(game.getGold(), -1);
		Assert.assertEquals(game.getHighScore(), 4321);
		Assert.assertEquals(game.getLevel(), 0);
		Assert.assertEquals(game.getLives(), 3);
		Assert.assertEquals(game.getScore(), 1024);
		Assert.assertEquals(game.getTurn(), 21);
	}
	
	@Test
	public void testReputationDeserialization() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String json = "{\"people\":11,\"state\":12,\"underworld\":41}";
		Reputation reputation = objectMapper.readValue(json, Reputation.class);
		Assert.assertNotNull(reputation);
		Assert.assertEquals(reputation.getReputationWithPeople(), 11);
		Assert.assertEquals(reputation.getReputationWithState(), 12);
		Assert.assertEquals(reputation.getReputationWithUnderworld(), 41);
	}
}
