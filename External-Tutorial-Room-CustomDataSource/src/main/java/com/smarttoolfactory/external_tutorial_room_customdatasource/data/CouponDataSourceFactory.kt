package com.smarttoolfactory.external_tutorial_room_customdatasource.data

import android.content.Context
import androidx.paging.DataSource

/**
 * data source factory passed to PageList which calls create method to get
 * data source object
 */
class CouponsDataSourceFactory(private val ctx: Context) : DataSource.Factory<Int, Coupon>() {

    private var couponsDataSource: CouponsDataSource? = null

    override fun create(): DataSource<Int, Coupon> {

        if (couponsDataSource == null) {
            couponsDataSource = CouponsDataSource(ctx)
        }

        return couponsDataSource as CouponsDataSource
    }
}