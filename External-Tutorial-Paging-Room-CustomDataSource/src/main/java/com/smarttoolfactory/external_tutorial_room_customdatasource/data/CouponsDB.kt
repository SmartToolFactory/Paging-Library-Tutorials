package com.smarttoolfactory.external_tutorial_room_customdatasource.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Coupon::class], version = 1)
abstract class CouponsDB : RoomDatabase() {
    abstract fun couponDAO(): CouponLocalDAO
}