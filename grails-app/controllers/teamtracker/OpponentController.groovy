package teamtracker

import org.springframework.dao.DataIntegrityViolationException

class OpponentController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [opponentInstanceList: Opponent.list(params), opponentInstanceTotal: Opponent.count()]
    }

    def create() {
        [opponentInstance: new Opponent(params)]
    }

    def save() {
        def opponentInstance = new Opponent(params)
        if (!opponentInstance.save(flush: true)) {
            render(view: "create", model: [opponentInstance: opponentInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'opponent.label', default: 'Opponent'), opponentInstance.id])
        redirect(action: "show", id: opponentInstance.id)
    }

    def show() {
        def opponentInstance = Opponent.get(params.id)
        if (!opponentInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'opponent.label', default: 'Opponent'), params.id])
            redirect(action: "list")
            return
        }

        [opponentInstance: opponentInstance]
    }

    def edit() {
        def opponentInstance = Opponent.get(params.id)
        if (!opponentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'opponent.label', default: 'Opponent'), params.id])
            redirect(action: "list")
            return
        }

        [opponentInstance: opponentInstance]
    }

    def update() {
        def opponentInstance = Opponent.get(params.id)
        if (!opponentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'opponent.label', default: 'Opponent'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (opponentInstance.version > version) {
                opponentInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'opponent.label', default: 'Opponent')] as Object[],
                          "Another user has updated this Opponent while you were editing")
                render(view: "edit", model: [opponentInstance: opponentInstance])
                return
            }
        }

        opponentInstance.properties = params

        if (!opponentInstance.save(flush: true)) {
            render(view: "edit", model: [opponentInstance: opponentInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'opponent.label', default: 'Opponent'), opponentInstance.id])
        redirect(action: "show", id: opponentInstance.id)
    }

    def delete() {
        def opponentInstance = Opponent.get(params.id)
        if (!opponentInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'opponent.label', default: 'Opponent'), params.id])
            redirect(action: "list")
            return
        }

        try {
            opponentInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'opponent.label', default: 'Opponent'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'opponent.label', default: 'Opponent'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
