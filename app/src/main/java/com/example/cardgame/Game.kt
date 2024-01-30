package com.example.cardgame

import com.example.cardgame.enums.Categorias
import com.example.cardgame.enums.Estado
import com.example.cardgame.enums.GameEstado

class Game {
    private var state : GameEstado
    private var categoria : Categorias

    constructor(state : GameEstado, cat: Categorias){
        this.state = state
        this.categoria=cat
    }

    // Para el listado de cartas he usado un arraylist de cartas el cual vamos a ir
    // modificando dependiendo de la categoria. Pense que lo mas sencillo era esto.
    var cartas: ArrayList<Carta> = arrayListOf()

    // Por defecto las cartas seran animales
    init {
        jugarCartasAnimales()
    }

    // Se asignan las cartas de la categoria de ANIMALES al array de las cartas
    fun jugarCartasAnimales(){
        this.categoria = Categorias.ANIMALES
        this.state = GameEstado.INICIADO
        this.cartas.clear()
        this.cartas.addAll(
            listOf(
                Carta(1, Estado.OCULTA, R.drawable.foto_1_animales, R.drawable.poker_svgrepo_com),
                Carta(2, Estado.OCULTA, R.drawable.foto_1_animales, R.drawable.poker_svgrepo_com),
                Carta(3, Estado.OCULTA, R.drawable.foto_2_animales, R.drawable.poker_svgrepo_com),
                Carta(4, Estado.OCULTA, R.drawable.foto_2_animales, R.drawable.poker_svgrepo_com),
                Carta(5, Estado.OCULTA, R.drawable.foto_3_animales, R.drawable.poker_svgrepo_com),
                Carta(6, Estado.OCULTA, R.drawable.foto_3_animales, R.drawable.poker_svgrepo_com),
                Carta(7, Estado.OCULTA, R.drawable.foto_4_animales, R.drawable.poker_svgrepo_com),
                Carta(8, Estado.OCULTA, R.drawable.foto_4_animales, R.drawable.poker_svgrepo_com),
                Carta(9, Estado.OCULTA, R.drawable.foto_5_animales, R.drawable.poker_svgrepo_com),
                Carta(10, Estado.OCULTA, R.drawable.foto_5_animales, R.drawable.poker_svgrepo_com),
                Carta(11, Estado.OCULTA, R.drawable.foto_6_animales, R.drawable.poker_svgrepo_com),
                Carta(12, Estado.OCULTA, R.drawable.foto_6_animales, R.drawable.poker_svgrepo_com),
                Carta(13, Estado.OCULTA, R.drawable.foto_7_animales, R.drawable.poker_svgrepo_com),
                Carta(14, Estado.OCULTA, R.drawable.foto_7_animales, R.drawable.poker_svgrepo_com),
                Carta(15, Estado.OCULTA, R.drawable.foto_8_animales, R.drawable.poker_svgrepo_com),
                Carta(16, Estado.OCULTA, R.drawable.foto_8_animales, R.drawable.poker_svgrepo_com)
            )
        )
        // Barajamos las cartas
        this.cartas.shuffle()
    }

    // Se asignan las cartas de la categoria de PERSONAS al array de las cartas
    fun jugarCartasPersonas(){
        this.categoria = Categorias.PERSONAS
        this.state = GameEstado.INICIADO
        this.cartas.clear()
        this.cartas.addAll( listOf(
            Carta(1, Estado.OCULTA, R.drawable.personas_foto_1, R.drawable.poker_svgrepo_com),
            Carta(2, Estado.OCULTA, R.drawable.personas_foto_1, R.drawable.poker_svgrepo_com),
            Carta(3, Estado.OCULTA, R.drawable.personas_foto_2, R.drawable.poker_svgrepo_com),
            Carta(4, Estado.OCULTA, R.drawable.personas_foto_2, R.drawable.poker_svgrepo_com),
            Carta(5, Estado.OCULTA, R.drawable.personas_foto_3, R.drawable.poker_svgrepo_com),
            Carta(6, Estado.OCULTA, R.drawable.personas_foto_3, R.drawable.poker_svgrepo_com),
            Carta(7, Estado.OCULTA, R.drawable.personas_foto_4, R.drawable.poker_svgrepo_com),
            Carta(8, Estado.OCULTA, R.drawable.personas_foto_4, R.drawable.poker_svgrepo_com),
            Carta(9, Estado.OCULTA, R.drawable.personas_foto_5, R.drawable.poker_svgrepo_com),
            Carta(10, Estado.OCULTA, R.drawable.personas_foto_5, R.drawable.poker_svgrepo_com),
            Carta(11, Estado.OCULTA, R.drawable.personas_foto_6, R.drawable.poker_svgrepo_com),
            Carta(12, Estado.OCULTA, R.drawable.personas_foto_6, R.drawable.poker_svgrepo_com),
            Carta(13, Estado.OCULTA, R.drawable.personas_foto_7, R.drawable.poker_svgrepo_com),
            Carta(14, Estado.OCULTA, R.drawable.personas_foto_7, R.drawable.poker_svgrepo_com),
            Carta(15, Estado.OCULTA, R.drawable.personas_foto_8, R.drawable.poker_svgrepo_com),
            Carta(16, Estado.OCULTA, R.drawable.personas_foto_8, R.drawable.poker_svgrepo_com)
        ))
        // Barajamos las cartas
        this.cartas.shuffle()
    }


