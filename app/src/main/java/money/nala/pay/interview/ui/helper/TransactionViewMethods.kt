package money.nala.pay.interview.ui.helper

import android.content.Context
import android.text.Html
import android.text.SpannedString
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.IntDef
import androidx.core.content.ContextCompat
import money.nala.pay.interview.R
import money.nala.pay.interview.model.Transaction
import money.nala.pay.interview.model.TransactionType
import money.nala.pay.interview.model.TransactionType.Companion.from
import money.nala.pay.interview.model.WalletServiceCurrency
import money.nala.pay.interview.utils.formatAmountWithCurrency
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

object TransactionViewMethods {

    const val DIRECTION_OUTGOING = 0
    const val DIRECTION_INCOMING = 1
    const val DIRECTION_UNKNOWN = 2

    @Retention(RetentionPolicy.SOURCE)
    @IntDef(
        DIRECTION_OUTGOING,
        DIRECTION_INCOMING,
        DIRECTION_UNKNOWN
    )
    annotation class TransactionDirectionInt

    @TransactionDirectionInt
    fun getDirection(trans: Transaction, c: Context?
    ): Int {
        if (trans.transactionType != TransactionType.MONEY_RECEIVED) {
            return DIRECTION_OUTGOING
        } else if (trans.transactionType == TransactionType.MONEY_RECEIVED) {
            return DIRECTION_INCOMING
        }
        return DIRECTION_OUTGOING
    }

    fun getStandardizedAmountWithCurrency(
        trans: Transaction,
        c: Context?
    ): CharSequence {
        return getStandardizedAmountWithCurrency(trans, c, true)
    }

    fun getStandardizedAmountWithCurrency(trans: Transaction, c: Context?, showSign: Boolean): CharSequence {
        return if (c == null) SpannedString("") else getCustomAmountWithCurrency(
            c,
            trans.amount,
            if (showSign && getDirection(
                    trans,
                    c
                ) == DIRECTION_INCOMING
            ) "+" else "",
            WalletServiceCurrency.TZS.getNameAsString(c)
        )
    }

    fun getCustomAmountWithCurrency(c: Context?, amount: String, sign: String, currency: String): CharSequence {
        return if (c == null) SpannedString("") else try {
            Html.fromHtml(
                amount.formatAmountWithCurrency(
                    c,
                    R.string.txt_amount,
                    sign,
                    currency.toUpperCase()
                )
            )
        } catch (e: Exception) {
            SpannedString("")
        }
    }

    @ColorInt
    fun getAmountColor(trans: Transaction, c: Context?): Int {
        if (c == null) return 0

        return when (getDirection(trans, c)) {
            DIRECTION_OUTGOING -> ContextCompat.getColor(c, R.color.transaction_outgoing)
            DIRECTION_INCOMING -> ContextCompat.getColor(c, R.color.transaction_incoming)
            DIRECTION_UNKNOWN -> ContextCompat.getColor(c, R.color.transaction_pending)
            else -> ContextCompat.getColor(c, R.color.transaction_pending)
        }
    }

    fun getWho(trans: Transaction, c: Context?): String {
        return when {
            trans.recipient.isNotEmpty() -> {
                trans.recipient
            }
            trans.recipientNumber.isNotEmpty() -> {
                trans.recipientNumber
            }
            trans.description.isNotEmpty() -> {
                trans.description
            }
            else -> {
                trans.walletService.operatorName
            }
        }
    }

    @DrawableRes
    fun getPic(trans: Transaction, c: Context?): Int {
        return when (trans.transactionType) {
            TransactionType.BUY_BUNDLE -> R.drawable.ic_type_bundle
            TransactionType.CASH_OUT, TransactionType.CASH_OUT_PHONE -> R.drawable.ic_type_cashout
            TransactionType.CHECK_BALANCE -> R.drawable.ic_type_check_balance
            TransactionType.AIRTIME, TransactionType.API_AIRTIME, TransactionType.AIRTIME_FOR_FRIEND -> R.drawable.ic_type_airtime
            TransactionType.SEND_MONEY -> if (trans.subType.contains("bank")) {
                R.drawable.ic_type_bank
            } else {
                R.drawable.ic_account_avatar
            }
            TransactionType.PAY_BILL, TransactionType.PAYMENT -> R.drawable.ic_type_bill_generic
            TransactionType.MONEY_RECEIVED -> R.drawable.ic_type_money_received
            else -> 0
        }
    }

    fun getMemo(trans: Transaction, c: Context): String {
        return when (from(trans.type)) {
            TransactionType.BUY_BUNDLE -> c.getString(R.string.transaction_history_buy_bundle)
            TransactionType.CASH_OUT, TransactionType.CASH_OUT_PHONE -> c.getString(R.string.transaction_history_withdraw)
            TransactionType.CHECK_BALANCE -> c.getString(R.string.transaction_history_check_balance)
            TransactionType.AIRTIME, TransactionType.API_AIRTIME -> c.getString(R.string.transaction_history_buy_airtime)
            TransactionType.AIRTIME_FOR_FRIEND -> c.getString(R.string.transaction_history_buy_airtime_for_friend)
            TransactionType.SEND_MONEY -> c.getString(R.string.transaction_history_send_money)
            TransactionType.PAY_BILL -> c.getString(R.string.transaction_history_paybill)
            TransactionType.MONEY_RECEIVED -> c.getString(R.string.transaction_history_money_received)
            TransactionType.PAYMENT -> c.getString(R.string.transaction_history_payment)
            else -> c.getString(R.string.txt_error)
        }
    }
}