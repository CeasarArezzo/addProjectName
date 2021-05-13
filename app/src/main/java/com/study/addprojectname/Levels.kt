package com.study.addprojectname

class Levels {
    private val baseElementDamage = 10
    val enemy : String
    val seed : Int
    var fireDamage : Int = baseElementDamage
    var waterDamage : Int = baseElementDamage
    var grassDamage : Int = baseElementDamage
    var electricityDamage : Int = baseElementDamage
    var darkDamage : Int = baseElementDamage
    var lightDamage : Int = baseElementDamage
    var groundDamage : Int = baseElementDamage
    var airDamage : Int = baseElementDamage
    var healing : Int = 0
    var reduceDamage : Int = 0
    var timer : Int = Int.MAX_VALUE
    val playerHealthPoints : Int
    val enemyHealthPoints : Int


    constructor(Enemy: String, Seed : Int, playerHealthPoints : Int, enemyHealthPoints : Int)
    {
        this.enemy = Enemy
        this.seed = Seed
        this.playerHealthPoints = playerHealthPoints
        this.enemyHealthPoints = enemyHealthPoints
    }

    constructor(Enemy: String, Seed : Int, playerHealthPoints : Int, enemyHealthPoints: Int, fireDamage : Int, grassDamage : Int, electricityDamage : Int, darkDamage : Int, lightDamage : Int, groundDamage : Int, airDamage : Int, healing : Int, reduceDamage : Int, timer : Int)
    {
        this.enemy = Enemy
        this.seed = Seed
        this.playerHealthPoints = playerHealthPoints
        this.enemyHealthPoints = enemyHealthPoints
        this.fireDamage = fireDamage
        this.waterDamage = waterDamage
        this.grassDamage = grassDamage
        this.electricityDamage = electricityDamage
        this.darkDamage = darkDamage
        this.lightDamage = lightDamage
        this.groundDamage = groundDamage
        this.airDamage = airDamage
        this.healing = healing
        this.reduceDamage = reduceDamage
        this.timer = timer
    }
}