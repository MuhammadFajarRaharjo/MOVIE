package com.belajar.movie.profile

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.belajar.movie.MasterActivity
import com.belajar.movie.R
import com.belajar.movie.auth.signIn.User
import com.belajar.movie.utils.Preferences
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_edit_profile.*
import java.util.*

class EditProfileActivity : AppCompatActivity() {

    private lateinit var filePath: Uri
    lateinit var storage: FirebaseStorage
    lateinit var storageRef: StorageReference
    private lateinit var reference: DatabaseReference
    private lateinit var preferences: Preferences
    private var update = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        preferences = Preferences(this)
        reference = FirebaseDatabase.getInstance().getReference("User")
        storage = FirebaseStorage.getInstance()
        storageRef = storage.getReference("Images")

//        Set value edit Txt
        preferences.apply {
            if (!getValueString("url").isNullOrBlank()) {
                Glide.with(this@EditProfileActivity)
                    .load(getValueString("url"))
                    .apply(RequestOptions.circleCropTransform())
                    .into(iv_photo)
                iv_add_photo.setImageResource(R.drawable.ic_baseline_edit_24)
            }
            et_name.setText(getValueString("name"))
            et_username.setText(getValueString("username"))
            et_email.setText(getValueString("email"))
            et_password.setText(getValueString("name"))
        }

//        Get password User
        reference.child(preferences.getValueString("username").toString())
            .addListenerForSingleValueEvent(
                object : ValueEventListener {
                    override fun onDataChange(p0: DataSnapshot) {
                        val user = p0.getValue(User::class.java)
                        et_password.setText(user!!.password)
                    }

                    override fun onCancelled(p0: DatabaseError) {
                        Toast.makeText(this@EditProfileActivity, p0.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                })

//        Back
        iv_back.setOnClickListener { onBackPressed() }

//        Take Photo
        iv_add_photo.setOnClickListener {
            ImagePicker.with(this)
                .cameraOnly()
                .crop()
                .start()
        }

//        Update Data
        btn_update.setOnClickListener {
            val name: String = et_name.text.toString()
            val username: String = et_username.text.toString()
            val email: String = et_email.text.toString()
            val password: String = et_password.text.toString()

//            Validation Character Edit Text
            val failedCharacter = listOf('.', ',', '\\', '/', '@', ' ')
            when {
                name == "" -> {
                    et_name.error = "Masukkan Nama"
                    et_name.requestFocus()
                }
                username == "" -> {
                    et_username.error = "Masukkan Username"
                    et_username.requestFocus()
                }
                username.indexOfAny(failedCharacter.toCharArray()) >= 0 -> {
                    et_username.error = "Masukkan Username tanpa $failedCharacter"
                    et_username.requestFocus()
                }
                email == "" || !email.contains("@") -> {
                    et_email.error = "Masukkan Email"
                    et_email.requestFocus()
                }
                password == "" -> {
                    et_password.error = "Masukkan Password"
                    et_password.requestFocus()
                }
                else -> {
                    update(
                        name,
                        username,
                        email,
                        password,
                        preferences.getValueString("saldo").toString(),
                        preferences.getValueString("url").toString()
                    )
                }
            }
        }
    }

    //    function Update Data from Edit Text
    private fun update(
        name: String,
        username: String,
        email: String,
        password: String,
        saldo: String,
        url: String
    ) {
        val user = User(
            name = name,
            username = username,
            email = email,
            password = password,
            saldo = saldo,
            url = url
        )

        when (username) {
            preferences.getValueString("username") -> {
                reference.child(username).updateChildren(user.toMap()).addOnSuccessListener {
                    preferences.apply {
                        setValue("name", user.name.toString())
                        setValue("email", user.email.toString())
                        setValue("password", user.password.toString())
                    }
                    if (update) {
                        updatePhoto()
                    }
                    else {
                        Toast.makeText(this, "Data berhasil di Update", Toast.LENGTH_LONG).show()
                        Intent(this, MasterActivity::class.java).run { startActivity(this) }
                    }
                }.addOnFailureListener {
                    Toast.makeText(this, "Data gagal di Update", Toast.LENGTH_LONG).show()
                }
            }
            else -> {
                usernameValidation(username, user)
            }
        }
    }

    private fun removeAndCreate(username: String, user: User) {
        reference.child(preferences.getValueString("username").toString()).removeValue().addOnSuccessListener {
            reference.child(username).setValue(user)
            preferences.apply {
                setValue("name", user.name.toString())
                setValue("username", user.username.toString())
                setValue("email", user.email.toString())
                setValue("password", user.password.toString())
            }
            if (update) updatePhoto()
            else Intent(this, MasterActivity::class.java).run { startActivity(this) }
        }
    }

    private fun usernameValidation(username: String, user: User) {
        reference.child(username).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                when (dataSnapshot.getValue(User::class.java)) {
                    null -> {
                        removeAndCreate(username, user)
                    }
                    else -> {
                        Toast.makeText(
                            this@EditProfileActivity,
                            "Username sudah digunakan",
                            Toast.LENGTH_LONG
                        ).show()
                        et_username.requestFocus()
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@EditProfileActivity, databaseError.message, Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    //    Function Update or Add Photo
    private fun updatePhoto() {
        val progressDialog = ProgressDialog(this)
        progressDialog.run {
            setTitle("Uploading")
            show()
        }
        val ref = storageRef.child("${UUID.randomUUID()}")
        ref.putFile(filePath)
            .addOnSuccessListener {
                progressDialog.dismiss()
                Toast.makeText(this, "Data berhasil di Update", Toast.LENGTH_LONG).show()

//                Delete Old Image
                val image = storage.getReferenceFromUrl(preferences.getValueString("url").toString())
                image.delete().addOnSuccessListener {

//                    get Image Url
                    ref.downloadUrl.addOnSuccessListener {
                        reference.child("${preferences.getValueString("username")}")
                            .child("url")
                            .setValue(it.toString())
                        preferences.setValue("url", it.toString())
                        Intent(this, MasterActivity::class.java).run { startActivity(this) }
                    }
                }
            }
            .addOnFailureListener {
                progressDialog.dismiss()
                Toast.makeText(this, "Upload Failed", Toast.LENGTH_SHORT).show()
            }
            .addOnProgressListener { taskSnapshot ->
                val progress =
                    100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                progressDialog.setMessage("Upload ${progress.toInt()}%")
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                //Image Uri will not be null for RESULT_OK
                filePath = data?.data!!
                update = true
                Glide.with(this)
                    .load(filePath)
                    .apply(RequestOptions.circleCropTransform())
                    .into(iv_photo)

                iv_add_photo.setImageResource(R.drawable.ic_baseline_edit_24)
            }
            ImagePicker.RESULT_ERROR -> {
                Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }
    }
}