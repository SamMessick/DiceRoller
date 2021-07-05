package com.example.diceroller

/**
 * Interface for a tossable object with a number of random outcomes determined by sides
 */
abstract class Tossable {

    protected abstract val sides : Int
    protected abstract val name : String
    protected abstract var lastSide : String

    abstract fun toss() : String
}