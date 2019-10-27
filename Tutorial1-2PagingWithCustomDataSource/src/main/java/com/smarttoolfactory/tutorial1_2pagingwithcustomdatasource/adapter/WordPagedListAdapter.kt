package com.smarttoolfactory.tutorial1_2pagingwithcustomdatasource.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.smarttoolfactory.tutorial1_2pagingwithcustomdatasource.data.Word
import com.smarttoolfactory.tutorial1_2pagingwithcustomdatasource.R


class WordPagedListAdapter :
    PagedListAdapter<Word, WordPagedListAdapter.WordViewHolder>(DIFF_CALLBACK) {


    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): WordViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item_paged, parent, false)
        return WordViewHolder(view)
    }

    override fun onBindViewHolder(@NonNull holder: WordViewHolder, position: Int) {
        val current = getItem(position)
        holder.wordItemView.text = current?.word
    }


    class WordViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.textView)
    }

    companion object {

        var DIFF_CALLBACK: DiffUtil.ItemCallback<Word> = object : DiffUtil.ItemCallback<Word>() {

            override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
                return oldItem.word === newItem.word
            }

            override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
                return oldItem == newItem
            }
        }
    }
}