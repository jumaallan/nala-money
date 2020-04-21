package money.nala.pay.interview.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import money.nala.pay.interview.R
import money.nala.pay.interview.data.model.Transaction
import money.nala.pay.interview.data.settings.Settings
import money.nala.pay.interview.ui.adapter.TransactionAdapter
import money.nala.pay.interview.ui.viewmodels.TransactionViewModel
import money.nala.pay.interview.utils.makeSnackbar
import money.nala.pay.interview.utils.toast
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private lateinit var transactionListAdapter: TransactionAdapter

    private val transactionViewModel: TransactionViewModel by viewModel()
    private val settings: Settings by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        transactionListAdapter = TransactionAdapter(mutableListOf(), settings.shouldShowBalance(), object : TransactionAdapter.OnItemClickListener {
            override fun onItemClick(item: Transaction?, position: Int) {
                // can we re-use this - not sure if this is the best logic
                // so I want to check the item position = if 0, it toggles the state of the shared-pref
                // if not, it handles the transaction click - open new page
                if (position == 0) {
                    // lets check the current state and toggle that
                    // open to better ways of doing this
                    var shouldShowBalances = true
                    if (settings.shouldShowBalance()) {
                        shouldShowBalances = false
                    }
                    settings.setShouldShowBalance(shouldShowBalances)
                    makeSnackbar("Balance Visibility toggled to $shouldShowBalances")
                } else {
                    toast("The transaction amount is - " + item?.amount)
                    // open a new transaction activity here - pass the transaction too maybe?
                    val intent = Intent(applicationContext, TransactionActivity::class.java)
                    intent.putExtra("uuid", item?.uuid)
                    intent.putExtra("type", item?.type)
                    intent.putExtra("amount", item?.amount)
                    intent.putExtra("recipient", item?.recipient)
                    intent.putExtra("recipientNumber", item?.recipientNumber)
                    intent.putExtra("description", item?.description)
                    startActivity(intent)
                }
            }
        })

        transaction_list.adapter = transactionListAdapter
        transaction_list.layoutManager = LinearLayoutManager(this)

        // transformed the settings into a livedata object - so the balance change is fluid
        transactionViewModel.getShouldShowBalance().observe(this) {
            // so we listen to the shared pref here as a live data object
            transactionListAdapter.setBalanceVisibility(it)
            transactionListAdapter.notifyDataSetChanged()
        }

        // this listens for transactions as a livedata object
        transactionViewModel.getAllTransactions().observe(this) {
            transactionListAdapter.swap(it)
        }
    }
}