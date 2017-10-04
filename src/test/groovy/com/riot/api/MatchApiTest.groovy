package com.riot.api

import com.riot.dto.Match.Match
import com.riot.dto.Match.MatchList
import com.riot.dto.Match.MatchTimeline
import com.riot.staticInfo.RiotEnum
import org.junit.Ignore
import spock.lang.Specification

@Ignore
class MatchApiTest extends Specification
{
	def "test getMatchByMatchId"()
	{
		given:
			RiotApi api = new RiotApi(RiotEnum.apiKey)
		when:
			Match match = api.getMatchByMatchId(2448574892)
		then:
			match != null
			match.getGameId() == 2448574892
	}

	def "test getMatchTimelineByMatchId"()
	{
		given:
			RiotApi api = new RiotApi(RiotEnum.apiKey)
		when:
			MatchTimeline matchTimeline = api.getMatchTimelineByMatchId(2448574892)
		then:
			matchTimeline != null
			matchTimeline.getFrameInterval() == 60000
	}

	def "test getMatchListByAccountId"()
	{
		given:
			RiotApi api = new RiotApi(RiotEnum.apiKey)
		when:
			MatchList matchList = api.getMatchListByAccountId(206871870)
		then:
			matchList != null
			matchList.getMatches() != null
	}
}
