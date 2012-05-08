package teamtracker

import spock.lang.Specification

// utility class originally down loaded from Github  ctoestreich / grails-spock-constraints
abstract class ConstraintUnitSpec extends Specification {

	String getLongString(Integer length) {
		def longString = ""
		length.times { longString += "a" }
		longString
	}

	String getEmail(Boolean valid) {
		valid ? "dexter@miamipd.gov" : "dexterm@m"
	}

	String getUrl(Boolean valid) {
		valid ? "http://www.google.com" : "http:/ww.helloworld.com"
	}

	String getCreditCard(Boolean valid) {
		valid ? "4111111111111111" : "41014"
	}

	void checkExpectedError(obj, field, error) {
		def validated = obj.validate()
		
		// did we get the expected error?
		if (error && error != 'no') {
			assert !validated
			assert obj.errors[field]
			assert error == obj.errors[field]
	    // are we error free?		
		} else {
			assert !obj.errors[field]
		}
	}
}
