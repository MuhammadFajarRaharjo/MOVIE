package com.belajar.movie.auth.signIn

import android.os.Parcelable
import com.google.firebase.database.Exclude
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User (
    var name: String? = "",
    var email: String? = "",
    var password: String? = "",
    var saldo: String? = "",
    var url: String? = "",
    var username: String? = ""
) : Parcelable {
    @Exclude
    fun toMap(): Map<String, String?> {
        return mapOf(
            "name" to name,
            "email" to email,
            "password" to password,
            "saldo" to saldo,
            "url" to url,
            "username" to username,
        )
    }
}
