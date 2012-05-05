package teamtracker

import spock.lang.Specification

// down loaded from GIT  ctoestreich / grails-spock-constraints
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

	void validateConstraints(obj, field, error) {
		def validated = obj.validate()
		if (error && error != 'valid') {
			assert !validated
			assert obj.errors[field]
			assert error == obj.errors[field]
		} else {
			assert !obj.errors[field]
		}
	}
}
