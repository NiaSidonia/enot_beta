package com.example.enot.activities.optionActivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.enot.R
import com.example.enot.activities.MainActivity
import com.example.enot.fragments.ProfileFragment
import com.example.enot.models.UserImage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProfilePhotoUpload : AppCompatActivity() {

    private lateinit var inputURL : EditText
    private lateinit var changeButton : Button
    val refImage = FirebaseDatabase.getInstance().getReference("UserImage")
    val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_photo_upload)

        init()

        changeButton.setOnClickListener {

            val url = inputURL.text.toString()

            if (url.isEmpty()) {

                return@setOnClickListener

            } else {

                val userimage = UserImage(url)
                refImage.child(auth.currentUser?.uid!!).setValue(userimage)

                startActivity(Intent(this, MainActivity::class.java))

            }

        }

    }

    private fun init() {
        inputURL = findViewById(R.id.inputURL)
        changeButton = findViewById(R.id.changeButton)
    }

}