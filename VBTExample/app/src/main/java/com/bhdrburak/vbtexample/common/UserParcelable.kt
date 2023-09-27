package com.bhdrburak.vbtexample.common

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class UserParcelable(val firstName: String, val lastName: String) : Parcelable