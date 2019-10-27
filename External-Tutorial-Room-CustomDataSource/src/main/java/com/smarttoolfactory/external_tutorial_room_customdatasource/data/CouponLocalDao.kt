package com.smarttoolfactory.external_tutorial_room_customdatasource.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CouponLocalDAO {
    //to fetch data required to display in each page
    @Query("SELECT * FROM Coupon WHERE  _id >= :id LIMIT :size")
    fun getCouponsBySize(id: Int, size: Int): List<Coupon>

    //this is used to populate db
    @Insert
    fun insertCoupons(coupons: List<Coupon>)
}