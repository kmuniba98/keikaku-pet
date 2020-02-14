package com.keikakupet

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.content.ContentValues

class MyDBHandler(context: Context, name: String?,
                  factory: SQLiteDatabase.CursorFactory?, version: Int) :
    SQLiteOpenHelper(context, DATABASE_NAME,
        factory, DATABASE_VERSION) {

    //function to create the database
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TASKS_TABLE = ("CREATE TABLE " +
                TABLE_TASKS + "("
                + COLUMN_NAME + "TEXT PRIMARY KEY," +
                COLUMN_PRIORITY
                + " TEXT," + COLUMN_DEADLINE + "TEXT" + ")")
        db.execSQL(CREATE_TASKS_TABLE)
    }

    //onUpgrade occurs when a new version number of database is found
    override fun onUpgrade(
        db: SQLiteDatabase, oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS)
        onCreate(db)

    }

    companion object {

        private val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "taskList.db"
        val TABLE_TASKS = "tasks"

        val COLUMN_NAME = "name"
        val COLUMN_PRIORITY = "priorityLevel"
        val COLUMN_DEADLINE = "deadline"
    }

    fun addTask(task: Task) {

        val values = ContentValues()
        values.put(COLUMN_NAME, task.name)
        values.put(COLUMN_PRIORITY, task.priorityLevel)
        values.put(COLUMN_DEADLINE, task.deadline)

        val db = this.writableDatabase

        db.insert(TABLE_TASKS, null, values)
        db.close()

    }

}

