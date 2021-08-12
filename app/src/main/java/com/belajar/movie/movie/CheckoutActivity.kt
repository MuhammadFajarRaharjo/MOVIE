package com.belajar.movie.movie

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.belajar.movie.MasterActivity
import com.belajar.movie.R
import com.belajar.movie.model.Checkout
import com.belajar.movie.utils.Preferences
import kotlinx.android.synthetic.main.activity_checkout.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class CheckoutActivity : AppCompatActivity() {

    private lateinit var preferences: Preferences
    private var dataList: ArrayList<Checkout> = ArrayList()
    private var total: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        preferences = Preferences(this)
        dataList = intent.getSerializableExtra("data") as ArrayList<Checkout>

        dataList.indices.forEach { total += dataList[it].harga }

        dataList.add(Checkout(bangku = "Total harus di bayar", harga = total))

        rv_seat.layoutManager = LinearLayoutManager(this)
        rv_seat.adapter = CheckoutAdapter(dataList) {}

        // Set Time Now
        // tv_date_and_time.text = SimpleDateFormat("dd MMMM, yyyy").format(Date())
        // Validation saldo is not blank
        if (preferences.getValueString("saldo") != "") {
            tv_saldo.text = currency(preferences.getValueString("saldo")!!.toDouble())

            if (preferences.getValueString("saldo")!!.toInt() > total) {
                tv_saldo_kurang.visibility = View.INVISIBLE
                btn_bayar.visibility = View.VISIBLE
            }
        }

        iv_back.setOnClickListener { onBackPressed() }

        btn_bayar.setOnClickListener {
            Intent(this, CheckoutSuccesActivity::class.java).run {
                finishAffinity()
                startActivity(this)
            }
        }

        btn_batal.setOnClickListener {
            Intent(this, MasterActivity::class.java).run {
                finishAffinity()
                startActivity(this)
            }
        }
    }

    private fun currency(number: Double): String {
        val localeID = Locale("in", "ID")
        val format = NumberFormat.getCurrencyInstance(localeID)
        return format.format(number)
    }
}