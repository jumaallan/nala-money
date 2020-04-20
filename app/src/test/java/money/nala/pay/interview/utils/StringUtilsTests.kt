package money.nala.pay.interview.utils

import org.junit.Assert
import org.junit.Test

class StringUtilsTests {

    @Test
    fun toSafeDouble_blankString() {
        // Given
        val string = ""

        // When
        val result = string.toSafeDouble()

        // Then
        Assert.assertEquals(null, result)
    }

    @Test
    fun formatAmountCommas_invalidAmount01() {

    }

}