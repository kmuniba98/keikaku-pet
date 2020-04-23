package com.keikakupet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_to_do_list.*
import android.widget.ArrayAdapter
import android.widget.ListView
import android.database.Cursor
import kotlin.properties.Delegates
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ToDoListActivity : AppCompatActivity(), NewTaskDialogFragment.NewTaskDialogListener {
    private var tasks = arrayListOf<Task>()
    private var listItems = arrayListOf<String>()
    private var listAdapter: ArrayAdapter<String>? = null
    private var listView: ListView? = null


    private val DBHelper = MyDBHandler(this, "TASKS_LIST",null ,1 )
   

    override fun onDialogPositiveClick(dialog: DialogFragment, task:Task) {
        tasks.add(task)
        //Add task to database
        DBHelper.addTask(task)
        //Adding task from database to listItems
        var DBtask = DBHelper.findTask(task.name)
        if(DBtask != null) {
            listItems.add("${DBtask.name}\n${DBtask.deadline.getTime()}\n${DBtask.priority}")
        }
        //Original addition of task to listItems
        listItems.add("${task.name}\n${task.deadline.getTime()}\n${task.priority}")
        listAdapter?.notifyDataSetChanged()
        populateListView()
        Snackbar.make(addTaskBtn, "Task Added Successfully", Snackbar.LENGTH_LONG).setAction("Action", null).show()
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
    }

    fun showNewTaskUI() {
        val newFragment = NewTaskDialogFragment.newInstance(R.string.new_task_dialog_title)
        newFragment.show(supportFragmentManager, "newtask")
    }

    private fun populateListView() {
        //val cursor = DBHelper.getAllRow()

        listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        listView?.adapter = listAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do_list)

        val addTaskBtn = findViewById<FloatingActionButton>(R.id.addTaskBtn)
        addTaskBtn.setOnClickListener {
            showNewTaskUI()
        }

        listView = findViewById(R.id.toDoListView)
    }
}
