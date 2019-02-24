package ee.az.mugloar.utils;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.Base64.Decoder;

import org.apache.log4j.Logger;

import ee.az.mugloar.api.model.Message;

public class DecryptionUtils {
	private static Decoder decoderBase64 = Base64.getDecoder();
	private static Logger logger = Logger.getLogger(DecryptionUtils.class);

	public static List<Message> decrypt(Collection<Message> messages) {
		List<Message> result = new ArrayList<>(messages.size());
		
		for (Message m : messages) {
			if (m.getEncrypted() != null) {
				DataCollector.collectEncryptedMessages(m);
				if ("1".contentEquals(m.getEncrypted())) {
					result.add(decodeBase64(m));
				} else if ("2".contentEquals(m.getEncrypted())) {
					result.add(decodeRot13(m));
				} else {
					logger.debug("Unknown encryption, discarding message " + m);
				}
			} else {
				result.add(m);
			}
		}
		return result;
	}
	
	public static Message decodeBase64(Message message) {
		Message result = new Message();
		result.setAdId(decodeBase64(message.getAdId()));
		result.setEncrypted(null);
		result.setExpiresIn(message.getExpiresIn());
		result.setMessage(decodeBase64(message.getMessage()));
		result.setProbability(decodeBase64(message.getProbability()));
		result.setReward(message.getReward());
		return result;
	}
	
	public static Message decodeRot13(Message message) {
		Message result = new Message();
		result.setAdId(decodeRot13(message.getAdId()));
		result.setEncrypted(null);
		result.setExpiresIn(message.getExpiresIn());
		result.setMessage(decodeRot13(message.getMessage()));
		result.setProbability(decodeRot13(message.getProbability()));
		result.setReward(message.getReward());
		return result;
	}
	
	public static String decodeBase64(String encrypted) {
		return new String(decoderBase64.decode(encrypted));
	}

	// https://stackoverflow.com/questions/8981296/rot-13-function-in-java
	public static String decodeRot13(String input) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if       (c >= 'a' && c <= 'm') c += 13;
			else if  (c >= 'A' && c <= 'M') c += 13;
			else if  (c >= 'n' && c <= 'z') c -= 13;
			else if  (c >= 'N' && c <= 'Z') c -= 13;
			sb.append(c);
		}
		return sb.toString();
	}
}
