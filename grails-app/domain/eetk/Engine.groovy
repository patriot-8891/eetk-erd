package eetk

// Тип двигателя
class Engine {

    String engineType

    static constraints = {
        engineType blank: false, nullable: false, unique: true
    }

    static mapping = {
        version false
        autoTimestamp false
    }

    @Override
    String toString() {
        if (engineType)
            return engineType
        else
            return super.toString()
    }
}
