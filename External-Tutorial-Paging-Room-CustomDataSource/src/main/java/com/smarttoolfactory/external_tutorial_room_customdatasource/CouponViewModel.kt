package com.smarttoolfactory.external_tutorial_room_customdatasource

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.smarttoolfactory.external_tutorial_room_customdatasource.data.Coupon
import com.smarttoolfactory.external_tutorial_room_customdatasource.data.CouponsDataSourceFactory


class CouponViewModel(application: Application) : ViewModel() {
    //PagedList controls data loading using data source
    var couponList: LiveData<PagedList<Coupon>>

    init {

        //instantiate CouponsDataSourceFactory
        val factory = CouponsDataSourceFactory(application)

        //create PagedList Config
        val config = PagedList.Config.Builder().setEnablePlaceholders(true)
            .setInitialLoadSizeHint(20)
            .setPageSize(25).build()

        //create LiveData object using LivePagedListBuilder which takes
        //data source factory and page config as params
        couponList = LivePagedListBuilder(factory, config).build()
    }

    //factory for creating view model,
    // required because we need to pass Application to view model object
    class CouponViewModelFactory(private val mApplication: Application) :
        ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(viewModel: Class<T>): T {
            return CouponViewModel(mApplication) as T
        }
    }
}