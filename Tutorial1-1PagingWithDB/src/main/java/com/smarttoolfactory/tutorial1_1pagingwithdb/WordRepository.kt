package com.smarttoolfactory.tutorial1_1pagingwithdb

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.smarttoolfactory.tutorial1_1pagingwithdb.data.Word
import com.smarttoolfactory.tutorial1_1pagingwithdb.data.WordDao
import com.smarttoolfactory.tutorial1_1pagingwithdb.data.WordRoomDatabase
import java.util.concurrent.Executors


// Note that in order to unit test the WordRepository, you have to remove the Application
// dependency. This adds complexity and much more code, and this sample is not about testing.
// See the BasicSample in the android-architecture-components repository at
// https://github.com/googlesamples

class WordRepository(application: Application) {

    private val executor = Executors.newSingleThreadExecutor()

    private val wordDao: WordDao

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allWordsPaged: LiveData<PagedList<Word>>

    val allWords: LiveData<List<Word>>

    init {
        val db =
            WordRoomDatabase.getDatabase(
                application
            )
        wordDao = db.wordDao()

        // Create a config for paged list
        val config = PagedList.Config.Builder()
            .setPageSize(15)
            // ⚠️ set placeholders false to diplay shorter scrollbar
            // Disables null placeholders
            .setEnablePlaceholders(false)
            .setPrefetchDistance(8)
            // Max size MUST BE >= 2 * page size + prefetch distance
            .setMaxSize(50)
            .build()


        //  Returns for Page Size = 15, Prefetch distance = 8
//      🎃 WordPagedListAdapter onBindViewHolder() position: 37
//      🥶 WordPagedListAdapter getCurrentList(): 45
//      🎃 WordPagedListAdapter onBindViewHolder() position: 23
//      🥶 WordPagedListAdapter getCurrentList(): 45

        // Create factory for paged list
        val dataSourceFactory: DataSource.Factory<Int, Word> = wordDao.getWordsPaged()

        val pagedListBuilder = LivePagedListBuilder(dataSourceFactory, config)

        // PageList<Word>
        allWordsPaged = pagedListBuilder.build()

        // List<Word>
        allWords = wordDao.getWords()

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