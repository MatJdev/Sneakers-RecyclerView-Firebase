package com.example.firebase1

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_home_sneakers.*


class HomeSneakersActivity : AppCompatActivity(), ElementoAdapter.onItemListener {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_FireBase1)
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
        setContentView(R.layout.activity_home_sneakers)

        setupRecyclerView1()
        prueba()

        btnVerTodo.setOnClickListener{
            setupRecyclerView1()
            btnVerTodo.setVisibility(View.INVISIBLE)
        }

    }

    private fun setupRecyclerView1(){
        var sneakers = ArrayList<Sneaker>()

        db.collection("sneakers").get().addOnSuccessListener { documents ->

            for (document in documents) {
                val wallItem = document.toObject(Sneaker::class.java)
                wallItem.name = document["name"].toString()
                wallItem.img = document["img"].toString()
                wallItem.color1 = document["color1"].toString()
                wallItem.color2 = document["color2"].toString()
                wallItem.date = document["date"].toString()
                wallItem.marca = document["marca"].toString()
                wallItem.modelo = document["modelo"].toString()
                wallItem.icon = document["icon"].toString()
                wallItem.resellPrice = document["resellPrice"].toString()
                wallItem.retailPrice = document["retailPrice"].toString()
                sneakers.add(wallItem)
            }
            var recycler = findViewById<RecyclerView>(R.id.rvSneakers)
            recycler.adapter = ElementoAdapter(this, sneakers,this)

            recycler.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        }.addOnFailureListener { exception ->
            Log.w(TAG, "Error getting documents.", exception)
        }


    }

    private fun prueba() {
        db.collection("sneakers").get().addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
        }.addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
        }
    }

    override fun onItemClick(nombre: String) {

    }

    override fun onIconClick(modelo: String) {
        var sneakers = ArrayList<Sneaker>()

        db.collection("sneakers").whereEqualTo("modelo", modelo).get().addOnSuccessListener { documents ->

            for (document in documents) {
                val wallItem = document.toObject(Sneaker::class.java)
                wallItem.name = document["name"].toString()
                wallItem.img = document["img"].toString()
                wallItem.color1 = document["color1"].toString()
                wallItem.color2 = document["color2"].toString()
                wallItem.date = document["date"].toString()
                wallItem.marca = document["marca"].toString()
                wallItem.modelo = document["modelo"].toString()
                wallItem.icon = document["icon"].toString()
                wallItem.resellPrice = document["resellPrice"].toString()
                wallItem.retailPrice = document["retailPrice"].toString()
                sneakers.add(wallItem)
            }
            var recycler = findViewById<RecyclerView>(R.id.rvSneakers)
            recycler.adapter = ElementoAdapter(this, sneakers,this)

            recycler.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        }.addOnFailureListener { exception ->
            Log.w(TAG, "Error getting documents.", exception)
        }

        btnVerTodo.setVisibility(View.VISIBLE)
    }

    override fun onInfoClick(nombre: String) {
        db.collection("sneakers").whereEqualTo("name", nombre).get().addOnSuccessListener {
            it.forEach {
                val homeIntent = Intent(this, InfoSneakerActivity::class.java).apply {
                    putExtra("name", it.get("name").toString())
                    putExtra("retail", it.get("retailPrice").toString())
                    putExtra("resell", it.get("resellPrice").toString())
                    putExtra("marca", it.get("marca").toString())
                    putExtra("modelo", it.get("modelo").toString())
                    putExtra("color1", it.get("color1").toString())
                    putExtra("color2", it.get("color2").toString())
                    putExtra("date", it.get("date").toString())
                    putExtra("img", it.get("img").toString())
                    putExtra("description", it.get("description").toString())
                }
                startActivity(homeIntent)
            }

        }

    }


    override fun onImagenItinerarioClick(imagen: String) {
        val intent = Intent(this, ImagenRecyclerView::class.java)
        intent.putExtra("imageUrl", imagen)
        startActivity(intent)
    }

}