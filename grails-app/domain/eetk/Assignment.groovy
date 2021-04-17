package eetk

// Назначение
class Assignment {

    String title

    static constraints = {
        title blank: false, nullable: false, unique: true
    }

    static mapping = {
        version false
        autoTimestamp false
    }

    @Override
    String toString() {
        if (title)
            return title
        else
            return super.toString()
    }
}
