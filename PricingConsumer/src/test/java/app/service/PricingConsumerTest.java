package app.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

public class PricingConsumerTest {

	@Test
	public void testMockPricing() {
		PriceCalculator calculator = new PriceCalculator();
		List<PriceData> prices = new ArrayList<PriceData>();

		IPriceData prcdata = mock(IPriceData.class);
		when(prcdata.getPrice()).thenReturn(100.0d);

		prices.add(new PriceData(prcdata.getPrice(), new Date()));
		prices.add(new PriceData(prcdata.getPrice(), new Date()));

		assertEquals("The avg should be 100.0", 100.0d, calculator.findAvgPrice(prices, 7), 0.001);
	}

	@Test
	public void testPriceSameCount() {
		PriceCalculator calculator = new PriceCalculator();
		List<PriceData> prices = new ArrayList<PriceData>();
		prices.add(new PriceData(100.0, new Date()));
		prices.add(new PriceData(200.0, new Date()));
		prices.add(new PriceData(200.0, new Date()));
		prices.add(new PriceData(500.0, new Date()));
		prices.add(new PriceData(300.0, new Date()));

		assertEquals("The avg should be 260.0", 260.0d, calculator.findAvgPrice(prices, 5), 0.001);
	}

	@Test
	public void testPriceDifferentCount() {
		PriceCalculator calculator = new PriceCalculator();
		List<PriceData> prices = new ArrayList<PriceData>();
		prices.add(new PriceData(100.0, new Date()));
		prices.add(new PriceData(200.0, new Date()));
		prices.add(new PriceData(300.0, new Date()));
		prices.add(new PriceData(500.0, new Date()));
		prices.add(new PriceData(300.0, new Date()));

		assertEquals("The avg should be 200.0", 200.0d, calculator.findAvgPrice(prices, 3), 0.001);
	}

	@Test
	public void testPriceZeroCount() {
		PriceCalculator calculator = new PriceCalculator();
		List<PriceData> prices = new ArrayList<PriceData>();
		prices.add(new PriceData(100.0, new Date()));
		prices.add(new PriceData(200.0, new Date()));
		prices.add(new PriceData(300.0, new Date()));
		prices.add(new PriceData(500.0, new Date()));
		prices.add(new PriceData(300.0, new Date()));

		assertEquals("The avg should be 0.0", 0.0d, calculator.findAvgPrice(prices, 0), 0.001);
	}

	@Test
	public void testPriceGreaterThanActualCount() {
		PriceCalculator calculator = new PriceCalculator();
		List<PriceData> prices = new ArrayList<PriceData>();
		prices.add(new PriceData(100.0, new Date()));
		prices.add(new PriceData(200.0, new Date()));
		prices.add(new PriceData(300.0, new Date()));
		prices.add(new PriceData(400.0, new Date()));

		assertEquals("The avg should be 250.0", 250.0d, calculator.findAvgPrice(prices, 7), 0.001);
	}

}
