package com.smarttoolfactory.external_tutorial_room_customdatasource.data

import android.content.Context
import androidx.annotation.NonNull
import androidx.paging.PageKeyedDataSource


//data source for PagedList, it is used for loading data for each page
class CouponsDataSource(ctx: Context) : PageKeyedDataSource<Int, Coupon>() {

    private val couponDAO: CouponLocalDAO = LocalRepository().getCouponDB(ctx)!!.couponDAO()

    //is called too load initial data
    override fun loadInitial(
        @NonNull params: LoadInitialParams<Int>,
        @NonNull callback: LoadInitialCallback<Int, Coupon>
    ) {


        var couponList = couponDAO.getCouponsBySize(0, params.requestedLoadSize)

        //this is required to handle first request after db is created or app is installed
        var noOfTries = 0

        while (couponList.isEmpty()) {
            couponList = couponDAO.getCouponsBySize(0, params.requestedLoadSize)
            noOfTries++
            if (noOfTries == 6) {
                break
            }
            try {
                Thread.sleep(500)
            } catch (e: InterruptedException) {
            }

        }

        callback.onResult(
            couponList, null,
            couponList.size + 1
        )

        println("🤑 CouponsDataSource loadInitial() couponList size: ${couponList.size}")


    }

    override fun loadBefore(
        @NonNull params: LoadParams<Int>,
        @NonNull callback: LoadCallback<Int, Coupon>
    ) {

        println("🏪 CouponsDataSource loadBefore()")
    }

    //is called to load pages of data using key passed in params
    override fun loadAfter(
        @NonNull params: LoadParams<Int>,
        @NonNull callback: LoadCallback<Int, Coupon>
    ) {


        val couponList = couponDAO.getCouponsBySize(params.key, params.requestedLoadSize)
        val nextKey = params.key + couponList.size
        callback.onResult(couponList, nextKey)

        println("🗿 CouponsDataSource loadAfter() couponList size: ${couponList.size},params.key: ${params.key}, next key: $nextKey")
    }
}