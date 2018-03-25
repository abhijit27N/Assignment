package web.app.ui;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Controller

public class PricingController {

	@RequestMapping(value = "/PricingQuery", method = RequestMethod.GET)

	public String showLoginPage(ModelMap model) {

		return "PricingQuery";

	}

	@RequestMapping(value = "/PricingQuery", method = RequestMethod.POST)

	public String showWelcomePage(ModelMap model, @RequestParam String number) {

		String result = "Calculate avg for " + number;

		System.out.println("Calling get WS request .... \n");
		Double avg_prc = jerseyGetRequest(number);

		model.put("price", avg_prc);

		return "Result";

	}

	public double jerseyGetRequest(String count) {
		try {

			Client client = Client.create();

			WebResource webResource = client.resource("http://localhost:8080/getPrice").queryParam("count", count);

			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			String output = response.getEntity(String.class);

			ObjectMapper mapper = new ObjectMapper();
			PriceData prc = mapper.readValue(output, PriceData.class);

			System.out.println("Output from Server .... \n");
			System.out.println(prc);

			return prc.price;

		} catch (Exception e) {

			e.printStackTrace();

		}
		return 0;
	}
}
