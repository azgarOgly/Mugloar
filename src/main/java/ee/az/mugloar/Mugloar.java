package ee.az.mugloar;

import java.util.Collection;

import org.apache.log4j.Logger;

import ee.az.mugloar.api.MugloarApi;
import ee.az.mugloar.api.model.Adventure;
import ee.az.mugloar.api.model.Game;
import ee.az.mugloar.api.model.Item;
import ee.az.mugloar.api.model.Message;
import ee.az.mugloar.api.model.Reputation;
import ee.az.mugloar.api.model.ShoppingResponse;
import ee.az.mugloar.logic.GameLogic;
import ee.az.mugloar.utils.DataCollector;

/**
 * @author Artur Zinatullin (2019)
 *
 */
public class Mugloar {

	private final static String SEPARATOR_L = "=====================================================================";
	private final static String SEPARATOR_H = "*********************************************************************";
	private static Logger logger = Logger.getLogger(Mugloar.class);

	public static void main(String[] args) throws Exception {
		try {
			runGame();
		} catch (Exception e) {
			logger.error("Game failed.", e);
		}
	}

	public static void runGame() {
		
		String separator = SEPARATOR_L; 
		
		logger.info(separator);
		logger.info("Starting game");
		logger.info("");

		Game game = MugloarApi.startGame();
		logger.debug(String.format("Started the game with id %s", game.getGameId()));
		logger.debug("Game started " + game);

		Reputation reputation = null;
		Adventure adventureResult;
		ShoppingResponse shoppingResponse = null;

		while (true) {
			logger.info(separator);
			
			Collection<Message> messages = MugloarApi.getMessages(game.getGameId());
			for (Message m : messages) {
				logger.debug("Message " + m);
			}

			Message adventureToSolve = GameLogic.selectAdventure(messages);
			logger.info(String.format("'%s', %sg, '%s'", adventureToSolve.getMessage(), adventureToSolve.getReward(), adventureToSolve.getProbability()));
			logger.debug(String.format("Going to adventure '%s', for %s gold, risk level '%s'", adventureToSolve.getMessage(), adventureToSolve.getReward(), adventureToSolve.getProbability()));
			
			adventureResult = MugloarApi.solveAdventure(game.getGameId(), adventureToSolve.getAdId());
			GameLogic.setCurrentAdventure(adventureResult);
			DataCollector.collectAdventures(adventureToSolve, adventureResult);

			logger.info(String.format("Adventure is %s, lives remaining %d, balance %d gold, score %d", adventureResult.isSuccess() ? "sucess" : "failure", adventureResult.getLives(), adventureResult.getGold(), adventureResult.getScore()));
			logger.debug("Adventure result " + adventureResult);
			
			if (adventureResult.getLives() == 0) {
				DataCollector.addGame(adventureResult.getScore(), shoppingResponse != null ? shoppingResponse.getLevel() : 0);
				break;
			}

			if (GameLogic.investigateReputation()) {
				reputation = MugloarApi.getReputation(game.getGameId());
				logger.info(String.format("Reputation with people %d, state %d, underworld %d", reputation.getReputationWithPeople(), reputation.getReputationWithState(), reputation.getReputationWithUnderworld()));
				logger.debug("Current reputation " + reputation);
			}
			
			if (GameLogic.goShopping()) {
				Collection<Item> shoppingOffers = MugloarApi.getShopOffers(game.getGameId());
				for (Item i : shoppingOffers) {
					logger.debug("Item for sell " + i);
				}
	
				Item itemToBuy = GameLogic.selectItem(shoppingOffers);
				if (itemToBuy != null) {
					shoppingResponse = MugloarApi.buyItem(game.getGameId(), itemToBuy.getId());
					String shoppingSuccess = Boolean.parseBoolean(shoppingResponse.getShoppingSuccess()) ? "successful" : "failure";
					logger.info(String.format("Bying %s for %d of %d gold, lives %d, level %d, %s", itemToBuy.getName(), itemToBuy.getCost(), adventureResult.getGold(), shoppingResponse.getLives(), shoppingResponse.getLevel(), shoppingSuccess));
					logger.debug("Buying " + itemToBuy.getId() + " response " + shoppingResponse);
				}
			}
			
			if (adventureResult.getScore() >= 1000) {
				separator = SEPARATOR_H;
			}
		}
		
		logger.info(separator);
		logger.info("Game Over.");

		logger.info("Score: " + adventureResult.getScore());
		logger.debug("Last adventure resulted " + adventureResult);

		if (reputation != null) {
			logger.debug("Reputation " + reputation);
		}
		logger.info(separator);
	}
}
