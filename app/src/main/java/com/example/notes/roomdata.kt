package com.example.notes

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = [nootee::class], version = 1)
abstract class roomdata :RoomDatabase() {

   abstract fun getnotedow() :notedow
}