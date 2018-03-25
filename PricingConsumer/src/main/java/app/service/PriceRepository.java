package app.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PriceRepository extends MongoRepository<PriceData, String> {

	public PriceData findByPrice(Double price);

}