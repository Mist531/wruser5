package com.example.worldcinemafinaly

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.goTo_sIgnUp
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.email_signUp

class SignIn : AppCompatActivity() {

    private val passwordRegular = "[a-zA-z0-9]{8,}"
    private val emailRegular = "[a-zA-z0-9]+@[a-zA-z0-9]+\\.[a-zA-z]{1,3}"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        arrow_signIn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        authorization.setOnClickListener{
            if (email_signIn.text.isNotEmpty() and password_signIn.text.isNotEmpty()) {
                if (email_signIn.text.toString().trim().matches(emailRegular.toRegex())) {
                    if (password_signIn.text.toString().trim().matches(passwordRegular.toRegex())) {
                        authoriz()
                    } else password_signIn.error = "Введён неккоректный пароль"
                } else email_signIn.error = "Введён неккоректный email"
            }
            else
                if (email_signIn.text.isEmpty()) email_signIn.error = "Заполните email"
            if (password_signIn.text.isEmpty()) password_signIn.error = "Заполните пароль"
        }
    }

    private fun authoriz() {
        Toast.makeText(this, "Алилуя", Toast.LENGTH_SHORT).show()
    }
}