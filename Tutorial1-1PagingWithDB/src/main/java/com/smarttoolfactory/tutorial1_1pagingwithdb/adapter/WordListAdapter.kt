package com.smarttoolfactory.tutorial1_1pagingwithdb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.smarttoolfactory.tutorial1_1pagingwithdb.R
import com.smarttoolfactory.tutorial1_1pagingwithdb.data.Word


class WordListAdapter : ListAdapter<Word, WordListAdapter.WordViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(view)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = getItem(position)
        holder.wordItemView.text = current?.word

        println("🏪 WordListAdapter onBindViewHolder() position: $position")
        println("🚗 WordListAdapter getCurrentList(): ${currentList?.size}")
    }


    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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