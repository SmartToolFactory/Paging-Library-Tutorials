package com.smarttoolfactory.external_tutorial_room_customdatasource.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.concurrent.Executors


class LocalRepository {
    private var couponDB: CouponsDB? = null
    private val LOCK = Any()
    private var ctx: Context? = null


    private val dbCallback = object : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            Executors.newSingleThreadScheduledExecutor().execute(Runnable { addCoupons(ctx) })
        }
    }

    @Synchronized
    fun getCouponDB(context: Context?): CouponsDB? {

        if (couponDB == null) {
            ctx = context
            synchronized(LOCK) {
                if (couponDB == null) {
                    couponDB = Room.databaseBuilder(
                        context!!.applicationContext,
                        CouponsDB::class.java,
                        "Coupons Database"
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(dbCallback)
                        .build()


                }
            }
        }
        return couponDB
    }

    private fun addCoupons(ctx: Context?) {

        val couponList = ArrayList<Coupon>()

        for (s in initCoupons) {
            val ss = s.split("\\|".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

            couponList.add(Coupon(store = ss[0], offer = ss[1]))
        }
        getCouponDB(ctx)!!.couponDAO().insertCoupons(couponList)


//        List<Coupon> couponList = new ArrayList<Coupon>();
//
//        for(String s : initCoupons){
//            String[] ss = s.split("\\|");
//            couponList.add(new Coupon(ss[0], ss[1]));
//        }
//        getCouponDB(ctx).couponDAO().insertCoupons(couponList);
    }

    private val initCoupons = arrayOf(
        "amazon|falt 20% off on fashion",
        "amazon|upto 30% off on electronics",
        "ebay|falt 20% off on fashion",
        "ebay|upto 40% off on electronics",
        "nordstorm|falt 30% off on fashion",
        "bestbuy|upto 80% off on electronics",
        "sears|falt 60% off on fashion",
        "ee|upto 40% off on electronics",
        "macys|falt 30% off on fashion",
        "alibaba|upto 90% off on electronics",
        "nordstorm|falt 90% off on fashion",
        "ebay|upto 40% off on electronics",
        "nordstorm|falt 30% off on fashion",
        "ebay|upto 70% off on electronics",
        "jcpenny|falt 50% off on fashion",
        "ebay|upto 50% off on electronics",
        "khols|falt 70% off on fashion",
        "ebay|upto 40% off on electronics",
        "target|falt 30% off on fashion",
        "ebay|upto 20% off on electronics",
        "costco|falt 80% off on fashion",
        "ebay|upto 40% off on electronics",
        "walmart|falt 10% off on fashion",
        "ebay|upto 10% off on electronics",
        "nordstorm|falt 30% off on fashion",
        "ebay|upto 70% off on electronics",
        "ebay|falt 40% off on fashion",
        "ebay|upto 40% off on electronics",
        "nordstorm|falt 70% off on fashion",
        "ebay|upto 80% off on electronics",
        "nordstorm|falt 30% off on fashion",
        "ebay|upto 40% off on electronics",
        "nordstorm|falt 60% off on fashion",
        "ebay|upto 50% off on electronics",
        "ebay|falt 30% off on fashion",
        "ebay|upto 70% off on electronics",
        "ebay|falt 30% off on fashion",
        "ebay|upto 40% off on electronics",
        "uuuu|falt 30% off on fashion",
        "ebay|upto 40% off on electronics",
        "tttt|falt 30% off on fashion",
        "ebay|upto 40% off on electronics",
        "ssss|falt 30% off on fashion",
        "ebay|upto 40% off on electronics",
        "eee|falt 30% off on fashion",
        "ebay|upto 40% off on electronics",
        "www|falt 30% off on fashion",
        "ebay|upto 40% off on electronics",
        "rrrr|falt 30% off on fashion",
        "tyyyy|upto 40% off on electronics",
        "vvvv|falt 30% off on fashion",
        "wwwwe|upto 40% off on electronics",
        "bbbb|falt 30% off on fashion",
        "ssssssssssssa|upto 40% off on electronics",
        "mmmm|falt 30% off on fashion",
        "rrtttt|upto 40% off on electronics"
    )

}