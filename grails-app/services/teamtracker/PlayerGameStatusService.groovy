
package teamtracker

/**
 * Doc tip: Or take the easy (and better) road and never store state in a service
 ==== scope choices: =====
 prototype - A new service is created every time it is injected into another class
 request - A new service will be created per request
 flash - A new service will be created for the current and next request only
 flow - In web flows the service will exist for the scope of the flow
 conversation - In web flows the service will exist for the scope of the conversation. ie a root flow and its sub flows
 session - A service is created for the scope of a user session
 singleton (default) - Only one instance of the service ever exists
 **/

class PlayerGameStatusService {

	// must use dependency injection to make this work
	static transactional = true

	// this is the default, but adding here to remember we can change
	static scope = "singleton"

	static final Map statusType =["Unsure":"redx.ico", "Playing":"greencheck.png", "Available":"available.ico", "Subbing":"greencheck.png", "NotPlaying":"redx.ico", "Unknown":"redx.ico" ]

	def gameService

	def returnStatusStrings() {

		statusType.keySet()
	}

	def defaultStatus() {
		"Unknown"
	}

	def allGames(){
		println("in all games "+ gameService?.class?.getName())
		allGameList = gameService?.allGames()
		println "Gamelist: " + allGameList
		return allGameList
	}


	/** Determine how many players are attending a game.  If a playergame status 
	 * is set to "Playing" then add to the count, otherwise do not count that player
	 * 
	 * @param game  the game that is being counted.
	 * @return the number of players attending the game that is associated with the 
	 * given game id.
	 */
	def attendanceCount(Game game)	{

		// could write a single query to get this I would think
		// but my first attempt ( using findAllByGameAndStatus) did not work
		PlayerGameStatus[] playerStatusList = PlayerGameStatus.findAllByGame(game)

		def count = 0
		playerStatusList.each{
			if (it.status.equals("Playing")){
				count++
			}
		}

		return count

	}

	/** Returns a list of players who are subs that are available for this game.
	 * 
	 * @param game the game to use to determine sub list.
	 *  @return the list of subs that are available for the game.
	 */
	def List<Player>  subList(Game gameArg){

		// could write a single query to get this I would think


		def gamesOnDayQuery = PlayerGameStatus.where {game==gameArg}
		def subListQuery = gamesOnDayQuery.where{player.playerType=="sub"}
		def availableSubListQuery = subListQuery.where{status=="Available"}


		Collection<PlayerGameStatus> subList = availableSubListQuery.findAll()
		print "================ found these  subs: " + subList

		// I don't know how to project from the query, so I am just looping
		// through to get players.  TOLEARN:  How to do projection
		def List<Player> subListPlayers = new ArrayList<Player>()
		subList.each{
			def Player player = it.player
			subListPlayers.add(player)
		}

		return subListPlayers
	}

	/** 
	 * provide status for a player for all existing games.
	 * */
	def createPlayerGameStatusForNewPlayer(long playerId){

		Player player = Player.get(playerId)
		println("====================  just retrieved player " + player)

	PlayerGameStatus.withNewSession{
		
		  

			def List<PlayerGameStatus> statusList = new ArrayList<PlayerGameStatus>()

			def allGames = Game.list()

			allGames.each {
				println("creating pgs for " + player + " and " +it)
				// why is this call to this service limited to 8 calls ???  the app hangs after that number
				//def playerGameStatus = new PlayerGameStatus( this,it,playerGameStatusService.defaultStatus())
				def playerGameStatus = new PlayerGameStatus( player,it,"Unknown")
				statusList.add(playerGameStatus)

			}
			println("created playerGameStatuses for " + player)
			println("status list " + statusList)
			statusList.each{ 
				
				it.save()}
		}


	}
}

