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

class PlayerGameStatusServiceIntegrationSpec extends Specification {

	def setup() {

	}


	def "Verify attendance count is correct for one player (int) "() {


		//---------------------- create UUT using a static date
		given:

		createTestDomain()

		Game game= Game.findByDate('5/12/2013')
		Player player = Player.findByFirstName("Janice")

		def  PlayerGameStatus pgs1 ;


		pgs1 = PlayerGameStatus.findByGameAndPlayer(game, player)
		pgs1.status="Playing"
		pgs1.save()
		
		def PlayerGameStatusService uut = new PlayerGameStatusService()
		



		//--------------------- use the service to determine player count
		when:

		int count =uut.attendanceCount(game);

		//----------------------
		then:
		// count should be one

		assertEquals (1, count)

	}


	def "Verify attendance count is correct for two players  (int)"() {


		//---------------------- create UUT using a static date
		given:
		
		def  PlayerGameStatus pgs1 ;
		
		Game game= Game.findByDate('5/12/2013')
		Player player = Player.findByFirstName("Cindy")
		pgs1 = PlayerGameStatus.findByGameAndPlayer(game, player)
		pgs1.status="Playing"
		pgs1.save()
		
		player = Player.findByFirstName("Sally")
		pgs1 = PlayerGameStatus.findByGameAndPlayer(game, player)
		pgs1.status="Playing"
		pgs1.save()



		//--------------------- use the service to determine player count
		when:

		PlayerGameStatusService uut = new PlayerGameStatusService()
		int count =uut.attendanceCount(game);

		//----------------------
		then:
		// count should be one

		assertEquals (3, count)

	}




	private void createTestDomain() {
		def opp1 = new Opponent(name:"Diamond Divas").save()
		def opp2 = new Opponent(name:"Chico's Bail Bonds").save()

		println "***  IN TEST creating game 1"
		new Game(date:new Date('5/12/2013')  , opponent1:opp1).save()
	

		println "***creating game 2"
		new Game(date:new Date('5/19/2013')  , opponent1:opp2).save()
		
		new Player(firstName:"Janice").save()
		new Player(firstName:"Cindy").save()
		new Player(firstName:"Sally").save()

	}

}
