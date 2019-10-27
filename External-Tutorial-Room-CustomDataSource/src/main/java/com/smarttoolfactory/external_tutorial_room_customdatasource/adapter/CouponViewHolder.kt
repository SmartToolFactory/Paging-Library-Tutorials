package com.smarttoolfactory.external_tutorial_room_customdatasource.adapter

import com.smarttoolfactory.external_tutorial_room_customdatasource.data.Coupon
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.smarttoolfactory.external_tutorial_room_customdatasource.R


class CouponViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var storeNameTv: TextView
    var couponTv: TextView

    init {
        storeNameTv = view.findViewById(R.id.coupon_store)
        couponTv = view.findViewById(R.id.coupon_tv)
    }

    fun bindTO(coupon: Coupon) {
        storeNameTv.text = coupon.store
        couponTv.text = coupon.offer
    }
}