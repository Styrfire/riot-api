package com.riot.api;

import com.riot.enums.METHOD;
import com.riot.exception.RiotApiException;
import com.riot.exception.RiotExceptionCreator;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

class QueryManager
{
	private static Logger logger = Logger.getLogger(QueryManager.class);

	private final String apiKey;
	private final RiotRateLimiter rateLimiter;
	private final RestTemplate restTemplate;

	QueryManager(String apiKey)
	{
		this.apiKey = apiKey;
		this.rateLimiter = new RiotRateLimiter();
		this.restTemplate = new RestTemplate();
	}

	String query(String queryUrl, METHOD method) throws RiotApiException
	{
		try
		{
			// check to make sure the method isn't exceeding it's rate limit
			rateLimiter.preApiCallRateLimit(method);

			// make the api call
			String urlString = "https://na1.api.riotgames.com" + queryUrl + "api_key=" + apiKey;
			logger.info("urlString = " + urlString);
			ResponseEntity<String> responseEntity = restTemplate.getForEntity(urlString, String.class);

			// handle exceptions
			if (responseEntity.getStatusCode().value() >= 300)
				RiotExceptionCreator.throwException(responseEntity.getStatusCode().value());

			// rate limit headers
			Map<String, List<String>> headers = responseEntity.getHeaders();
			logger.debug(headers.get("X-App-Rate-Limit"));
			logger.debug(headers.get("X-App-Rate-Limit-Count"));
			logger.debug(headers.get("X-Method-Rate-Limit"));
			logger.debug(headers.get("X-Method-Rate-Limit-Count"));

			// updated method rate limit objects
			rateLimiter.postApiCallRateLimit(method, headers);

			// return the response
			return responseEntity.getBody();
		}
		catch (RiotApiException e)
		{
			throw e;
		}
		catch (HttpClientErrorException e)
		{
			logger.error(e);
			RiotExceptionCreator.throwException(e.getStatusCode().value());
			return null;
		}
		catch (Exception e)
		{
			logger.error(e);
			throw new RiotApiException("Oops... Something went wrong...");
		}
	}
}
