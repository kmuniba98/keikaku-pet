package com.keikakupet

import java.util.*

class PetStatus {

    private var maxHealth: Int = 10
    private var currentHealth: Int = 10
    private var healthIncrementer: Int = 2 // amount by which health increments when a task is completed
    private var healthDecrementer: Int = 0 // amount by which health decrements when user approaches / misses deadline
    private var isHungry: Boolean = false
    private var isTired: Boolean = false
    private var isSick: Boolean = false
    private var isAlive: Boolean = true

    // method to update pet's health and ailment upon completing a task
    fun processCompletedTask(){
        incrementHealth()
        removeAilment()
    }

    // method to update pet's health and ailment upon missing a task
    fun processMissedTask(){
        decrementHealth()
        addAilment()
    }

    /*
    Potentially creating another method to update pet's
    health and status because of an approaching deadline.
     */

    // method to decrement the pet's health
    private fun decrementHealth(){
        currentHealth-=healthDecrementer
        if(currentHealth <= 0)
            isAlive = false
    }

    // method to increment the pet's health
    private fun incrementHealth(){
        val sum = currentHealth + healthIncrementer
        if(sum > maxHealth)
            currentHealth = maxHealth
        else
            currentHealth = sum
    }

    // method to add an ailment to the pet
    private fun addAilment(){
        // if no ailment, randomly assign hungry or tired
        if(!isHungry && !isTired && !isSick){
            val rand = Random()
            val randBool = rand.nextBoolean()
            if(randBool)
                isHungry = true
            else
                isTired = true
            healthDecrementer = 1
        }

        // otherwise, if hungry XOR tired, assign the other
        else if(isHungry && !isTired){
            isTired = true
            healthDecrementer = 2
        }
        else if(isTired && !isHungry){
            isHungry = true
            healthDecrementer = 2
        }

        // otherwise, if both hungry AND tired, assign sick
        else if(isHungry && isTired){
            isSick = true
            healthDecrementer = 3
        }
    }

    private fun removeAilment(){
        // if sick, remove sick
        if(isSick){
            isSick = false
            healthDecrementer = 2
        }

        // otherwise, if hungry and tired, remove one of the two randomly
        else if(isHungry && isTired){
            val rand = Random()
            val randBool = rand.nextBoolean()
            if(randBool)
                isHungry = false
            else
                isTired = false
            healthDecrementer = 1
        }

        // otherwise, if hungry XOR tired, remove relevant ailment
        else if(isHungry && !isTired){
            isHungry = false
            healthDecrementer = 0
        }
        else if(isTired){
            isTired = false
            healthDecrementer = 0
        }
    }
}