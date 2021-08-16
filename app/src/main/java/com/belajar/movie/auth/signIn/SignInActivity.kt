package com.belajar.movie.auth.signIn

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.belajar.movie.MasterActivity
import com.belajar.movie.R
import com.belajar.movie.auth.signUp.SignUpActivity
import com.belajar.movie.onboarding.OnboardingNowPlayingActivity
import com.belajar.movie.utils.Preferences
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_sign_in.*


class SignInActivity : AppCompatActivity() {

    lateinit var iUsername: String
    lateinit var iPassword: String
    lateinit var dbReference: DatabaseReference
    lateinit var database: FirebaseDatabase
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        database = FirebaseDatabase.getInstance()
        dbReference = database.getReference("User")
        preferences = Preferences(this)

        preferences.setValue("onboarding", "1")

        btn_sign_in.setOnClickListener {
            iUsername = et_username.text.toString()
            iPassword = et_password.text.toString()

            when {
                iUsername == "" -> {
                    et_username.error = "Masukan Username"
                    et_username.requestFocus()
                }
                iPassword == "" -> {
                    et_password.error = "Masukkan Password"
                    et_password.requestFocus()
                }
                else -> login(iUsername, iPassword)
            }
        }

        btn_sign_up.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun login(iUsername: String, iPassword: String) {
        dbReference.child(iUsername).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(User::class.java)

                when {
                    user == null ->
                        Toast.makeText(
                            this@SignInActivity,
                            "Username tidak di temukan",
                            Toast.LENGTH_LONG
                        ).show()
                    user.password != iPassword ->
                        Toast.makeText(
                            this@SignInActivity,
                            "Password salah",
                            Toast.LENGTH_LONG
                        ).show()
                    else -> {
                        preferences.apply {
                            setValue("name", user.name.toString())
                            setValue("username", user.username.toString())
                            setValue("email", user.email.toString())
                            setValue("saldo", user.saldo.toString())
                            setValue("url", user.url.toString())
                            setValue("login", true)
                        }

                        finishAffinity()
                        Intent(this@SignInActivity, MasterActivity::class.java).also {
                            startActivity(it)
                        }
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignInActivity, databaseError.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}