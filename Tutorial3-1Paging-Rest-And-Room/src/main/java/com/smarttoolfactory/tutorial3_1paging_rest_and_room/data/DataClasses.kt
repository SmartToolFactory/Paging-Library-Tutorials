package com.smarttoolfactory.tutorial3_1paging_rest_and_room.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class RepoResponse(
    @SerializedName("total_count") val total: Int = 0,
    @SerializedName("items") val items: List<RepoModel> = emptyList(),
    val nextPage: Int? = null
)

data class RepoResult(
    val data: LiveData<PagedList<RepoModel>>,
    val networkErrors: LiveData<String>
)

@Entity(tableName = "repos")
data class RepoModel(
    @PrimaryKey @field:SerializedName("id") val id: Long,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("full_name") val fullName: String,
    @field:SerializedName("description") val description: String?,
    @field:SerializedName("html_url") val url: String,
    @field:SerializedName("stargazers_count") val stars: Int,
    @field:SerializedName("forks_count") val forks: Int,
    @field:SerializedName("language") val language: String?
)