package teamtracker

import static org.junit.Assert.*
import grails.test.mixin.domain.DomainClassUnitTestMixin
import grails.test.mixin.services.ServiceUnitTestMixin


import grails.test.mixin.support.*
import grails.buildtestdata.mixin.Build
import spock.lang.Specification

import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */

@TestFor(PlayerGameStatusService)

class PlayerGameStatusServiceSpec extends Specification {

	//	def "Verify attendance count is correct for zero players"() {
	//
	//		//---------------------- create UUT using a static date
	//		given:
	//
	//		Date testDate = createTestDomain()
	//
	//		Game game= Game.findByDate(testDate)
	//		Player player = Player.findByFirstName("Janice")
	//		println("++++++++++++ " + game)
	//		println("++++++++++++ " + player)
	//
	//		def  pgs1 = PlayerGameStatus.findByGameAndPlayer(game, player)
	//		println("++++++++++++ " + pgs1)
	//		pgs1.status="Available" // something other than "Playing"
	//		pgs1.save(flush:true)
	//
	//		//--------------------- use the service to determine player count
	//		when:
	//
	//
	//		int count =service.attendanceCount(game);
	//
	//		//----------------------
	//		then:
	//		// count should be zero
	//
	//		assertEquals (0, count)
	//
	//	}

	def "Verify attendance count is correct for one player "() {

		//---------------------- create UUT using a static date
		given:

		Date testDate =  new Date("1/12/2011")


		Player p1 = new Player([firstName:'Janice',lastName:"smith"])
		Player p2 = new Player([firstName:'Jen',lastName:"smith"])
		Player p3 = new Player([firstName:'Jolene',lastName:"smith"])

		p1.save(flush:true)
		p2.save(flush:true)
		p3.save(flush:true)

		Game g1 = new Game(date:testDate).save(flush:true)

		Game game= Game.findByDate(testDate)
		Player player = Player.findByFirstName("Janice")

		def  pgs1 = PlayerGameStatus.findByGameAndPlayer(game, player)
		pgs1.status="Playing"
		pgs1.save(flush:true)

		//--------------------- use the service to determine player count
		when:


		int count =service.attendanceCount(game);

		//----------------------
		then:
		// count should be one

		assertEquals (1, count)

	}


	//	def "Verify attendance count is correct for two players "() {
	//
	//
	//		//---------------------- create UUT using a static date
	//		given:
	//
	//		Date testDate = createTestDomain()
	//
	//		Game game= Game.findByDate(testDate)
	//		Player player = Player.findByFirstName("Janice")
	//		println("==========" + game + " ==== " + player)
	//
	//		def  pgs1 ;
	//
	//
	//		pgs1 = PlayerGameStatus.findByGameAndPlayer(game, player)
	//		pgs1.status="Playing"
	//		pgs1.save(flush:true)
	//		player = Player.findByFirstName("Sally")
	//		pgs1 = PlayerGameStatus.findByGameAndPlayer(game, player)
	//		pgs1.status="Playing"
	//		pgs1.save(flush:true)
	//
	//
	//
	//		//--------------------- use the service to determine player count
	//		when:
	//		int count =service.attendanceCount(game);
	//
	//		//----------------------
	//		then:
	//		// count should be two
	//
	//		assertEquals (2, count)
	//
	//	}
	//
	//
	//	def "Verify sub shows up on sub list "() {
	//
	//
	//		//---------------------- create UUT using a static date
	//		given:
	//
	//
	//		Date testDate = createTestDomain()
	//
	//		Game game= Game.findByDate(testDate)
	//		Player player1 = Player.findByFirstName("Janice")
	//		println("==========" + game + " ==== " + player1)
	//		player1.playerType="sub"
	//		player1.save(flush:true)
	//
	//		def  PlayerGameStatus pgs1 ;
	//
	//
	//		pgs1 = PlayerGameStatus.findByGameAndPlayer(game, player1)
	//		pgs1.status="Available"
	//		println "==============   in test : " + pgs1
	//
	//		Player player = Player.findByFirstName("Sally")
	//
	//		player.playerType="sub"
	//		player.save(flush:true)
	//		println "==============   player in test : " + player
	//
	//		def PlayerGameStatus pgs2 = PlayerGameStatus.findByGameAndPlayer(game, player)
	//		pgs2.status="Playing"
	//		println "==============   in test : " + pgs2
	//		pgs2.save(flush:true)
	//		println "==============   in test : " + pgs2
	//
	//
	//
	//		//--------------------- use the service to determine player count
	//		when:
	//
	//
	//		def  List<Player> subList =service.subList(game);
	//
	//		//----------------------
	//		then:
	//		// list should contain Janice, but not Sally
	//
	//		assertEquals (1, subList.size())
	//		assertFalse(subList[0].equals(player))
	//		assertTrue(subList[0].equals(player1))
	//	}
	//




	private Date createTestDomain() {
		def Date testDate = new Date("1/12/2011")

		mockDomain(PlayerGameStatus)

		mockDomain(Player,[
			[firstName:"Janice"],
			[firstName:"Sally"],
			[firstName:"Cindy"]
		])

		mockDomain(Game, [[date:testDate]])

		return testDate
	}
}
