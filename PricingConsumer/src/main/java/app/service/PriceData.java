package app.service;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class PriceData implements IPriceData{

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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Customer [price=" + price + ", time=" + time + "]";
	}

}