fun main(args: Array<String>) {
    println("Hello World!")
    println(dodaj(3,4))
}

fun dodaj(x: Int, y: Int) = x + y

fun foo(x: Int) : Int{
    return x * x
}

var zmienna: Int = 0
val stala: Int = 5
var nullable: String? = "hello world"
var nonnullable: String = "hello world"

fun xd() {
  //  nonnullable = null - nie mozna
    nullable = null
}

fun foo2(x: String?) {
    if(zmienna != null) {

    }
}

class Student(val imie: String, val nazwisko: String)
val student1 = Student("wojtek", "szlosek")

open class Pies {
 open fun szczekaj() {
     println("hau hau")
 }

}

class OwczarekNiemiecki: Pies() {
    override fun szczekaj() {
        println("wuf wuf")
    }
}


fun petle(args: Array<String>) {

    var it = 0
    while (args[it].length < 5) {
        it++
    }

    var range = 0..5
    var range2 = 0 until 5
    var range3 = 'a'..'r' step 2

    for (x in range) {

    }

    do {

    }while (1<3)
}

var studenci1 = setOf<String>("Kowalski", "Nowak", "Kowalczyk", "Psikuta")
var studenci2 = setOf<String>("Kowalczyk", "Psikuta", "Kowalski", "Nowak")

fun foo3() {
    println(studenci1 == studenci2)
    println(studenci1 === studenci2)
}

fun max(x: Int, y: Int) = if (x > y) x else y

object Student() {
    fun spac() {
        println("Zzzzz")
    }
}

Student.spac()

