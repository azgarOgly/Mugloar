package ee.az.mugloar;

import static ee.az.mugloar.MessageUtils.listMessages;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import ee.az.mugloar.api.model.Message;
import ee.az.mugloar.utils.DecryptionUtils;

public class DecryptionUtilTest {

	private static Logger logger = Logger.getLogger(DecryptionUtilTest.class);

	@Test
	public void testDecryptMessages() {
		List<Message> messages = new ArrayList<>();
		messages.add(produceMessage("Vk1FQlNLMkE=", "SW52ZXN0aWdhdGUgQm9yaXZvaSDDk2RpbnNzb24gYW5kIGZpbmQgb3V0IHRoZWlyIHJlbGF0aW9uIHRvIHRoZSBtYWdpYyBkb2cu", "157", 1, "U3VpY2lkZSBtaXNzaW9u", "1"));
		messages.add(produceMessage("dExrUUFxUHM=", "SW52ZXN0aWdhdGUgTHVjeW5hIFdvb2R3YXJkIGFuZCBmaW5kIG91dCB0aGVpciByZWxhdGlvbiB0byB0aGUgbWFnaWMgY2hpY2tlbi4=", "133", 3, "U3VpY2lkZSBtaXNzaW9u", "1"));
		messages.add(produceMessage("RmFlQnk5YW0=", "SW52ZXN0aWdhdGUgQmVydG9sZG8gVGl0dGVuc29yIGFuZCBmaW5kIG91dCB0aGVpciByZWxhdGlvbiB0byB0aGUgbWFnaWMgaG9yc2Uu", "121", 1, "UGxheWluZyB3aXRoIGZpcmU=", "1"));
		messages.add(produceMessage("6vzI8r8x", "Xvyy Ntarfn Fureohear jvgu jngre naq znxr Ryvnxvz Fnaqref sebz pnfgyr va Sreacrnx gb gnxr gur oynzr", "105", 1, "Fhvpvqr zvffvba", "2"));
		messages.add(produceMessage("yFHhXF6T", "Xvyy Snl Qvpxfba jvgu ubhfr naq znxr Gvegn Oneaneq sebz cnynpr va Beevatnaaba gb gnxr gur oynzr", "121", 1, "Fhvpvqr zvffvba", "2"));
		messages.add(produceMessage("dElBd0FsV3k=", "SW52ZXN0aWdhdGUgR2F6c2kgTCdoZXJuYXVsdCBhbmQgZmluZCBvdXQgdGhlaXIgcmVsYXRpb24gdG8gdGhlIG1hZ2ljIGNsb3RoZXMu", "178", 2, "UmF0aGVyIGRldHJpbWVudGFs", "1"));
		logger.debug("Encrypted:");
		listMessages(messages, logger);
		
		messages = DecryptionUtils.decrypt(messages);
		Assert.assertNotNull(messages);
		Assert.assertEquals(messages.size(), 6);
		Assert.assertNotNull(messages.get(0));
		Assert.assertEquals(messages.get(0).getAdId(), "VMEBSK2A");
		Assert.assertEquals(messages.get(3).getAdId(), "6imV8e8k");
		Assert.assertEquals(messages.get(3).getProbability(), "Suicide mission");
		logger.debug("Decrypted:");
		listMessages(messages, logger);
	}
	
	private Message produceMessage(String adId, String message, String reward, int expiresIn, String probability, String encrypted) {
		Message m = MessageUtils.produceMessage(adId, message, reward, expiresIn, probability);
		m.setEncrypted(encrypted);
		return m;
	}
}
