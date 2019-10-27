package com.smarttoolfactory.tutorial1_1pagingwithdb.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface WordDao {

    @Query("SELECT * from word_table ORDER BY word ASC")
    fun getWordsAscPaged(): DataSource.Factory<Int, Word>


    @Query("SELECT * from word_table ORDER BY word ASC")
    fun getWordsAsc(): LiveData<List<Word>>

    // This method will create a DataSource for Paging. It will provide Number of Items required to
    // show on a page. DataSource.Factory is used to creating a DataSource.
    @Query("SELECT * from word_table")
    fun getWordsPaged(): DataSource.Factory<Int, Word>


    @Query("SELECT * from word_table")
    fun getWords(): LiveData<List<Word>>

    // We do not need a conflict strategy, because the word is our primary key, and you cannot
    // add two items with the same primary key to the database. If the table has more than one
    // column, you can use @Insert(onConflict = OnConflictStrategy.REPLACE) to update a row.
    // or OnConflictStrategy.IGNORE

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(word: Word): Long

    @Query("DELETE FROM word_table")
    fun deleteAll()

    @Query("SELECT COUNT(word) FROM word_table")
    fun getRowCount(): LiveData<Int>
}