package com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide

import androidx.paging.PagedList

class TestBoundaryCallback : PagedList.BoundaryCallback<News>() {


    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        println("🏜 TestBoundaryCallback onZeroItemsLoaded()")
    }


    override fun onItemAtFrontLoaded(itemAtFront: News) {
        super.onItemAtFrontLoaded(itemAtFront)

        println("🏜 TestBoundaryCallback onItemAtFrontLoaded() itemAtFront: $itemAtFront")


    }

    override fun onItemAtEndLoaded(itemAtEnd: News) {
        super.onItemAtEndLoaded(itemAtEnd)

        println("🏜 TestBoundaryCallback onItemAtEndLoaded() itemAtEnd: $itemAtEnd")

    }
}