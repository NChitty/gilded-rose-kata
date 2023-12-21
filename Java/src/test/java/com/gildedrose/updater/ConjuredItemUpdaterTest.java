package com.gildedrose.updater;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

public class ConjuredItemUpdaterTest {
  private final ItemUpdater itemUpdater = new ConjuredItemUpdater();
  @Test
  void givenBeforeSellIn_thenQualityDecreasesDouble() {
    Item item = new Item("this doesn't matter anymore", 1, 2);
    itemUpdater.updateItem(item);
    assertEquals(0, item.getQuality());
    assertEquals(0, item.getSellIn());
  }
}
