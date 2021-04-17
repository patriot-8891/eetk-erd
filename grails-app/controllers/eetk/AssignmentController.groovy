package eetk

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class AssignmentController {

    AssignmentService assignmentService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond assignmentService.list(params), model:[assignmentCount: assignmentService.count()]
    }

    def show(Long id) {
        respond assignmentService.get(id)
    }

    def create() {
        respond new Assignment(params)
    }

    def save(Assignment assignment) {
        if (assignment == null) {
            notFound()
            return
        }

        try {
            assignmentService.save(assignment)
        } catch (ValidationException e) {
            respond assignment.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'assignment.label', default: 'Assignment'), assignment.id])
                redirect assignment
            }
            '*' { respond assignment, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond assignmentService.get(id)
    }

    def update(Assignment assignment) {
        if (assignment == null) {
            notFound()
            return
        }

        try {
            assignmentService.save(assignment)
        } catch (ValidationException e) {
            respond assignment.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'assignment.label', default: 'Assignment'), assignment.id])
                redirect assignment
            }
            '*'{ respond assignment, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        assignmentService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'assignment.label', default: 'Assignment'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'assignment.label', default: 'Assignment'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
