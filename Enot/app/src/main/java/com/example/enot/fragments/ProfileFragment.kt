package com.example.enot.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.enot.LoginActivity
import com.example.enot.R
import com.example.enot.activities.optionActivities.ProfilePhotoUpload
import com.example.enot.models.UserImage
import com.example.enot.models.UserInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.math.log

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var logOutButton : Button
    private lateinit var changePhoto : Button
    private lateinit var profileImage : ImageView
    private lateinit var username : TextView
    private lateinit var emailProfile : TextView
    val refUser = FirebaseDatabase.getInstance().getReference("UserInfo")
    val refImage = FirebaseDatabase.getInstance().getReference("UserImage")
    val auth = FirebaseAuth.getInstance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logOutButton = view.findViewById(R.id.logOutButton)
        profileImage = view.findViewById(R.id.profileImage)
        username = view.findViewById(R.id.username)
        emailProfile = view.findViewById(R.id.emailProfile)
        changePhoto = view.findViewById(R.id.changePhoto)

        refUser.child(auth.currentUser?.uid!!).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val info = dataSnapshot.getValue(UserInfo::class.java) ?:return
                username.text = info.username
                emailProfile.text = info.email
            }

            override fun onCancelled(error: DatabaseError) {
                return
            }

        })

        logOutButton.setOnClickListener {

            auth.signOut()
            startActivity(Intent(activity, LoginActivity::class.java))
            activity?.finish()

        }

        changePhoto.setOnClickListener {

            startActivity(Intent(activity, ProfilePhotoUpload::class.java))

        }

        refImage.child(auth.currentUser?.uid!!).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val url = snapshot.getValue(UserImage::class.java) ?:return

                Glide.with(this@ProfileFragment).load(url.profileImage)
                    .placeholder(R.drawable.artworks_000039099950_dq7vot_t500x500)
                    .into(profileImage)
            }

            override fun onCancelled(error: DatabaseError) {
                return
            }

        })

    }

}