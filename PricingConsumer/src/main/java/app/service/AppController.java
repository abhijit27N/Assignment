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

		int iter = 0;
		int i_count = Integer.parseInt(count);

		double total = 0.0;

		for (PriceData data : prices) {
			if (iter > i_count)
				break;

			System.out.println("Price = " + data.price + " time = " + data.time);

			total = total + data.price;
		}

		double avg_prc = Math.round(total / i_count);

		System.out.println("Avg Price = " + avg_prc);
		return new PriceData(avg_prc, new Date());
	}

	@RequestMapping(value = "/Pricesave", method = RequestMethod.POST)
	public ResponseEntity<String> greetingPost(@RequestBody PriceData prcdata) {
		System.out.println("price = " + prcdata.price + " time = " + prcdata.time);
		repository.save(prcdata);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
