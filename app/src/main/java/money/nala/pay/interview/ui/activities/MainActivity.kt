package money.nala.pay.interview.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import money.nala.pay.interview.R
import money.nala.pay.interview.ui.adapter.TransactionAdapter
import money.nala.pay.interview.ui.viewmodels.TransactionViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var transactionListAdapter: TransactionAdapter

    private val transactionModel: TransactionViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initTransactionListView()

        subscribeToTransactionModel()
    }

    private fun initTransactionListView() {
        transactionListAdapter = TransactionAdapter(mutableListOf(), true) // save this on shared pref maybe???
        transaction_list.adapter = transactionListAdapter
        transaction_list.layoutManager = LinearLayoutManager(this)
    }

    private fun subscribeToTransactionModel() {
        Timber.d("subscribeToTransactionModel()")

        val allTransactions = transactionModel.getAllTransactions()
        Timber.d("allTransactions: ${allTransactions.size}")

        transactionListAdapter.swap(allTransactions)
    }
}