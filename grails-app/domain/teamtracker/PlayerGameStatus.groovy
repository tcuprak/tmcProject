package teamtracker

/** Maintains the many-to-many relationship of the status for every player for every game */
class PlayerGameStatus {

	String status
	static belongsTo=[game:Game,player:Player]
	Player player
	Game game
	static constraints = {
		game(nullable:false)

		// do not allow the same player/game to create a duplicate record
		player (unique: 'game', nullable:false)

		status(inList:PlayerGameStatusService.statusType.keySet().asType(List.class))
	}

	@Override
	public String toString() {
		return "PlayerGameStatus [  ${this.player.firstName}  :  ${this.game.date}  :  ${status} ]";
	}


//	public PlayerGameStatus(long player, long game, String status) {
//		super();
//		this.player = Player.findById(player)
//		this.game = Game.findById(game)
//		this.status = status
//	}


	public PlayerGameStatus(Player player, Game game, String status) {
		this.player = player
		this.game = game
		this.status = status
	}
	
}
