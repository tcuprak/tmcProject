
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
}
