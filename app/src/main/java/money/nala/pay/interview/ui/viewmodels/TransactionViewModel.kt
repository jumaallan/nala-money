package money.nala.pay.interview.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import money.nala.pay.interview.model.Transaction
import money.nala.pay.interview.model.TransactionType
import money.nala.pay.interview.utils.Utils
import money.nala.pay.interview.utils.deserializeJsonToList

class TransactionViewModel : ViewModel() {

    fun getAllTransactions(): List<Transaction> {
        val transactionsJson = Utils.readFromAssetFile(fileName = TRANSACTIONS_FILE)
        Log.d("TransactionViewModel", "transactionsJson: $transactionsJson")
        val transactions: MutableList<Transaction> = transactionsJson.deserializeJsonToList()
        insertDateHeaders(transactions)
        return transactions
    }

    private fun insertDateHeaders(transactions: MutableList<Transaction>?) {
        if (transactions == null || transactions.isEmpty()) return
        for (transaction in transactions) {
            if (transaction.transactionType == TransactionType.UNKNOWN) {
                // The list of transactions already has dates
                return
            }
        }
        val iterator = transactions.listIterator()
        do {
            // TODO Change it from string based comparison to day/month/year comparison
            if (iterator.nextIndex() == 0 ||
                Utils.createHumanReadableTimestamp(transactions[iterator.nextIndex()].timestamp, "d MMMM")
                    != Utils.createHumanReadableTimestamp(transactions[iterator.previousIndex()].timestamp, "d MMMM")) {
                val dateHeader = createEmptyTransaction(transactions[iterator.nextIndex()].timestamp)
                iterator.add(dateHeader)
            }
            iterator.next()
        } while (iterator.hasNext())
    }

    private fun createEmptyTransaction(timestamp: Long): Transaction {
        return Transaction(
            0,
            "",
            0,
            "unknown",
            "unknown",
            "",
            "",
            "",
            "",
            "",
            "",
            timestamp
        )
    }

    companion object {
        const val TRANSACTIONS_FILE = "transactions.json"
    }
}