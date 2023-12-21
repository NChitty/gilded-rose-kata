package com.gildedrose.updater;

import com.gildedrose.Item;

public class SpecialEventItemUpdater implements ItemUpdater {
  @Override
  public void updateItem(Item item) {
    ItemUpdater.super.updateItem(item);

    if (item.getQuality() >= 50) return;
    item.setQuality(this.updateQuality(item.getQuality(), 1));
    if (item.getSellIn() >= 10) return;
    if (item.getQuality() >= 50) return;
    item.setQuality(this.updateQuality(item.getQuality(), 1));

    if (item.getSellIn() >= 5) return;
    if (item.getQuality() >= 50) return;
    item.setQuality(this.updateQuality(item.getQuality(), 1));

    if (item.getSellIn() >= 0) return;
    item.setQuality(0);
  }
}
