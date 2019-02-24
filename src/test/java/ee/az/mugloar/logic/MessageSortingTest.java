package ee.az.mugloar.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import static ee.az.mugloar.MessageUtils.produceMessage;
import static ee.az.mugloar.MessageUtils.listMessages;

import ee.az.mugloar.api.model.Message;

public class MessageSortingTest {
	
	private static Logger logger = Logger.getLogger(MessageSortingTest.class);

	@Test
	public void sortMessagesGreedyTest() {
		List<Message> messages = new ArrayList<Message>();
		messages.add(produceMessage("id0", "message0", "reward0", 6, "probability0"));
		messages.add(produceMessage("id1", "message1", "reward1", 6, "probability1"));
		messages.add(produceMessage("id2", "message2", "reward2", 6, "probability2"));
		messages.add(produceMessage("id3", "message3", "reward3", 6, "probability3"));
		messages.add(produceMessage("id4", "message4", "reward4", 6, "probability4"));
		
		logger.debug("Messages before sorting");
		listMessages(messages);
		
		Collections.sort(messages, new GameLogic.GreedyMessageComparator());
		
		logger.debug("Messages after sorting");
		listMessages(messages);

		Assert.assertNotNull(messages);
		Assert.assertNotNull(messages.get(0));
		Assert.assertEquals(messages.get(0).getAdId(), "id4");
	}
	
	@Test
	public void sortMessagesMathTest() {
		List<Message> messages = new ArrayList<Message>();
		messages.add(produceMessage("id0", "message0", "100", 6, "Gamble"));
		messages.add(produceMessage("id1", "message1", "20", 6, "Gamble"));
		messages.add(produceMessage("id2", "message2", "40", 6, "Gamble"));
		messages.add(produceMessage("id3", "message3", "200", 6, "Gamble"));
		messages.add(produceMessage("id4", "message4", "A1", 6, "Gamble"));
		messages.add(produceMessage("id5", "message5", "1000", 6, "foobar"));
		messages.add(produceMessage("id6", "message6", null, 6, "foobar"));
		messages.add(produceMessage("id7", "message7", "12", 6, null));
		
		logger.debug("Messages before sorting");
		listMessages(messages);
		
		Collections.sort(messages, new GameLogic.MathMessageComparator());
		
		logger.debug("Messages after sorting");
		listMessages(messages);

		Assert.assertNotNull(messages);
		Assert.assertNotNull(messages.get(0));
		Assert.assertEquals(messages.get(0).getAdId(), "id3");
		Assert.assertNotNull(messages.get(4));
		Assert.assertEquals(messages.get(4).getAdId(), "id5");
		Assert.assertNotNull(messages.get(5));
		Assert.assertEquals(messages.get(5).getAdId(), "id7");
	}
	
	@Test
	public void sortMessagesCowardlyTest() {
		List<Message> messages = new ArrayList<Message>();
		messages.add(produceMessage("id0", "message0", "100", 6, "Gamble"));
		messages.add(produceMessage("id1", "message1", "20", 6, "Walk in the park"));
		messages.add(produceMessage("id2", "message2", "40", 6, "Gamble"));
		messages.add(produceMessage("id3", "message3", "200", 6, "Gamble"));
		messages.add(produceMessage("id4", "message4", "A1", 6, "Impossible"));
		messages.add(produceMessage("id5", "message5", "1", 6, "foobar"));
		
		logger.debug("Messages before sorting");
		listMessages(messages);
		
		Collections.sort(messages, new GameLogic.CowardlyMessageComparator());
		
		logger.debug("Messages after sorting");
		listMessages(messages);

		Assert.assertNotNull(messages);
		Assert.assertNotNull(messages.get(0));
		Assert.assertEquals(messages.get(0).getAdId(), "id1");
		Assert.assertNotNull(messages.get(4));
		Assert.assertEquals(messages.get(4).getAdId(), "id4");
		Assert.assertNotNull(messages.get(5));
		Assert.assertEquals(messages.get(5).getAdId(), "id5");
	}
}
