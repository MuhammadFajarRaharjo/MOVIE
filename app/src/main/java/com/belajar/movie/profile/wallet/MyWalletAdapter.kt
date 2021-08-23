package com.belajar.movie.profile.wallet

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.belajar.movie.R
import java.text.NumberFormat
import java.util.*

class MyWalletAdapter(private var data: List<Wallet>, private var listener: (Wallet) -> Unit) :
    RecyclerView.Adapter<MyWalletAdapter.ViewHolder>() {

    lateinit var contexAdapter: Context

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvJudul: TextView = view.findViewById(R.id.tv_judul)
        private val tvTanggal: TextView = view.findViewById(R.id.tv_tanggal_transaksi)
        private val tvMoney: TextView = view.findViewById(R.id.tv_money)

        @SuppressLint("SetTextI18n")
        fun bindItem(data: Wallet, listener: (Wallet) -> Unit, contex: Context) {
            data.also {
                tvTanggal.text = it.date
                tvJudul.text = it.title

                val localeId = Locale("id", "ID")
                val formatRupiah = NumberFormat.getCurrencyInstance(localeId)
                when (it.status) {
                    true -> {
                        tvMoney.text = "+ ${formatRupiah.format(it.money)}"
                        tvMoney.setTextColor(Color.GREEN)
                    }
                    else -> tvMoney.text = "- ${formatRupiah.format(it.money)}"
                }
            }

            itemView.setOnClickListener {
                listener(data)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyWalletAdapter.ViewHolder {
        contexAdapter = parent.context
        val layoutInflater = LayoutInflater.from(contexAdapter)
        val inflatedView = layoutInflater.inflate(R.layout.row_transaksi, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: MyWalletAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contexAdapter)
    }

    override fun getItemCount(): Int = data.size

}
