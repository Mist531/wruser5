package com.example.worldcinemafinaly

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.goTo_sIgnUp
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.email_signUp
import okhttp3.*
import java.io.IOException

class SignUp : AppCompatActivity() {

    private val passwordRegular = "[a-zA-z0-9]{8,}"
    private val emailRegular = "[a-zA-z0-9]+@[a-zA-z0-9]+\\.[a-zA-z]{1,3}"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        arrow_signUp.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        registration.setOnClickListener{
            if (email_signUp.text.isNotEmpty() and password_signUp.text.isNotEmpty()) {
                if (email_signUp.text.toString().trim().matches(emailRegular.toRegex())) {
                    if (password_signUp.text.toString().trim().matches(passwordRegular.toRegex())) {
                        regist()
                    } else password_signUp.error = "Введён неккоректный пароль"
                } else email_signUp.error = "Введён неккоректный email"
            }
            else
                if (email_signUp.text.isEmpty()) email_signUp.error = "Заполните email"
                if (password_signUp.text.isEmpty()) password_signUp.error = "Заполните пароль"
        }
    }

    private fun regist() {
        try {
            val client = OkHttpClient()
            val url = BuildConfig.Server

            val email = email_signUp.text
            val password = password_signUp.text

            val formBody: RequestBody = FormBody.Builder()
                .add("email", "$email")
                .add("password", "$password")
                .build()

            val request = Request.Builder()
                .url("$url/auth/register")
                .post(formBody)
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    runOnUiThread {
                        Toast.makeText(this@SignUp, "Регистрация не получилась", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.code == 201) {
                        runOnUiThread {
                            Toast.makeText(this@SignUp, "Ура Ура", Toast.LENGTH_SHORT).show()
                        }
                    }
                    if (response.code == 400) {
                        runOnUiThread {
                            Toast.makeText(this@SignUp, "Упс", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
        catch (e: IOException) {
            Toast.makeText(this@SignUp, "Исключение", Toast.LENGTH_SHORT)
        }
    }
}