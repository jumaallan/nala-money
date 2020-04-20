package money.nala.pay.interview.ui.viewmodels

import androidx.lifecycle.ViewModel
import money.nala.pay.interview.data.repository.TransactionRepository

class TransactionViewModel(
        private val transactionRepository: TransactionRepository
) : ViewModel() {

}