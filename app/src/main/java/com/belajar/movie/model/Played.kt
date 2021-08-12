package com.belajar.movie.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Played (
    var nama: String = "",
    var url: String = "",
) : Parcelable
