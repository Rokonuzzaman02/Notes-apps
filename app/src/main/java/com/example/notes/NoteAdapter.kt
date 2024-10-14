package com.example.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.notes.databinding.IteamBinding

class NoteAdapter: ListAdapter <nootee, viewnote>(comparator) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewnote {
        return viewnote(IteamBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: viewnote, position: Int) {

        getItem(position).let {

            holder.binding.apply {

                textt.text = it.title
                deattt.text = it.date
                taimtt.text =it.time
            }
        }

    }
    companion object{

        var comparator = object : DiffUtil.ItemCallback<nootee>() {
            override fun areItemsTheSame(oldItem: nootee, newItem: nootee): Boolean {
                return oldItem==newItem
            }

            override fun areContentsTheSame(oldItem: nootee, newItem: nootee): Boolean {
                return oldItem == newItem
            }

        }
    }
}