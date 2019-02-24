package ee.az.mugloar.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import ee.az.mugloar.api.model.Item;

public class ItemSortingTest {

	@Test
	public void sortItemsTest() {
		List<Item> items = new ArrayList<>();
		items.add(produceItem("id0", "Name0", 0));
		items.add(produceItem("id1", "Name0", 10));
		items.add(produceItem("id2", "Name0", 100));
		items.add(null);
		
		Collections.sort(items, new GameLogic.ItemByPriceComparator());
		
		Assert.assertNotNull(items);
		Assert.assertNotNull(items.get(0));
		Assert.assertEquals(items.get(0).getId(), "id2");
	}
	
	private Item produceItem(String id, String name, int cost) {
		Item result = new Item();
		result.setId(id);
		result.setCost(cost);
		result.setName(name);
		return result;
	}
}
