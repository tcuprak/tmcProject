package teamtracker

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*



import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
//@TestMixin(GrailsUnitTestMixin)
@TestFor(Game)
class GameSpec extends ConstraintUnitSpec {

    def setup() {
        //mock a game with some data (put unique violations in here so they can be tested, the others aren't needed)
        mockForConstraintsTests(Game, [new Game(result:"unknown")])
    }
	
	/** Verify that the constraints placed on the creation of the Game domain object are enforced.  Did not check nullable:true  */
	@Unroll()
    def "Verify Game domain constraints: The value '#val' for the field '#field' results in  #error error "() {
		
		
		given:
        def obj = new Game("$field": val)
		

        when:
        checkExpectedError(obj, field, error)
		
		then: "assertion is true"

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

   
}
