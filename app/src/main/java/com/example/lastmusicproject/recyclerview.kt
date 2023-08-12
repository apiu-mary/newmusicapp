//package com.example.lastmusicproject
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.LinearLayout
//import android.widget.Toast
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.lastmusicproject.databinding.ActivityRecyclerviewBinding
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class recyclerview : AppCompatActivity() {
//    lateinit var binding: ActivityRecyclerviewBinding
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding=ActivityRecyclerviewBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        var adapter=SongsAdapter(emptyList())
//        binding.rvrecyclerview.adapter = adapter
//        binding.rvrecyclerview.layoutManager = LinearLayoutManager(this)
//
//    }
//
//
//private fun fetchAlbums() {
//    val apiClient = ApiClient.buildClient(ApiInterface::class.java)
//    val request = apiClient.getAlbum()
//    request.enqueue(object : Callback<SongsResponse> {
//        override fun onResponse(call: Call<SongsResponse>, response: Response<SongsResponse>) {
//            if (response.isSuccessful) {
//                val songs = response.body()?.songs
//                if (songs != null) {
//                    adapter.songList = songs
//                    adapter.notifyDataSetChanged()
//                }
//            } else {
//                Toast.makeText(baseContext, response.errorBody()?.string(), Toast.LENGTH_LONG).show()
//            }
//        }
//
//        override fun onFailure(call: Call<SongsResponse>, t: Throwable) {
//            Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
//        }
//    })
//}
//}
package com.example.lastmusicproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lastmusicproject.databinding.ActivityRecyclerviewBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecyclerViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerviewBinding
    private lateinit var adapter: SongsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = SongsAdapter(emptyList())
        binding.rvrecyclerview.adapter = adapter
        binding.rvrecyclerview.layoutManager = LinearLayoutManager(this)

        fetchAlbums()
    }

    private fun fetchAlbums() {
        val apiClient = ApiClient.buildClient(ApiInterface::class.java)
        val request = apiClient.getAlbum()
        request.enqueue(object : Callback<SongsResponse> {
            override fun onResponse(call: Call<SongsResponse>, response: Response<SongsResponse>) {
                if (response.isSuccessful) {
                    val songs = response.body()?.songs
                    if (songs != null) {
                        adapter.songList = songs
                        adapter.notifyDataSetChanged()
                    }
                } else {
                    Toast.makeText(baseContext, response.errorBody()?.string(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<SongsResponse>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}
