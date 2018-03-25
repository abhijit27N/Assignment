package web.app.ui;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class PriceData {

	@Id
	public String id;

	public Double price;
	public Date time;

	public PriceData() {
	}

	public PriceData(Double price, Date time) {
		this.price = price;
		this.time = time;
	}

	@Override
	public String toString() {
		return "PriceData [price=" + price + ", time=" + time + "]";
	}

}