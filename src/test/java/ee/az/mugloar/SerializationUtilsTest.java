package ee.az.mugloar;

import java.util.Collection;

import org.testng.Assert;
import org.testng.annotations.Test;

import ee.az.mugloar.api.model.Adventure;
import ee.az.mugloar.api.model.Message;
import ee.az.mugloar.utils.SerializationUtils;

public class SerializationUtilsTest {
	@Test
	public void testMessagesDeserialization() throws Exception {
		String json = "[{\"adId\":\"eKSe3QUQ\",\"message\":\"Help Jalen Eddudóttir to transport a magic sheep to savannah in Thornwell\",\"reward\":23,\"expiresIn\":6,\"probability\":\"Sure thing\"},{\"adId\":\"gjyADxkg\",\"message\":\"Help Blaanid Sandford to fix their dog\",\"reward\":4,\"expiresIn\":6,\"probability\":\"Piece of cake\"},{\"adId\":\"nxzjjhHH\",\"message\":\"Help Walerian Hameldon to transport a magic squirrel to grassland in Newfair\",\"reward\":21,\"expiresIn\":6,\"probability\":\"Walk in the park\"},{\"adId\":\"b7w12rlP\",\"message\":\"Escort Teo Strange to savannah in Lightwell where they can meet with their long lost water\",\"reward\":42,\"expiresIn\":6,\"probability\":\"Sure thing\"},{\"adId\":\"R3BpOk7X\",\"message\":\"Help Ambakoum Sappington to reach an agreement with Laila Walmsley on the matters of disputed bucket\",\"reward\":14,\"expiresIn\":6,\"probability\":\"Walk in the park\"},{\"adId\":\"CBGRLdh2\",\"message\":\"Help Signy Radclyffe to sell an unordinary horse on the local market\",\"reward\":21,\"expiresIn\":6,\"probability\":\"Quite likely\"},{\"adId\":\"rqtnOXgv\",\"message\":\"Help Armas Huldudóttir to write their biographical novel about their difficulties with a deranged chicken\",\"reward\":39,\"expiresIn\":6,\"probability\":\"Walk in the park\"},{\"adId\":\"K1I7FMNk\",\"message\":\"Help Alp Sharman to fix their sheep\",\"reward\":6,\"expiresIn\":6,\"probability\":\"Piece of cake\"},{\"adId\":\"cq3gCeER\",\"message\":\"Help Valencia Brynjólfsson to fix their cat\",\"reward\":8,\"expiresIn\":6,\"probability\":\"Piece of cake\"},{\"adId\":\"9uo3zC75\",\"message\":\"Help Anu Paulson to sell an unordinary chariot on the local market\",\"reward\":31,\"expiresIn\":6,\"probability\":\"Walk in the park\"}]";
		
		Collection<Message> messages = SerializationUtils.getMessages(json);
		
		Assert.assertNotNull(messages);
		Assert.assertEquals(messages.size(), 10);
		
		Message message = messages.iterator().next();
		Assert.assertEquals(message.getAdId(), "eKSe3QUQ");
		Assert.assertEquals(message.getExpiresIn(), 6);
		Assert.assertEquals(message.getMessage(), "Help Jalen Eddudóttir to transport a magic sheep to savannah in Thornwell");
		Assert.assertEquals(message.getProbability(), "Sure thing");
		Assert.assertEquals(message.getReward(), "23");
	}
	
	public void testAdventureDeserialization() throws Exception {
		String json = "{\"success\":true,\"lives\":3,\"gold\":66,\"score\":66,\"highScore\":4321,\"turn\":2,\"message\":\"You successfully solved the mission!\"}";
		Adventure adventure = SerializationUtils.getAdventure(json);
		Assert.assertNotNull(adventure);
		Assert.assertTrue(adventure.isSuccess());
		Assert.assertEquals(adventure.getGold(), 66);
		Assert.assertEquals(adventure.getHighScore(), 4321);
		Assert.assertEquals(adventure.getLives(), 3);
		Assert.assertEquals(adventure.getMessage(), "You successfully solved the mission!");
		Assert.assertEquals(adventure.getScore(), 66);
		Assert.assertEquals(adventure.getTurn(), 2);
	}
}
