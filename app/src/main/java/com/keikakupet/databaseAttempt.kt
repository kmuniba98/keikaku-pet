package com.keikakupet

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.content.ContentValues
import android.database.Cursor


//the DBHandler is what creates, edits, and deletes the database
class MyDBHandler(context: Context, name: String?,
                  factory: SQLiteDatabase.CursorFactory?, version: Int) :
    SQLiteOpenHelper(context, DATABASE_NAME,
        factory, DATABASE_VERSION) {

    //function to create the database
    //execSQL is the method that actually creates the database
    //onCreate just sets up what to send to that method
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TASKS_TABLE = ("CREATE TABLE " +
                TABLE_TASKS + "("
                + COLUMN_NAME + "TEXT PRIMARY KEY," +
                COLUMN_PRIORITY
                + " INT," + COLUMN_DEADLINE + "TEXT" + ")")
        db.execSQL(CREATE_TASKS_TABLE)
    }

    //onUpgrade occurs when a new version number of database is found
    //removes old database to create a new one
    override fun onUpgrade(
        db: SQLiteDatabase, oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS)
        onCreate(db)

    }

    //declares constants for database name, version, and variables
    companion object {

        private val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "taskList.db"
        val TABLE_TASKS = "tasks"

        val COLUMN_NAME = "name"
        val COLUMN_PRIORITY = "priority"
        val COLUMN_DEADLINE = "deadline"
    }

    //input: instance of task class
    //writable database provides a reference to the current database
    //then use that reference to insert the object
    fun addTask(task: Task) {

        val values = ContentValues()
        values.put(COLUMN_NAME, task.name)
        values.put(COLUMN_PRIORITY, task.priority)
        //need to change/cast deadline to string here
        values.put(COLUMN_DEADLINE, task.deadline.toString())

        val db = this.writableDatabase

        db.insert(TABLE_TASKS, null, values)
        db.close()

    }


    fun findTask(name: String?): Task? {
        val query =
            "SELECT * FROM $TABLE_TASKS WHERE $COLUMN_NAME =  \"$name\""
        val db = this.writableDatabase

        val cursor = db.rawQuery(query, null)

        var task: Task? = null

        if (cursor.moveToFirst()) {
            cursor.moveToFirst()

            val name = cursor.getString(0)
            val priorityLevel = Integer.parseInt(cursor.getString(1))
            val deadline = cursor.getString(2)
            //task = Task(name, priority, deadline)
            cursor.close()
        }
        db.close()
        return task
    }

    fun getAllRow(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_TASKS", null)
    }


    fun deleteTask(name: String): Boolean {
        var result = false

        val query =
            "SELECT * FROM $TABLE_TASKS WHERE $COLUMN_NAME = \"$name\""

        val db = this.writableDatabase

        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            val name = cursor.getString(0)
            db.delete(
                TABLE_TASKS, COLUMN_NAME + " = ?",
                arrayOf(name)
            )
            cursor.close()
            result = true
        }
        db.close()
        return result

    }


}

