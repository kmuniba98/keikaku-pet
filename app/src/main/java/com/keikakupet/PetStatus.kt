package com.keikakupet

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import java.io.File
import java.util.*
import java.io.BufferedReader

class PetStatus(
    var maxHealth: Int = 10,var currentHealth: Int = 10,
    var healthIncrementer: Int = 2, // amount by which health increments when a task is completed
    var healthDecrementer: Int = 0, // amount by which health decrements when user approaches / misses deadline
    var isHungry: Boolean = false,
    var isTired: Boolean = false,
    var isSick: Boolean = false,
    var isAlive: Boolean = true) {

    companion object {

        lateinit var context: Context // getters and setters for java are automatically generated

        operator fun invoke(): PetStatus {
            //if a json exists, use it to update PetStatus
            val context = context
            var file = File(context.getFilesDir(), "PetStatus.json")
            if(file.exists()){
                val bufferedReader: BufferedReader = file.bufferedReader()
                val json = bufferedReader.readText()
                val retrievedStatus = Gson().fromJson(json, PetStatus::class.java)
                Log.d("JSON_FROM_FILE", json)
                return retrievedStatus
            } else {
                return PetStatus()
            }
        }
    }

    // method to update pet's health and ailment upon completing a task
    fun processCompletedTask(){
        incrementHealth()
        removeAilment()
        Log.d("TASK_COMPLETE", "completed task processed")
    }

    fun getHealthPercent(): Double {
        return currentHealth.toDouble() / maxHealth
    }

    // method to update pet's health and ailment upon missing a task
    fun processMissedTask(){
        decrementHealth()
        addAilment()
        Log.d("TASK_MISSED", "missed task processed")
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
        updateJson()
    }

    // method to increment the pet's health
    fun incrementHealth(){
        val sum = currentHealth + healthIncrementer
        if(sum > maxHealth)
            currentHealth = maxHealth
        else
            currentHealth = sum
        updateJson()
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

        updateJson()
    }

    // method to remove an ailment from the pet
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

        updateJson()
    }

    private fun updateJson(){
        var json: String = Gson().toJson(this)
        val context = context
        var file = File(context.getFilesDir(), "PetStatus.json")
        file.writeText(json)
        Log.d("JSON_UPDATED", json)
    }

}