package ee.az.mugloar;

import java.util.List;

import org.apache.log4j.Logger;

import ee.az.mugloar.api.model.Message;
import ee.az.mugloar.logic.MessageSortingTest;

public class MessageUtils {

	private static Logger logger = Logger.getLogger(MessageSortingTest.class);

	public static Message produceMessage(String adId, String message, String reward, int expiresIn, String probability) {
		Message result =  new Message();
		result.setAdId(adId);
		result.setExpiresIn(expiresIn);
		result.setMessage(message);
		result.setProbability(probability);
		result.setReward(reward);
		return result;
	} 

	public static void listMessages(List<Message> messages, Logger logger) {
		for(Message m : messages) {
			logger.debug(m);
		}
	}
	
	public static void listMessages(List<Message> messages) {
		listMessages(messages, logger);
	}
}
