package app.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

	@Autowired
	private PriceRepository repository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@RequestMapping("/getPrice")
	public PriceData getAvgPrice(@RequestParam(value = "count", defaultValue = "10") String count) {
		System.out.println(" No of prices to be looked back" + count);

		Query query = new Query();
		query.with(new Sort(Sort.Direction.ASC, "time"));
		List<PriceData> prices = mongoTemplate.find(query, PriceData.class);

		PriceCalculator calculator = new PriceCalculator();
		double avg_prc = calculator.findAvgPrice(prices, Integer.parseInt(count));

		return new PriceData(avg_prc, new Date());
	}

	@RequestMapping(value = "/Pricesave", method = RequestMethod.POST)
	public ResponseEntity<String> greetingPost(@RequestBody PriceData prcdata) {
		System.out.println("price = " + prcdata.price + " time = " + prcdata.time);
		repository.save(prcdata);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
