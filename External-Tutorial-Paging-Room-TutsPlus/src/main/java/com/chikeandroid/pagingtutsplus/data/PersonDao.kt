package com.chikeandroid.pagingtutsplus.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PersonDao {

    @Query("SELECT * FROM persons")
    fun getPersons(): LiveData<List<Person>>

    @Query("SELECT * FROM persons")
    fun getAllPaged(): DataSource.Factory<Int, Person>

    @Insert
    fun insertAll(persons: List<Person>)

    @Delete
    fun delete(person: Person)
}