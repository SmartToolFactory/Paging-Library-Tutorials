package com.chikeandroid.pagingtutsplus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.chikeandroid.pagingtutsplus.adapter.PersonAdapter
import com.chikeandroid.pagingtutsplus.adapter.PersonNonPagedAdapter
import com.chikeandroid.pagingtutsplus.viewmodels.PersonsViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: PersonsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(PersonsViewModel::class.java)

        // Paged Adapter
        val adapterPaged = PersonAdapter()
        subscribeUiPaged(adapterPaged)

        // Non-paged Adapter
//        val adapter = PersonNonPagedAdapter()

        findViewById<RecyclerView>(R.id.name_list).adapter = adapterPaged

//        subscribeUI(adapter)
    }


    private fun subscribeUiPaged(adapter: PersonAdapter) {
        viewModel.getPersonLiveData().observe(this, Observer { names ->
            if (names != null) adapter.submitList(names)
        })
    }

    private fun subscribeUI(adapter: PersonNonPagedAdapter) {

        viewModel.getPersonNonPagedLiveData().observe(this, Observer {
            if (it != null) adapter.submitList(it)
        })
    }
}