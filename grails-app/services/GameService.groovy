

class GameService {

    static transactional = true

     def allGames() {
		print "calling getAll" 
		def gameList 
		gameList=Game.list(null)
		println gameList
    }
}
