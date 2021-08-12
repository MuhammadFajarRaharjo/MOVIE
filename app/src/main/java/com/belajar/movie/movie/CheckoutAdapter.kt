package com.belajar.movie.movie

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.belajar.movie.R
import com.belajar.movie.model.Checkout
import java.text.NumberFormat
import java.util.*

class CheckoutAdapter(private var data: List<Checkout>, private var listener: (Checkout) -> Unit) :
    RecyclerView.Adapter<CheckoutAdapter.ViewHolder>() {

    private lateinit var contexAdapter: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CheckoutAdapter.ViewHolder {
        contexAdapter = parent.context
        val layoutInflater = LayoutInflater.from(contexAdapter)
        val inflatedView = layoutInflater.inflate(R.layout.row_seat, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: CheckoutAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position], listener)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvbangku: TextView = view.findViewById(R.id.tv_seat)
        private val tvHarga: TextView = view.findViewById(R.id.tv_harga)

        @SuppressLint("SetTextI18n")
        fun bindItem(data: Checkout, listener: (Checkout) -> Unit) {
            val locale = Locale("id", "ID")
            val formatRupppiah = NumberFormat.getCurrencyInstance(locale)

            data.also {
                tvHarga.text = formatRupppiah.format(it.harga.toDouble())

                if (data.bangku.startsWith("Total")) {
                    tvbangku.text = it.bangku
                    tvbangku.setCompoundDrawables(null,null, null, null)
                } else tvbangku.text = "Seat No. ${it.bangku}"

            }

            itemView.setOnClickListener {
                listener(data)
            }
        }
    }
}
