package com.riot.api

import com.riot.dto.Match.Match
import com.riot.dto.Match.MatchTimeline
import com.riot.enums.METHOD
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spock.lang.Ignore
import spock.lang.Specification

class MatchApiIntegrationTest extends Specification
{
	private String apiKey

	private static Logger logger = LoggerFactory.getLogger(MatchApiIntegrationTest.class)

	@Ignore
	def "test getMatchByMatchId"()
	{
		given:
			RiotApi api = new RiotApi("API_KEY")
		when:
			Match match = api.getMatchByMatchId(1111111111)
		then:
			match != null
	}

	@Ignore
	def "test getMatchTimelineByMatchId"()
	{
		given:
			RiotApi api = new RiotApi("")
		when:
			MatchTimeline matchTimeline = api.getMatchTimelineByMatchId(1111111111)
		then:
			matchTimeline != null
	}

	@Ignore
	def "test getMatchListByAccountId with parameters"()
	{
		given:
			apiKey = System.getProperty("api.key")
			RiotApi api = new RiotApi(apiKey)
			QueryManager queryManager = new QueryManager(apiKey)
		when:
			// with Chadwîck using My Worst Enemy key
			String[] matchList = new MatchApi().getMatchesByPuuid(queryManager, null, null, null, null, null, null, null, null)
		then:
			matchList != null
	}
}
