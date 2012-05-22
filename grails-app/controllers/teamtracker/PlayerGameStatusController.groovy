package teamtracker

import org.springframework.dao.DataIntegrityViolationException

class PlayerGameStatusController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

	


    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [playerGameStatusInstanceList: PlayerGameStatus.list(params), playerGameStatusInstanceTotal: PlayerGameStatus.count()]
    }

    def create() {
        [playerGameStatusInstance: new PlayerGameStatus(params)]
    }

    def save() {
        def playerGameStatusInstance = new PlayerGameStatus(params)
        if (!playerGameStatusInstance.save(flush: true)) {
            render(view: "create", model: [playerGameStatusInstance: playerGameStatusInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'playerGameStatus.label', default: 'PlayerGameStatus'), playerGameStatusInstance.id])
        redirect(action: "show", id: playerGameStatusInstance.id)
    }

    def show() {
        def playerGameStatusInstance = PlayerGameStatus.get(params.id)
        if (!playerGameStatusInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'playerGameStatus.label', default: 'PlayerGameStatus'), params.id])
            redirect(action: "list")
            return
        }

        [playerGameStatusInstance: playerGameStatusInstance]
    }

    def edit() {
        def playerGameStatusInstance = PlayerGameStatus.get(params.id)
        if (!playerGameStatusInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'playerGameStatus.label', default: 'PlayerGameStatus'), params.id])
            redirect(action: "list")
            return
        }

        [playerGameStatusInstance: playerGameStatusInstance]
    }

    def update() {
        def playerGameStatusInstance = PlayerGameStatus.get(params.id)
        if (!playerGameStatusInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'playerGameStatus.label', default: 'PlayerGameStatus'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (playerGameStatusInstance.version > version) {
                playerGameStatusInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'playerGameStatus.label', default: 'PlayerGameStatus')] as Object[],
                          "Another user has updated this PlayerGameStatus while you were editing")
                render(view: "edit", model: [playerGameStatusInstance: playerGameStatusInstance])
                return
            }
        }

        playerGameStatusInstance.properties = params

        if (!playerGameStatusInstance.save(flush: true)) {
            render(view: "edit", model: [playerGameStatusInstance: playerGameStatusInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'playerGameStatus.label', default: 'PlayerGameStatus'), playerGameStatusInstance.id])
        redirect(action: "show", id: playerGameStatusInstance.id)
    }

    def delete() {
        def playerGameStatusInstance = PlayerGameStatus.get(params.id)
        if (!playerGameStatusInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'playerGameStatus.label', default: 'PlayerGameStatus'), params.id])
            redirect(action: "list")
            return
        }

        try {
            playerGameStatusInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'playerGameStatus.label', default: 'PlayerGameStatus'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'playerGameStatus.label', default: 'PlayerGameStatus'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
