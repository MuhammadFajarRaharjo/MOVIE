package com.belajar.movie.movie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.belajar.movie.R
import com.belajar.movie.model.Film
import com.belajar.movie.model.Played
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var mDatabase: DatabaseReference
    private var played = ArrayList<Played>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val data = intent.getParcelableExtra<Film>("data")
        val comingSoon = intent.getBooleanExtra("comingSoon", false)
        database = FirebaseDatabase.getInstance()
        mDatabase = database.getReference("Film").child(data!!.judul).child("play")

        data.also {
            tv_movie.text = it.judul
            tv_genre_movie.text = it.genre
            when (comingSoon) {
                true -> tv_rating.visibility = View.INVISIBLE
                else -> tv_rating.text = it.rating
            }
            tv_subtitle_storyboard.text = it.desc

            Glide.with(this)
                .load(it.poster)
                .apply(RequestOptions.centerCropTransform())
                .into(iv_poster)
        }

        rv_played.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        getData()

        iv_back.setOnClickListener {
            onBackPressed()
        }

        when (comingSoon) {
            true -> btn_pulih_bangku.visibility = View.INVISIBLE
            else -> btn_pulih_bangku.setOnClickListener {
                Intent(this, PilihBangkuActivity::class.java).putExtra("data", data).run {
                    startActivity(this)
                }
            }
        }
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                played.clear()
                dataSnapshot.children.forEach {
                    val film = it.getValue(Played::class.java)
                    played.add(film!!)
                }

                rv_played.adapter = PlayedAdapter(played) {

                }
            }

            override fun onCancelled(databaseEror: DatabaseError) {
                Toast.makeText(
                    this@MovieDetailActivity,
                    databaseEror.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}