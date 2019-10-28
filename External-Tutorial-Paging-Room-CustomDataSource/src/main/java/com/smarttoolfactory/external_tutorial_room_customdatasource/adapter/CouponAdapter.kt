package com.smarttoolfactory.external_tutorial_room_customdatasource.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.smarttoolfactory.external_tutorial_room_customdatasource.R
import com.smarttoolfactory.external_tutorial_room_customdatasource.data.Coupon


/** this adapter displays coupon items in recycler view
 * it extends PagedListAdapter which gets data from PagedList
 * and displays in recycler view as data is available in PagedList
 */
class CouponAdapter :
    PagedListAdapter<Coupon, CouponViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CouponViewHolder {
        val li = LayoutInflater.from(parent.context)
        val view = li.inflate(R.layout.coupon_item, parent, false)
        return CouponViewHolder(view)
    }

    override fun onBindViewHolder(holder: CouponViewHolder, position: Int) {

        println("🎃 CouponAdapter onBindViewHolder() position: $position")
        println("🥶 CouponAdapter getCurrentList(): ${currentList?.size}")

        val coupon = getItem(position)
        if (coupon != null) {
            holder.bindTO(coupon)
        }
    }

    companion object {

        //DiffUtil is used to find out whether two object in the list are same or not
        var DIFF_CALLBACK: DiffUtil.ItemCallback<Coupon> =
            object : DiffUtil.ItemCallback<Coupon>() {
                override fun areItemsTheSame(
                    @NonNull oldCoupon: Coupon,
                    @NonNull newCoupon: Coupon
                ): Boolean {
                    return oldCoupon._id == newCoupon._id
                }

                override fun areContentsTheSame(
                    @NonNull oldCoupon: Coupon,
                    @NonNull newCoupon: Coupon
                ): Boolean {
                    return oldCoupon == newCoupon
                }
            }
    }
}