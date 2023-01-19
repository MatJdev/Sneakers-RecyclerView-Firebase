package com.example.firebase1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

enum class ProviderType {
    BASIC
}

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("Provider")
        setup(email ?: "", provider ?: "Basic")

        btnEntrar.setOnClickListener {
            startActivity(Intent(this, HomeSneakersActivity::class.java))
        }

        logOutButton2.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun setup(email: String, provider: String) {

        title = "Registro con éxito"
        emailTextView.text = email
        providerTextView.text = provider

        btnEntrar.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }
    }
}

