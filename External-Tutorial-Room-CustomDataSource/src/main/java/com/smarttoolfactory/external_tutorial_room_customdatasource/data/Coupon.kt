package com.smarttoolfactory.external_tutorial_room_customdatasource.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Coupon(
    @PrimaryKey(autoGenerate = true)
    var _id: Int = 0,
    var store: String? = null,
    var offer: String? = null
) {


    override fun equals(obj: Any?): Boolean {
        if (obj === this)
            return true
        val coupon = obj as Coupon?
        return coupon!!._id == this._id &&
                coupon.store === coupon.store &&
                coupon.offer === coupon.offer
    }
}