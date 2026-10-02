package com.riot.api;

import com.google.gson.Gson;
import com.riot.dto.StaticData.ChampionList;
import com.riot.enums.METHOD;
import com.riot.exception.RiotApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

// TODO: update
class StaticDataApi
{
	private static Logger logger = LoggerFactory.getLogger(StaticDataApi.class);

	String getStaticLastPatchVersion(QueryManager queryManager) throws RiotApiException
	{
		String queryString = "https://ddragon.leagueoflegends.com/api/versions.json";

		String response = queryManager.query(queryString, METHOD.STATIC);

		return new Gson().fromJson(response, List.class).get(0).toString();
	}

	ChampionList getStaticChampionInfo(QueryManager queryManager, String patchVersion) throws RiotApiException
	{
		String queryString = "http://ddragon.leagueoflegends.com/cdn/" + patchVersion + "/data/en_US/champion.json";

		String response = queryManager.query(queryString, METHOD.STATIC);

		return new Gson().fromJson(response, ChampionList.class);
	}
}
