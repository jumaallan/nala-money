package money.nala.pay.interview.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import money.nala.pay.interview.data.model.Transaction
import money.nala.pay.interview.data.repository.TransactionRepository
import money.nala.pay.interview.utils.BalanceSharedPreferenceLiveData

class TransactionViewModel(
        private val transactionRepository: TransactionRepository
) : ViewModel() {

    fun getAllTransactions(): LiveData<List<Transaction>> {
        return transactionRepository.getAllTransactions()
    }

    fun getShouldShowBalance(): BalanceSharedPreferenceLiveData {
        return getShouldShowBalance()
    }

}