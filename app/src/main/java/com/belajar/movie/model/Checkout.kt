package com.belajar.movie.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Checkout(
    var nameFilm: String= "",
    var bangku: String = "",
    var harga: Int = 0,
) : Parcelable
