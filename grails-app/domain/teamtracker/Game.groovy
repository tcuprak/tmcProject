package teamtracker

/** 
 * A Game has a place and time and an opponent.
 * 
 * @author mom
 *
 */
class Game {

	Date date
	Opponent opponent1
	String location = "TBD"
	String field
	Boolean weAreHomeTeam
	String result = "scheduled"
	String notes
	
	static hasMany = [playerStatus:PlayerGameStatus]
	

	// load the opponents when we load this
	// not a big performance hit and
	// if we don't, toString will break
	static mapping = {
		opponent1 lazy:false
		

	}


	String toString(){
		def dateStr = date.format('MM/dd/yy')
		def startTimeStr = date.format("hh:mm a")
		"$dateStr at $startTimeStr vs. $opponent1"
	}
	String startTime(){
		def startTimeStr = date.format("hh:mm a")
		"$startTimeStr"
	}

	// inject service into this domain class
	def playerGameStatusService


	static constraints = {
		date(nullable:false)
		opponent1(nullable:true)		
		location(nullable:true)
		field(nullable:true)
		weAreHomeTeam(nullable:true)
		result(nullable:true, inList:[
			"scheduled",
			"win",
			"loss",
			"tie",
			"forfeit"
		], default:"scheduled")

		notes(nullable:true)
	}

	/**  After inserting a new game, make sure we create the PlayerGameStatus that we need for all existing players */

//	def afterInsert() {
//		this.withNewSession() {session ->
//			def allPlayers = Player.list()
//			println "in afterinsert   domain is Game ${this}"
//
//			allPlayers.each {
//
//				def status = new PlayerGameStatus( it,this, "Unknown")
//				//status.setGame(this)
//				//status.setPlayer(it)
//				//status.setStatus(playerGameStatusService.defaultStatus())
//				//status.setStatus("Unknown")
//				println(  " ${it} updating === ${status} ")
//				status.save()
//				println(  " ${it} update done === ${status} ")
//			}
//		}
//	}

	/** TBD -- use cascading deletes correctly
	 * Before deleting a game, make sure we delete the PlayerGameStatus that we need for all existing players */

//	def beforeDelete() {
//		println "in beforeDelete   domain is Game ${this}"
//		this.withNewSession() {session ->
//			def statusToRemove = PlayerGameStatus.findByGame(this)
//
//			println(  " need to remove ${statusToRemove.count()}  ")
//
//			statusToRemove.each {
//
//				println(  " ${it} will be deleted ")
//				it.delete()
//
//				println(  " ${it} remove done  ")
//			}
//		}
//	}
}
