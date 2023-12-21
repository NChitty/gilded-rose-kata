package com.gildedrose;

public interface ItemUpdater {
  default int updateQuality(int current, int delta) {
    if (current < 0) return current;

    return Math.max(0, current + delta);
  }

  default void updateItem(Item item) {
    if (item.getName().equals(GildedRose.SULFURAS)) return;

    item.setSellIn(item.getSellIn() - 1);

    if (item.getName().equals(GildedRose.AGED_BRIE)) {
      if (item.getQuality() >= 50) return;
      item.setQuality(this.updateQuality(item.getQuality(), 1));

      if (item.getSellIn() >= 0) return;
      item.setQuality(this.updateQuality(item.getQuality(), 1));
      return;
    }

    if (item.getName().equals(GildedRose.BACKSTAGE_PASS)) {
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
      return;
    }

    item.setQuality(this.updateQuality(item.getQuality(), -1));

    if (item.getSellIn() >= 0) return;
    item.setQuality(this.updateQuality(item.getQuality(), -1));
  }
}
