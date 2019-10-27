package com.smarttoolfactory.tutorial1_2pagingwithcustomdatasource.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface WordDao {

    @Query("SELECT * from word_table ORDER BY word ASC")
    fun getWordsAsc(): LiveData<List<Word>>

    @Query("SELECT * from word_table")
    fun getWords(): LiveData<List<Word>>

    @Query("SELECT * from word_table")
    fun getWordDataFactory(): DataSource.Factory<Int, Word>

    @Query("SELECT * FROM word_table LIMIT :size OFFSET :offSet")
    fun getWordsBySize(size: Int, offSet: Int): List<Word>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(word: Word): Long

    @Query("DELETE FROM word_table")
    fun deleteAll()

    @Query("SELECT COUNT(word) FROM word_table")
    fun getRowCount(): LiveData<Int>
}