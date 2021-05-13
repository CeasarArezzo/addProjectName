package com.study.addprojectname

class Level {
    private val baseElementDamage = 10
    val levelNumber : Int
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
    val damagePerTurn : Int
    val playerHealthPoints : Int = 100
    var enemyHealthPoints : Int = 100


    constructor(LevelNumber : Int, Enemy: String, Seed : Int, DamagePerTurn : Int)
    {
        this.levelNumber = LevelNumber
        this.enemy = Enemy
        this.seed = Seed
        this.damagePerTurn = DamagePerTurn
    }

    constructor(LevelNumber : Int, Enemy: String, Seed : Int, enemyHealthPoints: Int, DamagePerTurn : Int, fireDamage : Int, grassDamage : Int, electricityDamage : Int, darkDamage : Int, lightDamage : Int, groundDamage : Int, airDamage : Int, healing : Int, reduceDamage : Int, timer : Int)
    {
        this.levelNumber = LevelNumber
        this.enemy = Enemy
        this.seed = Seed
        this.enemyHealthPoints = enemyHealthPoints
        this.damagePerTurn = DamagePerTurn
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