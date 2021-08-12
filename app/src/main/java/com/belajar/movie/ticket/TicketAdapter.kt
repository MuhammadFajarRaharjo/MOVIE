package com.belajar.movie.ticket

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.belajar.movie.R
import com.belajar.movie.model.Checkout

class TicketAdapter(private var data: List<Checkout>, private var listener: (Checkout) -> Unit) :
    RecyclerView.Adapter<TicketAdapter.ViewHolder>() {

    private lateinit var contexAdapter: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TicketAdapter.ViewHolder {
        contexAdapter = parent.context
        val layoutInflater = LayoutInflater.from(contexAdapter)
        val inflatedView = layoutInflater.inflate(R.layout.row_seat_ticket, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: TicketAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position], listener)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvbangku: TextView = view.findViewById(R.id.tv_seat)

        @SuppressLint("SetTextI18n")
        fun bindItem(data: Checkout, listener: (Checkout) -> Unit) {
            tvbangku.text = "Seat No. ${data.bangku}"

            itemView.setOnClickListener {
                listener(data)
            }
        }
    }
}
