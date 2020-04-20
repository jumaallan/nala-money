package money.nala.pay.interview.model

import money.nala.pay.interview.R
import java.util.*

enum class Language(val id: Int,
                    val code: String,
                    val localNameStringResource: Int,
                    val locale: Locale
) {
    ENGLISH(1, "en", R.string.settings_language_english, Locale("en")),
    SWAHILI(2, "sw", R.string.settings_language_swahili, Locale("sw")),
    LUGANDA(3, "lg", R.string.settings_language_luganda, Locale("lg"));

    companion object {
        fun fromCode(code: String) =
            values().first { it.code == code }
    }
}