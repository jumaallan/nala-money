package money.nala.pay.interview.model

import java.util.*

data class Wallet(
    val id: Long,
    val udid: String,
    val service: WalletService,
    var phoneNumber: String = "",
    var balance: String = "",
    var balanceTimestamp: Long = NO_TIMESTAMP,
    var active: Boolean = true
) {

    fun activate() {
        active = true
    }

    fun deactivate() {
        active = false
    }

    fun updateBalance(balance: String, balanceTimestamp: Long = Date().time) {
        this.balance = balance
        this.balanceTimestamp = balanceTimestamp
    }

    fun updatePhoneNumber(phoneNumber: String) {
        if (phoneNumber.isNotEmpty()) {
            this.phoneNumber = phoneNumber
        }
    }

    fun hasBalanceTimestamp() = balanceTimestamp != NO_TIMESTAMP

    companion object {
        // Default Values
        const val NO_TIMESTAMP = -1L
    }
}