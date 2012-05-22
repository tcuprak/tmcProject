
<%@ page import="teamtracker.Game" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'game.label', default: 'Game')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-game" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-game" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list game">
			
				<g:if test="${gameInstance?.date}">
				<li class="fieldcontain">
					<span id="date-label" class="property-label"><g:message code="game.date.label" default="Date" /></span>
					
						<span class="property-value" aria-labelledby="date-label"><g:formatDate date="${gameInstance?.date}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.opponent}">
				<li class="fieldcontain">
					<span id="opponent-label" class="property-label"><g:message code="game.opponent.label" default="Opponent" /></span>
					
						<span class="property-value" aria-labelledby="opponent-label"><g:link controller="opponent" action="show" id="${gameInstance?.opponent?.id}">${gameInstance?.opponent?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.location}">
				<li class="fieldcontain">
					<span id="location-label" class="property-label"><g:message code="game.location.label" default="Location" /></span>
					
						<span class="property-value" aria-labelledby="location-label"><g:fieldValue bean="${gameInstance}" field="location"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.field}">
				<li class="fieldcontain">
					<span id="field-label" class="property-label"><g:message code="game.field.label" default="Field" /></span>
					
						<span class="property-value" aria-labelledby="field-label"><g:fieldValue bean="${gameInstance}" field="field"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.weAreHomeTeam}">
				<li class="fieldcontain">
					<span id="weAreHomeTeam-label" class="property-label"><g:message code="game.weAreHomeTeam.label" default="We Are Home Team" /></span>
					
						<span class="property-value" aria-labelledby="weAreHomeTeam-label"><g:formatBoolean boolean="${gameInstance?.weAreHomeTeam}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.result}">
				<li class="fieldcontain">
					<span id="result-label" class="property-label"><g:message code="game.result.label" default="Result" /></span>
					
						<span class="property-value" aria-labelledby="result-label"><g:fieldValue bean="${gameInstance}" field="result"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.notes}">
				<li class="fieldcontain">
					<span id="notes-label" class="property-label"><g:message code="game.notes.label" default="Notes" /></span>
					
						<span class="property-value" aria-labelledby="notes-label"><g:fieldValue bean="${gameInstance}" field="notes"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.playerStatus}">
				<li class="fieldcontain">
					<span id="playerStatus-label" class="property-label"><g:message code="game.playerStatus.label" default="Player Status" /></span>
					
						<g:each in="${gameInstance.playerStatus}" var="p">
						<span class="property-value" aria-labelledby="playerStatus-label"><g:link controller="playerGameStatus" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${gameInstance?.id}" />
					<g:link class="edit" action="edit" id="${gameInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
