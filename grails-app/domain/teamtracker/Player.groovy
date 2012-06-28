package teamtracker

class Player {
	String firstName
	String lastName

	/** TODO make these multiple and add address */
	String phone
	String email

	String playerType ="sub"
	Date dateJoined
	Date birthday
	String playerNotes

	// many-to-many relationship with Games indicates status for a specific game
	static hasMany = [gameStatus:PlayerGameStatus]
	//	static mapping = {gameStatus cascade: 'delete'}

	String toString(){
		"$firstName, $lastName ($playerType)"
	}

	// inject service into this domain class
	def playerGameStatusService


	static constraints = {
		firstName(nullable:false)
		lastName(nullable:true)
		playerType (inList:[
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

	/** Before inserting a newly created Player, it is necessary that we 
	 * provide status for that player for all existing games.  
	 * We have to create a separate database session because this player is 
	 * attached to a transient PlayerGameStatus */	
	def beforeInsert() {
		println("====================    preparing to insert player " + this.id)
		def allGames = teamtracker.Game.list()
		allGames?.each {
			this.gameStatus.add(new PlayerGameStatus(this, it, "Unknown"))
		}


	}

	public Player() {
		super()
	//	def allGames = teamtracker.Game.list()
		gameStatus = new ArrayList()

//		 
	}
}
