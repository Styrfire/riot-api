package com.riot.api

import com.riot.dto.Summoner.Summoner
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spock.lang.Ignore
import spock.lang.Specification

class SummonerApiIntegrationTest extends Specification
{
	private static Logger logger = LoggerFactory.getLogger(SummonerApiIntegrationTest.class)

	@Ignore
	def "test getSummonerByName"()
	{
		given:
			RiotApi api = new RiotApi("API_KEY")
		when:
			Summoner summoner = api.getSummonerByName("Zann Starfire")
		then:
			summoner != null
			summoner.getProfileIconId() == 554
			summoner.getName() == "Zann Starfire"
			summoner.getAccountId() == "cPkWNSpBp7c3IdQi712Zp9TfDyn27rn20EpbgGVPs3HCvb4"
			summoner.getId() == "2I8qKyCSEAN5Ooyu0e8mEQDtEHxzfExHMGxZqBFXmhBjJ1s"
	}
}
