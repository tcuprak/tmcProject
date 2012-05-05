package teamtracker


import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import grails.buildtestdata.mixin.Build


/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
@Build(teamtracker.Game)
class GameTests {

    void setUp() {
        // Setup logic here
    }

    void tearDown() {
        // Tear down logic here
    }

   @Test public void testCreateValidGame() {
       def uut =Game.build(date: new Date("01/01/2012"))
	   System.out.println(uut);
	   assert(uut.toString().equals("01/01/12 at 12:00 AM vs. null"))

	   
    }
}

