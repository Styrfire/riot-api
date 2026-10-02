package com.riot.api;

import com.google.gson.Gson;
import com.riot.dto.Match.Match;
import com.riot.dto.Match.MatchTimeline;
import com.riot.enums.METHOD;
import com.riot.exception.RiotApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

class MatchApi
{
	private static Logger logger = LoggerFactory.getLogger(MatchApi.class);

	String[] getMatchesByPuuid(QueryManager queryManager, String puuid, Long startTime, Long endTime, Integer queue, String type, Integer start, Integer count) throws RiotApiException
	{
		logger.debug("puuid = " + puuid);
		logger.debug("startTime = " + startTime);
		logger.debug("endTime = " + endTime);
		logger.debug("queues = " + queue);
		logger.debug("type = " + type);
		logger.debug("start = " + start);
		logger.debug("count = " + count);

		StringBuilder queryString = new StringBuilder("/lol/match/" + matchApiVersion + "/matches/by-puuid/" + puuid + "/ids?");

		if (startTime != null)
			queryString.append("startTime=").append(startTime).append("&");

		if (endTime != null)
			queryString.append("endTime=").append(endTime).append("&");

		if (queue != null)
			queryString.append("queue=").append(queue).append("&");

		if (type != null)
			queryString.append("type=").append(type).append("&");

		if (start != null)
			queryString.append("start=").append(start).append("&");

		if (count != null)
			queryString.append("count=").append(count).append("&");

		String response = queryManager.query(queryString.toString(), METHOD.MATCHES_BY_PUUID);

		return new Gson().fromJson(response, String[].class);

	}

	@Value("${matchApiVersion}")
	String matchApiVersion;

	Match getMatchByMatchId(QueryManager queryManager, String matchId) throws RiotApiException
	{
		logger.debug("matchId = " + matchId);
		String queryString = "/lol/match/" + matchApiVersion + "/matches/" + matchId + "?";

		String response = queryManager.query(queryString, METHOD.MATCH_BY_MATCH_ID);

		return new Gson().fromJson(response, Match.class);
	}

//	MatchTimeline getMatchTimelineByMatchId(QueryManager queryManager, Long matchId) throws RiotApiException
//	{
//		logger.debug("matchId = " + matchId);
//		String queryString = "/lol/match/" + matchApiVersion + "/timelines/by-match/" + matchId.toString() + "?";
//
//		String response = queryManager.query(queryString, METHOD.MATCH_TIMELINE_BY_MATCH_ID);
//
//		return new Gson().fromJson(response, MatchTimeline.class);
//	}
}
