package com.example.firebase1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.vdx.designertoast.DesignerToast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView1 = findViewById<ImageView>(R.id.iv1)
        Glide.with(this).load(R.drawable.sneakers).into(imageView1)
        
        setup()
    }

    private fun setup(){
        title = "Autenticación"



        signUpButton.setOnClickListener {
            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()) {
                if (passwordEditText.text.length < 6) {
                    //Toast.makeText(this, "La contraseña debe tener almenos 6 caracteres", Toast.LENGTH_LONG).show()
                    //Custom Toast
                    DesignerToast.Info(this, "La contraseña debe tener almenos 6 caracteres", Gravity.BOTTOM, Toast.LENGTH_LONG)
                } else {
                    FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(emailEditText.text.toString(),
                            passwordEditText.text.toString()).addOnCompleteListener {
                            if (it.isSuccessful) {
                                showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                            } else {
                                showAlert()
                            }
                        }
                }

            }
        }

        logInButton.setOnClickListener {
            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(emailEditText.text.toString(),
                        passwordEditText.text.toString()).addOnCompleteListener {
                        if (it.isSuccessful) {
                            //showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                            startActivity(Intent(this, HomeSneakersActivity::class.java))
                        } else {
                            showAlert()
                        }
                    }
            }
        }
    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String, provider: ProviderType) {
        val homeIntent = Intent(this, HomeActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
    }


}