package com.smarttoolfactory.tutorial1_2pagingwithcustomdatasource

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.smarttoolfactory.tutorial1_2pagingwithcustomdatasource.data.Word
import com.smarttoolfactory.tutorial1_2pagingwithcustomdatasource.data.WordDao
import com.smarttoolfactory.tutorial1_2pagingwithcustomdatasource.data.WordDataSource
import com.smarttoolfactory.tutorial1_2pagingwithcustomdatasource.data.WordRoomDatabase
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

    val allWordsPagedCustom: LiveData<PagedList<Word>>

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
        val dataSourceFactory: DataSource.Factory<Int, Word> = wordDao.getWordDataFactory()

        val pagedListBuilder = LivePagedListBuilder(dataSourceFactory, config)

        // PageList<Word>
        allWordsPaged = pagedListBuilder.build()

        // PageList<Word> from WordDataSource
        val anotherDataFactory = object : DataSource.Factory<Int, Word>() {
            override fun create(): DataSource<Int, Word> {
                return WordDataSource(wordDao)
            }
        }


        val customPagedListBuilder = LivePagedListBuilder(anotherDataFactory, config)
        allWordsPagedCustom = customPagedListBuilder.build()

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