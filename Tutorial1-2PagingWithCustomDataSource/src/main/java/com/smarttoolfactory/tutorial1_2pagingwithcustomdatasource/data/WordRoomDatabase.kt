package com.smarttoolfactory.tutorial1_2pagingwithcustomdatasource.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Word::class], version = DATABASE_VERSION, exportSchema = false)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao


    companion object {

        fun getDatabase(application: Application): WordRoomDatabase {
            return Room.databaseBuilder(
                application,
                WordRoomDatabase::class.java,
                DATABASE_NAME
            ).build()
        }

    }

}

const val DATABASE_VERSION = 1
const val DATABASE_NAME = "words.db"