package com.belajar.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.belajar.movie.dashboard.DashboardFragment
import com.belajar.movie.profile.ProfileFragment
import com.belajar.movie.ticket.TicketFragment
import kotlinx.android.synthetic.main.activity_master.*

class MasterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_master)

        val dashboardFragment = DashboardFragment()
        val ticketFragment = TicketFragment()
        val profileFragment = ProfileFragment()

        setFragment(dashboardFragment)

        iv_dashboard.setOnClickListener{
            setFragment(dashboardFragment)

            iv_dashboard.setImageResource(R.drawable.ic_dashboard_active)
            iv_ticket.setImageResource(R.drawable.ic_ticket_not_active)
            iv_profile.setImageResource(R.drawable.ic_profile_not_active)
        }

        iv_ticket.setOnClickListener{
            setFragment(ticketFragment)

            iv_dashboard.setImageResource(R.drawable.ic_dashboard_not_active)
            iv_ticket.setImageResource(R.drawable.ic_ticket_active)
            iv_profile.setImageResource(R.drawable.ic_profile_not_active)
        }

        iv_profile.setOnClickListener{
            setFragment(profileFragment)

            iv_dashboard.setImageResource(R.drawable.ic_dashboard_not_active)
            iv_ticket.setImageResource(R.drawable.ic_ticket_not_active)
            iv_profile.setImageResource(R.drawable.ic_profile_active)
        }
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().also {
            it.replace(R.id.frame_layout, fragment)
                .commit()
        }
    }
}