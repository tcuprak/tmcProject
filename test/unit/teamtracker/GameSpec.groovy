package teamtracker

import static org.junit.Assert.*

//import grails.test.mixin.*
import grails.test.mixin.support.*
//import org.junit.*
import grails.buildtestdata.mixin.Build

import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
//@TestMixin(GrailsUnitTestMixin)
@TestFor(Game)
 class GameSpec extends ConstraintUnitSpec {

	def setup() {
		//mock a game with some data
		mockForConstraintsTests(Game, [new Game()])
	}

	/** Verify that the constraints placed on the creation of the Game domain object are enforced.  Did not check nullable:true  */
	@Unroll()
	def "Verify Game domain constraints: The value '#val' for the field '#field' results in  #error error "() {

		//---------------------- create UUT using table values
		given:

		def obj = new Game("$field": val)

		//---------------------- use util class to assert correct error
		when:
		checkExpectedError(obj, field, error)

		//----------------------
		then: "assertion is true"

		//----------------------  Not testing all constraints. Don't care about nullable==true
		where:

		error                  | field     | val
		'no'                   | 'result'  | null
		'no'                   | 'result'  | 'scheduled'
		'no'                   | 'result'  | 'win'
		'no'                   | 'result'  | 'loss'
		'no'                   | 'result'  | 'tie'
		'no'                   | 'result'  | 'forfeit'
		'inList'               | 'result'  | 'unknown'
		'inList'               | 'result'  | 'a very long string '
		'inList'               | 'result'  | "x"
		'no'                   | 'date'    | new Date()
		'nullable'             | 'date'    | null
	}
	

	def "Verify Game date is in correct format"() {

		//---------------------- create UUT using a static date
		given:
		def  Game  uut = new Game(date: new Date("1/2/2011"))

		//---------------------- use util class to assert correct error
		when:
		def result = uut.startTime();

		//----------------------
		then:  
		result.equals("12:00 AM")


	}
	
	
	def "Verify Game string is in correct format"() {

		//---------------------- create UUT using a static date
		given:

		def  Game  uut = new Game(date: new Date("1/2/2011"), opponent:new Opponent(name:'Chicos Bail Bonds'))

		//---------------------- 
		when:
		def result = uut.toString();

		//----------------------
		then:
		result.equals("01/02/11 at 12:00 AM vs. Chicos Bail Bonds")


	}



}
