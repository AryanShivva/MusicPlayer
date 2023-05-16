package com.example.musicplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.SeekBar

class image : AppCompatActivity() {

    lateinit var mediaPlayer: MediaPlayer
    var totalTime : Int =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        supportActionBar?.hide()

        mediaPlayer=MediaPlayer.create(this,R.raw.music)
        mediaPlayer.setVolume(1f,1f)
        totalTime=mediaPlayer.duration

        val btnPlay =findViewById<ImageView>(R.id.play)
        val btnPause =findViewById<ImageView>(R.id.pause)
        val btnStop =findViewById<ImageView>(R.id.stop)
        val seekBarVariable=findViewById<SeekBar>(R.id.seekBar)

        btnPlay.setOnClickListener{
            mediaPlayer.start()
        }

        btnPause.setOnClickListener{
            mediaPlayer.pause()
        }

        btnStop.setOnClickListener{
            mediaPlayer.stop()
            mediaPlayer.reset()
            mediaPlayer.release()
        }

        //when user changes the time stamp of the music we should reflect the change in the seekbar

        seekBarVariable.max = totalTime
        seekBarVariable.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(fromUser){
                    mediaPlayer.seekTo(progress)
                }

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        //change to seekbar position based on the music
        val handler = Handler()
        handler.postDelayed(object :Runnable{
            override fun run() {
                try {
                    seekBarVariable.progress = mediaPlayer.currentPosition
                    handler.postDelayed(this,1000)
            }catch (exception:java.lang.Exception){
                seekBarVariable.progress = 0
            }

            }

        },0)


    }
}