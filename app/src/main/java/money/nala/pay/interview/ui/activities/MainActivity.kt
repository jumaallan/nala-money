package money.nala.pay.interview.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import money.nala.pay.interview.R
import money.nala.pay.interview.ui.adapter.TransactionAdapter
import money.nala.pay.interview.ui.viewmodels.TransactionViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

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
        transactionListAdapter = TransactionAdapter(mutableListOf(), true)
        transaction_list.adapter = transactionListAdapter
        transaction_list.layoutManager = LinearLayoutManager(this)
    }

    private fun subscribeToTransactionModel() {
        Log.d("MainActivity", "subscribeToTransactionModel()")

        val allTransactions = transactionModel.getAllTransactions()
        Log.d("MainActivity", "allTransactions: ${allTransactions.size}")

        transactionListAdapter.swap(allTransactions)
    }
}