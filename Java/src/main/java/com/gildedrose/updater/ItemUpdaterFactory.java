package com.gildedrose.updater;

public class ItemUpdaterFactory {
  public static ItemUpdater buildLegendaryUpdater() {
    return new LegendaryItemUpdater();
  }

  public static ItemUpdater buildAgedUpdater() {
    return new AgedItemUpdater();
  }

  public static ItemUpdater buildUpdater() {
    return new DefaultItemUpdater();
  }

  public static ItemUpdater buildSpecialEventUpdater() {
    return new SpecialEventItemUpdater();
  }
  public static ItemUpdater buildConjuredUpdater() {
    return new ConjuredItemUpdater();
  }
}
