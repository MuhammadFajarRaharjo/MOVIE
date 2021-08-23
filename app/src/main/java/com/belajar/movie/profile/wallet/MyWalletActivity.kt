package com.belajar.movie.profile.wallet

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.belajar.movie.R
import com.belajar.movie.utils.Preferences
import kotlinx.android.synthetic.main.activity_my_wallet.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class MyWalletActivity : AppCompatActivity() {

    private val dataList = ArrayList<Wallet>()
    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_wallet)

        preferences = Preferences(this)

        val saldo = preferences.getValueString("saldo")
        if (saldo!!.isNotBlank()) tv_saldo.text = formatRupiah(saldo.toDouble())
        else tv_saldo.text = "IDR 0"

        dataList.add(Wallet("Avangers End Game", "Sabtu, 2 Agustus 2020", 45000.0, false))
        dataList.add(Wallet("Baby Bos", "Sabtu, 4 Agustus 2020", 45000.0, false))
        dataList.add(Wallet("Moana", "Sabtu, 5 Agustus 2020", 45000.0, false))
        dataList.add(Wallet("Top Up", "Sabtu, 10 Agustus 2020", 100000.0))

        rv_history_transaksi.layoutManager = LinearLayoutManager(this)
        rv_history_transaksi.adapter = MyWalletAdapter(dataList) {}

        iv_back.setOnClickListener { onBackPressed() }

        btn_top_up.setOnClickListener {
            val intent = Intent(this, TopUpWalletActivity::class.java)
            startActivity(intent)
        }
    }

    private fun formatRupiah(number: Double): String {
        return NumberFormat.getCurrencyInstance(Locale("id", "ID")).format(number)
    }
}