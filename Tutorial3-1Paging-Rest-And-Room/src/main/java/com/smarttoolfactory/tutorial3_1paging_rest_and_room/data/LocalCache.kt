package com.smarttoolfactory.tutorial3_1paging_rest_and_room.data

import android.util.Log
import androidx.paging.DataSource
import java.util.concurrent.Executor

class LocalCache(
    private val repoDao: RepoDao,
    private val ioExecutor: Executor
) {

    /**
     * Insert a list of repos in the database, on a background thread.
     */
    fun insert(repos: List<RepoModel>, insertFinished: () -> Unit) {
        ioExecutor.execute {
            Log.d("LocalCache", "inserting ${repos.size} repos")
            repoDao.insert(repos)
            insertFinished()
        }
    }

    /**
     * Request a LiveData<List<RepoModel>> from the Dao, based on a repo name.
     */
    fun reposByName(name: String): DataSource.Factory<Int, RepoModel> {
        // appending '%' so we can allow other characters to be before and after the query string
        val query = "%${name.replace(' ', '%')}%"
        return repoDao.reposByName(query)
    }
}