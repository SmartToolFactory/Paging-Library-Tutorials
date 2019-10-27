package com.zoftino.roomrxjava.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {CouponEntity.class}, version = 1)
public abstract class CouponDatabase extends RoomDatabase {
    public abstract CouponDAO couponDao();
}
