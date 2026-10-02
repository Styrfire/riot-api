package com.riot.api;

import com.google.common.util.concurrent.RateLimiter;
import com.riot.dto.RateLimiter.RateLimiterListData;
import com.riot.enums.METHOD;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class RiotRateLimiter
{
	private static Logger logger = LoggerFactory.getLogger(RiotRateLimiter.class);

	private Map<METHOD, RateLimiterListData> rateLimiterList;

	RiotRateLimiter()
	{
		rateLimiterList = new HashMap<>();
	}

	void preApiCallRateLimit(METHOD method)
	{
		// if method is on rateLimiterList
		if (rateLimiterList.containsKey(method))
		{
			// if method is outside time window, remove it from list because the time window has reset
			// if tryAcquire() returns true, it's because the time has reset
			if (rateLimiterList.get(method).getRateLimiter().tryAcquire())
				rateLimiterList.remove(method);
			// if method is still inside time window
			else
			{
				// if method doesn't have anymore calls left within time window, wait, then remove from the list
				// because time window has reset
				if (rateLimiterList.get(method).getNumOfCalls() >= rateLimiterList.get(method).getMaxNumOfCalls())
				{
					rateLimiterList.get(method).getRateLimiter().acquire();
					rateLimiterList.remove(method);
				}
			}
		}

		// if app is on rateLimiterList
		if (rateLimiterList.containsKey(METHOD.API_SHORT))
		{
			// if app is outside time window, remove it from list because the time window has reset
			if (rateLimiterList.get(METHOD.API_SHORT).getRateLimiter().tryAcquire())
				rateLimiterList.remove(METHOD.API_SHORT);
			// if method is still inside time window
			else
			{
				// if method doesn't have anymore calls left within time window, wait, then remove from the list
				// because time window has reset
				if (rateLimiterList.get(METHOD.API_SHORT).getNumOfCalls() >= rateLimiterList.get(METHOD.API_SHORT).getMaxNumOfCalls())
				{
					rateLimiterList.get(METHOD.API_SHORT).getRateLimiter().acquire();
					rateLimiterList.remove(METHOD.API_SHORT);
				}
			}
		}

		// if app is on rateLimiterList
		if (rateLimiterList.containsKey(METHOD.API_LONG))
		{
			// if app is outside time window, remove it from list because the time window has reset
			if (rateLimiterList.get(METHOD.API_LONG).getRateLimiter().tryAcquire())
				rateLimiterList.remove(METHOD.API_LONG);
			// if method is still inside time window
			else
			{
				// if method doesn't have anymore calls left within time window, wait, then remove from the list
				// because time window has reset
				if (rateLimiterList.get(METHOD.API_LONG).getNumOfCalls() >= rateLimiterList.get(METHOD.API_LONG).getMaxNumOfCalls())
				{
					rateLimiterList.get(METHOD.API_LONG).getRateLimiter().acquire();
					rateLimiterList.remove(METHOD.API_LONG);
				}
			}
		}
	}

	void postApiCallRateLimit(METHOD method, Map<String, List<String>> headers)
	{
		// if method wasn't on rateLimiterList, add it
		if (!rateLimiterList.containsKey(method))
		{
			// if headers are not null, go normal path
			if (headers != null)
			{
				double timeWindow = Double.parseDouble(Arrays.asList(headers.get("X-Method-Rate-Limit").get(0).split(":")).get(1));

				RateLimiterListData rateLimiterListData = new RateLimiterListData();
				rateLimiterListData.setRateLimiter(RateLimiter.create(1 / timeWindow));
				// immediately burn the permit for the preApiCallRateLimiter
				rateLimiterListData.getRateLimiter().acquire();
				rateLimiterListData.setNumOfCalls(Integer.parseInt(Arrays.asList(headers.get("X-Method-Rate-Limit-Count").get(0).split(":")).get(0)));
				rateLimiterListData.setMaxNumOfCalls(Integer.parseInt(Arrays.asList(headers.get("X-Method-Rate-Limit").get(0).split(":")).get(0)));
				rateLimiterListData.setTimeWindowInSeconds(Integer.parseInt(Arrays.asList(headers.get("X-Method-Rate-Limit-Count").get(0).split(":")).get(1)));

				logger.debug("methodTimeWindow = " + timeWindow +
						"\nmethodNumOfCalls = " + rateLimiterListData.getNumOfCalls() +
						"\nmethodMaxNumOfCalls = " + rateLimiterListData.getMaxNumOfCalls() +
						"\nmethodTimeWindowInSeconds = " + rateLimiterListData.getTimeWindowInSeconds());

				rateLimiterList.put(method, rateLimiterListData);
			}
			// else, we're in retry logic and don't have headers to instantiate the Rate Limiter. Wait for the next call and it should correct itself
			else
			{
				logger.error("In retry logic and don't have headers to instantiate Rate Limiter");
			}
		}
		// if method was on list, update the number of calls it's made within the time window
		else
		{
			rateLimiterList.get(method).setNumOfCalls(rateLimiterList.get(method).getNumOfCalls() + 1);
		}

		// static methods don't count against the application api and won't have the headers
		// if api short wasn't on rateLimiterList and the method wasn't static, add it
		if (!rateLimiterList.containsKey(METHOD.API_SHORT) && (method != METHOD.STATIC))
		{
			// if headers are not null, go normal path
			if (headers != null)
			{
				double timeWindow = Double.parseDouble(Arrays.asList(headers.get("X-App-Rate-Limit").get(0).split("[:,]")).get(1));

				RateLimiterListData rateLimiterListData = new RateLimiterListData();
				rateLimiterListData.setRateLimiter(RateLimiter.create(1 / timeWindow));
				// immediately burn the permit for the preApiCallRateLimiter
				rateLimiterListData.getRateLimiter().acquire();
				rateLimiterListData.setNumOfCalls(Integer.parseInt(Arrays.asList(headers.get("X-App-Rate-Limit-Count").get(0).split("[:,]")).get(0)));
				rateLimiterListData.setMaxNumOfCalls(Integer.parseInt(Arrays.asList(headers.get("X-App-Rate-Limit").get(0).split("[:,]")).get(0)));
				rateLimiterListData.setTimeWindowInSeconds(Integer.parseInt(Arrays.asList(headers.get("X-App-Rate-Limit-Count").get(0).split("[:,]")).get(1)));

				logger.debug("appShortTimeWindow = " + timeWindow +
						"\nappShortNumOfCalls = " + rateLimiterListData.getNumOfCalls() +
						"\nappShortMaxNumOfCalls = " + rateLimiterListData.getMaxNumOfCalls() +
						"\nappShortTimeWindowInSeconds = " + rateLimiterListData.getTimeWindowInSeconds());

				rateLimiterList.put(METHOD.API_SHORT, rateLimiterListData);
			}
			// else, we're in retry logic and don't have headers to instantiate the Rate Limiter. Wait for the next call and it should correct itself
			else
			{
				logger.error("In retry logic and don't have headers ton instantiate Rate Limiter");
			}
		}
		// if api short was on list and the method wasn't static, update the number of calls it's made within the time window
		else if (method != METHOD.STATIC)
		{
			rateLimiterList.get(METHOD.API_SHORT).setNumOfCalls(rateLimiterList.get(METHOD.API_SHORT).getNumOfCalls() + 1);
		}

		// if api long wasn't on rateLimiterList and the method wasn't static, add it
		if (!rateLimiterList.containsKey(METHOD.API_LONG) && (method != METHOD.STATIC))
		{
			// if headers are not null, go normal path
			if (headers != null)
			{
				double timeWindow = Double.parseDouble(Arrays.asList(headers.get("X-App-Rate-Limit").get(0).split("[:,]")).get(3));

				RateLimiterListData rateLimiterListData = new RateLimiterListData();
				rateLimiterListData.setRateLimiter(RateLimiter.create(1 / timeWindow));
				// immediately burn the permit for the preApiCallRateLimiter
				rateLimiterListData.getRateLimiter().acquire();
				rateLimiterListData.setNumOfCalls(Integer.parseInt(Arrays.asList(headers.get("X-App-Rate-Limit-Count").get(0).split("[:,]")).get(2)));
				rateLimiterListData.setMaxNumOfCalls(Integer.parseInt(Arrays.asList(headers.get("X-App-Rate-Limit").get(0).split("[:,]")).get(2)));
				rateLimiterListData.setTimeWindowInSeconds(Integer.parseInt(Arrays.asList(headers.get("X-App-Rate-Limit-Count").get(0).split("[:,]")).get(3)));

				logger.debug("appLongTimeWindow = " + timeWindow +
						"\nappLongNumOfCalls = " + rateLimiterListData.getNumOfCalls() +
						"\nappLongMaxNumOfCalls = " + rateLimiterListData.getMaxNumOfCalls() +
						"\nappLongTimeWindowInSeconds = " + rateLimiterListData.getTimeWindowInSeconds());

				rateLimiterList.put(METHOD.API_LONG, rateLimiterListData);
			}
			// else, we're in retry logic and don't have headers to instantiate the Rate Limiter. Wait for the next call and it should correct itself
			else
			{
				logger.error("In retry logic and don't have headers ton instantiate Rate Limiter");
			}
		}
		// if api long was on list and the method wasn't static, update the number of calls it's made within the time window
		else if (method != METHOD.STATIC)
		{
			rateLimiterList.get(METHOD.API_LONG).setNumOfCalls(rateLimiterList.get(METHOD.API_LONG).getNumOfCalls() + 1);
		}
	}
}
