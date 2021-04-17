package eetk


// Спецификация самолета
class Airplane {

    String brand
    String title
    Engine engine
    Assignment assignment
    Designer designer

    static constraints = {
        brand blank: false, nullable: false, unique: true
        title blank: true, nullable: true
        engine blank: false, nullable: false
        assignment blank: false, nullable: false
        designer blank: false, nullable: false
    }

    static mapping = {
        version false
        autoTimestamp false
    }

    @Override
    String toString() {
        if (brand)
            return brand
        else
            return super.toString()
    }
}
