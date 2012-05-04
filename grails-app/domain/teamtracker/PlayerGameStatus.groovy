package teamtracker

class PlayerGameStatus {

	

	String status
	

	
	static belongsTo=[player:Player, game:Game]



	static constraints = {
		status(inList:PlayerGameStatusService.statusType.keySet().asType(List.class))
	}


	@Override
	public String toString() {
		return "PlayerGameStatus [  ${player}  :  ${game}  :  ${status} ]";
	}


	public PlayerGameStatus(Player player, Game game, String status) {
		super();
		this.player = player;
		this.game = game;
		this.status = status;
	}
	public PlayerGameStatus() {
		super();
	}






}
