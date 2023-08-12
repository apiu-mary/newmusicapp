package com.example.lastmusicproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lastmusicproject.databinding.SonglistBinding


class SongsAdapter(var songList: List<Song>) : RecyclerView.Adapter<SongViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val binding = SonglistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SongViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return songList.size
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        var detail=songList[position]
        holder.binding.apply {
            tvalbum.text=detail.title.toString()
            tvid.text=detail.id.toString()
            tvtitle.text=detail.cover.toString()
        }

    }
}

class SongViewHolder(var binding: SonglistBinding) : RecyclerView.ViewHolder(binding.root) {


}
