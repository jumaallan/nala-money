package money.nala.pay.interview.data.repository

import androidx.lifecycle.MutableLiveData
import money.nala.pay.interview.data.model.Transaction
import money.nala.pay.interview.data.model.TransactionType
import money.nala.pay.interview.data.settings.Settings
import money.nala.pay.interview.data.settings.SettingsConstants
import money.nala.pay.interview.utils.BalanceSharedPreferenceLiveData
import money.nala.pay.interview.utils.Utils
import money.nala.pay.interview.utils.deserializeJsonToList
import timber.log.Timber

class TransactionRepository(
        private val settings: Settings
) {

    fun getAllTransactions(): MutableLiveData<List<Transaction>> {
        val transactionsJson = Utils.readFromAssetFile(fileName = TRANSACTIONS_FILE)
        Timber.d("transactionsJson: $transactionsJson")
        val transactions: MutableList<Transaction> = transactionsJson.deserializeJsonToList()
        insertDateHeaders(transactions)
        val mutableLiveData: MutableLiveData<List<Transaction>> = MutableLiveData()
        mutableLiveData.value = transactions
        return mutableLiveData
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

    fun getShouldShowBalance(): BalanceSharedPreferenceLiveData {
        return BalanceSharedPreferenceLiveData(
                sharedPreferences = settings.settings,
                key = SettingsConstants.SHOULD_SHOW_BALANCE,
                defValue = settings.shouldShowBalance()
        )
    }

    companion object {
        const val TRANSACTIONS_FILE = "transactions.json"
    }
}