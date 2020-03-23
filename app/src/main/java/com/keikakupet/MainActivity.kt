package com.keikakupet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    //create variables for database
    var DBHelper: MyDBHandler by Delegates.notNull()
    var myDB: SQLiteDatabase by Delegates.notNull()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    //initialize database variables
        DBHelper = MyDBHandler(this, "TASKS_LIST",null ,1 )
        myDB = DBHelper.getWritableDatabase()




        // launch ToDoListActivity
        val toDoListBtn = findViewById<Button>(R.id.toDoListBtn)
        toDoListBtn.setOnClickListener {
            val startIntent = Intent(this, ToDoListActivity::class.java)
            startActivity(startIntent)
        }
    }
}
