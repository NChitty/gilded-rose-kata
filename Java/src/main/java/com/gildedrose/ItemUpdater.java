package com.gildedrose;

public interface ItemUpdater {
  default int updateQuality(int current, int delta) {
    return Math.max(0, current + delta);
  }

  default void updateItem(Item item) {
    if (!item.getName().equals(GildedRose.AGED_BRIE)
        && !item.getName().equals(GildedRose.BACKSTAGE_PASS)) {
      if (item.getQuality() > 0) {
        if (!item.getName().equals(GildedRose.SULFURAS)) {
          item.setQuality(this.updateQuality(item.getQuality(), -1));
        }
      }
    } else {
      if (item.getQuality() < 50) {
        item.setQuality(this.updateQuality(item.getQuality(), 1));

        if (item.getName().equals(GildedRose.BACKSTAGE_PASS)) {
          if (item.getSellIn() < 11) {
            if (item.getQuality() < 50) {
              item.setQuality(this.updateQuality(item.getQuality(), 1));
            }
          }

          if (item.getSellIn() < 6) {
            if (item.getQuality() < 50) {
              item.setQuality(this.updateQuality(item.getQuality(), 1));
            }
          }
        }
      }
    }

    if (!item.getName().equals(GildedRose.SULFURAS)) {
      item.setSellIn(item.getSellIn() - 1);
    }

    if (item.getSellIn() < 0) {
      if (!item.getName().equals(GildedRose.AGED_BRIE)) {
        if (!item.getName().equals(GildedRose.BACKSTAGE_PASS)) {
          if (item.getQuality() > 0) {
            if (!item.getName().equals(GildedRose.SULFURAS)) {
              item.setQuality(this.updateQuality(item.getQuality(), -1));
            }
          }
        } else {
          item.setQuality(0);
        }
      } else {
        if (item.getQuality() < 50) {
          item.setQuality(this.updateQuality(item.getQuality(), 1));
        }
      }
    }
  }
}
