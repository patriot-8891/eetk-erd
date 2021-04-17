package eetk

import grails.gorm.services.Service

@Service(Designer)
interface DesignerService {

    Designer get(Serializable id)

    List<Designer> list(Map args)

    Long count()

    void delete(Serializable id)

    Designer save(Designer designer)

    Designer findByName(String name)

}