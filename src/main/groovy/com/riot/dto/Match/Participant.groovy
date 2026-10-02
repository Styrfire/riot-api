package com.riot.dto.Match

class Participant
{
	int allInPings // Yellow crossed swords
	int assistMePings // Green flag
	int assists
	int baronKills
	int bountyLevel
	int champExperience
	int champLevel
	int championId
	String championName
	int commandPings // Blue generic ping (ALT+click)
	int championTransform // This field is currently only utilized for Kayn's transformations. (Legal values: 0 - None, 1 - Slayer, 2 - Assassin)
	int consumablesPurchased
	Challenges challenges
	int damageDealtToBuildings
	int damageDealtToObjectives
	int damageDealtToTurrets
	int damageSelfMitigated
	int deaths
	int detectorWardsPlaced
	int doubleKills
	int dragonKills
	boolean eligibleForProgression
	int enemyMissingPings // Yellow question mark
	int enemyVisionPings // Red eyeball
	boolean firstBloodAssist
	boolean firstBloodKill
	boolean firstTowerAssist
	boolean firstTowerKill
	boolean gameEndedInEarlySurrender // This is an offshoot of the OneStone challenge. The code checks if a spell with the same instance ID does the final point of damage to at least 2 Champions. It doesn't matter if they're enemies, but you cannot hurt your friends.
	boolean gameEndedInSurrender
	int holdPings
	int getBackPings // Yellow circle with horizontal line
	int goldEarned
	int goldSpent
	String individualPosition // Both individualPosition and teamPosition are computed by the game server and are different versions of the most likely position played by a player. The individualPosition is the best guess for which position the player actually played in isolation of anything else. The teamPosition is the best guess for which position the player actually played if we add the constraint that each team must have one top player, one jungle, one middle, etc. Generally the recommendation is to use the teamPosition field over the individualPosition field.
	int inhibitorKills
	int inhibitorTakedowns
	int inhibitorsLost
	int item0
	int item1
	int item2
	int item3
	int item4
	int item5
	int item6
	int itemsPurchased
	int killingSprees
	int kills
	String lane
	int largestCriticalStrike
	int largestKillingSpree
	int largestMultiKill
	int longestTimeSpentLiving
	int magicDamageDealt
	int magicDamageDealtToChampions
	int magicDamageTaken
	Missions missions
	int neutralMinionsKilled
	int nexusKills
	int nexusTakedowns
	int nexusLost
	int objectivesStolen
	int objectivesStolenAssists
	int onMyWayPings // Blue arrow pointing at ground
	int participantId
	int playerScore0
	int playerScore1
	int playerScore2
	int playerScore3
	int playerScore4
	int playerScore5
	int playerScore6
	int playerScore7
	int playerScore8
	int playerScore9
	int playerScore10
	int playerScore11
	int pentaKills
	Perks perks
	int physicalDamageDealt
	int physicalDamageDealtToChampions
	int physicalDamageTaken
	int placement
	int playerAugment1
	int playerAugment2
	int playerAugment3
	int playerAugment4
	int playerSubteamId
	int pushPings // Green minion
	int profileIcon
	String puuid
	int quadraKills
	String riotIdGameName
	String riotIdTagline
	String role
	int sightWardsBoughtInGame
	int spell1Casts
	int spell2Casts
	int spell3Casts
	int spell4Casts
	int subteamPlacement
	int summoner1Casts
	int summoner1Id
	int summoner2Casts
	int summoner2Id
	String summonerId
	int summonerLevel
	String summonerName
	boolean teamEarlySurrendered
	int teamId
	String teamPosition // Both individualPosition and teamPosition are computed by the game server and are different versions of the most likely position played by a player. The individualPosition is the best guess for which position the player actually played in isolation of anything else. The teamPosition is the best guess for which position the player actually played if we add the constraint that each team must have one top player, one jungle, one middle, etc. Generally the recommendation is to use the teamPosition field over the individualPosition field.
	int timeCCingOthers
	int timePlayed
	int totalAllyJungleMinionsKilled
	int totalDamageDealt
	int totalDamageDealtToChampions
	int totalDamageShieldedOnTeammates
	int totalDamageTaken
	int totalHeal // Whenever positive health is applied (which translates to all heals in the game but not things like regeneration), totalHeal is incremented by the amount of health received. This includes healing enemies, jungle monsters, yourself, etc
	int totalHealsOnTeammates // Whenever positive health is applied (which translates to all heals in the game but not things like regeneration), totalHealsOnTeammates is incremented by the amount of health received. This is post modified, so if you heal someone missing 5 health for 100 you will get +5 totalHealsOnTeammates
	int totalMinionsKilled // totalMillionsKilled = mMinionsKilled, which is only incremented on kills of kTeamMinion, kMeleeLaneMinion, kSuperLaneMinion, kRangedLaneMinion and kSiegeLaneMinion
	int totalTimeCCDealt
	int totalTimeSpentDead
	int totalUnitsHealed
	int tripleKills
	int trueDamageDealt
	int trueDamageDealtToChampions
	int trueDamageTaken
	int turretKills
	int turretTakedowns
	int turretsLost
	int unrealKills
	int visionScore
	int visionClearedPings
	int visionWardsBoughtInGame
	int wardsKilled
	int wardsPlaced
	boolean win
}
