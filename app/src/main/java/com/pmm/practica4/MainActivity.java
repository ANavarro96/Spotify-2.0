package com.pmm.practica4;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageButton izq;
    ImageButton dcha;
    ImageButton play;
    ImageView foto;
    MediaPlayer mediaPlayer;

    String actual = "punkyfer";
    /*
        El evento OnPrepared se lanzaría una vez, cuando el mp se encuentra listo para
        reproducir audio.
        En nuestro caso, como se obtiene el audio por medios locales, no hace falta caputar este evento.
        Si tuvieramos que obtener las canciones de la SD o por Streaming, entonces si nos interesaría c
        capturar este evento.

    */
    private MediaPlayer.OnPreparedListener funcionPrepared = new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {
            // Empezamos la canción
            mediaPlayer.start();
        }
    };


    private View.OnClickListener botones = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            cambiarCanciones();
        }
    };

    private View.OnClickListener botonPlay = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            pararCancion();
        }
    };

    public void cambiarCanciones(){
        System.out.println(actual);
        if (actual.equalsIgnoreCase("punkyfer")) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = MediaPlayer.create(this, R.raw.no_somos_nada);
            mediaPlayer.start();
            foto.setImageResource(R.drawable.nsn);
            actual = "nsn";
        }
        else {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = MediaPlayer.create(this, R.raw.punkyfer);
            mediaPlayer.start();
            actual = "punkyfer";
            foto.setImageResource(R.drawable.elueldlp);
        }

    }

    public void pararCancion(){
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
        else {
            mediaPlayer.pause();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        izq = findViewById(R.id.izq);
        dcha = findViewById(R.id.dcha);
        play = findViewById(R.id.play);
        foto = findViewById(R.id.foto);
        foto.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        izq.setOnClickListener(botones);
        dcha.setOnClickListener(botones);
        play.setOnClickListener(botonPlay);

        // Asignamos la canción a sonar
        mediaPlayer = MediaPlayer.create(this, R.raw.punkyfer);
        // Establecemos el volumen. Puede ser que por edefcto el volumen sea muy bajo
        mediaPlayer.setVolume(0.5f, 0.5f);
        // Empezamos la canción
        mediaPlayer.start();

        // mediaPlayer.setOnPreparedListener(funcionPrepared)

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) mediaPlayer.release();
    }

}