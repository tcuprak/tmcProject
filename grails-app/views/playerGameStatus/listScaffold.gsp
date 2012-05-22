
<%@ page import="teamtracker.PlayerGameStatus" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'playerGameStatus.label', default: 'PlayerGameStatus')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-playerGameStatus" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-playerGameStatus" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="playerGameStatus.game.label" default="Game" /></th>
					
						<th><g:message code="playerGameStatus.player.label" default="Player" /></th>
					
						<g:sortableColumn property="status" title="${message(code: 'playerGameStatus.status.label', default: 'Status')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${playerGameStatusInstanceList}" status="i" var="playerGameStatusInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${playerGameStatusInstance.id}">${fieldValue(bean: playerGameStatusInstance, field: "game")}</g:link></td>
					
						<td>${fieldValue(bean: playerGameStatusInstance, field: "player")}</td>
					
						<td>${fieldValue(bean: playerGameStatusInstance, field: "status")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${playerGameStatusInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
