package com.smarttoolfactory.tutorial3_1paging_rest_and_room.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [RepoModel::class],
    version = 1,
    exportSchema = false
)
abstract class RepoDb : RoomDatabase() {

    abstract fun reposDao(): RepoDao

    companion object {

        @Volatile
        private var INSTANCE: RepoDb? = null

        fun getInstance(context: Context): RepoDb =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                RepoDb::class.java, "Github.db")
                .build()
    }
}