package com.smarttoolfactory.tutorial3_1paging_rest_and_room.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.smarttoolfactory.tutorial3_1paging_rest_and_room.data.Repository

class ViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelSearch::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ViewModelSearch(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}