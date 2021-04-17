package eetk

import grails.gorm.services.Service

@Service(Engine)
interface EngineService {

    Engine get(Serializable id)

    List<Engine> list(Map args)

    Long count()

    void delete(Serializable id)

    Engine save(Engine engine)

    Engine findByEngineType(String engineType)

}