package com.riot.api

import com.riot.dto.ChampionMastery.ChampionMastery
import com.riot.enums.METHOD
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spock.lang.Ignore
import spock.lang.Specification

class ChampionMasteryApiTest extends Specification
{
	private static Logger logger = LoggerFactory.getLogger(ChampionMasteryApiTest.class)

	@Ignore
	def "test getChampionMasteriesBySummonerId"()
	{
		given:
			QueryManager queryManagerMock = Mock(QueryManager)
			ClassLoader classLoader = getClass().getClassLoader()
			File file = new File(classLoader.getResource("apiResponses/championMastery/championMasteriesBySummonerId.json").getFile())
			String responseJson = new String(file.readBytes())
			logger.debug("responseJson = " + responseJson)
			queryManagerMock.query(_ as String, _ as METHOD) >> responseJson
		when:
			List<ChampionMastery> championMastery = new ChampionMasteryApi().getChampionMasteriesBySummonerId(queryManagerMock, 44199889)
		then:
			championMastery != null
			championMastery.get(0).getChampionLevel() == 7
			championMastery.get(0).getChestGranted()
			championMastery.get(0).getChampionPoints() == 247627
			championMastery.get(0).getChampionPointsSinceLastLevel() == 226027
			championMastery.get(0).getChampionPointsUntilNextLevel() == 0
			championMastery.get(0).getTokensEarned() == 0
			championMastery.get(0).getChampionId() == 154
			championMastery.get(0).getLastPlayTime() == 1520147481000
			championMastery.get(1).getChampionId() == 56
			championMastery.get(2).getChampionId() == 5
	}
}
