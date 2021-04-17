package eetk

import grails.gorm.services.Service

@Service(Airplane)
interface AirplaneService {

    Airplane get(Serializable id)

    List<Airplane> list(Map args)

    Long count()

    void delete(Serializable id)

    Airplane save(Airplane airplane)

    Airplane findByBrand(String brand)

}