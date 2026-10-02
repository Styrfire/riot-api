package com.riot.api;

import com.riot.dto.ChampionMastery.ChampionMastery;
import com.riot.dto.Match.Match;
//import com.riot.dto.Match.MatchTimeline;
import com.riot.dto.StaticData.ChampionList;
import com.riot.dto.Summoner.Summoner;
import com.riot.exception.RiotApiException;

import java.util.List;

public class RiotApi
{
	private final QueryManager queryManager;

	private final ChampionMasteryApi championMasteryApi;
	private final MatchApi matchApi;
	private final StaticDataApi staticDataApi;
	private final SummonerApi summonerApi;

	public RiotApi(String apiKey)
	{
		this.queryManager = new QueryManager(apiKey);

		this.championMasteryApi = new ChampionMasteryApi();
		this.matchApi = new MatchApi();
		this.staticDataApi = new StaticDataApi();
		this.summonerApi = new SummonerApi();
	}

	public Summoner getSummonerByName(String summonerName) throws RiotApiException
	{
		return summonerApi.getSummonerByName(queryManager, summonerName);
	}

	public String[] getMatchesByPuuid(String puuid, Long startTime, Long endTime, Integer queue, String type, Integer start, Integer count) throws RiotApiException
	{
		return matchApi.getMatchesByPuuid(queryManager, puuid, startTime, endTime, queue, type, start, count);
	}

	public Match getMatchByMatchId(String matchId) throws RiotApiException
	{
		return matchApi.getMatchByMatchId(queryManager, matchId);
	}

	// TODO add functionality back
/*	public MatchTimeline getMatchTimelineByMatchId(Long matchId) throws RiotApiException
	{
		return matchApi.getMatchTimelineByMatchId(queryManager, matchId);
	}*/

	public List<ChampionMastery> getChampionMasteriesBySummonerId(String encryptedSummonerId) throws RiotApiException
	{
		return championMasteryApi.getChampionMasteriesBySummonerId(queryManager, encryptedSummonerId);
	}

	public String getStaticLastPatchVersion() throws RiotApiException
	{
		return staticDataApi.getStaticLastPatchVersion(queryManager);
	}

	public ChampionList getStaticChampionInfo(String patchVersion) throws RiotApiException
	{
		return staticDataApi.getStaticChampionInfo(queryManager, patchVersion);
	}
}
