package com.example.notes

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface notedow {
    @Insert
    fun insertdata(note:nootee)
@Update
    fun updatedata(note:nootee)
@Delete
    fun deletedata(note:nootee)

    @Query("Select * From nootee")
    fun getalldata() : List<nootee>
}