package eetk

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class DesignerServiceSpec extends Specification {

    DesignerService designerService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Designer(...).save(flush: true, failOnError: true)
        //new Designer(...).save(flush: true, failOnError: true)
        //Designer designer = new Designer(...).save(flush: true, failOnError: true)
        //new Designer(...).save(flush: true, failOnError: true)
        //new Designer(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //designer.id
    }

    void "test get"() {
        setupData()

        expect:
        designerService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Designer> designerList = designerService.list(max: 2, offset: 2)

        then:
        designerList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        designerService.count() == 5
    }

    void "test delete"() {
        Long designerId = setupData()

        expect:
        designerService.count() == 5

        when:
        designerService.delete(designerId)
        sessionFactory.currentSession.flush()

        then:
        designerService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Designer designer = new Designer()
        designerService.save(designer)

        then:
        designer.id != null
    }
}
