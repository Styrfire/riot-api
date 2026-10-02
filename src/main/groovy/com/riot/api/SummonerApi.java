package com.riot.api;

import com.google.gson.Gson;
import com.riot.dto.Summoner.Summoner;
import com.riot.enums.METHOD;
import com.riot.exception.RiotApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

// TODO: update
class SummonerApi
{
	private static final Logger logger = LoggerFactory.getLogger(SummonerApi.class);

	@Value("${summonerApiVersion}")
	String summonerApiVersion;

	Summoner getSummonerByName(QueryManager queryManager, String summonerName) throws RiotApiException
	{
		logger.debug("summonerName = " + summonerName);
		String queryString = "/lol/summoner/" + summonerApiVersion + "/summoners/by-name/" + summonerName + "?";

		String response = queryManager.query(queryString, METHOD.SUMMONER_BY_NAME);

		return new Gson().fromJson(response, Summoner.class);
	}
}
