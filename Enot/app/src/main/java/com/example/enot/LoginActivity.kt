package com.example.enot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.enot.activities.MainActivity
import com.example.enot.activities.RecoverActivity
import com.example.enot.activities.RegistrationActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var emailLogin : EditText
    private lateinit var passwordLogin : EditText
    private lateinit var loginButton : Button
    private lateinit var registrationButton : Button
    private lateinit var recoverButton : Button
    val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (auth.currentUser?.uid != null) {
            startActivity(Intent(this, MainActivity::class.java))
        }
        setContentView(R.layout.activity_login)

        init()

        loginButton.setOnClickListener {

            val email = emailLogin.text.toString()
            val password = passwordLogin.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {return@addOnCompleteListener}
            }

        }

        registrationButton.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }

        recoverButton.setOnClickListener {
            startActivity(Intent(this, RecoverActivity::class.java))
        }

    }

    private fun init() {

        emailLogin = findViewById(R.id.loginEmail)
        passwordLogin = findViewById(R.id.loginPassword)
        loginButton = findViewById(R.id.logInButton)
        registrationButton = findViewById(R.id.registrationButton)
        recoverButton = findViewById(R.id.recoverButton)

    }

}