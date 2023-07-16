////package com.example.lastmusicproject
////
////import android.media.MediaPlayer
////import android.os.Bundle
////import androidx.appcompat.app.AppCompatActivity
////import com.example.lastmusicproject.databinding.ActivityMainBinding
////
////class MainActivity : AppCompatActivity() {
////    lateinit var  binding: ActivityMainBinding
////    lateinit var  mediaPlayer: MediaPlayer
////    var currentSong=0
////    var songs =arrayOf(R.raw.song1,R.raw.forgiveme)
////
////    override fun onCreate(savedInstanceState: Bundle?) {
////        super.onCreate(savedInstanceState)
////
////        binding=ActivityMainBinding.inflate(layoutInflater)
////        setContentView(binding.root)
////
////
////
////
////
////
////        mediaPlayer = MediaPlayer.create(this, songs[currentSong])
////        binding.ivpause.setOnClickListener {
////            if (!mediaPlayer.isPlaying)
////                mediaPlayer.start()
////                    play_button.setImageResource(R.drawable.baseline_pause_24)
////        }
////        binding.ivpause.setOnClickListener {
////            if (mediaPlayer.isPlaying)
////                mediaPlayer.pause()
////        }
////        binding.ivnext.setOnClickListener {
////            mediaPlayer.stop()
////            currentSong=(currentSong-1+songs.size)%songs.size
////            mediaPlayer = MediaPlayer.create(this, songs[currentSong])
////            mediaPlayer.start()
////        }
////
////
////
////
////    }
////    override fun onDestroy() {
////        super.onDestroy()
////        mediaPlayer.release()
////    }
////}
//package com.example.lastmusicproject
//
//import android.animation.ObjectAnimator
//import android.media.MediaPlayer
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import com.example.lastmusicproject.databinding.ActivityMainBinding
//
//class MainActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityMainBinding
//    private lateinit var mediaPlayer: MediaPlayer
//    private var currentSong = 0
//    private val songs = arrayOf(R.raw.song1, R.raw.forgiveme)
//    private var rotationAnimator: ObjectAnimator? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        mediaPlayer = MediaPlayer.create(this, songs[currentSong])
//
//        binding.ivplay.setOnClickListener {
//            if (!mediaPlayer.isPlaying) {
//                mediaPlayer.start()
//                binding.ivplay.setImageResource(R.drawable.baseline_pause_24)
//                startRotationAnimation()
//            } else {
//                mediaPlayer.pause()
//                binding.ivplay.setImageResource(R.drawable.baseline_play_arrow_24)
//                stopRotationAnimation()
//            }
//        }
//
//        binding.ivnext.setOnClickListener {
//            mediaPlayer.stop()
//            currentSong = (currentSong + 1) % songs.size
//            mediaPlayer = MediaPlayer.create(this, songs[currentSong])
//            mediaPlayer.start()
//            startRotationAnimation()
//        }
//    }
//    private fun startRotationAnimation() {
//        if (rotationAnimator == null) {
//            rotationAnimator = ObjectAnimator.ofFloat(binding.ivimageview, "rotation", 0f, 360f)
//            rotationAnimator?.duration = 2000 // Adjust the duration as needed
//            rotationAnimator?.repeatCount = ObjectAnimator.INFINITE // Repeat indefinitely
//        }
//        rotationAnimator?.start()
//    }
//
//    private fun stopRotationAnimation() {
//        rotationAnimator?.end()
//    }
//
//
//    override fun onDestroy() {
//        super.onDestroy()
//        mediaPlayer.release()
//    }
//}
package com.example.lastmusicproject

import android.animation.ObjectAnimator
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.example.lastmusicproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mediaPlayer: MediaPlayer
    private var currentSong = 0
    private val songs = arrayOf(R.raw.song1, R.raw.forgiveme)
    private var rotationAnimator: ObjectAnimator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mediaPlayer = MediaPlayer.create(this, songs[currentSong])

        binding.ivplay.setOnClickListener {
            if (!mediaPlayer.isPlaying) {

                mediaPlayer.start()
                binding.ivplay.setImageResource(R.drawable.baseline_pause_24)
                startRotationAnimation()
            } else {
                mediaPlayer.pause()
                binding.ivplay.setImageResource(R.drawable.baseline_play_arrow_24)
                stopRotationAnimation()
            }
        }

        binding.ivnext.setOnClickListener {
            mediaPlayer.stop()
            currentSong = (currentSong + 1) % songs.size
            mediaPlayer = MediaPlayer.create(this, songs[currentSong])
            mediaPlayer.start()
            startRotationAnimation()
        }

        binding.seekBar.max = mediaPlayer.duration

        val handler = android.os.Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                binding.seekBar.progress = mediaPlayer.currentPosition
                handler.postDelayed(this, 1000)
            }
        }, 0)

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                mediaPlayer.pause()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                mediaPlayer.start()
            }
        })
    }
    private fun getSongname(index:Int):String{
        return  when(index){
            0 ->"song1"
            1 -> "Forgive Me"
            else -> ""
        }
    }

    private fun startRotationAnimation() {
        if (rotationAnimator == null) {
            rotationAnimator = ObjectAnimator.ofFloat(binding.ivimageview, "rotation", 0f, 360f)
            rotationAnimator?.duration = 2000 // Adjust the duration as needed
            rotationAnimator?.repeatCount = ObjectAnimator.INFINITE // Repeat indefinitely
        }
        rotationAnimator?.start()
    }

    private fun stopRotationAnimation() {
        rotationAnimator?.end()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}
