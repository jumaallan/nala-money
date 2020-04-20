package money.nala.pay.interview.model

import money.nala.pay.interview.R

enum class WalletServiceCountry(val countryCode: Int,
                                val nameResource: Int,
                                val flagResource: Int,
                                val countryIso: String,
                                val defaultServiceCurrency: WalletServiceCurrency,
                                val languages: List<Language>) {
    TZ(255, R.string.country_name_tanzania,
        R.drawable.ic_country_tz, WalletServiceCountry.COUNTRY_TZ_ISO, WalletServiceCurrency.TZS,
        listOf(Language.SWAHILI, Language.ENGLISH)),
    UG(256, R.string.country_name_uganda,
        R.drawable.ic_country_ug, WalletServiceCountry.COUNTRY_UG_ISO, WalletServiceCurrency.UGX,
        listOf(Language.ENGLISH)),
    EMPTY(0, 0, 0, "empty", WalletServiceCurrency.EMPTY,
        emptyList());

    companion object {
        @JvmStatic
        fun validCountries() =
            values().filter { it != EMPTY }

        private const val COUNTRY_TZ_ISO = "TZ"
        private const val COUNTRY_UG_ISO = "UG"
    }
}