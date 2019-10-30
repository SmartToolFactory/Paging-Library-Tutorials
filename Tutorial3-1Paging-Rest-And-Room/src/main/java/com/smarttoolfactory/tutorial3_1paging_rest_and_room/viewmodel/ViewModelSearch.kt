package com.smarttoolfactory.tutorial3_1paging_rest_and_room.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.smarttoolfactory.tutorial3_1paging_rest_and_room.data.RepoModel
import com.smarttoolfactory.tutorial3_1paging_rest_and_room.data.RepoResult
import com.smarttoolfactory.tutorial3_1paging_rest_and_room.data.Repository

class ViewModelSearch(private val repository: Repository) : ViewModel() {

    private val queryLiveData = MutableLiveData<String>()

    private val repoResult: LiveData<RepoResult> = Transformations.map(queryLiveData) {
        repository.search(it)
    }

    val repos: LiveData<PagedList<RepoModel>> = Transformations.switchMap(repoResult) { it -> it.data }
    val networkErrors: LiveData<String> = Transformations.switchMap(repoResult) { it ->
        it.networkErrors
    }

    /**
     * Search a repository based on a query string.
     */
    fun searchRepo(queryString: String) {
        queryLiveData.postValue(queryString)
    }


    /**
     * Get the last query value.
     */
    fun lastQueryValue(): String? = queryLiveData.value
}