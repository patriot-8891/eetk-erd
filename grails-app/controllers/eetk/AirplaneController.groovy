package eetk

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class AirplaneController {

    AirplaneService airplaneService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond airplaneService.list(params), model:[airplaneCount: airplaneService.count()]
    }

    def show(Long id) {
        respond airplaneService.get(id)
    }

    def create() {
        respond new Airplane(params)
    }

    def save(Airplane airplane) {
        if (airplane == null) {
            notFound()
            return
        }

        try {
            airplaneService.save(airplane)
        } catch (ValidationException e) {
            respond airplane.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'airplane.label', default: 'Airplane'), airplane.id])
                redirect airplane
            }
            '*' { respond airplane, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond airplaneService.get(id)
    }

    def update(Airplane airplane) {
        if (airplane == null) {
            notFound()
            return
        }

        try {
            airplaneService.save(airplane)
        } catch (ValidationException e) {
            respond airplane.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'airplane.label', default: 'Airplane'), airplane.id])
                redirect airplane
            }
            '*'{ respond airplane, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        airplaneService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'airplane.label', default: 'Airplane'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'airplane.label', default: 'Airplane'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
