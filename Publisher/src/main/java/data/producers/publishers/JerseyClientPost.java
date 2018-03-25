package data.producers.publishers;

import java.time.LocalDate;
import java.util.Random;

import org.json.simple.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyClientPost {

	public static void main(String[] args) {

		try {

			Client client = Client.create();

			WebResource webResource = client.resource("http://localhost:8080/Pricesave");

			JSONObject json = new JSONObject();

			ClientResponse response;

			while (true) {
				Thread.sleep(5000);

				json.put("price", Integer.toString(1 + new Random().nextInt(100)));
				json.put("time", LocalDate.now().toString());

				response = webResource.type("application/json").post(ClientResponse.class, json.toString());

				if (response.getStatus() != 201) {
					throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
				}

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

	}
}
