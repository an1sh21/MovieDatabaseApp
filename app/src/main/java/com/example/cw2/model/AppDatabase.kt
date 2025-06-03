package com.example.cw2.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Movie::class], version = 2)
abstract class AppDatabase : RoomDatabase(){
    abstract fun movieDao(): MovieDao


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase?=null


        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "movies_db"
                ).fallbackToDestructiveMigration(true) // true = ALL tables will be dropped and recreated
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    }