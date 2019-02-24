package ee.az.mugloar;

import java.util.Base64;
import java.util.Base64.Decoder;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class EncryptionTest {

	private static Logger logger = Logger.getLogger(EncryptionTest.class);

	@Test
	public void testDecryption() {
		String encrypted;
		encrypted = "R2FtYmxl";
		logger.debug(String.format("Dectypting '%s' to '%s'", encrypted, decryptBase64(encrypted)));
		encrypted = "SG1tbS4uLi4=";
		logger.debug(String.format("Dectypting '%s' to '%s'", encrypted, decryptBase64(encrypted)));
		encrypted = "UmF0aGVyIGRldHJpbWVudGFs";
		logger.debug(String.format("Dectypting '%s' to '%s'", encrypted, decryptBase64(encrypted)));
		encrypted = "UGxheWluZyB3aXRoIGZpcmU=";
		logger.debug(String.format("Dectypting '%s' to '%s'", encrypted, decryptBase64(encrypted)));
		encrypted = "U3VpY2lkZSBtaXNzaW9u";
		logger.debug(String.format("Dectypting '%s' to '%s'", encrypted, decryptBase64(encrypted)));
	}
	
	public String decryptBase64(String encrypted) {
		Decoder decoder = Base64.getDecoder();
		byte[] decryptedBytes = decoder.decode(encrypted);
		String decrypted = new String(decryptedBytes);
		return decrypted;
	}
}
