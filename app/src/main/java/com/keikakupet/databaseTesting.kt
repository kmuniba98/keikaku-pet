/*
package com.keikakupet


//import android.support.v7.app.AppCompatActivity
import androidx.appcompat.app.AppCompatActivity;
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

        var name = name.text.toString()

        var priorityLevel = Integer.parseInt(priorityLevel)

        var deadline = deadline.text.toString()

        var task = Task(name.text.toString(), priorityLevel, deadline.text.toString() )

        dbHandler.addTask(task)
        //name.setText("")
        //priorityLevel
        //deadline.setText("")
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

        val result = dbHandler.deleteTask(name.text.toString())

        if (result) {
            //I don't know what we do on the delete action yet
            task.name = "Deleted"
        } else
            name.text = "No Match Found"
    }
}
*/
