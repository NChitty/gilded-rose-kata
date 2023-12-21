package com.gildedrose;

class GildedRose {
  private final Item[] items;
  private final ItemUpdater itemUpdater;

  public GildedRose(Item[] items) {
    this.items = items;
    this.itemUpdater = new ItemUpdater() {
      @Override
      public void updateItem(Item item) {
        ItemUpdater.super.updateItem(item);
      }
    };
  }

  static final String AGED_BRIE = "Aged Brie";
  static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
  static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

  public void updateQuality() {
    for (int i = 0; i < items.length; i++) {
      this.itemUpdater.updateItem(items[ i ]);
    }
  }

  public Item[] getItems() {
    return this.items;
  }
}
