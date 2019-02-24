package ee.az.mugloar.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import ee.az.mugloar.Mugloar;
import ee.az.mugloar.api.model.Adventure;
import ee.az.mugloar.api.model.Message;

public class DataCollector {

	private static Logger logger = Logger.getLogger(DataCollector.class);
	private static Set<String> collectedProbabilities = new HashSet<>();
	private static Set<Message> encryptedMessages = new HashSet<>();
	private static Map<String, Risk> risks = new HashMap<>();
	private static Map<String, ByName> adventuresByName = new HashMap<>();
	private static Score score = new Score();

	public static void main(String[] args) throws Exception {
		int gamesToRun = 30;
		if (args.length > 0) {
			try {
				gamesToRun = Integer.valueOf(args[0]);
			} catch (NumberFormatException ignore) {}
		}
		
		logger.info("Starting data collection");

		long start = System.currentTimeMillis();
		for (int i=0; i<gamesToRun; i++) {
			logger.info(String.format("Running game %d of %d", i+1, gamesToRun));
			Mugloar.runGame();
			if (i>0 && (i+1)%10 == 0) {
				showStats(start, System.currentTimeMillis());
			}
		}
		long end = System.currentTimeMillis();
		
		showStats(start, end);
	}
	
	private static void showStats(long start, long end) {
		logger.info("***********************************************************");	
		logger.info("Data collected");
		long totalTime = (end-start)/1000;
		int totalGames = score.getTotalGames();
		logger.info(String.format("%d games played in %d seconds, %d seconds average", totalGames, totalTime, totalTime/totalGames));

		/*
		logger.info("Probablilities:");
		for (String d : collectedProbabilities) {
			logger.info(d);
		}
		logger.info("Encrypted Messages:");
		for (Message m : encryptedMessages) {
			logger.info(m);
		}
		*/
		logger.info("Risk statistics:");
		for (String riskName : risks.keySet()) {
			Risk risk = risks.get(riskName);
			int rate = risk.getSuccess() * 100 / risk.getTotal();
			logger.info(String.format("Risk '%s' success %d/%d (%d%%)", riskName, risk.getSuccess(), risk.getTotal(), rate));
		}
		logger.info("Adventure statistics by first word:");
		for (ByName bn : adventuresByName.values()) {
			int rate = bn.getFailures() * 100 / bn.getTotal();
			logger.info(String.format("Prefix '%s' failure %d/%d (%d%%)", bn.getNamePrefix(), bn.getFailures(), bn.getTotal(), rate));
		}
		logger.info("Levels and scores:");
		logger.info(String.format("Level: %d < %d < %d", score.getMinLevel(), score.getAverageLevel(), score.getMaxLevel()));
		logger.info(String.format("Score: %d < %d < %d", score.getMinScore(), score.getAverageScore(), score.getMaxScore()));
		int rate = score.getGamesBelow1k() * 100 / score.getTotalGames();
		logger.info(String.format("Games with score below 1000: %d / %d (%d%%)", score.getGamesBelow1k(), score.getTotalGames(), rate));
		logger.info("***********************************************************");	
	}
	
	public static void collectEncryptedMessages(Message message) {
		encryptedMessages.add(message);
	}
	
	public static void collectProbability(String probability) {
		collectedProbabilities.add(probability);
	}
	
	public static void collectAdventures(Message message, Adventure adventure) {
		String probability = message.getProbability();
		collectProbability(probability);
		Risk risk = risks.get(probability);
		if (risk == null) {
			risk = new Risk();
			risks.put(probability, risk);
		}
		if (adventure.isSuccess()) {
			risk.addSuccess();
		}
		risk.addTotal();
		
		String namePrefix = GeneralUtils.getFirstWord(message.getMessage());
		logger.debug("Adventure name prefix " + namePrefix);
		ByName byName = adventuresByName.get(namePrefix);
		if (byName == null) {
			byName = new ByName(namePrefix);
			adventuresByName.put(namePrefix, byName);
		}
		byName.addGame(!adventure.isSuccess());
	}
	
	public static void addGame(int score, int level) {
		DataCollector.score.addGame(score, level);
	}
	
	private static class Risk {
		private int total;
		private int success;

		public void addSuccess() {
			success++;
		}
		public void addTotal() {
			total++;
		}
		public int getTotal() {
			return total;
		}
		public int getSuccess() {
			return success;
		}
	}
	
	private static class ByName {
		private  final String namePrefix;
		private int total;
		private int failures;
		
		public ByName(String namePrefix) {
			this.namePrefix = namePrefix;
		}
		public void addGame(boolean failure) {
			total++;
			if(failure) {
				failures++;
			}
		}
		public String getNamePrefix() {
			return namePrefix;
		}
		public int getTotal() {
			return total;
		}
		public int getFailures() {
			return failures;
		}
	}
	
	private static class Score {
		private int totalGames;
		private int minScore = Integer.MAX_VALUE;
		private int maxScore;
		private int totalScore;
		private int gamesBelow1k;

		private int minLevel = Integer.MAX_VALUE;;
		private int maxLevel;
		private int totalLevel;
		
		public void addGame(int score, int level) {
			totalGames++;
			totalScore += score;
			totalLevel += level;
			if (minScore > score) {
				minScore = score;
			}
			if (maxScore < score) {
				maxScore = score;
			}
			if (minLevel > level) {
				minLevel = level;
			}
			if (maxLevel < level) {
				maxLevel = level;
			}
			if (score < 1000) {
				gamesBelow1k++;
			}
		}
		
		public int getMinScore() {
			return minScore;
		}
		public int getMaxScore() {
			return maxScore;
		}
		public int getTotalGames() {
			return totalGames;
		}
		public int getMinLevel() {
			return minLevel;
		}
		public int getMaxLevel() {
			return maxLevel;
		}
		public int getAverageLevel() {
			return totalLevel/totalGames;
		}
		public int getAverageScore() {
			return totalScore/totalGames;
		}
		public int getGamesBelow1k() {
			return gamesBelow1k;
		}
	}
}
