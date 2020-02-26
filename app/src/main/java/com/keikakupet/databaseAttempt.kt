//package com.keikakupet
//
//import android.database.sqlite.SQLiteDatabase
//import android.database.sqlite.SQLiteOpenHelper
//import android.content.Context
//import android.content.ContentValues
//
//
////the DBHandler is what creates, edits, and deletes the database
//class MyDBHandler(context: Context, name: String?,
//                  factory: SQLiteDatabase.CursorFactory?, version: Int) :
//    SQLiteOpenHelper(context, DATABASE_NAME,
//        factory, DATABASE_VERSION) {
//
//    //function to create the database
//    //execSQL is the method that actually creates the database
//    //onCreate just sets up what to send to that method
//    override fun onCreate(db: SQLiteDatabase) {
//        val CREATE_TASKS_TABLE = ("CREATE TABLE " +
//                TABLE_TASKS + "("
//                + COLUMN_NAME + "TEXT PRIMARY KEY," +
//                COLUMN_PRIORITY
//                + " INT," + COLUMN_DEADLINE + "TEXT" + ")")
//        db.execSQL(CREATE_TASKS_TABLE)
//    }
//
//    //onUpgrade occurs when a new version number of database is found
//    //removes old database to create a new one
//    override fun onUpgrade(
//        db: SQLiteDatabase, oldVersion: Int,
//        newVersion: Int
//    ) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS)
//        onCreate(db)
//
//    }
//    //declares constants for database name, version, and variables
//    companion object {
//
//        private val DATABASE_VERSION = 1
//        private const val DATABASE_NAME = "taskList.db"
//        val TABLE_TASKS = "tasks"
//
//        val COLUMN_NAME = "name"
//        val COLUMN_PRIORITY = "priorityLevel"
//        val COLUMN_DEADLINE = "deadline"
//    }
//
//    //input: instance of task class
//    //writable database provides a reference to the current database
//    //then use that reference to insert the object
//    fun addTask(task: Task) {
//
//        val values = ContentValues()
//        values.put(COLUMN_NAME, task.name)
//        values.put(COLUMN_PRIORITY, task.priorityLevel)
//        values.put(COLUMN_DEADLINE, task.deadline)
//
//        val db = this.writableDatabase
//
//        db.insert(TABLE_TASKS, null, values)
//        db.close()
//
//    }
//
//    //Have not yet added a query function yet
//
//    //Misc notes for later
//    //way to add object to databse: (inside of a fun newTask(view : View)
//    //dbHandler.addTask(task)
//}
//
