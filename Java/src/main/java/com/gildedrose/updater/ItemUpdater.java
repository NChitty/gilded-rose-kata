package com.gildedrose.updater;

import com.gildedrose.Item;

public interface ItemUpdater {
  default int updateQuality(int current, int delta) {
    if (current < 0) return current;

    return Math.max(0, current + delta);
  }

  default void updateItem(Item item) {
    item.setSellIn(item.getSellIn() - 1);
  }
}
