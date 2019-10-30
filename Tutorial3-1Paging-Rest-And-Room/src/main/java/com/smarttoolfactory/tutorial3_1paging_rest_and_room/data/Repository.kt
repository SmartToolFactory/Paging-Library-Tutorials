package com.smarttoolfactory.tutorial3_1paging_rest_and_room.data

import android.util.Log
import androidx.paging.LivePagedListBuilder
import com.smarttoolfactory.tutorial3_1paging_rest_and_room.api.GithubService

class Repository(
    private val service: GithubService,
    private val cache: LocalCache
) {

    /**
     * Search repositories whose names match the query.
     */
    fun search(query: String): RepoResult {
        Log.d("Repository", "New query: $query")

        // Get data source factory from the local cache
        val dataSourceFactory = cache.reposByName(query)

        // Construct the boundary callback
        val boundaryCallback = BoundaryCondition(query, service, cache)
        val networkErrors = boundaryCallback.networkErrors

        // Get the paged list
        val data =
            LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
                .setBoundaryCallback(boundaryCallback)
                .build()

        // Get the network errors exposed by the boundary callback
        return RepoResult(data, networkErrors)
    }

    companion object {
        private const val DATABASE_PAGE_SIZE = 20
    }
}