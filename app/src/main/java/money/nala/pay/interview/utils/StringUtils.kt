@file:JvmName("StringUtils")

package money.nala.pay.interview.utils

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import money.nala.pay.interview.NalaApp
import java.text.NumberFormat
import java.util.*

/**
 * Safely convert the string to the double.
 */
fun String.toSafeDouble(): Double? =
        try {
            if (isEmpty()) {
                null
            } else {
                dropWhile { !it.isDigit() && it != '-' }
                        .dropLastWhile { !it.isDigit() }
                        .replace("[,\\s]".toRegex(), "")
                        .toDouble()
            }
        } catch (e: NumberFormatException) {
            null
        } catch (e: NullPointerException) {
            null
        }

fun String.formatAmount(): String? {
    return try {
        val amountDouble = toSafeDouble()
        if (amountDouble != null) {
            // Format the Integer properly
            val numberFormat = NumberFormat.getNumberInstance(Locale.US)
            numberFormat.minimumFractionDigits = 0
            numberFormat.maximumFractionDigits = 2
            numberFormat.format(amountDouble)
        } else {
            null
        }
    } catch (e: java.lang.Exception) {
        null
    }
}

@JvmOverloads
fun String.formatAmountWithCurrency(
        context: Context = NalaApp.appContext,
        @StringRes
        stringResId: Int = -1,
        sign: String = "",
        currency: String = ""
): String? {
    val formattedAmount = formatAmount()
    return if (stringResId == -1) {
        formattedAmount
    } else {
        try {
            context.getString(
                    stringResId,
                    sign,
                    currency,
                    formattedAmount
            )
        } catch (e: Exception) {
            formattedAmount
        }
    }
}

fun String.deserializeJavaMapToMap(): Map<String, String> {
    val strings = replace("[{}]".toRegex(), "").trim()
    if (strings.isEmpty() || !contains("=")) {
        return emptyMap()
    }

    return strings.split(",\\s".toRegex()).associate {
        if (it.contains("=")) {
            val (left, right) = it.split("=")
            left to right
        } else {
            it to ""
        }
    }
}

inline fun <reified T, reified K> String.deserializeJsonToMap(): MutableMap<T, K> {
    return try {
        Gson().fromJson(
                this,
                TypeToken.getParameterized(MutableMap::class.java, T::class.java, K::class.java).type
        )
    } catch (e: Exception) {
        Log.e("StringUtils", e.message, e)
        mutableMapOf()
    }
}

inline fun <reified T> String.deserializeJsonToList(): MutableList<T> {
    return try {
        Gson().fromJson(
                this,
                TypeToken.getParameterized(MutableList::class.java, T::class.java).type
        )
    } catch (e: Exception) {
        Log.e("StringUtils", e.message, e)
        mutableListOf()
    }
}

fun Activity.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun View.makeSnackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}

fun Activity.makeSnackbar(message: String) {
    findViewById<View>(android.R.id.content).makeSnackbar(message)
}