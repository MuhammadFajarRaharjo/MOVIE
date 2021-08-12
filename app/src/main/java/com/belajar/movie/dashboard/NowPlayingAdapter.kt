package com.belajar.movie.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.belajar.movie.R
import com.belajar.movie.model.Film
import com.bumptech.glide.Glide

class NowPlayingAdapter(private var data: List<Film>, private var listener: (Film) -> Unit) :
    RecyclerView.Adapter<NowPlayingAdapter.ViewHolder>() {

    lateinit var contexAdapter: Context

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val tvJudul: TextView = view.findViewById(R.id.tv_judul)
        private val tvGenre: TextView = view.findViewById(R.id.tv_genre)
        private val tvRating: TextView = view.findViewById(R.id.tv_rating)
        private val ivImage: ImageView = view.findViewById(R.id.iv_poster)

        fun bindItem(data: Film, listener: (Film) -> Unit, contex: Context) {
            data.also {
                tvGenre.text = it.genre
                tvJudul.text = it.judul
                tvRating.text = it.rating

                Glide.with(contex)
                    .load(it.poster)
                    .into(ivImage)
            }

            itemView.setOnClickListener{
                listener(data)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NowPlayingAdapter.ViewHolder {
        contexAdapter = parent.context
        val layoutInflater = LayoutInflater.from(contexAdapter)
        val inflatedView = layoutInflater.inflate(R.layout.card_now_playing, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: NowPlayingAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contexAdapter)
    }

    override fun getItemCount(): Int = data.size

}
