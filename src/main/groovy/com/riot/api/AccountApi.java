package com.riot.api;

import com.google.gson.Gson;
import com.riot.dto.Account.Account;
import com.riot.enums.METHOD;
import com.riot.exception.RiotApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

public class AccountApi {
	private static final Logger logger = LoggerFactory.getLogger(AccountApi.class);

//	@Value("${accountApiVersion}")
//	String accountApiVersion;

	Account getAccountByName(QueryManager queryManager, String summonerName) throws RiotApiException
	{
		String[] summonerNameSplit = summonerName.split("#");
		if (summonerNameSplit.length != 2)
			throw new RiotApiException("The summoner name wasn't in the format {summoner name}#{tagline}");

		logger.debug("summonerNameSplit[0] = " + summonerNameSplit[0]);
		logger.debug("summonerNameSplit[1] = " + summonerNameSplit[1]);
//		String queryString = "/riot/account/" + accountApiVersion + "/accounts/by-riot-id/" + summonerName + "/NA1?";
		String queryString = "/riot/account/v1/accounts/by-riot-id/" + summonerNameSplit[0] + "/" + summonerNameSplit[1] + "?";

		String response = queryManager.query(queryString, METHOD.ACCOUNT_BY_NAME);

		return new Gson().fromJson(response, Account.class);
	}
}
