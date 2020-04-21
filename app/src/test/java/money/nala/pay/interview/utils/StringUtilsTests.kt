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
    fun toSafeDouble_noAmount() {
        // Given
        val string = "0"

        // When
        val result = string.toSafeDouble()

        // Then
        Assert.assertEquals(0.0, result)
    }

    @Test
    fun toSafeDouble_validAmount01() {
        // Given
        val string = "100"

        // When
        val result = string.toSafeDouble()

        // Then
        Assert.assertEquals(100.0, result)
    }

    @Test
    fun toSafeDouble_validAmount02() {
        // Given
        val string = "1,000"

        // When
        val result = string.toSafeDouble()

        // Then
        Assert.assertEquals(1000.0, result)
    }

    @Test
    fun toSafeDouble_validAmount03() {
        // Given
        val string = "130.24"

        // When
        val result = string.toSafeDouble()

        // Then
        Assert.assertEquals(130.24, result)
    }


    @Test
    fun formatAmountCommas_invalidAmount01() {
        val amount: String? = null

        val result = amount?.formatAmount()

        Assert.assertEquals(null, result)
    }

    @Test
    fun formatAmountCommas_invalidAmount02() {
        val amount = "text"

        val result = amount.formatAmount()

        Assert.assertEquals(null, result)
    }


    @Test
    fun formatAmountCommas_validAmount01() {
        val amount = "10000"

        val result = amount.formatAmount()

        Assert.assertEquals(1, result?.count { it == ',' })
    }

    @Test
    fun formatAmountCommas_validAmount02() {
        val amount = "100000.43"

        val result = amount.formatAmount()

        Assert.assertEquals(1, result?.count { it == ',' })
    }

    @Test
    fun formatAmountCommas_validAmount03() {
        val amount = "1000000.00"

        val result = amount.formatAmount()

        Assert.assertEquals(2, result?.count { it == ',' })
    }

    @Test
    fun formatAmountCommas_validAmount04() {
        val amount = "10,000"

        val result = amount.formatAmount()

        Assert.assertEquals(1, result?.count { it == ',' })
    }

    @Test
    fun formatAmountCommas_validAmount05() {
        val amount = "10,000.233"

        val result = amount.formatAmount()

        Assert.assertEquals(1, result?.count { it == ',' })
    }

}