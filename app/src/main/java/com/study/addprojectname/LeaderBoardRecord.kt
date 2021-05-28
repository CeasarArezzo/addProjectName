package com.study.addprojectname

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LeaderBoardRecord (
    val levelName : String,
    val playerName: String,
    val score : String

) : Parcelable