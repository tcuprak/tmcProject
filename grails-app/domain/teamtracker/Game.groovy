package teamtracker

/** 
 * A Game has a place and time and an opponent.
 * 
 * @author mom
 *
 */
class Game {

	

	Date date
	Opponent opponent
	String location = "TBD"
	String field
	Boolean weAreHomeTeam
	String result
	String notes

	static hasMany = [playerStatus:PlayerGameStatus]


	// load the opponents when we load this
	// not a big performance hit and
	// if we don't, toString will break
	static mapping = { opponent lazy:false }
	//	static mapping = { opponent lazy:false,  playerStatus cascade:'delete' }


	String toString(){
		def dateStr = date.format('MM/dd/yy')
		def startTimeStr = startTime()
		"$dateStr at $startTimeStr vs. $opponent"
	}
	String startTime(){
		def startTimeStr = date.format("hh:mm a")
		"$startTimeStr"
	}

	// inject service into this domain class
	def playerGameStatusService


	static constraints = {
		date(nullable:false)
		opponent(nullable:true)
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


	/** Before inserting a newly created Game, it is necessary that we
	 * provide status for that game for all existing Players.
	 */
	def beforeInsert() {
		println("====================  preparing to insert game " + this.id)

		def allPlayers = Player.list()
		
		allPlayers?.each {
			println("creating pgs for " + this + " and " +it)
			playerStatus.add( new PlayerGameStatus( it,this,"Unknown"))

		}
	}
	
	public Game() {
		super();		
		playerStatus = new ArrayList()

	}

}

