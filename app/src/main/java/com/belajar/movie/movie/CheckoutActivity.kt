package com.belajar.movie.movie

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.belajar.movie.MasterActivity
import com.belajar.movie.R
import com.belajar.movie.model.Checkout
import com.belajar.movie.model.Film
import com.belajar.movie.ticket.TicketDetailActivity
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
        val film = intent.getParcelableExtra<Film>("film")

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

            showNotif(film!!)
        }

        btn_batal.setOnClickListener {
            Intent(this, TicketDetailActivity::class.java).run {
                finishAffinity()
                startActivity(this)
            }
        }
    }

    private fun showNotif(film: Film) {
        val NOTIFICATION_CHANEL_ID = "chanel_bwa_notif"
        val context = this.applicationContext
        var notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channelName = "BWAMOV Notification Channel"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(NOTIFICATION_CHANEL_ID, channelName, importance)
            notificationManager.createNotificationChannel(channel)
        }

        val intent = Intent(this, TicketDetailActivity::class.java)
        intent.putExtra("data", film)

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(this, NOTIFICATION_CHANEL_ID)
        builder.setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_logo_mov)
            .setLargeIcon(
                BitmapFactory.decodeResource(this.resources, R.mipmap.ic_launcher)
            )
            .setTicker("Notif BWA Starting")
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setLights(Color.RED, 3000, 3000)
            .setDefaults(Notification.DEFAULT_SOUND)
            .setContentTitle("Pembayaran ${film.judul} Telah di berhasil")
            .setContentText("BWAMOV")

        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(115, builder.build())
    }

    private fun currency(number: Double): String {
        val localeID = Locale("in", "ID")
        val format = NumberFormat.getCurrencyInstance(localeID)
        return format.format(number)
    }
}