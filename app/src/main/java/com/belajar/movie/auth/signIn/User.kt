package com.belajar.movie.auth.signIn

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User (
    var name: String? = "",
    var email: String? = "",
    var password: String? = "",
    var saldo: String? = "",
    var url: String? = "",
    var username: String? = ""
) : Parcelable
