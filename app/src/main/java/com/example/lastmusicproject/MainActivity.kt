//////
//////package com.example.lastmusicproject
//////
//////import android.animation.ObjectAnimator
//////import android.media.MediaPlayer
//////import android.os.Bundle
//////import android.widget.SeekBar
//////import android.widget.Toast
//////import androidx.appcompat.app.AppCompatActivity
//////import androidx.recyclerview.widget.LinearLayoutManager
//////import com.example.lastmusicproject.databinding.ActivityMainBinding
//////import retrofit2.Call
//////import retrofit2.Response
//////
//////class MainActivity : AppCompatActivity() {
//////    lateinit var adapter: SongsAdapter
//////    var products:List<Song> =emptyList()
//////    private lateinit var binding: ActivityMainBinding
//////    private lateinit var mediaPlayer: MediaPlayer
//////    private var currentSong = 0
//////    private val songs = arrayOf(R.raw.song1, R.raw.forgiveme)
//////    private var rotationAnimator: ObjectAnimator? = null
//////
//////    override fun onCreate(savedInstanceState: Bundle?) {
//////        super.onCreate(savedInstanceState)
//////        binding = ActivityMainBinding.inflate(layoutInflater)
//////        setContentView(binding.root)
//////        adapter=SongsAdapter(emptyList())
//////        binding.recyclerView.adapter=adapter
//////        binding.recyclerView.layoutManager=LinearLayoutManager(this)
//////
//////
//////
//////        mediaPlayer = MediaPlayer.create(this, songs[currentSong])
//////
//////        binding.ivplay.setOnClickListener {
//////            if (!mediaPlayer.isPlaying) {
//////
//////                mediaPlayer.start()
//////                binding.ivplay.setImageResource(R.drawable.baseline_pause_24)
//////                startRotationAnimation()
//////            } else {
//////                mediaPlayer.pause()
//////                binding.ivplay.setImageResource(R.drawable.baseline_play_arrow_24)
//////                stopRotationAnimation()
//////            }
//////        }
//////
//////        binding.ivnext.setOnClickListener {
//////            mediaPlayer.stop()
//////            currentSong = (currentSong + 1) % songs.size
//////            mediaPlayer = MediaPlayer.create(this, songs[currentSong])
//////            mediaPlayer.start()
//////            startRotationAnimation()
//////        }
//////
//////        binding.seekBar.max = mediaPlayer.duration
//////
//////        val handler = android.os.Handler()
//////        handler.postDelayed(object : Runnable {
//////            override fun run() {
//////                binding.seekBar.progress = mediaPlayer.currentPosition
//////                handler.postDelayed(this, 1000)
//////            }
//////        }, 0)
//////
//////        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
//////            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//////                if (fromUser) {
//////                    mediaPlayer.seekTo(progress)
//////                }
//////            }
//////
//////            override fun onStartTrackingTouch(seekBar: SeekBar?) {
//////                mediaPlayer.pause()
//////            }
//////
//////            override fun onStopTrackingTouch(seekBar: SeekBar?) {
//////                mediaPlayer.start()
//////            }
//////        })
//////    }
//////    private fun getSongname(index:Int):String{
//////        return  when(index){
//////            0 ->"song1"
//////            1 -> "Forgive Me"
//////            else -> ""
//////        }
//////    }
//////
//////    private fun startRotationAnimation() {
//////        if (rotationAnimator == null) {
//////            rotationAnimator = ObjectAnimator.ofFloat(binding.ivimageview, "rotation", 0f, 360f)
//////            rotationAnimator?.duration = 2000 // Adjust the duration as needed
//////            rotationAnimator?.repeatCount = ObjectAnimator.INFINITE // Repeat indefinitely
//////        }
//////        rotationAnimator?.start()
//////    }
//////
//////    private fun stopRotationAnimation() {
//////        rotationAnimator?.end()
//////    }
//////
//////    override fun onDestroy() {
//////        super.onDestroy()
//////        mediaPlayer.release()
//////    }
//////
//////fun fetchAlbums(){
//////    var apiClient = ApiClient.buildClient(ApiInterface::class.java)
//////
//////    var request=apiClient.getAlbum().also {
//////        it.enqueue(object : retrofit2.Callback<SongsResponse> {
//////            override fun onResponse(call:Call<SongsResponse>,response: Response<SongsResponse>){
//////                if (response.isSuccessful){
//////                    var songs = response.body()?.songs
//////                    if (songs != null) {
//////                        adapter.songList = songs
//////                        adapter.notifyDataSetChanged()
//////
//////                    val adapter = SongsAdapter(emptyList())
//////
//////
//////                    Toast.makeText(baseContext,
//////
//////                        "fetched ${songs?.size} products", Toast.LENGTH_SHORT).show()
//////
//////
//////
//////
//////                }
//////                else {
//////                    Toast.makeText(baseContext, response.errorBody()?.string(), Toast.LENGTH_LONG).show()
//////                }
//////            }
//////
//////             fun onFailure(call:Call<SongsResponse>, t:Throwable){
//////                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
//////            }
//////        }
//////
//////            override fun onFailure(call: Call<SongsResponse>, t: Throwable) {
//////                TODO("Not yet implemented")
//////            }
//////        })
//////    }
//////
//////}
//////}
////package com.example.lastmusicproject
////
////import android.animation.ObjectAnimator
////import android.media.MediaPlayer
////import android.os.Bundle
////import android.widget.SeekBar
////import android.widget.Toast
////import androidx.appcompat.app.AppCompatActivity
////import androidx.recyclerview.widget.LinearLayoutManager
////import com.example.lastmusicproject.databinding.ActivityMainBinding
////import retrofit2.Call
////import retrofit2.Callback
////import retrofit2.Response
////
////class MainActivity : AppCompatActivity() {
////
////    private lateinit var adapter: SongsAdapter
////    private lateinit var binding: ActivityMainBinding
////    private lateinit var mediaPlayer: MediaPlayer
////    private var currentSong = 0
////    private val songs = arrayOf(R.raw.song1, R.raw.forgiveme)
////    private var rotationAnimator: ObjectAnimator? = null
////
////
////    override fun onCreate(savedInstanceState: Bundle?) {
////        super.onCreate(savedInstanceState)
////        binding = ActivityMainBinding.inflate(layoutInflater)
////        setContentView(binding.root)
////
////        // Configure the RecyclerView
//////      binding.rvlayoutManager = LinearLayoutManager(this)
////
////
////       adapter = SongsAdapter(emptyList())
////        binding.rvrecyclerview.adapter = adapter
////
////
////
////        mediaPlayer = MediaPlayer.create(this, songs[currentSong])
////
////        binding.ivplay.setOnClickListener {
////            if (!mediaPlayer.isPlaying) {
////                mediaPlayer.start()
////                binding.ivplay.setImageResource(R.drawable.baseline_pause_24)
////                startRotationAnimation()
////            } else {
////                mediaPlayer.pause()
////                binding.ivplay.setImageResource(R.drawable.baseline_play_arrow_24)
////                stopRotationAnimation()
////            }
////        }
////
////        binding.ivnext.setOnClickListener {
////            mediaPlayer.stop()
////            currentSong = (currentSong + 1) % songs.size
////            mediaPlayer = MediaPlayer.create(this, songs[currentSong])
////            mediaPlayer.start()
////            startRotationAnimation()
////        }
////
////        binding.seekBar.max = mediaPlayer.duration
////
////        val handler = android.os.Handler()
////        handler.postDelayed(object : Runnable {
////            override fun run() {
////                binding.seekBar.progress = mediaPlayer.currentPosition
////                handler.postDelayed(this, 1000)
////            }
////        }, 0)
////
////        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
////            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
////                if (fromUser) {
////                    mediaPlayer.seekTo(progress)
////                }
////            }
////
////            override fun onStartTrackingTouch(seekBar: SeekBar?) {
////                mediaPlayer.pause()
////            }
////
////            override fun onStopTrackingTouch(seekBar: SeekBar?) {
////                mediaPlayer.start()
////            }
////        })
////
////        fetchAlbums()
////    }
////
////    private fun startRotationAnimation() {
////        if (rotationAnimator == null) {
////            rotationAnimator = ObjectAnimator.ofFloat(binding.ivimageview, "rotation", 0f, 360f)
////            rotationAnimator?.duration = 2000 // Adjust the duration as needed
////            rotationAnimator?.repeatCount = ObjectAnimator.INFINITE // Repeat indefinitely
////        }
////        rotationAnimator?.start()
////    }
////
////    private fun stopRotationAnimation() {
////        rotationAnimator?.end()
////    }
////
////    override fun onDestroy() {
////        super.onDestroy()
////        mediaPlayer.release()
////    }
////
////    private fun fetchAlbums() {
////        val apiClient = ApiClient.buildClient(ApiInterface::class.java)
////        val request = apiClient.getAlbum()
////        request.enqueue(object : Callback<SongsResponse> {
////            override fun onResponse(call: Call<SongsResponse>, response: Response<SongsResponse>) {
////                if (response.isSuccessful) {
////                    val songs = response.body()?.songs
////                    if (songs != null) {
////                        adapter.songList = songs
////                        adapter.notifyDataSetChanged()
////                    }
////                } else {
////                    Toast.makeText(baseContext, response.errorBody()?.string(), Toast.LENGTH_LONG).show()
////                }
////            }
////
////            override fun onFailure(call: Call<SongsResponse>, t: Throwable) {
////                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
////            }
////        })
////    }
////}
//package com.example.lastmusicproject
//
//import android.animation.ObjectAnimator
//import android.content.Intent
//import android.media.MediaPlayer
//import android.os.Bundle
//import android.widget.Button
//import android.widget.SeekBar
//import androidx.appcompat.app.AppCompatActivity
//import com.example.lastmusicproject.databinding.ActivityMainBinding
//
//class MainActivity : AppCompatActivity() {
//
//
//
//    private lateinit var adapter: SongsAdapter
//    lateinit var button:Button
//    private lateinit var binding: ActivityMainBinding
//    private lateinit var mediaPlayer: MediaPlayer
//    private var currentSong = 0
//    private val songs = arrayOf(R.raw.song1, R.raw.forgiveme)
//    private var rotationAnimator: ObjectAnimator? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding=ActivityMainBinding.inflate(layoutInflater)
//        button.setOnClickListener {
//            val intent=Intent(this, RecyclerViewActivity::class.java)
//
//        }
//
//
//        setContentView(binding.root)
//
//
//
//        // Configure the RecyclerView
//
//
//        adapter = SongsAdapter(emptyList())
//
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
//
//        binding.seekBar.max = mediaPlayer.duration
//
//        val handler = android.os.Handler()
//        handler.postDelayed(object : Runnable {
//            override fun run() {
//                binding.seekBar.progress = mediaPlayer.currentPosition
//                handler.postDelayed(this, 1000)
//            }
//        }, 0)
//
//        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
//            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//                if (fromUser) {
//                    mediaPlayer.seekTo(progress)
//                }
//            }
//
//            override fun onStartTrackingTouch(seekBar: SeekBar?) {
//                mediaPlayer.pause()
//            }
//
//            override fun onStopTrackingTouch(seekBar: SeekBar?) {
//                mediaPlayer.start()
//            }
//        })
//
//
//    }
//
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
//    override fun onDestroy() {
//        super.onDestroy()
//        mediaPlayer.release()
//    }
//
//
//}
package com.example.lastmusicproject

import android.animation.ObjectAnimator
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.example.lastmusicproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: SongsAdapter
    private lateinit var button: Button
    private lateinit var binding: ActivityMainBinding
    private lateinit var mediaPlayer: MediaPlayer
    private var currentSong = 0
    private val songs = arrayOf(R.raw.song1, R.raw.forgiveme)
    private var rotationAnimator: ObjectAnimator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        button = binding.btbutton


//        button = binding.btbutton

        button.setOnClickListener {
            val intent = Intent(this, RecyclerViewActivity::class.java)
            startActivity(intent)
        }

        // Configure the RecyclerView
        adapter = SongsAdapter(emptyList())

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
