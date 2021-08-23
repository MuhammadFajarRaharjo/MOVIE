package com.belajar.movie.profile.wallet

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.belajar.movie.R
import com.belajar.movie.utils.Preferences
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_top_up_wallet.*
import java.text.NumberFormat
import java.util.*

class TopUpWalletActivity : AppCompatActivity() {

    private var amount10 = false
    private var amount25 = false
    private var amount50 = false
    private var amount75 = false
    private var amount100 = false
    private var amount200 = false
    private var amount: Double = 0.0

    private lateinit var databaseReference: DatabaseReference
    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_up_wallet)

        databaseReference = FirebaseDatabase.getInstance().getReference("User")
        preferences = Preferences(this)
        var saldo: Double = preferences.getValueString("saldo")?.toDouble() ?: 0.0
        tv_saldo.text = formatRupiah(saldo)

        iv_back.setOnClickListener { onBackPressed() }

        et_amount.addTextChangedListener {
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    reset()
                }

                override fun afterTextChanged(s: Editable?) {}
            }
            reset()

        }

        tv_10k.setOnClickListener {
            if (amount10) {
                deselectAmount(tv_10k)
                amount10 = false
            } else {
                reset()
                selectAmount(tv_10k, 10000.0)
                amount10 = true
            }
        }

        tv_25k.setOnClickListener {
            if (amount25) {
                deselectAmount(tv_25k)
                amount25 = false
            } else {
                reset()
                selectAmount(tv_25k, 25000.0)
                amount25 = true
            }
        }

        tv_50k.setOnClickListener {
            if (amount50) {
                deselectAmount(tv_50k)
                amount50 = false
            } else {
                reset()
                selectAmount(tv_50k, 50000.0)
                amount50 = true
            }
        }

        tv_75k.setOnClickListener {
            if (amount75) {
                deselectAmount(tv_75k)
                amount75 = false
            } else {
                reset()
                selectAmount(tv_75k, 75000.0)
                amount75 = true
            }
        }

        tv_100k.setOnClickListener {
            if (amount100) {
                deselectAmount(tv_100k)
                amount100 = false
            } else {
                reset()
                selectAmount(tv_100k, 100000.0)
                amount100 = true
            }
        }

        tv_200k.setOnClickListener {
            if (amount200) {
                deselectAmount(tv_200k)
                amount200 = false
            } else {
                reset()
                selectAmount(tv_200k, 200000.0)
                amount200 = true
            }
        }

        btn_top_up_now.setOnClickListener {
            val etAmount = et_amount.text.toString()
            if (etAmount.isNotBlank()) amount = etAmount.toDouble()
            saldo += amount
            databaseReference.child(preferences.getValueString("username").toString())
                .child("saldo").setValue(saldo.toString())
                .addOnSuccessListener {
                    preferences.setValue("saldo", saldo.toString())
                }
            val intent = Intent(this, TopUpSuccesActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }
    }

    private fun reset() {
        this.amount = 0.0

        tv_10k.apply {
            setTextColor(resources.getColor(R.color.white))
            setBackgroundResource(R.drawable.shape_rounded_line_white)
        }
        tv_25k.apply {
            setTextColor(resources.getColor(R.color.white))
            setBackgroundResource(R.drawable.shape_rounded_line_white)
        }
        tv_50k.apply {
            setTextColor(resources.getColor(R.color.white))
            setBackgroundResource(R.drawable.shape_rounded_line_white)
        }
        tv_75k.apply {
            setTextColor(resources.getColor(R.color.white))
            setBackgroundResource(R.drawable.shape_rounded_line_white)
        }
        tv_100k.apply {
            setTextColor(resources.getColor(R.color.white))
            setBackgroundResource(R.drawable.shape_rounded_line_white)
        }
        tv_200k.apply {
            setTextColor(resources.getColor(R.color.white))
            setBackgroundResource(R.drawable.shape_rounded_line_white)
        }
    }

    private fun selectAmount(textView: TextView, amount: Double) {
        et_amount.clearFocus()
        et_amount.text.clear()
        textView.apply {
            setTextColor(resources.getColor(R.color.light_red))
            setBackgroundResource(R.drawable.shape_rounded_line_red)
        }
        this.amount = amount
    }

    private fun deselectAmount(textView: TextView) {
        textView.apply {
            setTextColor(resources.getColor(R.color.white))
            setBackgroundResource(R.drawable.shape_rounded_line_white)
        }
        amount = 0.0
    }

    private fun formatRupiah(number: Double): String {
        return NumberFormat.getCurrencyInstance(Locale("id", "ID")).format(number)
    }
}