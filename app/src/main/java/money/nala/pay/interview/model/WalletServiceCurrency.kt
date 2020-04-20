package money.nala.pay.interview.model

import android.content.Context
import androidx.appcompat.content.res.AppCompatResources
import money.nala.pay.interview.R
import money.nala.pay.interview.NalaApp

enum class WalletServiceCurrency(val localNameStringResource: Int,
                                 val localNameImageResource: Int,
                                 val isoCode: String) {
    TZS(R.string.currency_tzs, R.drawable.ic_currency_tzs, WalletServiceCurrency.CURRENCY_TZS_ISO),
    UGX(R.string.currency_ugx, R.drawable.ic_currency_ugx, WalletServiceCurrency.CURRENCY_UGX_ISO),
    EMPTY(0, 0, "empty");

    fun getNameAsString(context: Context = NalaApp.appContext) =
        context.getString(localNameStringResource)

    fun getNameAsImage(context: Context = NalaApp.appContext) =
        AppCompatResources.getDrawable(context, localNameImageResource)

    companion object {
        private const val CURRENCY_TZS_ISO = "TZS"
        private const val CURRENCY_UGX_ISO = "UGX"
    }
}