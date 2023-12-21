package com.gildedrose;

class GildedRose {
  Item[] items;

  public GildedRose(Item[] items) {
    this.items = items;
  }

  static final String AGED_BRIE = "Aged Brie";
  static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
  static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

  public void updateQuality() {
    for (int i = 0; i < items.length; i++) {
      if (!items[ i ].getName().equals(AGED_BRIE)
          && !items[ i ].getName().equals(BACKSTAGE_PASS)) {
        if (items[ i ].getQuality() > 0) {
          if (!items[ i ].getName().equals(SULFURAS)) {
            items[ i ].setQuality(items[ i ].getQuality() - 1);
          }
        }
      } else {
        if (items[ i ].getQuality() < 50) {
          items[ i ].setQuality(items[ i ].getQuality() + 1);

          if (items[ i ].getName().equals(BACKSTAGE_PASS)) {
            if (items[ i ].getSellIn() < 11) {
              if (items[ i ].getQuality() < 50) {
                items[ i ].setQuality(items[ i ].getQuality() + 1);
              }
            }

            if (items[ i ].getSellIn() < 6) {
              if (items[ i ].getQuality() < 50) {
                items[ i ].setQuality(items[ i ].getQuality() + 1);
              }
            }
          }
        }
      }

      if (!items[ i ].getName().equals(SULFURAS)) {
        items[ i ].setSellIn(items[ i ].getSellIn() - 1);
      }

      if (items[ i ].getSellIn() < 0) {
        if (!items[ i ].getName().equals(AGED_BRIE)) {
          if (!items[ i ].getName().equals(BACKSTAGE_PASS)) {
            if (items[ i ].getQuality() > 0) {
              if (!items[ i ].getName().equals(SULFURAS)) {
                items[ i ].setQuality(items[ i ].getQuality() - 1);
              }
            }
          } else {
            items[ i ].setQuality(items[ i ].getQuality() - items[ i ].getQuality());
          }
        } else {
          if (items[ i ].getQuality() < 50) {
            items[ i ].setQuality(items[ i ].getQuality() + 1);
          }
        }
      }
    }
  }
}
