package teamtracker

import static org.junit.Assert.*
import spock.lang.Specification
import grails.test.mixin.domain.DomainClassUnitTestMixin

//import grails.test.mixin.*
import grails.test.mixin.support.*
//import org.junit.*
import grails.buildtestdata.mixin.Build

import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
//@TestMixin(GrailsUnitTestMixin)
@TestFor(PlayerGameStatus)
@TestMixin(DomainClassUnitTestMixin)
class PlayerGameStatusSpec extends ConstraintUnitSpec{

	def setup() {
		//mock a game with some data
		mockForConstraintsTests(PlayerGameStatus, [new PlayerGameStatus()])
	}

	/** Verify that the constraints placed on the creation of the Game domain object are enforced.  Did not check nullable:true  */
	@Unroll()
	def "Verify PlayerGameStatus domain constraints: The value '#val' for the field '#field' results in  #error error "() {

		//---------------------- create UUT using table values
		given:

		def obj = new PlayerGameStatus("$field": val)

		//---------------------- use util class to assert correct error
		when:
		checkExpectedError(obj, field, error)

		//----------------------
		then: "assertion is true"

		//----------------------  test constraints for status
		where:

		error                  | field     | val
		'no'                   | 'status'  | 'Unsure'
		'no'                   | 'status'  | 'Playing'
		'no'                   | 'status'  | 'Available'
		'no'                   | 'status'  | 'Subbing'
		'no'                   | 'status'  | 'Unknown'
		'no'                   | 'status'  | 'NotPlaying'
		'inList'               | 'status'  | 'NotAStatus'
		'nullable'             | 'status'  | null

	}


	def "Verify that status associates to Player and Game"() {

		//---------------------- create UUT using a static date
		given:
		def Date testDate = new Date("1/1/2011")
		mockDomain(Player,[[firstName:"jenny"]])
		mockDomain(Game, [[date:testDate]])

		Game game= Game.findByDate(testDate)
		Player player= Player.findByFirstName("jenny")
	

		def  PlayerGameStatus uut;

		//---------------------- use util class to assert correct error
		when:
		uut = new PlayerGameStatus(player, game, "Subbing").save()
	

		//----------------------
		then:
		// retrieve from DB
		Game gameResult= Game.findByDate(testDate)
		Player playerResult= Player.findByFirstName("jenny")
		// verify that the uut was added to each collection
		assert (gameResult.playerStatus.contains(uut))
		assert (playerResult.gameStatus.contains(uut))
		

	}
	def "Verify that each player and game combination is unique ( no duplicate statuses  )"() {
		
			
		//---------------------- create UUT using a static date
		given:
		def testName ="Yvonne"
		def Date testDate = new Date("1/12/2011")
		
		mockDomain(Player,[[firstName:testName]])
		mockDomain(Game, [[date:testDate]])

		Game game= Game.findByDate(testDate)
		Player player= Player.findByFirstName(testName)
	

		def  PlayerGameStatus uut;

		//---------------------- add the same player, game 3 times
		when:
		uut = new PlayerGameStatus(player, game, "Subbing").save()
		uut = new PlayerGameStatus(player, game, "Unknown").save()
		uut = new PlayerGameStatus(player, game, "Unknown").save()
		
	

		//----------------------
		then:
		// retrieve from DB

		PlayerGameStatus[] result = PlayerGameStatus.findAll()
		
		assert (result.length ==1)
		assert (result[0].status="Subbing")
		
	}
}
