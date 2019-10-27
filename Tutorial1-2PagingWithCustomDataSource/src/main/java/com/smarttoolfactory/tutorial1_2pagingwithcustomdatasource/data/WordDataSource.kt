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

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Word>) {

        val wordList = wordDao.getWordsBySize(params.key, params.requestedLoadSize)
        val nextKey = params.key + wordList.size
        callback.onResult(wordList, nextKey)

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Word>) {

    }


}