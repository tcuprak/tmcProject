
<%@ page import="rostertracker.PlayerGameStatus"%>
<%@ page import="rostertracker.Game"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="main" />
<g:set var="pageName" value="Player Game Status" />

<title><g:message code="default.list.label" args="[pageName]" />
</title>
</head>
<body>
	<div class="nav">
		<span class="menuButton"><a class="home"
			href="${createLink(uri: '/')}"><g:message
					code="default.home.label" /> </a> </span> <span class="menuButton"><g:link
				class="create" action="create">
				<g:message code="default.new.label" args="[pageName]" />
			</g:link> </span>
	</div>
	<div class="body">
		<h1>Who's Coming?</h1>
		<%--  Display error if exists --%>
		<g:if test="${flash.message}">
			<div class="message">
				${flash.message}
			</div>
		</g:if>

		<%--  STart of my table --%>

		<div class="list">
			<table>
				<thead>
					<tr>
						<g:sortableColumn property="id"
							title="${message(code: 'playerGameStatus.id.label', default: 'Player')}" />

						<g:each in="${gameInstanceList}" status="gameIndex" var="nextGame">


							<th><g:formatDate format="MMMM dd" date="${nextGame.date}" />
								at ${nextGame.startTime}
							</th>

						</g:each>
					</tr>

				</thead>
				<tbody>

					<g:each in="${playerInstanceList}" status="playerIndex"
						var="nextPlayer">
						<tr class="${(playerIndex % 2) == 0 ? 'odd' : 'even'}">

							<td><g:link action="show" id="${nextPlayer.id}">
									${fieldValue(bean: nextPlayer, field: "lastName")}, ${fieldValue(bean: nextPlayer, field: "firstName")}
								</g:link>
							</td>


							<g:each in="${playerGameStatusInstanceList}" var="nextStatus">

								<g:if test="${nextStatus.player.id == nextPlayer.id}">

									<g:set var="filename" value="${nextStatus.status}.png" />
									<td><g:select name="nextStatus.status"
											from="${nextStatus.constraints.status.inList}"
											value="${nextStatus?.status}"
											 /> <img
										src="${resource(dir:'images\\icon', file:filename)}"
										alt="${filename}" border="0" />
									</td>


								</g:if>

							</g:each>


						</tr>


					</g:each>
				</tbody>
			</table>
		</div>

		<div class="buttons">
			<span class="button"><g:actionSubmit class="save"
					action="update"
					value="${message(code: 'default.button.update.label', default: 'Update')}" />
			</span>
		</div>
		<div class="paginateButtons">
			<g:paginate total="${playerGameStatusInstanceTotal}" />
		</div>
	</div>
</body>
</html>
