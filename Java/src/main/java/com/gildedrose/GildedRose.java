package com.gildedrose;

import com.gildedrose.updater.ItemUpdater;
import com.gildedrose.updater.ItemUpdaterFactory;
import java.util.HashMap;
import java.util.Map;

class GildedRose {
  private final Item[] items;
  private final HashMap<String, ItemUpdater> itemUpdaters;
  private final ItemUpdater defaultUpdater;

  public GildedRose(Item[] items) {
    this.items = items;
    this.itemUpdaters = new HashMap<>();
    this.itemUpdaters.put(SULFURAS, ItemUpdaterFactory.buildLegendaryUpdater());
    this.itemUpdaters.put(AGED_BRIE, ItemUpdaterFactory.buildAgedUpdater());
    this.itemUpdaters.put(BACKSTAGE_PASS, ItemUpdaterFactory.buildSpecialEventUpdater());
    this.defaultUpdater = ItemUpdaterFactory.buildUpdater();
  }

  static final String AGED_BRIE = "Aged Brie";
  static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
  static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

  public void updateQuality() {
    for (int i = 0; i < items.length; i++) {
      String name = items[ i ].getName();
      this.itemUpdaters.getOrDefault(name, defaultUpdater).updateItem(items[ i ]);
    }
  }

  public Item[] getItems() {
    return this.items;
  }
}
