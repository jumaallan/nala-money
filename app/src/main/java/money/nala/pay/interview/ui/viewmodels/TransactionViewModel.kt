package money.nala.pay.interview.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import money.nala.pay.interview.data.model.Transaction
import money.nala.pay.interview.executers.AppExecutors
import money.nala.pay.interview.repository.TransactionRepository
import money.nala.pay.interview.utils.BalanceSharedPreferenceLiveData

class TransactionViewModel(
        private val transactionRepository: TransactionRepository,
        private val appExecutors: AppExecutors
) : ViewModel() {

    private val _transactions = MutableLiveData<List<Transaction>>()

    fun getAllTransactions(): LiveData<List<Transaction>> {
        //Parse json on disk scheduler thread
        appExecutors.diskIO().execute {
            _transactions.postValue(transactionRepository.getAllTransactions())
        }
        return _transactions
    }


    fun getShouldShowBalance(): BalanceSharedPreferenceLiveData {
        return transactionRepository.getShouldShowBalance()
    }
}