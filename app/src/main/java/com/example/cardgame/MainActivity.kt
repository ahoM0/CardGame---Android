package com.example.cardgame


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cardgame.enums.Categorias
import com.example.cardgame.enums.Estado
import com.example.cardgame.enums.GameEstado
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    // Comienza el juego y por defecto asignamos al adaptador unas cartas de la categoria ANIMALES por defecto
    // Cuando se elige la categoria ya comienza el juego con las cartas de esa categoria
    private var game = Game(GameEstado.SINCOMENZAR, Categorias.ANIMALES)
    private lateinit var adaptador : AdaptadorCartas
    private  var carta1: Carta? = null
    private  var carta2: Carta? = null
    private var vidas = 10
    private var parejas = 0
    private lateinit var recycleview : RecyclerView
    private lateinit var b_jugar : Button
    private lateinit var mensajeFinal : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Asignamos como action Bar el toolbar creado
        setSupportActionBar(findViewById(R.id.toolbar))

        // Guardamos en variables elementos del layout que vamos a utilizar
        mensajeFinal = findViewById(R.id.mensajeFinal)
        recycleview = findViewById(R.id.recycle_view)
        b_jugar = findViewById(R.id.b_jugar)

        // Creamos el layout y el adaptador para el RecycleView
        var layoutmanager = GridLayoutManager(this, 4)
        adaptador = AdaptadorCartas(game.getCards(), {carta -> onCardSelected(carta)})

        // Asignamos el layout que va ser un GridLayout y el adaptador al RecycleView
        recycleview.layoutManager=layoutmanager
        recycleview.adapter=adaptador

        // Desactivamos el recycle view hasta que se elija una categoria para jugar
        recycleview.isEnabled = false
        recycleview.visibility = View.INVISIBLE


        // Listener del boton jugar
        b_jugar.setOnClickListener(){
            if(game.getState() != GameEstado.INICIADO){
                // Si la categoria no esta elegida la pide abriendo el NavigationView
                // Cuando selecciona una categoria el juego cambia al estado GameEstado.INICIADO
                findViewById<DrawerLayout>(R.id.drawerlayout).openDrawer(GravityCompat.START)
            }

        }

        // Listener para las opciones del nav_view
        findViewById<NavigationView>(R.id.nav_view).setNavigationItemSelectedListener {
            when (it.itemId){
                R.id.reiniciar -> {
                    // Reiniciamos el juego con las cartas de la categoria que estaba definida.
                    game.reiniciarGame()

                    // Gestionamos varios elementos y variable para el comiezo de un nuevo juego.
                    gestionarElementos(false)

                    // Notificamos al adaptador
                    adaptador.notifyDataSetChanged()

                    //Cerramos el NavigationView
                    findViewById<DrawerLayout>(R.id.drawerlayout).closeDrawer(GravityCompat.START)
                    true
                }
                R.id.animales -> {
                    findViewById<TextView>(R.id.categoria_juego).text="ANIMALES"

                    // Define la categoria de ANIMALES, define el estado del Juego como INICIADO
                    // Y llena el array de cartas de objetos carta con Animales y las baraja.
                    game.jugarCartasAnimales()

                    // Gestionamos varios elementos y variable para el comiezo de un nuevo juego.
                    gestionarElementos(false)

                    // Tapamos las cartas para el comienzo del juego
                    game.taparCartas()

                    // Notificamos al adaptador
                    adaptador.notifyDataSetChanged()

                    // Cerramos el NavigationView
                    findViewById<DrawerLayout>(R.id.drawerlayout).closeDrawer(GravityCompat.START)
                    true
                }
                R.id.personas -> {
                    findViewById<TextView>(R.id.categoria_juego).text="PERSONAS"

                    // Define la categoria de PERSONAS, define el estado del Juego como INICIADO
                    // Y llena el array de cartas de objetos carta con PERSONAS y las baraja.
                    game.jugarCartasPersonas()

                    // Gestionamos varios elementos y variable para el comiezo de un nuevo juego.
                    gestionarElementos(false)

                    // Tapamos las cartas para el comienzo del juego
                    game.taparCartas()

                    // Notificamos al adaptador
                    adaptador.notifyDataSetChanged()

                    // Cerramos el NavigationView
                    findViewById<DrawerLayout>(R.id.drawerlayout).closeDrawer(GravityCompat.START)
                    true
                }
                R.id.comida -> {
                    findViewById<TextView>(R.id.categoria_juego).text="COMIDA"

                    // Define la categoria de COMIDA, define el estado del Juego como INICIADO
                    // Y llena el array de cartas de objetos carta con COMIDA y las baraja.
                    game.jugarCartasComida()

                    // Gestionamos varios elementos y variable para el comiezo de un nuevo juego.
                    gestionarElementos(false)

                    // Tapamos las cartas para el comienzo del juego
                    game.taparCartas()

                    // Notificamos al adaptador
                    adaptador.notifyDataSetChanged()

                    // Cerramos el NavigationView
                    findViewById<DrawerLayout>(R.id.drawerlayout).closeDrawer(GravityCompat.START)
                    true
                }
                else -> super .onOptionsItemSelected(it)
            }
        }

    }
    // Listener de cada carta, cada vez que se toque una carta llamara aqui recogiendo la carta
    fun onCardSelected(carta: Carta){
        Log.e("JUEGO","La carta seleccionada tiene ID -> "+carta.id)

        // Primero la carta que seleccione no debe estar descubierta
        if (carta.estado != Estado.DESCUBIERTA){
            if (carta1 == null && carta2 == null){
                // Destapamos la carta
                carta.destapar()
                carta1 = carta
            } else if (carta2 == null && carta != carta1){
                // Destapamos la carta
                carta.destapar()
                carta2 = carta

                // Si son iguales cambiamos el estado a DESCUBIERTA y se quedarían descubiertas toda la partida
                if (carta1?.codImgDestapada == carta2?.codImgDestapada){
                    game.cambiarEstado(carta1!!)
                    game.cambiarEstado(carta2!!)
                    carta1 = null
                    carta2 = null
                    parejas++
                    Log.e("JUEGO","Se ha sumado una pareja. NumParejas: "+parejas)
                    // Si ha emparejado todas las cartas ha ganado el juego
                    if (parejas == (game.getCards().size / 2)){
                        game.setEstado(GameEstado.FINALIZADOVICTORIA)

                        // Mostramos imagen de Victoria
                        mensajeFinal.setImageResource(R.drawable.win)

                        // Gestionamos los elementos para finalizar la partida
                        gestionarElementos(true)
                    }
                }
                // Si las dos cartas estan seleccionadas y son diferentes
            }else if (carta1 != null && carta2 != null && carta != carta1 && carta != carta2){
                if (carta1?.codImgDestapada != carta2?.codImgDestapada){
                    // Si son diferentes las tapamos de nuevo y restamos una vida
                    game.taparCartasGuardadas(carta1!!, carta2!!)
                    carta1 = null
                    carta2 = null
                    this.vidas--
                    Log.e("JUEGO","Se ha restado una vida. NumVidas: "+vidas)
                    var texto_vidas = findViewById<TextView>(R.id.t_vidas)
                    texto_vidas.text = "$vidas"

                    // Si las vidas se acaban el juego termina. [Vidas = 10]
                    if (vidas == 0){
                        game.setEstado(GameEstado.FINALIZADODERROTA)

                        // Mostramos imagen de Derrota
                        mensajeFinal.setImageResource(R.drawable.lose)

                        // Gestionamos los elementos para finalizar la partida
                        gestionarElementos(true)
                    }
                }
                // Y destapamos la carta que se acaba de seleccionar
                carta.destapar()
                carta1 = carta
            }
        }

        // Notificamos al adaptador
        adaptador.notifyDataSetChanged()
    }

    // Pintamos las opciones del menu_nav_view
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // Gestion de las opciones del menu_nav_view
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.abrir_nav_view -> {
                findViewById<DrawerLayout>(R.id.drawerlayout).openDrawer(GravityCompat.START)
                true
            }
            R.id.salir -> {
                finish()
                true
            }
            else ->  super.onOptionsItemSelected(item)

        }

    }

    // Funciones que gestiona varios elementos para el transcurso del juego.
    fun gestionarElementos(activar: Boolean) {
        // Esta condicion es para cuando termina el juego
        if (activar) {
            // Activamos el boton de jugar por si quieres otra partida
            b_jugar.isEnabled = true
            b_jugar.visibility = View.VISIBLE

            // Desactivamos el recycle view
            recycleview.isEnabled = false
            recycleview.visibility = View.INVISIBLE

            // Mostramos mensaje de finalizacion
            mensajeFinal.visibility = View.VISIBLE

            // Ocultamos el contador de vidas
            var texto_vidas = findViewById<Button>(R.id.t_vidas)
            texto_vidas.visibility = View.INVISIBLE
            findViewById<Button>(R.id.texto).visibility = View.INVISIBLE
        } else
        // Y condicion es para cuando comienza el juego
        {
            // Desacivamos el boton de jugar
            b_jugar.isEnabled = false
            b_jugar.visibility = View.INVISIBLE

            // Activamos el recycle view
            recycleview.isEnabled = true
            recycleview.visibility = View.VISIBLE

            // Ocultamos mensaje de Finalizacion
            mensajeFinal.visibility = View.INVISIBLE

            // Mostramos el contador de vidas
            var texto_vidas = findViewById<TextView>(R.id.t_vidas)
            texto_vidas.text  = "10"
            texto_vidas.visibility = View.VISIBLE
            findViewById<Button>(R.id.texto).visibility = View.VISIBLE
        }

        // Reestablecemos las vidas y las parejas
        vidas = 10
        parejas = 0

        // Reestablecemos las cartas
        carta1 = null
        carta2 = null
    }

}