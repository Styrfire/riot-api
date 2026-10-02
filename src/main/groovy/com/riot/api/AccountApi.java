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

	@Value("${accountApiVersion}")
	String accountApiVersion;

	Account getAccountByName(QueryManager queryManager, String summonerName) throws RiotApiException
	{
		logger.debug("summonerName = " + summonerName);
		String queryString = "/riot/account/" + accountApiVersion + "/accounts/by-riot-id/" + summonerName + "/NA1?";

		String response = queryManager.query(queryString, METHOD.ACCOUNT_BY_NAME);

		return new Gson().fromJson(response, Account.class);
	}
}
