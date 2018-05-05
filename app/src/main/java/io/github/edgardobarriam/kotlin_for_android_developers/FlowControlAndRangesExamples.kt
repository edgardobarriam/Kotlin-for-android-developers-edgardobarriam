package io.github.edgardobarriam.kotlin_for_android_developers

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

fun flowControlAndRanges() {


    /* "If" expression */

    // We can use "if" as usual:
    val x = 4

    if (x > 1) {
        println("x is greater than 1")
    } else if (x == 1) {
        println("x equals 1")
    } else {
        println("x is smaller than 1")
    }

    // Also, we can use "if" to assign a result to a variable (similar to Java ternary operator)
    val numbers = listOf(1, 2, 6, 7)

    val res: String = if (numbers != null && numbers.size >= 4) "first result" else "second result"
    // Previous expression returns "first result" and assigns it to res

    /* "if" expressions always returns a value. In the first example, conditions are returning
       Unit, which can be ignored. */

    /* "When" expression: Similar to Java's "switch/case", but with more utilities. */

    val y = 0

    when (y) {
        1 -> println("y == 1")
        2 -> println("y == 2")
        3 -> {
            println("We can use code blocks too")
            println("y == 0")
        }

        else -> {
            println("else is executed as a default value (if none of the conditions are satisfied)")
        }
    }

    /* All of the Kotlin expressions can return a result, but the expression has to cover
       all possible cases, otherwise it won't compile. Returning a result using "when": */
    val result: String = when (y) {
        0, 1 -> "binary" // the condition can be a set of values, in this case 0 or 1
        else -> "other value"
    }

    // "when" expressions can check the type of the argument too
    val view: View = View(Activity())

    // Kotlin will automatically cast the argument to its corresponding type
    when (view) {
        is TextView -> view.text = "It's a TextView!"
        is EditText -> view.hint = "Your input goes here"
        is ViewGroup -> println("Number of children: ${view.childCount}")
        else -> view.visibility = View.GONE
    }

    // We can also use "when" to check if the argument is inside a range:
    val number = 999
    val specialNumbers = listOf(7, 42, 420, 1984)

    val rangeString = when (number) {
        in 1..10 -> "from 1 to 10"
        in 10..100 -> "from 10 to 100"
        in 100..1000 -> "from 100 to 1000"
        in specialNumbers -> "it's a special number!" // We can even check if it's in a collection
        else -> "none of the expected ranges"
    }

    /* We can use "when" without an argument, it gives the expression even more flexibility:
       ("when" expressions checks until it finds the first successful case) */

    val resultado = when {
        specialNumbers.contains(2018) -> "2018 is the year of the dog"
        x in 1..10 -> "between 1 and 10"
        view is ImageView -> "wow, view was an ImageView"
        else ->  "lorem ipsum"
    }

    /* "For" loops: Even though Kotlin provides great operators for iterables, "for" loops
     * can be useful in some situations: */

    val names = listOf("Albert", "Bob", "Charlie")

    for (name in names) {
        println(name)
    }

    // We can use a for loop to iterate over indices
    val animals = listOf("dog", "cat", "parrot", "iguana")
    for (index in 0..animals.size - 1) {
        println(animals[index])
    }

    // The previous example can be simplified by requesting indices directly
    for(i in animals.indices) {
        println(animals[i])
    }



    /* "While" and "Do while" loops: Not very common in Kotlin, usually there are
    *  more expressive ways to solve problems of this nature: */
    var count = 0

    while (x < 10) {
        count++
    }

    do {
        count--
    } while (count > 1)


    /* Ranges: To define a range, we use the ".." operator. They are very useful to simplify code: */
    var i = 7

    // See the following code:
    if (i >= 0 && i <=10) {
        print(i)
    }

    // With ranges it can be converted to:
    if (i in 0..10) {
        print(i)
    }

    // Ranges can be used for iterations too:
    for (j in 0..10) {
        println(j)
    }

    // Ranges are incremental by default. If we want to do the opposite we use "downTo"
    for (k in 10 downTo 0) {
        println(k)
    }

    // We can also define a custom step for iterations
    for (even in 2..10 step 2) println(even)

    // To exclude the last value use the "until" keyword
    for (z in 0 until 4) println(z) // Will iterate from 0 to 3

    // "Until" can be useful to iterate over lists
    val lottery = listOf(3, 5, 7, 13, 16, 18, 42)

    for (luckyNumber in 0 until lottery.size) println(luckyNumber)
}