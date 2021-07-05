package com.example.diceroller

/**
 * Coin class. Can be flipped and return a flip result
 */
class Coin() : Tossable() {

    override val sides = 2
    override val name = "coin"
    override var lastSide = "heads"

    override fun toss() : String {
        lastSide = listOf("Heads", "Tails").random().toString()
        return lastSide
    }
}