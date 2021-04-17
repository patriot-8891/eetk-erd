package eetk

// Конструктор
class Designer {

    String name

    static constraints = {
        name blank: false, nullable: false, unique: true
    }

    static mapping = {
        version false
        autoTimestamp false
    }

    @Override
    String toString() {
        if (name)
            return name
        else
            return super.toString()
    }
}
