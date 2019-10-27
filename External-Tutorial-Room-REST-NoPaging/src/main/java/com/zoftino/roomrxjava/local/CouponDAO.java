package com.zoftino.roomrxjava.local;



import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
public interface CouponDAO {
    @Query("SELECT * FROM CouponEntity")
    Flowable<List<CouponEntity>> getCoupons();

    @Query("SELECT * FROM CouponEntity WHERE store = :storeIn ")
    Maybe<CouponEntity> getCouponByStore(String storeIn);

    @Query("SELECT * FROM CouponEntity LIMIT 1")
    Single<CouponEntity> getOneCoupon();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCoupon(CouponEntity coupon);


    @Query("DELETE FROM CouponEntity")
    void deleteAllCoupons();
}
