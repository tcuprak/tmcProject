<%@ page import="teamtracker.Player" %>



<div class="fieldcontain ${hasErrors(bean: playerInstance, field: 'firstName', 'error')} ">
	<label for="firstName">
		<g:message code="player.firstName.label" default="First Name" />
		
	</label>
	<g:textField name="firstName" value="${playerInstance?.firstName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerInstance, field: 'lastName', 'error')} ">
	<label for="lastName">
		<g:message code="player.lastName.label" default="Last Name" />
		
	</label>
	<g:textField name="lastName" value="${playerInstance?.lastName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerInstance, field: 'playerType', 'error')} ">
	<label for="playerType">
		<g:message code="player.playerType.label" default="Player Type" />
		
	</label>
	<g:select name="playerType" from="${playerInstance.constraints.playerType.inList}" value="${playerInstance?.playerType}" valueMessagePrefix="player.playerType" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerInstance, field: 'phone', 'error')} ">
	<label for="phone">
		<g:message code="player.phone.label" default="Phone" />
		
	</label>
	<g:textField name="phone" value="${playerInstance?.phone}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="player.email.label" default="Email" />
		
	</label>
	<g:field type="email" name="email" value="${playerInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerInstance, field: 'playerNotes', 'error')} ">
	<label for="playerNotes">
		<g:message code="player.playerNotes.label" default="Player Notes" />
		
	</label>
	<g:textArea name="playerNotes" cols="40" rows="5" maxlength="1000" value="${playerInstance?.playerNotes}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerInstance, field: 'dateJoined', 'error')} ">
	<label for="dateJoined">
		<g:message code="player.dateJoined.label" default="Date Joined" />
		
	</label>
	<g:datePicker name="dateJoined" precision="day"  value="${playerInstance?.dateJoined}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: playerInstance, field: 'birthday', 'error')} ">
	<label for="birthday">
		<g:message code="player.birthday.label" default="Birthday" />
		
	</label>
	<g:datePicker name="birthday" precision="day"  value="${playerInstance?.birthday}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: playerInstance, field: 'gameStatus', 'error')} ">
	<label for="gameStatus">
		<g:message code="player.gameStatus.label" default="Game Status" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${playerInstance?.gameStatus?}" var="g">
    <li><g:link controller="playerGameStatus" action="show" id="${g.id}">${g?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="playerGameStatus" action="create" params="['player.id': playerInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'playerGameStatus.label', default: 'PlayerGameStatus')])}</g:link>
</li>
</ul>

</div>

