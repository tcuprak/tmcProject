package teamtracker

import org.grails.datastore.mapping.engine.event.AbstractPersistenceEventListener
import org.grails.datastore.mapping.engine.event.AbstractPersistenceEvent

abstract class GormUtil extends AbstractPersistenceEventListener {

	
	@Override
	protected void onPersistenceEvent(final AbstractPersistenceEvent event) {
		switch(event.eventType) {
			case PreInsert:
				println "PRE INSERT ${event.entityObject}"
			break
			case PostInsert:
				println "POST INSERT ${event.entityObject}"
			break
			case PreUpdate:
				println "PRE UPDATE ${event.entityObject}"
			break;
			case PostUpdate:
				println "POST UPDATE ${event.entityObject}"
			break;
			case PreDelete:
				println "PRE DELETE ${event.entityObject}"
			break;
			case PostDelete:
				println "POST DELETE ${event.entityObject}"
			break;
			case PreLoad:
				println "PRE LOAD ${event.entityObject}"
			break;
			case PostLoad:
				println "POST LOAD ${event.entityObject}"
			break;
		}
	}
}
