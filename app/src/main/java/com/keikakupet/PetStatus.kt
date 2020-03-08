package com.keikakupet

class PetStatus {

    private var maxHealth: Int = 5
    private var currentHealth: Int = 5
    private var isAlive: Boolean = true

    fun decrementHealth(){
        currentHealth--

        if(currentHealth <= 0)
            isAlive = false
    }

    fun incremementHealth(){
        currentHealth++
    }
}