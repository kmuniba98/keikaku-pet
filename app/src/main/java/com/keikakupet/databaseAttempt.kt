package com.keikakupet

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.content.ContentValues

class MyDBHandler(context: Context, name: String?,
                  factory: SQLiteDatabase.CursorFactory?, version: Int) :
    SQLiteOpenHelper(context, DATABASE_NAME,
        factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int,
                           newVersion: Int) {

    }
    companion object {

        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "taskList.db"
        val TABLE_TASKS = "tasks"

        val COLUMN_NAME = "name"
        val COLUMN_PRIORITY = "priorityLevel"
        val COLUMN_DEADLINE = "deadline"
    }

}﻿

