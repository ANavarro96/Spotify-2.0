package com.pmm.practica4

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageButton
import android.media.MediaPlayer.OnPreparedListener
import com.pmm.practica4.R
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import render.animations.Bounce
import render.animations.Render

class MainActivity : AppCompatActivity() {
    lateinit var izq: ImageButton
    lateinit var dcha: ImageButton
    lateinit var play: ImageButton
    lateinit var foto: ImageView
    lateinit var mediaPlayer: MediaPlayer
    var actual = "blink"

    /*
        El evento OnPrepared se lanzaría una vez, cuando el mp se encuentra listo para
        reproducir audio.
        En nuestro caso, como se obtiene el audio por medios locales, no hace falta caputar este evento.
        Si tuvieramos que obtener las canciones de la SD o por Streaming, entonces si nos interesaría
        capturar este evento.

    */
    private val funcionPrepared = OnPreparedListener { // Empezamos la canción
        mediaPlayer.start()
    }
    private val botones = View.OnClickListener { cambiarCanciones() }
    private val botonPlay = View.OnClickListener { pararCancion() }
    
    fun cambiarCanciones() {
        println(actual)
        if (actual.equals("blink", ignoreCase = true)) {
            mediaPlayer.stop()
            mediaPlayer.release()
            mediaPlayer = MediaPlayer.create(this, R.raw.pup)
            mediaPlayer.start()
            foto.setImageResource(R.drawable.pup)
            foto.scaleType = ImageView.ScaleType.FIT_START
            actual = "pup"
        } else {
            mediaPlayer.stop()
            mediaPlayer.release()
            mediaPlayer = MediaPlayer.create(this, R.raw.carousel)
            mediaPlayer.start()
            actual = "blink"
            foto.setImageResource(R.drawable.blink)
            foto.scaleType = ImageView.ScaleType.FIT_XY
        }
    }

    fun pararCancion() {
        if (!mediaPlayer.isPlaying) {
            mediaPlayer.start()
        } else {
            mediaPlayer.pause()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        izq = findViewById(R.id.izq)
        dcha = findViewById(R.id.dcha)
        play = findViewById(R.id.play)
        foto = findViewById(R.id.foto)
        foto.setScaleType(ImageView.ScaleType.CENTER_INSIDE)
        izq.setOnClickListener(botones)
        dcha.setOnClickListener(botones)
        play.setOnClickListener(botonPlay)

        // Asignamos la canción a sonar
        mediaPlayer = MediaPlayer.create(this, R.raw.carousel)
        // Establecemos el volumen. Puede ser que por defecto el volumen sea muy bajo
        mediaPlayer.setVolume(0.5f, 0.5f)
        // Empezamos la canción
        mediaPlayer.start()

        // mediaPlayer.setOnPreparedListener(funcionPrepared)
    }

    public override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}