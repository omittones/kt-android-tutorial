package com.example.console

data class Person(val firstName: String, val lastName: String) {
    fun speak() {
        println("my name is $firstName $lastName")
    }
}

fun main() {

    // functions and string interpolation
    val result = add(1, 2)
    println("result is $result")

    // range
    val range = 1..10
    for (i in range) {
        println("for looping $i")
    }

    val numbers = arrayOf("one", "two", "three", "four", "five", "six", "seven", null, null, null)
    println("one" in numbers)

    val justNumbers = numbers.filterNotNull()

    val one  = 1
    println("one has type: ${one.javaClass.name}")

    val anyOne : Any = 1
    println("anyOne has type: ${anyOne.javaClass.name}")

    //var oneAgain: Int = anyOne

    val person = Person("Max", "Headrome")
    person.speak()
}

fun add(a: Int, b: Int) = a + b

fun showString(string: String) {
    println(string)
}