    // Se asignan las cartas de la categoria COMIDA al array de las cartas
    fun jugarCartasComida(){
        this.categoria = Categorias.COMIDA
        this.state = GameEstado.INICIADO
        this.cartas.clear()
        this.cartas.addAll(  listOf(
            Carta(1, Estado.OCULTA, R.drawable.comida_foto_1, R.drawable.poker_svgrepo_com),
            Carta(2, Estado.OCULTA, R.drawable.comida_foto_1, R.drawable.poker_svgrepo_com),
            Carta(3, Estado.OCULTA, R.drawable.comida_foto_2, R.drawable.poker_svgrepo_com),
            Carta(4, Estado.OCULTA, R.drawable.comida_foto_2, R.drawable.poker_svgrepo_com),
            Carta(5, Estado.OCULTA, R.drawable.comida_foto_3, R.drawable.poker_svgrepo_com),
            Carta(6, Estado.OCULTA, R.drawable.comida_foto_3, R.drawable.poker_svgrepo_com),
            Carta(7, Estado.OCULTA, R.drawable.comida_foto_4, R.drawable.poker_svgrepo_com),
            Carta(8, Estado.OCULTA, R.drawable.comida_foto_4, R.drawable.poker_svgrepo_com),
            Carta(9, Estado.OCULTA, R.drawable.comida_foto_5, R.drawable.poker_svgrepo_com),
            Carta(10, Estado.OCULTA, R.drawable.comida_foto_5, R.drawable.poker_svgrepo_com),
            Carta(11, Estado.OCULTA, R.drawable.comida_foto_6, R.drawable.poker_svgrepo_com),
            Carta(12, Estado.OCULTA, R.drawable.comida_foto_6, R.drawable.poker_svgrepo_com),
            Carta(13, Estado.OCULTA, R.drawable.comida_foto_7, R.drawable.poker_svgrepo_com),
            Carta(14, Estado.OCULTA, R.drawable.comida_foto_7, R.drawable.poker_svgrepo_com),
            Carta(15, Estado.OCULTA, R.drawable.comida_foto_8, R.drawable.poker_svgrepo_com),
            Carta(16, Estado.OCULTA, R.drawable.comida_foto_8, R.drawable.poker_svgrepo_com)
        ))
        // Barajamos las cartas
        this.cartas.shuffle()

    }


    // Funcion para tapar las dos cartas que estaban destapadas y eran diferentes.
    fun taparCartasGuardadas(carta1 : Carta, carta2 : Carta){
        cartas.forEach {carta ->
            if (carta == carta1){
                carta.destapar()
            }
            if (carta == carta2){
                carta.destapar()
            }
        }
    }
    // Cambia el estado de las cartas cuando se emparejan
    fun cambiarEstado(carta1: Carta){
       cartas.forEach {carta ->
           if (carta == carta1){
               carta.estado = Estado.DESCUBIERTA
           }
       }
    }
    // Funcion ue reinicia el juego con la categoria puesta.
    fun reiniciarGame(){
        // Dependiendo de la categoria de juego el array sera de una categoria o otra.
        if(this.categoria == Categorias.PERSONAS){
            jugarCartasPersonas()
        }else if (this.categoria == Categorias.COMIDA){
            jugarCartasComida()
        }else if (this.categoria == Categorias.ANIMALES){
            jugarCartasAnimales()
        }
        // Tapamos las cartas
        taparCartas()
    }
    // Devuelve el array de cartas
    fun getCards() : ArrayList<Carta> {
        return cartas
    }
    // Tapa todas las cartas disponibles
    fun taparCartas(){
        this.cartas.forEach { cartita ->
            // Ocultamos la carta
            cartita.estado= Estado.OCULTA

            // Cambiamos el lugar de las caras de la carta.
            cartita.destapar()
        }
    }
    // Nos devuelve el estado del juego
     fun getState() : GameEstado{
        return this.state
    }
    // Define el estado del juego
    fun setEstado(estado :GameEstado){
        this.state = estado
    }

}