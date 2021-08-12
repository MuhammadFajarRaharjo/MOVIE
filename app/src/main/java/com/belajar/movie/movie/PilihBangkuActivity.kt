package com.belajar.movie.movie

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.belajar.movie.R
import com.belajar.movie.model.Checkout
import com.belajar.movie.model.Film
import kotlinx.android.synthetic.main.activity_pilih_bangku.*

class PilihBangkuActivity : AppCompatActivity() {

    private var a1 = false
    private var a2 = false
    private var a3 = false
    private var a4 = false
    private var a5 = false
    private var a6 = false

    private var b1 = false
    private var b2 = false
    private var b3 = false
    private var b4 = false
    private var b5 = false
    private var b6 = false

    private var c1 = false
    private var c2 = false
    private var c3 = false
    private var c4 = false
    private var c5 = false
    private var c6 = false

    private var d1 = false
    private var d2 = false
    private var d3 = false
    private var d4 = false
    private var d5 = false
    private var d6 = false

    private var e1 = false
    private var e2 = false
    private var e3 = false
    private var e4 = false
    private var e5 = false
    private var e6 = false

    private var total: Int = 0
    private val dataList: ArrayList<Checkout> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilih_bangku)

        val data: Film = intent.getParcelableExtra("data")!!
        tv_judul_movie.text = data.judul

        iv_back.setOnClickListener {
            onBackPressed()
        }

        // Button Seat A
        A1.setOnClickListener {
            when (a1) {
                true -> {
                    A1.setImageResource(R.drawable.ic_rectangle_empty)
                    total -= 1
                    a1 = false
                }
                else -> {
                    A1.setImageResource(R.drawable.ic_rectangle_selected)
                    total += 1
                    a1 = true
                    dataList.add(Checkout(data.judul,"A1", 45000))
                }
            }

            beliTiket(total)
        }

        A2.setOnClickListener {
            when (a2) {
                true -> {
                    A2.setImageResource(R.drawable.ic_rectangle_empty)
                    total -= 1
                    a2 = false
                }
                else -> {
                    A2.setImageResource(R.drawable.ic_rectangle_selected)
                    total += 1
                    a2 = true
                    dataList.add(Checkout(data.judul,"A2", 45000))
                }
            }

            beliTiket(total)
        }

        A3.setOnClickListener {
            when (a3) {
                true -> {
                    A3.setImageResource(R.drawable.ic_rectangle_empty)
                    total -= 1
                    a3 = false
                }
                else -> {
                    A3.setImageResource(R.drawable.ic_rectangle_selected)
                    total += 1
                    a3 = true
                    dataList.add(Checkout(data.judul,"A3", 45000))
                }
            }

            beliTiket(total)
        }

        A4.setOnClickListener {
            when (a4) {
                true -> {
                    A4.setImageResource(R.drawable.ic_rectangle_empty)
                    total -= 1
                    a4 = false
                }
                else -> {
                    A4.setImageResource(R.drawable.ic_rectangle_selected)
                    total += 1
                    a4 = true
                    dataList.add(Checkout(data.judul,"A4", 45000))
                }
            }

            beliTiket(total)
        }

        A5.setOnClickListener {
            when (a5) {
                true -> {
                    A5.setImageResource(R.drawable.ic_rectangle_empty)
                    total -= 1
                    a5 = false
                }
                else -> {
                    A5.setImageResource(R.drawable.ic_rectangle_selected)
                    total += 1
                    a5 = true
                    dataList.add(Checkout(data.judul,"A5", 45000))
                }
            }

            beliTiket(total)
        }

        A6.setOnClickListener {
            when (a6) {
                true -> {
                    A6.setImageResource(R.drawable.ic_rectangle_empty)
                    total -= 1
                    a6 = false
                }
                else -> {
                    A6.setImageResource(R.drawable.ic_rectangle_selected)
                    total += 1
                    a6 = true
                    dataList.add(Checkout(data.judul,"A6", 45000))
                }
            }

            beliTiket(total)
        }

        // Button Seat B
        B1.setOnClickListener {
            when (b1) {
                true -> {
                    B1.setImageResource(R.drawable.ic_rectangle_empty)
                    total -= 1
                    b1 = false
                }
                else -> {
                    B1.setImageResource(R.drawable.ic_rectangle_selected)
                    total += 1
                    b1 = true
                    dataList.add(Checkout(data.judul,"B1", 45000))
                }
            }

            beliTiket(total)
        }

        B2.setOnClickListener {
            when (b2) {
                true -> {
                    B2.setImageResource(R.drawable.ic_rectangle_empty)
                    total -= 1
                    b2 = false
                }
                else -> {
                    B2.setImageResource(R.drawable.ic_rectangle_selected)
                    total += 1
                    b2 = true
                    dataList.add(Checkout(data.judul,"B2", 45000))
                }
            }

            beliTiket(total)
        }

        B3.setOnClickListener {
            when (b3) {
                true -> {
                    B3.setImageResource(R.drawable.ic_rectangle_empty)
                    total -= 1
                    b3 = false
                }
                else -> {
                    B3.setImageResource(R.drawable.ic_rectangle_selected)
                    total += 1
                    b3 = true
                    dataList.add(Checkout(data.judul,"B3", 45000))
                }
            }

            beliTiket(total)
        }

        B4.setOnClickListener {
            when (b4) {
                true -> {
                    B4.setImageResource(R.drawable.ic_rectangle_empty)
                    total -= 1
                    b4 = false
                }
                else -> {
                    B4.setImageResource(R.drawable.ic_rectangle_selected)
                    total += 1
                    b4 = true
                    dataList.add(Checkout(data.judul,"B4", 45000))
                }
            }

            beliTiket(total)
        }

        B5.setOnClickListener {
            when (b5) {
                true -> {
                    B5.setImageResource(R.drawable.ic_rectangle_empty)
                    total -= 1
                    b5 = false
                }
                else -> {
                    B5.setImageResource(R.drawable.ic_rectangle_selected)
                    total += 1
                    b5 = true
                    dataList.add(Checkout(data.judul,"B5", 45000))
                }
            }

            beliTiket(total)
        }

        B6.setOnClickListener {
            when (b6) {
                true -> {
                    B6.setImageResource(R.drawable.ic_rectangle_empty)
                    total -= 1
                    b6 = false
                }
                else -> {
                    B6.setImageResource(R.drawable.ic_rectangle_selected)
                    total += 1
                    b6 = true
                    dataList.add(Checkout(data.judul,"B6", 45000))
                }
            }

            beliTiket(total)
        }

        // Button Seat C
        C1.setOnClickListener {
            when (c1) {
                true -> {
                    C1.setImageResource(R.drawable.ic_rectangle_empty)
                    total -= 1
                    b1 = false
                }
                else -> {
                    C1.setImageResource(R.drawable.ic_rectangle_selected)
                    total += 1
                    c1 = true
                    dataList.add(Checkout(data.judul,"C1", 45000))
                }
            }

            beliTiket(total)
        }

        C2.setOnClickListener {
            when (c2) {
                true -> {
                    C2.setImageResource(R.drawable.ic_rectangle_empty)
                    total -= 1
                    c2 = false
                }
                else -> {
                    C2.setImageResource(R.drawable.ic_rectangle_selected)
                    total += 1
                    c2 = true
                    dataList.add(Checkout(data.judul,"C2", 45000))
                }
            }

            beliTiket(total)
        }

        C3.setOnClickListener {
            when (c3) {
                true -> {
                    C3.setImageResource(R.drawable.ic_rectangle_empty)
                    total -= 1
                    c3 = false
                }
                else -> {
                    C3.setImageResource(R.drawable.ic_rectangle_selected)
                    total += 1
                    c3 = true
                    dataList.add(Checkout(data.judul,"C3", 45000))
                }
            }

            beliTiket(total)
        }

        C4.setOnClickListener {
            when (c4) {
                true -> {
                    C4.setImageResource(R.drawable.ic_rectangle_empty)
                    total -= 1
                    c4 = false
                }
                else -> {
                    C4.setImageResource(R.drawable.ic_rectangle_selected)
                    total += 1
                    c4 = true
                    dataList.add(Checkout(data.judul,"C4", 45000))
                }
            }

            beliTiket(total)
        }

        C5.setOnClickListener {
            when (c5) {
                true -> {
                    C5.setImageResource(R.drawable.ic_rectangle_empty)
                    total -= 1
                    c5 = false
                }
                else -> {
                    C5.setImageResource(R.drawable.ic_rectangle_selected)
                    total += 1
                    c5 = true
                    dataList.add(Checkout(data.judul,"C5", 45000))
                }
            }

            beliTiket(total)
        }

        C6.setOnClickListener {
            when (c6) {
                true -> {
                    C6.setImageResource(R.drawable.ic_rectangle_empty)
                    total -= 1
                    c6 = false
                }
                else -> {
                    C6.setImageResource(R.drawable.ic_rectangle_selected)
                    total += 1
                    c6 = true
                    dataList.add(Checkout(data.judul,"C6", 45000))
                }
            }

            beliTiket(total)
        }

        // Button Seat D
        D1.setOnClickListener {
            when (d1) {
                true -> {
                    D1.setImageResource(R.drawable.ic_rectangle_empty)
                    total -= 1
                    d1 = false
                }
                else -> {
                    D1.setImageResource(R.drawable.ic_rectangle_selected)
                    total += 1
                    d1 = true
                    dataList.add(Checkout(data.judul,"D1", 45000))
                }
            }

            beliTiket(total)
        }

        D2.setOnClickListener {
            when (d2) {
                true -> {
                    D2.setImageResource(R.drawable.ic_rectangle_empty)
                    total -= 1
                    d2 = false
                }
                else -> {
                    D2.setImageResource(R.drawable.ic_rectangle_selected)
                    total += 1
                    d2 = true
                    dataList.add(Checkout(data.judul,"D2", 45000))
                }
            }

            beliTiket(total)
        }

        D3.setOnClickListener {
            when (d3) {
                true -> {
                    D3.setImageResource(R.drawable.ic_rectangle_empty)
                    total -= 1
                    d3 = false
                }
                else -> {
                    D3.setImageResource(R.drawable.ic_rectangle_selected)
                    total += 1
                    d3 = true
                    dataList.add(Checkout(data.judul,"D3", 45000))
                }
            }

            beliTiket(total)
        }

        D4.setOnClickListener {
            when (d4) {
                true -> {
                    D4.setImageResource(R.drawable.ic_rectangle_empty)
                    total -= 1
                    d4 = false
                }
                else -> {
                    D4.setImageResource(R.drawable.ic_rectangle_selected)
                    total += 1
                    d4 = true
                    dataList.add(Checkout(data.judul,"D4", 45000))
                }
            }

            beliTiket(total)
        }

        D5.setOnClickListener {
            when (d5) {
                true -> {
                    D5.setImageResource(R.drawable.ic_rectangle_empty)
                    total -= 1
                    d5 = false
                }
                else -> {
                    D5.setImageResource(R.drawable.ic_rectangle_selected)
                    total += 1
                    b5 = true
                    dataList.add(Checkout(data.judul,"D5", 45000))
                }
            }

            beliTiket(total)
        }

        D6.setOnClickListener {
            when (d6) {
                true -> {
                    D6.setImageResource(R.drawable.ic_rectangle_empty)
                    total -= 1
                    d6 = false
                }
                else -> {
                    D6.setImageResource(R.drawable.ic_rectangle_selected)
                    total += 1
                    d6 = true
                    dataList.add(Checkout(data.judul,"D6", 45000))
                }
            }

            beliTiket(total)
        }

        // Button Seat E
        E1.setOnClickListener {
            when (e1) {
                true -> {
                    E1.setImageResource(R.drawable.ic_rectangle_empty)
                    total -= 1
                    e1 = false
                }
                else -> {
                    E1.setImageResource(R.drawable.ic_rectangle_selected)
                    total += 1
                    e1 = true
                    dataList.add(Checkout(data.judul,"E1", 45000))
                }
            }

            beliTiket(total)
        }

        E2.setOnClickListener {
            when (e2) {
                true -> {
                    E2.setImageResource(R.drawable.ic_rectangle_empty)
                    total -= 1
                    e2 = false
                }
                else -> {
                    E2.setImageResource(R.drawable.ic_rectangle_selected)
                    total += 1
                    e2 = true
                    dataList.add(Checkout(data.judul,"E2", 45000))
                }
            }

            beliTiket(total)
        }

        E3.setOnClickListener {
            when (e3) {
                true -> {
                    E3.setImageResource(R.drawable.ic_rectangle_empty)
                    total -= 1
                    e3 = false
                }
                else -> {
                    E3.setImageResource(R.drawable.ic_rectangle_selected)
                    total += 1
                    e3 = true
                    dataList.add(Checkout(data.judul,"E3", 45000))
                }
            }

            beliTiket(total)
        }

        E4.setOnClickListener {
            when (e4) {
                true -> {
                    E4.setImageResource(R.drawable.ic_rectangle_empty)
                    total -= 1
                    e4 = false
                }
                else -> {
                    E4.setImageResource(R.drawable.ic_rectangle_selected)
                    total += 1
                    e4 = true
                    dataList.add(Checkout(data.judul,"E4", 45000))
                }
            }

            beliTiket(total)
        }

        E5.setOnClickListener {
            when (e5) {
                true -> {
                    E5.setImageResource(R.drawable.ic_rectangle_empty)
                    total -= 1
                    e5 = false
                }
                else -> {
                    E5.setImageResource(R.drawable.ic_rectangle_selected)
                    total += 1
                    e5 = true
                    dataList.add(Checkout(data.judul,"E5", 45000))
                }
            }

            beliTiket(total)
        }

        E6.setOnClickListener {
            when (e6) {
                true -> {
                    E6.setImageResource(R.drawable.ic_rectangle_empty)
                    total -= 1
                    e6 = false
                }
                else -> {
                    E6.setImageResource(R.drawable.ic_rectangle_selected)
                    total += 1
                    e6 = true
                    dataList.add(Checkout(data.judul,"E6", 45000))
                }
            }

            beliTiket(total)
        }

        btn_checkout.setOnClickListener {
            Intent(this, CheckoutActivity::class.java).putExtra("data", dataList).run { startActivity(this) }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun beliTiket(tiket: Int) {
        when (tiket) {
            0 -> btn_checkout.visibility = View.INVISIBLE
            else -> {
                btn_checkout.text = "${getString(R.string.beli)} ($tiket)"
                btn_checkout.visibility = View.VISIBLE
            }
        }
    }
}