package money.nala.pay.interview.ui.adapter

import android.annotation.SuppressLint
import android.graphics.Outline
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.CallSuper
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import money.nala.pay.interview.R
import money.nala.pay.interview.data.model.Transaction
import money.nala.pay.interview.data.model.TransactionType
import money.nala.pay.interview.data.model.WalletService
import money.nala.pay.interview.ui.helper.TransactionViewMethods
import money.nala.pay.interview.utils.DimenUtils
import money.nala.pay.interview.utils.Utils
import timber.log.Timber
import java.util.*

@Suppress("DEPRECATION")
class TransactionAdapter(
        private val transactions: MutableList<Transaction>,
        private var showBalance: Boolean,
        private val listener: OnItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: Transaction?, position: Int)
    }

    private var recyclerView: RecyclerView? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        this.recyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)

        this.recyclerView = null
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return TYPE_HEADER
        }
        val transaction = transactions.getOrNull(position - 1)
        return when {
            transaction?.transactionType != TransactionType.UNKNOWN -> {
                TYPE_TRANS
            }
            else -> {
                TYPE_DATE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            TYPE_DATE -> {
                DateViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.li_transaction_date_header, parent, false))
            }
            TYPE_HEADER -> {
                HeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.li_transaction_header, parent, false))
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.li_transaction, parent, false)
                ViewCompat.setElevation(view, view.resources.getDimension(R.dimen.card_elevation_default))
                TransactionViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val current = if (position > 0)
                    transactions.getOrNull(position - 1) else null
                val previous = transactions.getOrNull(position - 2)?.let {
                    if (it.type != TransactionType.UNKNOWN.toString()) it else null
                }
                val next = transactions.getOrNull(position)?.let {
                    if (it.type != TransactionType.UNKNOWN.toString()) it else null
                }
                holder.bind(current, previous, next, showBalance, listener)
            }
        }
    }

    fun setBalanceVisibility(visible: Boolean) {
        showBalance = visible
        notifyDataSetChanged()
    }

    fun swap(newTransactions: List<Transaction>?) {
        if (newTransactions == null) return
        transactions.clear()
        if (newTransactions.isNotEmpty()) transactions.addAll(newTransactions)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    override fun getItemId(position: Int): Long {
        return if (position == 0) {
            -200
        } else {
            val transaction = transactions.getOrNull(position - 1)
            when {
                transaction == null -> {
                    -100
                }
                transaction.type != TransactionType.UNKNOWN.toString() -> {
                    transaction.id
                }
                else -> {
                    transaction.timestamp
                }
            }
        }
    }

    abstract class ViewHolder internal constructor(
            var view: View
    ) : RecyclerView.ViewHolder(view) {
        var transaction: Transaction? = null
        var previousTransaction: Transaction? = null
        var nextTransaction: Transaction? = null
        var showBalance: Boolean = true
        var onItemClickListener: OnItemClickListener? = null

        @CallSuper
        open fun bind(current: Transaction? = null, previous: Transaction? = null, next: Transaction? = null, show: Boolean, listener: OnItemClickListener) {
            transaction = current
            previousTransaction = previous
            nextTransaction = next
            showBalance = show
            onItemClickListener = listener
        }
    }

    private class DateViewHolder internal constructor(v: View) : ViewHolder(v) {
        var currentDate: Calendar? = Calendar.getInstance()
        var date: TextView? = view.findViewById(R.id.date)
        override fun bind(current: Transaction?, previous: Transaction?, next: Transaction?, show: Boolean, listener: OnItemClickListener) {
            super.bind(current, previous, next, show, listener)

            if (transaction == null) return

            transaction?.let {
                val transactionCalendar = Calendar.getInstance().apply {
                    timeInMillis = it.timestamp
                }
                if (transactionCalendar.get(Calendar.YEAR) == currentDate?.get(Calendar.YEAR)) {
                    date?.text = Utils.createHumanReadableTimestamp(it.timestamp, "d MMMM")
                } else {
                    date?.text = Utils.createHumanReadableTimestamp(it.timestamp, "d MMMM, yyyy")
                }
            }
        }
    }

    private class HeaderViewHolder internal constructor(v: View) : ViewHolder(v) {
        var hideBalanceButton: LinearLayout = view.findViewById(R.id.hideBalance_button)
        override fun bind(current: Transaction?, previous: Transaction?, next: Transaction?, show: Boolean, listener: OnItemClickListener) {
            super.bind(current, previous, next, show, listener)

            hideBalanceButton.setOnClickListener {
                listener.onItemClick(current, position)
            }
        }
    }

    private class TransactionViewHolder internal constructor(v: View) : ViewHolder(v) {
        var who: TextView? = view.findViewById(R.id.who)
        var type: TextView? = view.findViewById(R.id.type)
        var amount: TextView? = view.findViewById(R.id.amount)
        var sim: ImageView? = view.findViewById(R.id.transaction_sim)
        var pic: ImageView? = view.findViewById(R.id.pic)

        override fun bind(current: Transaction?, previous: Transaction?, next: Transaction?, show: Boolean, listener: OnItemClickListener) {
            super.bind(current, previous, next, show, listener)
            bindBackground(view)
            bindOutlineProvider(view)

            Timber.d("current: $current, previous: $previous, next: $next")

            transaction?.let {
                if (showBalance && it.amount.isNotEmpty()) {
                    amount?.text = TransactionViewMethods.getStandardizedAmountWithCurrency(it, view.context)
                    amount?.setTextColor(TransactionViewMethods.getAmountColor(it, view.context))
                } else {
                    amount?.text = ""
                }

                type?.isSingleLine = true
                type?.text = TransactionViewMethods.getMemo(it, view.context)

                if (it.walletService === WalletService.EMPTY) {
                    sim?.visibility = View.INVISIBLE
                } else {
                    sim?.setImageResource(it.walletService.historyLogo)
                }

                who?.text = TransactionViewMethods.getWho(it, view.context).trim()

                val resource = TransactionViewMethods.getPic(it, view.context)
                try {
                    pic?.setImageResource(resource)
                } catch (e: Exception) {
                    Timber.e(e)
                }

                it
            } ?: run {
                amount?.text = ""
                sim?.visibility = View.INVISIBLE
            }

            view.setOnClickListener {
                listener.onItemClick(current, position)
            }
        }

        private val contentPadding: Int
            get() = DimenUtils.dp2px(16f).toInt()

        @get:SuppressLint("NewApi")
        private val defaultOutline: ViewOutlineProvider?
            get() = if (isLollipop) ViewOutlineProvider.BACKGROUND else null

        @get:SuppressLint("NewApi")
        private val fixedOutline: ViewOutlineProvider?
            get() = if (isLollipop) object : ViewOutlineProvider() {
                @SuppressLint("NewApi")
                override fun getOutline(view: View, outline: Outline) {
                    outline.setRect(
                            0,
                            view.resources.getDimensionPixelSize(R.dimen.card_elevation_default),
                            view.width,
                            view.height
                    )
                }
            } else null

        @get:DrawableRes
        private val backgroundRes: Int
            get() = if (previousTransaction != null && nextTransaction != null) {
                R.drawable.rounded_middle_button_white
            } else if (nextTransaction != null) {
                R.drawable.rounded_top_button_white
            } else if (previousTransaction != null) {
                R.drawable.rounded_bottom_button_white
            } else {
                R.drawable.rounded_top_bottom_button_white
            }

        private fun bindBackground(itemView: View) {
            itemView.background = ContextCompat.getDrawable(itemView.context, backgroundRes)
            itemView.setPadding(contentPadding, contentPadding, contentPadding, contentPadding)
        }

        @SuppressLint("NewApi")
        private fun bindOutlineProvider(itemView: View) {
            if (!isLollipop) return
            if (backgroundRes == R.drawable.rounded_middle_button_white) {
                itemView.outlineProvider = fixedOutline
            } else {
                itemView.outlineProvider = defaultOutline
            }
        }

        private val isLollipop: Boolean
            get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
    }

    companion object {
        private const val TYPE_TRANS = 0
        private const val TYPE_DATE = 1
        private const val TYPE_HEADER = 2
    }
}