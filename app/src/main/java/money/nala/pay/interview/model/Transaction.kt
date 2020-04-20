package money.nala.pay.interview.model

import java.util.NoSuchElementException

data class Transaction(
    val id: Long,
    val uuid: String,
    val walletServiceId: Int,
    val type: String,
    val subType: String,
    val amount: String,
    val recipient: String,
    val recipientNumber: String,
    val description: String,
    val responseMessage: String,
    val extras: String,
    var timestamp: Long
) {
    val transactionType: TransactionType
        get() {
            return typeToTransactionType(type)
        }

    val walletService: WalletService
        get() {
            return try {
                WalletService.from(walletServiceId)
            } catch (e: NoSuchElementException) {
                WalletService.EMPTY
            }
        }

    companion object {
        fun typeToTransactionType(type: String): TransactionType {
            return TransactionType.values().firstOrNull { it.toString() == type } ?: TransactionType.UNKNOWN
        }
    }
}