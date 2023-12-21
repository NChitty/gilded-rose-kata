package com.gildedrose.updater;

import com.gildedrose.Item;

public class ConjuredItemUpdater implements ItemUpdater {
  @Override
  public void updateItem(Item item) {
    ItemUpdater.super.updateItem(item);
    item.setQuality(this.updateQuality(item.getQuality(), -2));

    if (item.getSellIn() >= 0) return;

    item.setQuality(this.updateQuality(item.getQuality(), -2));
  }
}
