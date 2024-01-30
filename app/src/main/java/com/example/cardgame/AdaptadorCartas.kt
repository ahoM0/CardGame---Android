package com.example.cardgame

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorCartas(private val listaCartas : ArrayList<Carta>, private val onCardListener:(Carta) -> Unit) : RecyclerView.Adapter<AdaptadorCartas.ViewHolder>() {

    // Esta clase pinta los objetos en la vista
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imagen : ImageView = view.findViewById(R.id.marco_carta)

        // Funcion que llama el metodo onBindViewHolder para pintar la imagen de cada objeto
        fun pintar(carta : Carta, onCardListener:(Carta) -> Unit) {
            imagen.setImageResource(carta.codImgDestapada)
            itemView.setOnClickListener{onCardListener(carta)}
        }

    }

    // Inflamos la vista con el layout de la carta solo tiene un imageview
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorCartas.ViewHolder {
        var vista = LayoutInflater.from(parent.context).inflate(R.layout.carta_entrada, parent, false)
        return ViewHolder(vista)
    }

    // Relaciona cada imagen de la carta con la imagen del holder
    override fun onBindViewHolder(holder: AdaptadorCartas.ViewHolder, position: Int) {
        // Pillamos el objeto que toque y lo pintamos llamando al metodo pintar de la clase ViewHolder
        var carta : Carta = listaCartas.get(position)
        holder.pintar(carta, onCardListener)

    }


    // Devuelve el total de cartas que va a pintar
    override fun getItemCount(): Int {
       return listaCartas.size
    }

}