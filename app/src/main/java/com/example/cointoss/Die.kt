package com.example.cointoss

/**
 * Dice class. Can be rolled and return a roll result.
 *
 * Constructor Parameters:
 *
 * sides: number of sides on the die
 */
class Die(override val sides: Int = 6) : Tossable() {

    override val name = "die"
    override var lastSide = "1"

    override fun toss(): String {
        lastSide = (1..sides).random().toString()
        return lastSide
    }

}