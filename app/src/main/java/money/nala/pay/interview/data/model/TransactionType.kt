package money.nala.pay.interview.data.model

import money.nala.pay.interview.R

enum class TransactionType(
    val hoverString: String,
    val isPayment: Boolean,
    val nameResource: Int
) {
    // Important! If we change these variable names, we will need to change toString
    // to keep the analytics values consistent.
    CHECK_BALANCE("check_balance", false, R.string.transaction_history_check_balance),
    CHECK_BUNDLE_BALANCE("check_bundle_balance", false, -1),
    SEND_MONEY("send_money", true, R.string.transaction_history_send_money),
    PAY_BILL("pay_bill", true, R.string.transaction_history_paybill),
    CASH_OUT("cash_out", true, R.string.transaction_history_withdraw),
    CASH_OUT_PHONE("cash_out_phone_number", true, R.string.transaction_history_withdraw),
    BUY_BUNDLE("bundle", true, R.string.transaction_history_buy_bundle),
    AIRTIME("airtime", true, R.string.transaction_history_buy_airtime),
    API_AIRTIME("api_airtime", true, R.string.transaction_history_buy_airtime),
    AIRTIME_FOR_FRIEND("airtime_for_friend", true, R.string.transaction_history_buy_airtime_for_friend),
    KYC("kyc", false, -1),
    RECEIVER_CHECK("receiver_check", false, -1),
    WAKALA_CHECK("wakala_check", false, -1),
    WAKALA_CHECK_PHONE("wakala_check_phone", false, -1),

    MONEY_RECEIVED("money_received", false, R.string.transaction_history_money_received),
    PAYMENT("payment", true, R.string.transaction_history_payment),

    UNKNOWN("unknown", false, -1);

    override fun toString() = hoverString

    fun isCashOut() = this == CASH_OUT || this == CASH_OUT_PHONE

    companion object {
        /**
         * Classify the type from the strings used in Hover attributes
         * @throws NoSuchElementException
         */
        @Throws(NoSuchElementException::class)
        fun from(value: String): TransactionType =
            values().firstOrNull { it.hoverString == value } ?: UNKNOWN

    }
}