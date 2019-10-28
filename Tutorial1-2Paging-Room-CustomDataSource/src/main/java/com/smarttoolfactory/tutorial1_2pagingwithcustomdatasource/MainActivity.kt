package com.smarttoolfactory.tutorial1_2pagingwithcustomdatasource

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smarttoolfactory.tutorial1_2pagingwithcustomdatasource.adapter.WordPagedListAdapter
import com.smarttoolfactory.tutorial1_2pagingwithcustomdatasource.data.TestBoundaryCallback
import com.smarttoolfactory.tutorial1_2pagingwithcustomdatasource.data.Word
import com.smarttoolfactory.tutorial1_2pagingwithcustomdatasource.data.WordDataSource

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
 *  val pagedListBuilder = LivePagedListBuilder(wordDao.getAllUsersPaged(), config)
 *  allWordsPaged = pagedListBuilder.build()
 *
 * * 4- Create an adapter that extends PagedListAdapter<Word, WordPagedListAdapter.WordViewHolder>
 *
 * * 5- setEnablePlaceholders(false) for scrollbar to be shorter

 */
class MainActivity : AppCompatActivity() {

    private val pagedListAdapter by lazy {
        WordPagedListAdapter()
    }

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
                repeat(200, insertItems())
            }

            findViewById<TextView>(R.id.tvItemCount).text = "Item Count $it"

        })

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)


        recyclerView.adapter = pagedListAdapter

        // Option 1 Listen PagedList with default DataSource
//        subscribeToPagedList()

        // Option 2 Listen PagedList with custom DataSource and BoundaryCallback
        subscribeToCustomPagedList()

    }


    /**
     * Subscribes to [PagedList] with default DataSource
     */
    private fun subscribeToPagedList() {

        wordRepository.allWordsPaged.observe(this, Observer {
            if (it != null) pagedListAdapter.submitList(it)
            println("😎 MainActivity allWordsPaged size: ${it.size}")
        })


    }

    /**
     * Subscribes to [PagedList] with [WordDataSource] and [TestBoundaryCallback]
     *
     * [WordDataSource] uses paging like data retrieve operation from network
     *
     * [TestBoundaryCallback] is for testing to see callback methods when list reaches end
     */
    private fun subscribeToCustomPagedList() {

        wordRepository.allWordsPagedCustom.observe(this, Observer {
            if (it != null) pagedListAdapter.submitList(it)
            println("😎 MainActivity allWordsPagedCustom size: ${it.size}")
        })
    }


    // Lambda Expression
    private fun insertItems(): (Int) -> Unit = { index ->
        wordRepository.insert(Word("Hello $index"))
    }
}
