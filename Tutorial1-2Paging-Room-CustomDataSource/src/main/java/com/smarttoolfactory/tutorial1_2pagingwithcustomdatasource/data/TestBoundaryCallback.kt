package com.smarttoolfactory.tutorial1_2pagingwithcustomdatasource.data

import androidx.paging.PagedList

class TestBoundaryCallback : PagedList.BoundaryCallback<Word>() {


    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        println("🏜 TestBoundaryCallback onZeroItemsLoaded()")
    }


    override fun onItemAtFrontLoaded(itemAtFront: Word) {
        super.onItemAtFrontLoaded(itemAtFront)

        println("🏜 TestBoundaryCallback onItemAtFrontLoaded() itemAtFront: $itemAtFront")


    }

    override fun onItemAtEndLoaded(itemAtEnd: Word) {
        super.onItemAtEndLoaded(itemAtEnd)

        println("🏜 TestBoundaryCallback onItemAtEndLoaded() itemAtEnd: $itemAtEnd")

    }
}