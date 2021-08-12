package com.belajar.movie.ticket

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.belajar.movie.R
import com.belajar.movie.model.Checkout
import com.belajar.movie.model.Film
import com.belajar.movie.utils.Preferences
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_checkout.rv_seat
import kotlinx.android.synthetic.main.activity_ticket_detail.*

class TicketDetailActivity : AppCompatActivity() {

    private lateinit var preferences: Preferences
    private var dataList: ArrayList<Checkout> = ArrayList()
    private var total: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_detail)

        preferences = Preferences(this)
        val data = intent.getParcelableExtra<Film>("data")

        data?.also {
            tv_judul.text = it.judul
            tv_genre.text = it.genre
            tv_rating.text = it.rating

            Glide.with(this)
                .load(data.poster)
                .into(iv_poster)
        }

        dataList.apply {
            (1..4).forEach { this.add(Checkout(bangku = "A$it")) }
        }

        rv_seat.layoutManager = LinearLayoutManager(this)
        rv_seat.adapter = TicketAdapter(dataList) {}

        iv_back.setOnClickListener { onBackPressed() }

        iv_qr.setOnClickListener { visiblility(View.VISIBLE) }

        btn_tutup.setOnClickListener { visiblility(View.INVISIBLE) }
    }

    private fun visiblility(param: Int) {
            view2.visibility = param
            cl_qr.visibility = param
    }
}