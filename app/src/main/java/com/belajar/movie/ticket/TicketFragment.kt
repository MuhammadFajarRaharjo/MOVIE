package com.belajar.movie.ticket

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.belajar.movie.R
import com.belajar.movie.dashboard.ComingSoonAdapter
import com.belajar.movie.model.Film
import com.belajar.movie.utils.Preferences
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_ticket.*

/**
 * A simple [Fragment] subclass.
 */
class TicketFragment : Fragment() {

    private lateinit var preferences: Preferences
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var mdatabase: DatabaseReference
    private var list = ArrayList<Film>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ticket, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferences = Preferences(requireContext())
        firebaseDatabase = FirebaseDatabase.getInstance()
        mdatabase = firebaseDatabase.getReference("Film")

        rv_ticket.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        getData()
    }

    private fun getData() {
        mdatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()

                snapshot.children.forEach {
                    val film = it.getValue(Film::class.java)
                    list.add(film!!)
                }

                rv_ticket.adapter = ComingSoonAdapter(list) {
                    val intent = Intent(context, TicketDetailActivity::class.java).putExtra("data", it)
                    startActivity(intent)
                }

                tv_ticket.text = "${list.size} Movies"
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

}