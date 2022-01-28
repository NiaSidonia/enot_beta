package com.example.enot.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.enot.LoginActivity
import com.example.enot.R
import com.google.firebase.auth.FirebaseAuth

class RecoverActivity : AppCompatActivity() {

    private lateinit var recoverEmail : EditText
    private lateinit var recoverButton : Button
    val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recover)

        init()

        recoverButton.setOnClickListener {

            val email = recoverEmail.text.toString()

            if (email.isEmpty()) {
                return@setOnClickListener
            } else {
                auth.sendPasswordResetEmail(email)

                startActivity(Intent(this, LoginActivity::class.java))
            }

        }

    }

    private fun init() {
        recoverEmail = findViewById(R.id.emailRecover)
        recoverButton = findViewById(R.id.recoverButton)
    }

}