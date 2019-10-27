package com.chikeandroid.pagingtutsplus.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "persons")
data class Person(
        @PrimaryKey val id: String,
        val name: String
) {
    override fun toString() = name
}