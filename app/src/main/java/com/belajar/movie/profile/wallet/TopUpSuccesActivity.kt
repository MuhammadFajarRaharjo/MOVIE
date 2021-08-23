package com.belajar.movie.profile.wallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.belajar.movie.MasterActivity
import com.belajar.movie.R
import kotlinx.android.synthetic.main.activity_top_up_success.*

class TopUpSuccesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_up_success)

        btn_home.setOnClickListener {
            finishAffinity()
            val intent = Intent(this, MasterActivity::class.java)
            startActivity(intent)
        }
    }
}