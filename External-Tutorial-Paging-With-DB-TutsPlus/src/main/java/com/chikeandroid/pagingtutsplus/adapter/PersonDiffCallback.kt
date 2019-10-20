package com.chikeandroid.pagingtutsplus.adapter
import androidx.recyclerview.widget.DiffUtil
import com.chikeandroid.pagingtutsplus.data.Person

class PersonDiffCallback : DiffUtil.ItemCallback<Person>() {

    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem.id == newItem.id
    }


}