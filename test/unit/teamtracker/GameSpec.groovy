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
	
	
	@Unroll()
    def "test Game all constraints #field is #error"() {
		
        when:
        def obj = new Game("$field": val)

        then:
        validateConstraints(obj, field, error)

        where:
        error                  | field     | val
		'nullable'             | 'result'  | null
		'valid'                | 'result'  | 'scheduled'
		'inList'               | 'result'  | 'unknown'
        
    }

   
}
