package com.belajar.movie.auth.signUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.belajar.movie.R
import com.belajar.movie.auth.signIn.User
import com.belajar.movie.utils.Preferences
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    lateinit var preferences: Preferences
    lateinit var database: FirebaseDatabase
    lateinit var dbReference: DatabaseReference
    lateinit var name: String
    private lateinit var username: String
    private lateinit var email: String
    private lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        preferences = Preferences(this)
        database = FirebaseDatabase.getInstance()
        dbReference = database.getReference("User")

        et_name.requestFocus()

        btn_register.setOnClickListener {
            name = et_name.text.toString()
            username = et_username.text.toString()
            email = et_email.text.toString()
            password = et_password.text.toString()

            when {
                name == "" -> {
                    et_name.error = "Masukkan Nama"
                    et_name.requestFocus()
                }
                username == "" -> {
                    et_username.error = "Masukkan Username"
                    et_username.requestFocus()
                }
                username.indexOf(".") >= 0 -> {
                    et_username.error = "Masukkan Username tanpa titik (.)"
                    et_username.requestFocus()
                }
                email == "" -> {
                    et_email.error = "Masukkan Email"
                    et_email.requestFocus()
                }
                password == "" -> {
                    et_password.error = "Masukkan Password"
                    et_password.requestFocus()
                }
                else -> {
                    saveUsername(name, username, email, password)
                }
            }
        }

        iv_back.setOnClickListener{
            onBackPressed()
        }
    }

    private fun saveUsername(name: String, username: String, email: String, password: String) {
        val user = User()
        user.also {
            it.name = name
            it.username = username
            it.email = email
            it.password = password
        }
        usernameValidation(username, user)
    }

    private fun usernameValidation(username: String, user: User) {
        dbReference.child(username).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                when (dataSnapshot.getValue(User::class.java)) {
                    null -> {
                        dbReference.child(username).setValue(user)
                        preferences.apply {
                            setValue("name", user.name.toString())
                            setValue("username", user.username.toString())
                            setValue("email", user.email.toString())
                            setValue("saldo", user.saldo.toString())
                            setValue("login", true)
                        }
                        val intent = Intent(this@SignUpActivity, AddPhotoActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else -> Toast.makeText(this@SignUpActivity, "Username sudah digunakan", Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignUpActivity, databaseError.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}