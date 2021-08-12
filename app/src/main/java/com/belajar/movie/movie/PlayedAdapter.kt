package com.belajar.movie.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.belajar.movie.R
import com.belajar.movie.model.Played
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class PlayedAdapter(private var data: List<Played>, private var listener: (Played) -> Unit) :
    RecyclerView.Adapter<PlayedAdapter.ViewHolder>() {

    lateinit var contexAdapter: Context

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val tvName: TextView = view.findViewById(R.id.tv_name)
        private val ivImage: ImageView = view.findViewById(R.id.iv_gambar)

        fun bindItem(data: Played, listener: (Played) -> Unit, contex: Context) {
            data.also {
                tvName.text = it.nama
                Glide.with(contex)
                    .load(it.url)
                    .apply(RequestOptions.circleCropTransform())
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
    ): PlayedAdapter.ViewHolder {
        contexAdapter = parent.context
        val layoutInflater = LayoutInflater.from(contexAdapter)
        val inflatedView = layoutInflater.inflate(R.layout.row_played, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: PlayedAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contexAdapter)
    }

    override fun getItemCount(): Int = data.size

}
