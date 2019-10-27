package com.smarttoolfactory.external_tutorial_room_customdatasource

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smarttoolfactory.external_tutorial_room_customdatasource.adapter.CouponAdapter


/**
 *
 * Example to check out custom DataSource implementation. CouponsDataSource class is where magic happens.
 *
 * 1. The important behavior of PagedList is that it can’t reload records if data is updated in the database.
 * To show updates of already loaded data, new PagedList object has to be created.
 *
 * 2. DataSource.Factory which is passed to LivePagedListBuilder is a factory for data source.
 * You need to create DataSource.Factory and override create() method which returns DataSource object.
 *
 * 3. DataSource is used for loading data by PagedList. You can create DataSource by extending one of
 * the three data source classes such as **PageKeyedDataSource**,
 * **ItemKeyedDataSource**, or **PositionalDataSource**.
 * You need to implement **loadInitial** and **loadAfter** methods.
 * Using parameters in **loadInitial** and **loadAfter** methods, you can prepare query and load data.
 *
 * 4. PagedListAdapter calls **loadAround** on PagedList object to make PagedList load data for next pages
 * as user scrolls the items in recycler view. PagedListAdapter listens for data changes
 * and displays the changes in recycler view.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.coupons_rv)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.getContext(),
            LinearLayoutManager.VERTICAL
        )
        recyclerView.addItemDecoration(dividerItemDecoration)

        val viewModel = ViewModelProviders.of(
            this,
            CouponViewModel.CouponViewModelFactory(this.application)
        )
            .get(CouponViewModel::class.java)

        val adapter = CouponAdapter()
        recyclerView.adapter = adapter


        //listen to data changes and pass it to adapter for displaying in recycler view
        viewModel.couponList.observe(this, Observer { pagedList ->

            println("😎 MainActivity couponList size: ${pagedList.size}")

            adapter.submitList(pagedList)

        })


    }
}
