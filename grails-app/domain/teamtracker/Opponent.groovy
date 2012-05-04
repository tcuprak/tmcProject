package teamtracker

class Opponent {

    String name
    String notes


    String toString(){ "$name" }
    static constraints = {
    name(nullable:false,blank:false)
    notes(nullable:true, blank:true, maxSize:500)
    }
}
