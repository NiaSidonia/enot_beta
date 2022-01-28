package com.example.enot.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.enot.LoginActivity
import com.example.enot.R
import com.example.enot.models.UserInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegistrationActivity : AppCompatActivity() {

    private lateinit var fullName : EditText
    private lateinit var emailRegistration : EditText
    private lateinit var passwordRegistration : EditText
    private lateinit var repeatPassword : EditText
    private lateinit var registrationButton : Button
    val refUser = FirebaseDatabase.getInstance().getReference("UserInfo")
    val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        init()

        registrationButton.setOnClickListener {

            val name = fullName.text.toString()
            val email = emailRegistration.text.toString()
            val password = passwordRegistration.text.toString()
            val repeat = repeatPassword.text.toString()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || repeat.isEmpty()) {
                return@setOnClickListener
            } else if (password != repeat) {
                return@setOnClickListener
            } else {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        val userInfo = UserInfo(name, email)
                        refUser.child(auth.currentUser?.uid!!).setValue(userInfo)

                        startActivity(Intent(this, LoginActivity::class.java))
                    } else {return@addOnCompleteListener}
                }
            }

        }

    }

    private fun init() {
        fullName = findViewById(R.id.identityRegister)
        emailRegistration = findViewById(R.id.emailRegister)
        passwordRegistration = findViewById(R.id.passwordRegister)
        repeatPassword = findViewById(R.id.repeatPassword)
        registrationButton = findViewById(R.id.registrationButton)
    }

}