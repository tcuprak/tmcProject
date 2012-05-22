
<%@ page import="teamtracker.Game" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'game.label', default: 'Game')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-game" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-game" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="date" title="${message(code: 'game.date.label', default: 'Date')}" />
					
						<th><g:message code="game.opponent.label" default="Opponent" /></th>
					
						<g:sortableColumn property="location" title="${message(code: 'game.location.label', default: 'Location')}" />
					
						<g:sortableColumn property="field" title="${message(code: 'game.field.label', default: 'Field')}" />
					
						<g:sortableColumn property="weAreHomeTeam" title="${message(code: 'game.weAreHomeTeam.label', default: 'We Are Home Team')}" />
					
						<g:sortableColumn property="result" title="${message(code: 'game.result.label', default: 'Result')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${gameInstanceList}" status="i" var="gameInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${gameInstance.id}">${fieldValue(bean: gameInstance, field: "date")}</g:link></td>
					
						<td>${fieldValue(bean: gameInstance, field: "opponent")}</td>
					
						<td>${fieldValue(bean: gameInstance, field: "location")}</td>
					
						<td>${fieldValue(bean: gameInstance, field: "field")}</td>
					
						<td><g:formatBoolean boolean="${gameInstance.weAreHomeTeam}" /></td>
					
						<td>${fieldValue(bean: gameInstance, field: "result")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${gameInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
