
<%@ page import="teamtracker.Player" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'player.label', default: 'Player')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-player" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-player" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list player">
			
				<g:if test="${playerInstance?.firstName}">
				<li class="fieldcontain">
					<span id="firstName-label" class="property-label"><g:message code="player.firstName.label" default="First Name" /></span>
					
						<span class="property-value" aria-labelledby="firstName-label"><g:fieldValue bean="${playerInstance}" field="firstName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${playerInstance?.lastName}">
				<li class="fieldcontain">
					<span id="lastName-label" class="property-label"><g:message code="player.lastName.label" default="Last Name" /></span>
					
						<span class="property-value" aria-labelledby="lastName-label"><g:fieldValue bean="${playerInstance}" field="lastName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${playerInstance?.playerType}">
				<li class="fieldcontain">
					<span id="playerType-label" class="property-label"><g:message code="player.playerType.label" default="Player Type" /></span>
					
						<span class="property-value" aria-labelledby="playerType-label"><g:fieldValue bean="${playerInstance}" field="playerType"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${playerInstance?.phone}">
				<li class="fieldcontain">
					<span id="phone-label" class="property-label"><g:message code="player.phone.label" default="Phone" /></span>
					
						<span class="property-value" aria-labelledby="phone-label"><g:fieldValue bean="${playerInstance}" field="phone"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${playerInstance?.email}">
				<li class="fieldcontain">
					<span id="email-label" class="property-label"><g:message code="player.email.label" default="Email" /></span>
					
						<span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${playerInstance}" field="email"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${playerInstance?.playerNotes}">
				<li class="fieldcontain">
					<span id="playerNotes-label" class="property-label"><g:message code="player.playerNotes.label" default="Player Notes" /></span>
					
						<span class="property-value" aria-labelledby="playerNotes-label"><g:fieldValue bean="${playerInstance}" field="playerNotes"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${playerInstance?.dateJoined}">
				<li class="fieldcontain">
					<span id="dateJoined-label" class="property-label"><g:message code="player.dateJoined.label" default="Date Joined" /></span>
					
						<span class="property-value" aria-labelledby="dateJoined-label"><g:formatDate date="${playerInstance?.dateJoined}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${playerInstance?.birthday}">
				<li class="fieldcontain">
					<span id="birthday-label" class="property-label"><g:message code="player.birthday.label" default="Birthday" /></span>
					
						<span class="property-value" aria-labelledby="birthday-label"><g:formatDate date="${playerInstance?.birthday}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${playerInstance?.gameStatus}">
				<li class="fieldcontain">
					<span id="gameStatus-label" class="property-label"><g:message code="player.gameStatus.label" default="Game Status" /></span>
					
						<g:each in="${playerInstance.gameStatus}" var="g">
						<span class="property-value" aria-labelledby="gameStatus-label"><g:link controller="playerGameStatus" action="show" id="${g.id}">${g?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${playerInstance?.id}" />
					<g:link class="edit" action="edit" id="${playerInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
