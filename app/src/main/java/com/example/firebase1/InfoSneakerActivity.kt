package com.example.firebase1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_info_sneaker.*

class InfoSneakerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
        setContentView(R.layout.activity_info_sneaker)

        val bundle = intent.extras
        val nameValor = bundle?.getString("name")
        val retailValor = bundle?.getString("retail")
        val resellValor = bundle?.getString("resell")
        val marcaValor = bundle?.getString("marca")
        val modeloValor = bundle?.getString("modelo")
        val dateValor = bundle?.getString("date")
        val color1Valor = bundle?.getString("color1")
        val color2Valor = bundle?.getString("color2")
        val imgValor = bundle?.getString("img")
        val descripcion = bundle?.getString("description")


        name.text = nameValor
        retail.text = "Precio retail: " + retailValor + "€"
        resell.text = "Precio resell: " + resellValor + "€"
        marca.text = "Marca: " + marcaValor
        modelo.text = "Modelo: " + modeloValor
        date.text = "Fecha de lanzamiento: " + dateValor
        color1.text = color1Valor + "/"
        color2.text = color2Valor
        textDescription.text = descripcion

        Glide.with(this).load(imgValor).into(imagen)

    }
}