package com.example.notes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class nootee(

    @PrimaryKey(autoGenerate = true)
    val id :Int=0,

    val title :String,
    val time :String,
    val date :String
)
