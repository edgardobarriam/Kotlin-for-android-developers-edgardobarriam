package io.github.edgardobarriam.kotlin_for_android_developers

fun nullSafetyExamples() {

    /* Kotlin makes use of question marks "?" to identify nullable types.
       This means that if a variable can be null, we have to deal with it in some way, otherwise
       it won't compile. */

    /* Everything in Kotlin is an object, even Java primitive types, so for example
       we can have a nullable integer: */

    val myNumber: Int? = null // The question mark indicates that myNumber can be null

    /* you can't work directly with nullable types without checking before. The following code
       won't compile: */

    val a: Int? = null
    // a.toLong() - We can't use a nullable type if we don't check nullity

    /* Kotlin has a feature called smart cast: If we check the nullity of an object, from that moment
       the object is automatically casted to it's non-nullable type. This only works on variables
       that can't be concurrently modified (from other threads for example). Smart cast is
       supported on val properties or local (val or var) variables. */

    val b: Int? = null

    if (b != null) {
        b.toLong() /* Kotlin automatically casts "b" to Int.
                      From this point we don't have to check nullity inside the if condition */
    }

    /* We can simplify the previous example even more: */
    val c: Int? = null
    c?.toLong() /* Here we are using the safe call operator (?.), this line will be executed only
                   if the variable is not null */

    val myLong = c?.toLong() ?: 42L /* We can also provide an alternative value in case "c" is null
                                       by using the elvis operator (?:) */

    val myFloat = c?.toFloat() ?: throw IllegalStateException() /* We can even use expressions
                                                                   with elvis operators */

    /* The !! operator forces the compiler to skip nullable restriction. This can be useful if
     we know for sure that the variable is non-nullable. However, we should avoid this operator
     as much as possible, try to use other solutions before using !!. */

    val i: Int? = null
    a!!.toLong() /* As you can see, in this case we are not checking for null situation.
                    The compiler doesn't indicate an error, but this will obviously
                    crash at runtime.
                 */
}
