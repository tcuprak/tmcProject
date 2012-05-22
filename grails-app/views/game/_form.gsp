<%@ page import="teamtracker.Game" %>



<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'date', 'error')} required">
	<label for="date">
		<g:message code="game.date.label" default="Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="date" precision="day"  value="${gameInstance?.date}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'opponent', 'error')} ">
	<label for="opponent">
		<g:message code="game.opponent.label" default="Opponent" />
		
	</label>
	<g:select id="opponent" name="opponent.id" from="${teamtracker.Opponent.list()}" optionKey="id" value="${gameInstance?.opponent?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'location', 'error')} ">
	<label for="location">
		<g:message code="game.location.label" default="Location" />
		
	</label>
	<g:textField name="location" value="${gameInstance?.location}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'field', 'error')} ">
	<label for="field">
		<g:message code="game.field.label" default="Field" />
		
	</label>
	<g:textField name="field" value="${gameInstance?.field}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'weAreHomeTeam', 'error')} ">
	<label for="weAreHomeTeam">
		<g:message code="game.weAreHomeTeam.label" default="We Are Home Team" />
		
	</label>
	<g:checkBox name="weAreHomeTeam" value="${gameInstance?.weAreHomeTeam}" />
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'result', 'error')} ">
	<label for="result">
		<g:message code="game.result.label" default="Result" />
		
	</label>
	<g:select name="result" from="${gameInstance.constraints.result.inList}" value="${gameInstance?.result}" valueMessagePrefix="game.result" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'notes', 'error')} ">
	<label for="notes">
		<g:message code="game.notes.label" default="Notes" />
		
	</label>
	<g:textField name="notes" value="${gameInstance?.notes}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'playerStatus', 'error')} ">
	<label for="playerStatus">
		<g:message code="game.playerStatus.label" default="Player Status" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${gameInstance?.playerStatus?}" var="p">
    <li><g:link controller="playerGameStatus" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="playerGameStatus" action="create" params="['game.id': gameInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'playerGameStatus.label', default: 'PlayerGameStatus')])}</g:link>
</li>
</ul>

</div>

