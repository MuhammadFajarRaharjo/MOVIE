package com.belajar.movie.auth.signUp

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.belajar.movie.MasterActivity
import com.belajar.movie.R
import com.belajar.movie.utils.Preferences
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_add_photo.*
import java.util.*

class AddPhotoActivity : AppCompatActivity() {

    private var statusAdd = false
    lateinit var filePath: Uri
    lateinit var storage: FirebaseStorage
    lateinit var database: FirebaseDatabase
    lateinit var storageRef: StorageReference
    lateinit var dbReference: DatabaseReference
    lateinit var preferences: Preferences

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_photo)

        preferences = Preferences(this)
        storage = FirebaseStorage.getInstance()
        database = FirebaseDatabase.getInstance()
        storageRef = storage.getReference("Images")
        dbReference = database.getReference("User")

        tv_username.text = "${getString(R.string.welcome)}\n${preferences.getValueString("username")}"

        iv_add_photo.setOnClickListener {
            when (statusAdd){
                true -> {
                    statusAdd = false
                    btn_save.visibility = View.VISIBLE
                    iv_add_photo.setImageResource(R.drawable.ic_baseline_add_24)
                    iv_photo.setImageResource(R.drawable.ic_user_picture_default)
                }

                else -> {
                    ImagePicker.with(this)
                        .cameraOnly()
                        .crop()
                        .start()
                }
            }
        }

        btn_save.setOnClickListener {
            val progressDialog = ProgressDialog(this)
            progressDialog.run {
                setTitle("Uploading")
                show()
            }

            val ref = storageRef.child("${UUID.randomUUID()}")
            ref.putFile(filePath)
                .addOnSuccessListener {
                    progressDialog.dismiss()
                    Toast.makeText(this, "Uploaded", Toast.LENGTH_SHORT).show()

                    ref.downloadUrl.addOnSuccessListener {
                        dbReference.child("${preferences.getValueString("username")}")
                            .child("url")
                            .setValue(it.toString())
                        preferences.setValue("url", it.toString())
                    }

                    preferences.setValue("login", true)
                    finishAffinity()
                    Intent(this, MasterActivity::class.java).run {
                        startActivity(this)
                    }
                }
                .addOnFailureListener {
                    progressDialog.dismiss()
                    Toast.makeText(this, "Upload Failed", Toast.LENGTH_SHORT).show()
                }
                .addOnProgressListener { taskSnapshot ->
                    val progres = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                    progressDialog.setMessage("Upload ${progres.toInt()}%")
                }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                //Image Uri will not be null for RESULT_OK
                filePath = data?.data!!
                statusAdd = true

                Glide.with(this)
                    .load(filePath)
                    .apply(RequestOptions.circleCropTransform())
                    .into(iv_photo)

                btn_save.visibility = View.VISIBLE
                iv_add_photo.setImageResource(R.drawable.ic_baseline_delete_forever_24)
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