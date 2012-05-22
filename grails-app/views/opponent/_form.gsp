<%@ page import="teamtracker.Opponent" %>



<div class="fieldcontain ${hasErrors(bean: opponentInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="opponent.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${opponentInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: opponentInstance, field: 'notes', 'error')} ">
	<label for="notes">
		<g:message code="opponent.notes.label" default="Notes" />
		
	</label>
	<g:textArea name="notes" cols="40" rows="5" maxlength="500" value="${opponentInstance?.notes}"/>
</div>

