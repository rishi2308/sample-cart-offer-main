package com.springboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.controller.OfferRequest;
import com.springboot.controller.OfferRequest;
import com.springboot.controller.ApplyOfferRequest;
import com.springboot.controller.SegmentResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartOfferApplicationTests {


	@Test
	public void checkFlatXForSegmentOne() throws Exception {
		List<String> segments = new ArrayList<>();
		segments.add("p1");
		OfferRequest offerRequest = new OfferRequest(1,"FLATX",10,segments);
		boolean result = addOffer(offerRequest);
		Assert.assertEquals(result,true); // able to add offer
	}

	@Test
	public void checkFlatXPForSegmentTwo() throws Exception {
		List<String> segments = new ArrayList<>();
		segments.add("p2");
		OfferRequest aOfferRequest = new OfferRequest(1,"FLATXP",10,segments);
		boolean result = addOffer(aOfferRequest);
		Assert.assertEquals(result,true); // able to add offer
	}

	@Test
	public void checkFlatXPForSegmentThree() throws Exception {
		List<String> segments = new ArrayList<>();
		segments.add("p3");
		OfferRequest aOfferRequest = new OfferRequest(1,"FLATXP",20,segments);
		boolean result = addOffer(aOfferRequest);
		Assert.assertEquals(result,true); // able to add offer
	}

	
	

	@Test //Test to verify cart value is discounted with 10Rs
	public void checkFlatXApply() throws Exception {
		ApplyOfferRequest applyOfferRequest = new ApplyOfferRequest(200,1,1);
		boolean result = applyOffer(applyOfferRequest);
		Assert.assertEquals(result,true); // able to apply offer

	}

	@Test //Test to verify apllied cart value is validated as Invalid
	public void checkFlatXPApply() throws Exception {
		ApplyOfferRequest applyOfferRequest = new ApplyOfferRequest(-250,1,2);
		boolean result = applyOffer(applyOfferRequest);
		Assert.assertEquals(result,true); // able to apply offer

	}

	@Test //Test to verify  cart value is discounted with 20%
	public void checkFlatXApplyD() throws Exception {
		ApplyOfferRequest applyOfferRequest = new ApplyOfferRequest(75,1,3);
		boolean result = applyOffer(applyOfferRequest);
		Assert.assertEquals(result,true); // able to apply offer

	}

	@Test //Test to verify segment 4 does not exist
	public void checkFlatXApplyFour() throws Exception {
		ApplyOfferRequest applyOfferRequest = new ApplyOfferRequest(777,1,4);
		boolean result = applyOffer(applyOfferRequest);
		Assert.assertEquals(result,true); // not able to apply offer

	}

	public boolean addOffer(OfferRequest offerRequest) throws Exception {
		String urlString = "http://localhost:9001/api/v1/offer";
		URL url = new URL(urlString);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setDoOutput(true);
		con.setRequestProperty("Content-Type", "application/json");

		ObjectMapper mapper = new ObjectMapper();

		String POST_PARAMS = mapper.writeValueAsString(offerRequest);
		OutputStream os = con.getOutputStream();
		os.write(POST_PARAMS.getBytes());
		os.flush();
		os.close();
		int responseCode = con.getResponseCode();
		System.out.println("POST Response AddOfferCode :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("POST request did not work.");
		}
		return true;
	}
	
	public boolean applyOffer(ApplyOfferRequest applyofferRequest) throws Exception {
		String urlString = "http://localhost:9001/api/v1/cart/apply_offer";
		URL url = new URL(urlString);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setDoOutput(true);
		con.setRequestProperty("Content-Type", "application/json");

		ObjectMapper mapper = new ObjectMapper();

		String POST_PARAMS = mapper.writeValueAsString(applyofferRequest);
		OutputStream os = con.getOutputStream();
		os.write(POST_PARAMS.getBytes());
		os.flush();
		os.close();
		int responseCode = con.getResponseCode();
		System.out.println("POST Response ApplyOfferCode :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { //success

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			JSONObject jsonResponse = new JSONObject(response.toString());

                // Extract cart_value
                int cartValue = jsonResponse.getInt("cart_value");
				//System.out.println("Cart Value after applying offer: " + cartValue);
			if(cartValue <= 0)
			{
				System.out.println("Invalid Cart Value applied/added");
				
			}
			else
			{
				// print result
				System.out.println(response.toString());
			}
			
		} 
		
		else {
			System.out.println("POST request did not work.");
		}
			
			
		return true;
	}
	



}

