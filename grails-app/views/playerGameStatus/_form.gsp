<%@ page import="teamtracker.PlayerGameStatus" %>



<div class="fieldcontain ${hasErrors(bean: playerGameStatusInstance, field: 'game', 'error')} required">
	<label for="game">
		<g:message code="playerGameStatus.game.label" default="Game" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="game" name="game.id" from="${teamtracker.Game.list()}" optionKey="id" required="" value="${playerGameStatusInstance?.game?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerGameStatusInstance, field: 'player', 'error')} required">
	<label for="player">
		<g:message code="playerGameStatus.player.label" default="Player" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="player" name="player.id" from="${teamtracker.Player.list()}" optionKey="id" required="" value="${playerGameStatusInstance?.player?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: playerGameStatusInstance, field: 'status', 'error')} ">
	<label for="status">
		<g:message code="playerGameStatus.status.label" default="Status" />
		
	</label>
	<g:select name="status" from="${playerGameStatusInstance.constraints.status.inList}" value="${playerGameStatusInstance?.status}" valueMessagePrefix="playerGameStatus.status" noSelection="['': '']"/>
</div>

