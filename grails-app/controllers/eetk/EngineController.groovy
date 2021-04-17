package eetk

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class EngineController {

    EngineService engineService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond engineService.list(params), model:[engineCount: engineService.count()]
    }

    def show(Long id) {
        respond engineService.get(id)
    }

    def create() {
        respond new Engine(params)
    }

    def save(Engine engine) {
        if (engine == null) {
            notFound()
            return
        }

        try {
            engineService.save(engine)
        } catch (ValidationException e) {
            respond engine.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'engine.label', default: 'Engine'), engine.id])
                redirect engine
            }
            '*' { respond engine, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond engineService.get(id)
    }

    def update(Engine engine) {
        if (engine == null) {
            notFound()
            return
        }

        try {
            engineService.save(engine)
        } catch (ValidationException e) {
            respond engine.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'engine.label', default: 'Engine'), engine.id])
                redirect engine
            }
            '*'{ respond engine, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        engineService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'engine.label', default: 'Engine'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'engine.label', default: 'Engine'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
