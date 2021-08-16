package com.belajar.movie.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.belajar.movie.R
import com.belajar.movie.auth.signIn.User
import com.belajar.movie.model.Film
import com.belajar.movie.movie.MovieDetailActivity
import com.belajar.movie.utils.Preferences
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class DashboardFragment : Fragment() {

    private lateinit var preferences: Preferences
    private lateinit var database: FirebaseDatabase
    private lateinit var dbReference: DatabaseReference
    private lateinit var dbUser: DatabaseReference

    private var dataList = ArrayList<Film>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        database = FirebaseDatabase.getInstance()
        dbReference = database.getReference("Film")
        dbUser = database.getReference("User")
        preferences = Preferences(activity?.applicationContext!!)
        tv_username.text = preferences.getValueString("username")

        setSaldo()

        if (preferences.getValueString("url") != "") {
            Glide.with(this)
                .load(preferences.getValueString("url"))
                .apply(RequestOptions.circleCropTransform())
                .into(iv_profile)
        }

        rv_now_playing.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_coming_soon.layoutManager = LinearLayoutManager(context)

        getData()
    }

    private fun getData() {
        dbReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()

                for (dataSnapshot in snapshot.children) {
                    val film = dataSnapshot.getValue(Film::class.java)
                    dataList.add(film!!)
                }

                rv_now_playing.adapter = NowPlayingAdapter(dataList) {
                    val intent =
                        Intent(context, MovieDetailActivity::class.java).putExtra("data", it)
                    startActivity(intent)
                }
                rv_coming_soon.adapter = ComingSoonAdapter(dataList) {
                    val intent =
                        Intent(context, MovieDetailActivity::class.java).putExtra("data", it)
                            .putExtra("comingSoon", true)
                    startActivity(intent)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun currency(number: Double): String {
        val localeID = Locale("in", "ID")
        val format = NumberFormat.getCurrencyInstance(localeID)
        return format.format(number)
    }

    private fun setSaldo() {
        val user: String = preferences.getValueString("username").toString()
        dbUser.child(user).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val saldo = p0.getValue(User::class.java)
                preferences.setValue("saldo", saldo?.saldo.toString())

                if (!preferences.getValueString("saldo").isNullOrBlank()) {
                    tv_saldo.text = currency(preferences.getValueString("saldo")!!.toDouble())
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(context, p0.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}