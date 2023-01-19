package com.example.firebase1

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vdx.designertoast.DesignerToast


class ElementoAdapter(
    var context: Context,
    var sneakers: ArrayList<Sneaker>,
    private val itemClickListener: HomeSneakersActivity
                        ): RecyclerView.Adapter<ElementoAdapter.ViewHolder>() {

    interface onItemListener {
        fun onImagenItinerarioClick(image: String)
        fun onItemClick(sneakerId: String)
        fun onIconClick(modelo: String)
        fun onInfoClick(nombre: String)
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        var imagenSneaker = view.findViewById<ImageView>(R.id.item_image)
        var nombreSneaker = view.findViewById<TextView>(R.id.item_title)
        var retailSneaker = view.findViewById<TextView>(R.id.item_retail)
        var resellSneaker = view.findViewById<TextView>(R.id.item_resell)
        var modeloSneaker = view.findViewById<TextView>(R.id.item_detail)
        var marcaSneaker = view.findViewById<TextView>(R.id.item_marca)
        var color1Sneaker = view.findViewById<TextView>(R.id.item_color1)
        var color2Sneaker = view.findViewById<TextView>(R.id.item_color2)
        var dateSneaker = view.findViewById<TextView>(R.id.item_date)
        var iconSneaker = view.findViewById<ImageView>(R.id.item_icon)


        fun bind(sneaker: Sneaker, context: Context) {
            nombreSneaker.text = sneaker.name
            modeloSneaker.text = "Modelo: " + sneaker.modelo
            marcaSneaker.text = "Marca: " + sneaker.marca
            retailSneaker.text = "Retail: " + sneaker.retailPrice + "€"
            resellSneaker.text = "Resell: " + sneaker.resellPrice + "€"
            color1Sneaker.text = sneaker.color1 + "/"
            color2Sneaker.text = sneaker.color2
            dateSneaker.text = "Lanzamiento: " + sneaker.date


            Log.i("IMAGEN", "Imagen: ${sneaker.img}")
            Glide.with(context).load(sneaker.img).into(imagenSneaker);
            Glide.with(context).load(sneaker.icon).into(iconSneaker)

            view.setOnClickListener {
                itemClickListener.onItemClick(sneaker.name)

                //Custom Toast
                DesignerToast.Info(view.context, "Sneaker pulsado", Gravity.BOTTOM, Toast.LENGTH_LONG)

                //Toast.makeText(view.context, "Sneaker pulsado", Toast.LENGTH_SHORT).show()

                itemClickListener.onInfoClick(sneaker.name)

            }
            imagenSneaker.setOnClickListener {
                itemClickListener.onImagenItinerarioClick(sneaker.img)
            }

            iconSneaker.setOnClickListener {
                //context.startActivity(Intent(context, InfoSneakerActivity::class.java))
                itemClickListener.onIconClick(sneaker.modelo)
            }



        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        //Aqui se infla la vista xml con datos

        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.card_layout,parent,false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var sneaker = sneakers[position]
        holder.bind(sneaker, context)
    }

    override fun getItemCount(): Int {
        return sneakers.size
    }

}
