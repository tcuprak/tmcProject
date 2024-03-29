
<%@ page import="teamtracker.Player" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'player.label', default: 'Player')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-player" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-player" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="firstName" title="${message(code: 'player.firstName.label', default: 'First Name')}" />
					
						<g:sortableColumn property="lastName" title="${message(code: 'player.lastName.label', default: 'Last Name')}" />
					
						<g:sortableColumn property="playerType" title="${message(code: 'player.playerType.label', default: 'Player Type')}" />
					
						<g:sortableColumn property="phone" title="${message(code: 'player.phone.label', default: 'Phone')}" />
					
						<g:sortableColumn property="email" title="${message(code: 'player.email.label', default: 'Email')}" />
					
						<g:sortableColumn property="playerNotes" title="${message(code: 'player.playerNotes.label', default: 'Player Notes')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${playerInstanceList}" status="i" var="playerInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${playerInstance.id}">${fieldValue(bean: playerInstance, field: "firstName")}</g:link></td>
					
						<td>${fieldValue(bean: playerInstance, field: "lastName")}</td>
					
						<td>${fieldValue(bean: playerInstance, field: "playerType")}</td>
					
						<td>${fieldValue(bean: playerInstance, field: "phone")}</td>
					
						<td>${fieldValue(bean: playerInstance, field: "email")}</td>
					
						<td>${fieldValue(bean: playerInstance, field: "playerNotes")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${playerInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
