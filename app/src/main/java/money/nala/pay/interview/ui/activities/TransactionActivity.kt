package money.nala.pay.interview.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_transaction.*
import money.nala.pay.interview.R

class TransactionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)

        // get data passed from the intent
        val uuid = intent?.getStringExtra("uuid").toString()
        val type = intent?.getStringExtra("type").toString()
        val amount = intent?.getStringExtra("amount").toString()
        val recipient = intent?.getStringExtra("recipient").toString()
        val recipientNumber = intent?.getStringExtra("recipientNumber").toString()
        val description = intent?.getStringExtra("description").toString()

        // set data to views here
        text_transaction_uuid.text = uuid
        text_transaction_type.text = type
        text_transaction_amount.text = amount
        text_recipient.text = recipient
        text_recipient_number.text = recipientNumber
        text_description.text = description
    }
}
