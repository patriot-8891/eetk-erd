package eetk

class BootStrap {

    AirplaneService airplaneService
    AssignmentService assignmentService
    DesignerService designerService
    EngineService engineService

    List<Map<String, String>> airplaneServiceList = [
            [brand: "Ан-124", engine: "Турбовинтовой", assignment: "Транспортный", designer: "Антонов О.К.", title: "Руслан"],
            [brand: "Ан-22", engine: "Турбовинтовой", assignment: "Транспортный", designer: "Антонов О.К.", title: "Антей"],
            [brand: "Ан-24", engine: "Турбовинтовой", assignment: "Пассажирский", designer: "Антонов О.К.", title: "Аннушка"],
            [brand: "И-153", engine: "Винтовой", assignment: "Истребитель", designer: "Поликарпов Н.Н.", title: "Чайка"],
            [brand: "По-2", engine: "Винтовой", assignment: "Ночной бомбардировщик", designer: "Поликарпов Н.Н.", title: "Кукурузник"],
            [brand: "Ту-104", engine: "Реактивный", assignment: "Пассажирский", designer: "Туполев А.Н."],
            [brand: "Ту-144", engine: "Реактивный", assignment: "Пассажирский", designer: "Туполев А.Н."],
            [brand: "Як-40", engine: "Реактивный", assignment: "Пассажирский", designer: "Яковлев А.С."],
            [brand: "Як-50", engine: "Реактивный", assignment: "Учебно-тренировочный", designer: "Яковлев А.С."],
    ]
    List<String> assignmentServiceList = ["Истребитель", "Ночной бомбардировщик", "Пассажирский", "Транспортный", "Учебно-тренировочный"]
    List<String> designerServiceList = ["Антонов О.К.", "Поликарпов Н.Н.", "Туполев А.Н.", "Яковлев А.С."]
    List<String> engineServiceList = ["Винтовой", "Реактивный", "Турбовинтовой"]

    def init = { servletContext ->
        assignmentServiceList.each { assignment ->
            if (!assignmentService.findByTitle(assignment))
                assignmentService.save(new Assignment(title: assignment))
        }
        designerServiceList.each { designer ->
            if (!designerService.findByName(designer))
                designerService.save(new Designer(name: designer))
        }
        engineServiceList.each { engine ->
            if (!engineService.findByEngineType(engine))
                engineService.save(new Engine(engineType: engine))
        }
        airplaneServiceList.each { map ->
            if (!airplaneService.findByBrand(map["brand"]))
                airplaneService.save(
                        new Airplane(
                                brand: map["brand"],
                                title: map["title"],
                                engine: engineService.findByEngineType(map["engine"]),
                                assignment: assignmentService.findByTitle(map["assignment"]),
                                designer: designerService.findByName(map["designer"])
                        )
                )
        }
    }

    def destroy = {
    }
}
