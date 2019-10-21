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
    val allWordsPaged: LiveData<PagedList<Word>>

    val allWords: LiveData<List<Word>>

    init {
        val db = WordRoomDatabase.getDatabase(application)
        wordDao = db.wordDao()

        // Create a config for paged list
        val config = PagedList.Config.Builder()
            .setPageSize(5)
            // ⚠️ set placeholders false to diplay shorter scrollbar
            // Disables null placeholders
            .setEnablePlaceholders(false)
            .setPrefetchDistance(5)
            .build()


        // Create factory for paged list
        val factory = wordDao.getAllUsersPaged()


        val pagedListBuilder = LivePagedListBuilder(factory, config)

        allWordsPaged = pagedListBuilder.build()


        allWords = wordDao.getAllUsers()

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