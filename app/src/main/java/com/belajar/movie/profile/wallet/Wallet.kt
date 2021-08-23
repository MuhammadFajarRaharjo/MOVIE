package com.belajar.movie.profile.wallet

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Wallet(
    var title: String = "",
    var date: String = "",
    var money: Double = 0.0,
    var status: Boolean = true
) : Parcelable
