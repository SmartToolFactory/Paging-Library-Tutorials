package com.smarttoolfactory.tutorial1_1pagingwithdb.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import java.util.concurrent.Executors


// Note that in order to unit test the WordRepository, you have to remove the Application
// dependency. This adds complexity and much more code, and this sample is not about testing.
// See the BasicSample in the android-architecture-components repository at
// https://github.com/googlesamples

class WordRepository(application: Application) {

    val executor = Executors.newSingleThreadExecutor()


    private val wordDao: WordDao
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allWords: LiveData<PagedList<Word>>

    init {
        val db = WordRoomDatabase.getDatabase(application)
        wordDao = db.wordDao()

        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(true)
            .setPrefetchDistance(10)
            .build()


        val pagedListBuilder = LivePagedListBuilder(wordDao.getAllUsers(), config)

        allWords = pagedListBuilder.build()

    }

    // You must call this on a non-UI thread or your app will crash.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    fun insert(word: Word) {

        executor.execute {
            println("WordRepository insert ${word.word} TO index: ${wordDao.insert(word)}")
        }
    }


    fun getItemCount(): LiveData<Int> {
        return wordDao.getRowCount()
    }
}