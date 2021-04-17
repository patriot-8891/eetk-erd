package eetk

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class DesignerController {

    DesignerService designerService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond designerService.list(params), model:[designerCount: designerService.count()]
    }

    def show(Long id) {
        respond designerService.get(id)
    }

    def create() {
        respond new Designer(params)
    }

    def save(Designer designer) {
        if (designer == null) {
            notFound()
            return
        }

        try {
            designerService.save(designer)
        } catch (ValidationException e) {
            respond designer.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'designer.label', default: 'Designer'), designer.id])
                redirect designer
            }
            '*' { respond designer, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond designerService.get(id)
    }

    def update(Designer designer) {
        if (designer == null) {
            notFound()
            return
        }

        try {
            designerService.save(designer)
        } catch (ValidationException e) {
            respond designer.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'designer.label', default: 'Designer'), designer.id])
                redirect designer
            }
            '*'{ respond designer, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        designerService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'designer.label', default: 'Designer'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'designer.label', default: 'Designer'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
