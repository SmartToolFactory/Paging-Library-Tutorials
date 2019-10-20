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

    init {
        val factory: DataSource.Factory<Int, Person> =
        AppDatabase.getInstance(getApplication()).personDao().getAllPaged()

        val pagedListBuilder: LivePagedListBuilder<Int, Person>  = LivePagedListBuilder<Int, Person>(factory,
                50)
        personLiveData = pagedListBuilder.build()
    }

    fun getPersonLiveData() = personLiveData
}