package com.example.firebase1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.PhotoView

class ImagenRecyclerView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imagen_recycler_view)

        var photoView = findViewById<PhotoView>(R.id.photo_view)

        if(intent.extras != null){
            Glide.with(this).load(intent.getStringExtra("imageUrl")).into(photoView)
        }
        title = "Imagen Zapatilla"
    }
}