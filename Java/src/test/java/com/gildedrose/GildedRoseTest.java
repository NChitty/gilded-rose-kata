package com.gildedrose;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

  @Test
  void foo() {
    Item[] items = new Item[] { new Item("foo", 0, 0) };
    GildedRose app = new GildedRose(items);
    app.updateQuality();
    assertEquals("foo", app.getItems()[ 0 ].getName());
  }

  @Nested
  class WhenBasicItem {
    @Test
    void givenNormal_thenBothSellInAndQualityDecreases() {
      Item item = new Item("foo", 1, 1);
      GildedRose app = new GildedRose(new Item[] { item });
      app.updateQuality();
      assertEquals(0, item.getQuality());
      assertEquals(0, item.getSellIn());
    }

    @Test
    void givenSellInZero_thenQualityDecreasesDouble() {
      Item item = new Item("foo", 0, 4);
      GildedRose app = new GildedRose(new Item[] { item });
      app.updateQuality();
      assertEquals(2, item.getQuality());
      assertEquals(-1, item.getSellIn());
    }

    @Test
    void givenSellInLessThanZero_thenQualityDecreasesDouble() {
      Item item = new Item("foo", -1, 2);
      GildedRose app = new GildedRose(new Item[] { item });
      app.updateQuality();
      assertEquals(0, item.getQuality());
      assertEquals(-2, item.getSellIn());
    }

    @Test
    void givenQualityIsZero_thenQualityRemainsZero() {
      Item item = new Item("foo", 1, 0);
      GildedRose app = new GildedRose(new Item[] { item });
      app.updateQuality();
      assertEquals(0, item.getQuality());
      assertEquals(0, item.getSellIn());
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, -2, -3, -4, -5, -10, -100, Integer.MIN_VALUE })
    void givenQualityLessThanZero_thenQualityUnmoved(int quality) {
      Item item = new Item("foo", 1, quality);
      GildedRose app = new GildedRose(new Item[] { item });
      app.updateQuality();
      assertEquals(quality, item.getQuality());
      assertEquals(0, item.getSellIn());
    }

    @ParameterizedTest
    @ValueSource(ints = { 52, 53, 54, 55, 56, 60, 100, 1000, Integer.MAX_VALUE })
    void givenQualityGreaterThanFifty_thenQualityDecreasesNormally(int quality) {
      Item item = new Item("foo", 1, quality);
      GildedRose app = new GildedRose(new Item[] { item });
      app.updateQuality();
      assertEquals(quality - 1, item.getQuality());
      assertEquals(0, item.getSellIn());
    }
  }

  @Nested
  class WhenAgedItem {
    @Test
    void givenAgedBrie_thenQualityIncreases() {
      Item item = new Item(GildedRose.AGED_BRIE, 1, 0);
      GildedRose app = new GildedRose(new Item[] { item });
      app.updateQuality();
      assertEquals(1, item.getQuality());
      assertEquals(0, item.getSellIn());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, -1, -2, -10, -100 })
    void givenPastSellIn_thenQualityIncreasesDouble(int sellIn) {
      Item item = new Item(GildedRose.AGED_BRIE, sellIn, 0);
      GildedRose app = new GildedRose(new Item[] { item });
      app.updateQuality();
      assertEquals(2, item.getQuality());
      assertEquals(sellIn - 1, item.getSellIn());
    }

    @ParameterizedTest
    @ValueSource(ints = { 50, 51, 52, 53, 54, 60, 100, 1000, Integer.MAX_VALUE })
    void givenQualityIsFiftyOrGreater_thenQualityUnchanged(int quality) {
      Item item = new Item(GildedRose.AGED_BRIE, 1, quality);
      GildedRose app = new GildedRose(new Item[] { item });
      app.updateQuality();
      assertEquals(quality, item.getQuality());
      assertEquals(0, item.getSellIn());
    }

    @ParameterizedTest
    @CsvSource(value = { "0,50", "0,51", "-1,50", "-1,51" })
    void givenSellInZeroOrLess_thenQualityUnchanged(int sellIn, int quality) {
      Item item = new Item(GildedRose.AGED_BRIE, sellIn, quality);
      GildedRose app = new GildedRose(new Item[] { item });
      app.updateQuality();
      assertEquals(quality, item.getQuality());
      assertEquals(sellIn - 1, item.getSellIn());
    }
  }

  @Nested
  class WhenLegendaryItem {
    @Test
    void givenSulfuras_thenRemainUnchanged() {
      Item item = new Item(GildedRose.SULFURAS, 1, 1);
      GildedRose app = new GildedRose(new Item[] { item });
      app.updateQuality();
      assertEquals(1, item.getQuality());
      assertEquals(1, item.getSellIn());
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, -1, 10, -10, Integer.MAX_VALUE, Integer.MIN_VALUE })
    void givenVariousSellIn_thenRemainUnchanged(int sellIn) {
      Item item = new Item(GildedRose.SULFURAS, sellIn, 1);
      GildedRose app = new GildedRose(new Item[] { item });
      app.updateQuality();
      assertEquals(1, item.getQuality());
      assertEquals(sellIn, item.getSellIn());
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, -1, 10, -10, Integer.MAX_VALUE, Integer.MIN_VALUE })
    void givenVariousQualities_thenRemainUnchanged(int quality) {
      Item item = new Item(GildedRose.SULFURAS, 1, quality);
      GildedRose app = new GildedRose(new Item[] { item });
      app.updateQuality();
      assertEquals(quality, item.getQuality());
      assertEquals(1, item.getSellIn());
    }
  }

  @Nested
  class WhenSpecialtyItem {
    @Test
    void givenBackStagePass_thenQualityIncreases() {
      Item item = new Item(GildedRose.BACKSTAGE_PASS, 10, 1);
      GildedRose app = new GildedRose(new Item[] { item });
      app.updateQuality();
      assertEquals(3, item.getQuality());
      assertEquals(9, item.getSellIn());
    }

    @ParameterizedTest
    @ValueSource(ints = { 11, 12, 13, 14, 15, 100, 1000, Integer.MAX_VALUE })
    void givenSellInGreaterThanTen_thenQualityIncreasesByOne(int sellIn) {
      Item item = new Item(GildedRose.BACKSTAGE_PASS, sellIn, 0);
      GildedRose app = new GildedRose(new Item[] { item });
      app.updateQuality();
      assertEquals(1, item.getQuality());
      assertEquals(sellIn - 1, item.getSellIn());
    }

    @ParameterizedTest
    @ValueSource(ints = { 10, 9, 8, 7, 6 })
    void givenSellInLessThanEqualTenAndGreaterThan5_thenQualityIncreasesByTwo(int sellIn) {
      Item item = new Item(GildedRose.BACKSTAGE_PASS, sellIn, 0);
      GildedRose app = new GildedRose(new Item[] { item });
      app.updateQuality();
      assertEquals(2, item.getQuality());
      assertEquals(sellIn - 1, item.getSellIn());
    }

    @ParameterizedTest
    @ValueSource(ints = { 5, 4, 3, 2, 1 })
    void givenSellInLessThanEqualFive_thenQualityIncreasesByThree(int sellIn) {
      Item item = new Item(GildedRose.BACKSTAGE_PASS, sellIn, 0);
      GildedRose app = new GildedRose(new Item[] { item });
      app.updateQuality();
      assertEquals(3, item.getQuality());
      assertEquals(sellIn - 1, item.getSellIn());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, -1, -2, -10, -100 })
    void givenSellInLessThanEqualZero_thenQualityClampedToZero(int sellIn) {
      Item item = new Item(GildedRose.BACKSTAGE_PASS, sellIn, -1);
      GildedRose app = new GildedRose(new Item[] { item });
      app.updateQuality();
      assertEquals(0, item.getQuality());
      assertEquals(sellIn - 1, item.getSellIn());
    }

    @ParameterizedTest
    @ValueSource(ints = { 50, 51, 52, 53, 54, 60, 100, 1000, Integer.MAX_VALUE })
    void givenQualityIsFiftyOrGreater_thenQualityUnchanged(int quality) {
      Item item = new Item(GildedRose.BACKSTAGE_PASS, 1, quality);
      GildedRose app = new GildedRose(new Item[] { item });
      app.updateQuality();
      assertEquals(quality, item.getQuality());
      assertEquals(0, item.getSellIn());
    }
  }
}
