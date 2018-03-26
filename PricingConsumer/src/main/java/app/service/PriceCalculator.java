package app.service;

import java.util.List;

public class PriceCalculator {

	public double findAvgPrice(List<PriceData> prices, int count) {
		int iter = 0;
		double total = 0.0;

		for (PriceData data : prices) {
			if (iter >= count)
				break;

			System.out.println("Price = " + data.price + " time = " + data.time);
			total = total + data.price;
			iter++;
		}

		double avg_prc = Math.round(total / iter);

		System.out.println("Avg Price = " + avg_prc);
		return avg_prc;
	}
}
