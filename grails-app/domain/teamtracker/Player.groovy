package teamtracker

class Player {
	String firstName
	String lastName

	/** TODO make these multiple and add address */
	String phone
	String email

	String status ="sub"
	Date dateJoined
	Date birthday
	String playerNotes
	
	// many-to-many relationship with status
	static hasMany = [gameStatus:PlayerGameStatus]

	String toString(){
		"$firstName, $lastName ($status)"
	}

	// inject service into this domain class
	//def playerGameStatusService
   
	
//	static mapping = {		
//		gameStatus cascade: "all-delete-orphan" 
//	}
	static constraints = {
		firstName(nullable:false)
		lastName(nullable:true)
		status (inList:[
			"active",
			"sub",
			"alternate",
			"retired"]
		)
		phone(nullable:true)
		email(email:true,nullable:true)
		playerNotes(nullable:true, maxSize : 1000)
		dateJoined(nullable:true)
		birthday(nullable:true)
		
		
	}
//
//	/** After inserting a newly created Player, it is necessary that we 
//	 * provide status for that player for all existing games.  
//	 * We have to create a separate database session because this player is 
//	 * attached to a transient PlayerGameStatus */	
//	def afterInsert() {
//		println("create player game status for all games for this player after insert player ${this}")
//		PlayerGameStatus.withNewSession() {session ->
//			def allGames = Game.list()
//			println("ready to update ${allGames.size()} games")
//
//			allGames.each {
//				def playerGameStatus = new PlayerGameStatus( this,it,playerGameStatusService.defaultStatus())
//
//				print("saving default game status: ${it.id} --> ${this.firstName}  .... ")
//				playerGameStatus.save(failOnError:true)
//				println "  ${this.firstName}  is updated"
//			}
//		}
//	}

//	/** TBD -- use cascading deletes correctly
//	 * Before deleting a game, make sure we delete the PlayerGameStatus that we need for all existing players */
//
//	def beforeDelete() {
//		println "in beforeDelete   domain is Player ${this}"
//		PlayerGameStatus.withNewSession() {session ->
//			def statusToRemove = PlayerGameStatus.findByPlayer(this)
//
//		//	println(  " need to remove ${statusToRemove.} status records associated with this player  ")
//			println(  " ready to remove ${statusToRemove}  ")
//			statusToRemove.each {
//
//				println(  " ${it} will be deleted ")
//				it.delete(flush:true)
//
//				println(  " ${it} remove done  ")
//			}
//		}
//	}
}