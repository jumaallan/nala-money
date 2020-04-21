package money.nala.pay.interview.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import money.nala.pay.interview.R
import money.nala.pay.interview.data.settings.Settings
import money.nala.pay.interview.ui.adapter.TransactionAdapter
import money.nala.pay.interview.ui.viewmodels.TransactionViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var transactionListAdapter: TransactionAdapter

    private val transactionViewModel: TransactionViewModel by viewModel()
    private val settings: Settings by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        settings.setShouldShowBalance(true)

        transactionListAdapter = TransactionAdapter(mutableListOf(), settings.shouldShowBalance())
        transaction_list.adapter = transactionListAdapter
        transaction_list.layoutManager = LinearLayoutManager(this)

        transactionViewModel.getShouldShowBalance().observe(this) {
            // so we listen to the shared pref here as a live data object
            transactionListAdapter.setBalanceVisibility(it)
            transactionListAdapter.notifyDataSetChanged()
        }

        transactionViewModel.getAllTransactions().observe(this) {
            transactionListAdapter.swap(it)
        }
    }
}