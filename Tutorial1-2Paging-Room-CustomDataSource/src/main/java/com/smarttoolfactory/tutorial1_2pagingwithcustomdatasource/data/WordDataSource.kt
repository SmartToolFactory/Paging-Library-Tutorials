package com.smarttoolfactory.tutorial1_2pagingwithcustomdatasource.data

import androidx.paging.PageKeyedDataSource

class WordDataSource(private val wordDao: WordDao) : PageKeyedDataSource<Int, Word>() {

    // Called only the first time data to be loaded
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Word>
    ) {

        val wordList = wordDao.getWordsBySize(params.requestedLoadSize, 0)

        callback.onResult(
            wordList,
            null,
            wordList.size + 1
        )

        println("🤑 WordDataSource loadInitial() couponList size: ${wordList.size}")


    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Word>) {

        // Loads as requested size after last index of loaded item
        // If last index is 30 and load size 10, loads 31 to 41 items next
        val wordList = wordDao.getWordsBySize(params.requestedLoadSize, params.key)
        val nextKey = params.key + wordList.size
        callback.onResult(wordList, nextKey)


        println("🗿 WordDataSource loadAfter() wordList size: ${wordList.size},params.key: ${params.key}, " +
                "requestedLoadSize: ${params.requestedLoadSize}, next key: $nextKey")
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Word>) {

    }


}