package com.study.addprojectname
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Level(
    val levelNumber : Int,
    val enemyName: String,
    val Seed : Int,
    val damagePerTurn : Int,
    val fireDamage : Int,
    val waterDamage: Int,
    val grassDamage : Int,
    val electricityDamage : Int,
    val darkDamage : Int,
    val lightDamage : Int,
    val groundDamage : Int,
    val airDamage : Int,
    val healing : Int,
    val reduceDamage : Int,
    val timer : Int,
    val baseElementDamage : Int = 10,
    var healthPoints  : Int,
    var health : Int = 100
) : Parcelable