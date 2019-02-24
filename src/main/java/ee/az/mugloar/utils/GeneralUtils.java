package ee.az.mugloar.utils;

public class GeneralUtils {
	public static String getFirstWord(String phrase) {
		if (phrase == null || phrase.isEmpty()) {
			return "";
		}
		return phrase.split(" ")[0];
	}
}
