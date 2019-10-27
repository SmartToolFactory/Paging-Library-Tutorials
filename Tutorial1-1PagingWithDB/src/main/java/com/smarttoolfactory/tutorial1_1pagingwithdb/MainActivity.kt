package com.smarttoolfactory.tutorial1_1pagingwithdb

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smarttoolfactory.tutorial1_1pagingwithdb.adapter.WordListAdapter
import com.smarttoolfactory.tutorial1_1pagingwithdb.adapter.WordPagedListAdapter
import com.smarttoolfactory.tutorial1_1pagingwithdb.data.Word


/**
 * Sample for paging only with Database
 *
 * * 1- Create a Dao that returns DataSource.Factory<Int, Word>
 *
 * * 2- Create a config to set properties of list by
 * config = PagedList.Config.Builder()
 * .setPageSize(20)
 * .setEnablePlaceholders(true)
 * .setPrefetchDistance(10)
 * .build()
 *
 * * 3- Create LivePagedBuilder and get LiveData<PagedList<Word>> using it
 *  val pagedListBuilder = LivePagedListBuilder(wordDao.getWordsPaged(), config)
 *  allWordsPaged = pagedListBuilder.build()
 *
 * * 4- Create an adapter that extends PagedListAdapter<Word, WordPagedListAdapter.WordViewHolder>
 *
 * * 5- setEnablePlaceholders(false) for scrollbar to be shorter

 */
class MainActivity : AppCompatActivity() {

    private val wordRepository by lazy {
        WordRepository(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindViews()
    }


    private fun bindViews() {

        wordRepository.getItemCount().observe(this, Observer {

            // Populate DB if there are no items
            if (it == 0) {
                repeat(10_000, insertItems())
            }

            findViewById<TextView>(R.id.tvItemCount).text = "Item Count $it"

        })

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)


        // TODO Option 1 Adapter
//        createList(recyclerView)

        // TODO Option 2 Paged Adapter
        createPagedList(recyclerView)


    }

    /**
     * Create a RecyclerView adapter that does not use paging
     */
    private fun createList(recyclerView: RecyclerView) {

        val adapter = WordListAdapter()

        recyclerView.adapter = adapter

        wordRepository.allWords.observe(this, Observer {
            if (it != null) adapter.submitList(it)
            println("😎 MainActivity allWords size: ${it.size}")
        })

    }

    /**
     * Create a RecyclerView adapter that uses [PagedList]
     */
    private fun createPagedList(recyclerView: RecyclerView) {

        val pagedListAdapter = WordPagedListAdapter()

        recyclerView.adapter = pagedListAdapter

        wordRepository.allWordsPaged.observe(this, Observer {
            if (it != null) pagedListAdapter.submitList(it)
            println("😎 MainActivity allWordsPaged size: ${it.size}")

        })

    }


    // Lambda Expression
    private fun insertItems(): (Int) -> Unit = { index ->
        wordRepository.insert(Word("Hello $index"))
    }
}
