package io.github.edgardobarriam.kotlin_for_android_developers

import io.github.edgardobarriam.kotlin_for_android_developers.extensions.toDateString
import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.DateFormat

class ExtensionTests {

    @Test fun testLongToDateString() {
        assertEquals("19-10-2015", 1445275635000L.toDateString())
    }

    @Test fun testDateStringFullFormat() {
        assertEquals("lunes 19 de octubre de 2015",
                1445275635000L.toDateString(DateFormat.FULL))
    }
}