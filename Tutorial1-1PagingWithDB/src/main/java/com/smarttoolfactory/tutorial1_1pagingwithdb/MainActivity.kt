package com.smarttoolfactory.tutorial1_1pagingwithdb

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smarttoolfactory.tutorial1_1pagingwithdb.adapter.WordListAdapter
import com.smarttoolfactory.tutorial1_1pagingwithdb.data.Word
import com.smarttoolfactory.tutorial1_1pagingwithdb.data.WordRepository

class MainActivity : AppCompatActivity() {

    val wordRepository by lazy {
        WordRepository(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        bindViews()
    }


    // Lambda Expression
    private fun insertItems(): (Int) -> Unit = { index ->

        println("MainActivit insertItems index: $index")

        wordRepository.insert(Word("Hello $index"))

    }

    private fun bindViews() {

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val pagedListAdapter = WordListAdapter()

        recyclerView.adapter = pagedListAdapter

        wordRepository.allWords.observe(this, androidx.lifecycle.Observer {
//            pagedListAdapter.setWords(it)
            if (it != null) pagedListAdapter.submitList(it)
        })

        wordRepository.getItemCount().observe(this, androidx.lifecycle.Observer {

            // Populate DB if there are no items
            if(it == 0) {
                repeat(10_000, insertItems())
            }

            findViewById<TextView>(R.id.tvItemCount).text = "Item Count $it"

        })

    }
}
