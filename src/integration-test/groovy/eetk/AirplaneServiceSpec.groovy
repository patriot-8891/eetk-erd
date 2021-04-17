package eetk

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class AirplaneServiceSpec extends Specification {

    AirplaneService airplaneService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Airplane(...).save(flush: true, failOnError: true)
        //new Airplane(...).save(flush: true, failOnError: true)
        //Airplane airplane = new Airplane(...).save(flush: true, failOnError: true)
        //new Airplane(...).save(flush: true, failOnError: true)
        //new Airplane(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //airplane.id
    }

    void "test get"() {
        setupData()

        expect:
        airplaneService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Airplane> airplaneList = airplaneService.list(max: 2, offset: 2)

        then:
        airplaneList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        airplaneService.count() == 5
    }

    void "test delete"() {
        Long airplaneId = setupData()

        expect:
        airplaneService.count() == 5

        when:
        airplaneService.delete(airplaneId)
        sessionFactory.currentSession.flush()

        then:
        airplaneService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Airplane airplane = new Airplane()
        airplaneService.save(airplane)

        then:
        airplane.id != null
    }
}
