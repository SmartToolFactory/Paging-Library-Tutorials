package com.chikeandroid.pagingtutsplus.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.chikeandroid.pagingtutsplus.data.AppDatabase
import com.chikeandroid.pagingtutsplus.data.Person

class PersonsViewModel constructor(application: Application)
    : AndroidViewModel(application) {

    private var personLiveData: LiveData<PagedList<Person>>

    private var personNonPagedLiveData: LiveData<List<Person>>

    init {
        val personDao = AppDatabase.getInstance(getApplication()).personDao()

        // Get Factory
        val factory: DataSource.Factory<Int, Person> = personDao.getAllPaged()

        val pagedListBuilder: LivePagedListBuilder<Int, Person>  = LivePagedListBuilder<Int, Person>(factory,
                5)
        personLiveData = pagedListBuilder.build()


        personNonPagedLiveData = personDao.getPersons()
    }

    fun getPersonLiveData() = personLiveData

    fun getPersonNonPagedLiveData() = personNonPagedLiveData
}