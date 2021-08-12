package com.belajar.movie.movie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.belajar.movie.MasterActivity
import com.belajar.movie.R
import kotlinx.android.synthetic.main.activity_checkout_success.*

class CheckoutSuccesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout_success)

        btn_home.setOnClickListener {
            Intent(this, MasterActivity::class.java).run {
                finishAffinity()
                startActivity(this)
            }
        }
    }
}