package com.riot.api

import com.riot.dto.ChampionMastery.ChampionMastery
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spock.lang.Ignore
import spock.lang.Specification

class RiotApiTest extends Specification
{
	private static Logger logger = LoggerFactory.getLogger(RiotApiTest.class)

	@Ignore
	def "test getChampionMasteriesBySummonerId"()
	{
		given:
			RiotApi riotApi = Mock(RiotApi)
			riotApi.getChampionMasteriesBySummonerId(_) >> new ArrayList<ChampionMastery>()
		when:
			List<ChampionMastery> championMasteryList = riotApi.getChampionMasteriesBySummonerId(44199889L)
		then:
			championMasteryList instanceof List<ChampionMastery>
	}

}
