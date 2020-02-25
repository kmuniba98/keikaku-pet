package com.keikakupet

//﻿package com.ebookfrenzy.database

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import kotlinx.android.synthetic.main.activity_database.*

class DatabaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)
    }

    fun newTask(view: View) {
        val dbHandler = MyDBHandler(this, null, null, 1)

        val name = name.text.toString()

        val priorityLevel = priorityLevel

            Task(name.text.toString(), priorityLevel, deadline.text.toString() )

        dbHandler.addTask(task)
        name.setText("")
        priorityLevel
        deadline.setText("")
    }

    fun lookupTask(view: View) {
        val dbHandler = MyDBHandler(this, null, null, 1)

        val task = dbHandler.findTask(
            name.text.toString())

        if (task != null) {
            priorityLevel = task.priorityLevel
            deadline.text = task.deadline.toString()

        } else {
            name.text = "No Match Found"
        }
    }

    fun removeTask(view: View) {
        val dbHandler = MyDBHandler(this, null, null, 1)

        val result = dbHandler.deleteTask(
            name.text.toString())

        if (result) {
            //I don't know what we do on the delete action yet
            //productID.text = "Record Deleted"
            //productName.setText("")
            //productQuantity.setText("")
        } else
            name.text = "No Match Found"
    }
}
