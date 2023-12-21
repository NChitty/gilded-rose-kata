package com.gildedrose.updater;

import com.gildedrose.Item;

public class DefaultItemUpdater implements ItemUpdater {
  @Override
  public void updateItem(Item item) {
    ItemUpdater.super.updateItem(item);

    item.setQuality(this.updateQuality(item.getQuality(), -1));

    if (item.getSellIn() >= 0) return;
    item.setQuality(this.updateQuality(item.getQuality(), -1));
  }
}
