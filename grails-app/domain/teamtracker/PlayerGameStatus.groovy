package teamtracker

/** Maintains the many-to-many relationship of the status for every player for every game */
class PlayerGameStatus {

	String status
	static belongsTo=[player:Player, game:Game]
 
	static constraints = {
		status(inList:PlayerGameStatusService.statusType.keySet().asType(List.class))
	}  

	@Override
	public String toString() {
		return "PlayerGameStatus [  ${this.player.firstName}  :  ${this.game.date}  :  ${status} ]";
	}


	public PlayerGameStatus(Player player, Game game, String status) {
		super();
		this.player = player
		this.game = game
		this.status = status
	}
	public PlayerGameStatus() {
		super();
	}
}
