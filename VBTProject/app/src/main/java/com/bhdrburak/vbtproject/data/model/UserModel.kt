package com.bhdrburak.vbtproject.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Entity(tableName = "repos")
@Parcelize
data class UserModel(
    @PrimaryKey
    val id: Int,
    var isFav: Boolean = false,
    @SerializedName("full_name")
    val fullname: String,
    @SerializedName("stargazers_count")
    val starCount: Int,
    @SerializedName("open_issues_count")
    val issuesCount: Int,

    ) : Parcelable
