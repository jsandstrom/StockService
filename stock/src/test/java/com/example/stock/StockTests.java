package com.example.stock;

import org.junit.jupiter.api.*;

class StockTests {

	@Test
	void testCalculateYield() {
		Stock s = new Stock();
		s.setName("Sandvik");
		s.setPrice(100.0);
		s.setDividend(10.0);

		Assertions.assertEquals(new Double(0.1), s.calculateYield());

	}
}
