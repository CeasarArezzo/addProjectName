package com.study.addprojectname
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Level(
    var baseElementDamage: Int,
    var levelNumber : Int = -1,
    var enemyName : String,
    var seed : Int,
    var fireDamage : Int = baseElementDamage,
    var waterDamage : Int = baseElementDamage,
    var grassDamage : Int = baseElementDamage,
    var electricityDamage : Int = baseElementDamage,
    var darkDamage : Int = baseElementDamage,
    var lightDamage : Int = baseElementDamage,
    var groundDamage : Int = baseElementDamage,
    var airDamage : Int = baseElementDamage,
    var healing : Int = 0,
    var reduceDamage : Int = 0,
    var timer : Int = Int.MAX_VALUE,
    var damagePerTurn : Int,
    var healthPoints : Int = 100
) : Parcelable {

    constructor(levelNumber : Int, enemy: String, seed : Int, damagePerTurn : Int) : this(
        10,
        levelNumber,
        enemy,
        seed,
        8,
        8,
        8,
        8,
        8,
        8,
        8,
        8,
        0,
        0,
        Int.MAX_VALUE,
        10,
        100 ) {
    }

    //todo: fix it nie chce mi sie tego poprawiac jak pierwszego xD
    constructor(LevelNumber : Int, Enemy: String, Seed : Int, DamagePerTurn : Int, fireDamage : Int, waterDamage: Int, grassDamage : Int, electricityDamage : Int, darkDamage : Int, lightDamage : Int, groundDamage : Int, airDamage : Int, healing : Int, reduceDamage : Int, timer : Int) : this (
        10,
        LevelNumber,
        Enemy,
        Seed ,
        fireDamage ,
        waterDamage,
        grassDamage,
        electricityDamage,
        darkDamage,
        lightDamage,
        groundDamage,
        airDamage,
        healing,
        reduceDamage,
        timer,
        DamagePerTurn,
        100
    )
}