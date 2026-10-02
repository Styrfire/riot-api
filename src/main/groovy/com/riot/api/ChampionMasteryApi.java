package com.riot.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.riot.dto.ChampionMastery.ChampionMastery;
import com.riot.enums.METHOD;
import com.riot.exception.RiotApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

class ChampionMasteryApi
{
	private static final Logger logger = LoggerFactory.getLogger(ChampionMasteryApi.class);

	@Value("${championMasteryApiVersion}")
	String championMasteryApiVersion;

	List<ChampionMastery> getChampionMasteriesBySummonerId(QueryManager queryManager, String encryptedSummonerId) throws RiotApiException
	{
		logger.debug("summonerId = " + encryptedSummonerId);
		String queryString = "/lol/champion-mastery/" + championMasteryApiVersion + "/champion-masteries/by-summoner/" + encryptedSummonerId + "?";

		String response = queryManager.query(queryString, METHOD.CHAMPION_MASTERIES_BY_SUMMONER_ID);

		return new Gson().fromJson(response, new TypeToken<ArrayList<ChampionMastery>>(){}.getType());
	}
}
