package com.smarttoolfactory.tutorial1_1pagingwithdb.data

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WordDao {

    // This method will create a DataSource for Paging. It will provide Number of Items required to
    // show on a page. DataSource.Factory is used to creating a DataSource.
    @Query("SELECT * from word_table ORDER BY word ASC")
    fun getAllUsers(): DataSource.Factory<Int, Word>

// TODO Check out if this works
//    @get:Query("SELECT * from word_table ORDER BY word ASC")
//    val allUsers: DataSource.Factory<Int, Word>

    // We do not need a conflict strategy, because the word is our primary key, and you cannot
    // add two items with the same primary key to the database. If the table has more than one
    // column, you can use @Insert(onConflict = OnConflictStrategy.REPLACE) to update a row.
    @Insert
    fun insert(word: Word)

    @Query("DELETE FROM word_table")
    fun deleteAll()
}