package eetk

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class EngineServiceSpec extends Specification {

    EngineService engineService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Engine(...).save(flush: true, failOnError: true)
        //new Engine(...).save(flush: true, failOnError: true)
        //Engine engine = new Engine(...).save(flush: true, failOnError: true)
        //new Engine(...).save(flush: true, failOnError: true)
        //new Engine(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //engine.id
    }

    void "test get"() {
        setupData()

        expect:
        engineService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Engine> engineList = engineService.list(max: 2, offset: 2)

        then:
        engineList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        engineService.count() == 5
    }

    void "test delete"() {
        Long engineId = setupData()

        expect:
        engineService.count() == 5

        when:
        engineService.delete(engineId)
        sessionFactory.currentSession.flush()

        then:
        engineService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Engine engine = new Engine()
        engineService.save(engine)

        then:
        engine.id != null
    }
}
