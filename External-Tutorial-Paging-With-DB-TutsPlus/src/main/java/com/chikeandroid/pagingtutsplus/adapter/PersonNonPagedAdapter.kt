package com.chikeandroid.pagingtutsplus.adapter

import androidx.paging.PagedListAdapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import com.chikeandroid.pagingtutsplus.R
import com.chikeandroid.pagingtutsplus.data.Person
import kotlinx.android.synthetic.main.item_person.view.*

class PersonNonPagedAdapter() : ListAdapter<Person, PersonNonPagedAdapter.PersonViewHolder>(PersonDiffCallback()) {

    override fun onBindViewHolder(holderPerson: PersonViewHolder, position: Int) {
        var person = getItem(position)

        if (person == null) {
            holderPerson.clear()
        } else {
            holderPerson.bind(person)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        return PersonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_person,
                parent, false))
    }


    class PersonViewHolder (view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

        var tvName: TextView = view.name

        fun bind(person: Person) {
            tvName.text = person.name
        }

        fun clear() {
            tvName.text = null
        }

    }
}
