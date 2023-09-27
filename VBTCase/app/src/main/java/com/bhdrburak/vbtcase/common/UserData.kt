package com.bhdrburak.vbtcase.common

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserData(val username: String, val age: Int) : Parcelable
