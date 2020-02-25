package com.keikakupet

import  androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_to_do_list.*
import android.widget.ArrayAdapter
import android.widget.ListView
import java.util.*

class ToDoListActivity : AppCompatActivity(), NewTaskDialogFragment.NewTaskDialogListener, RemoveTaskDialogFragment.NewRemoveTaskDialogListener {

    private var taskNames = arrayListOf<String>()
    private var taskDateTimes = arrayListOf<String>()
    private var listItems = arrayListOf<String>()
    private var listAdapter: ArrayAdapter<String>? = null
    private var listView: ListView? = null
    private var listItemsChecked = arrayListOf<Boolean>()
    private var listItemsDelete = arrayListOf<Boolean>()



    override fun onDialogPositiveClick(dialog: DialogFragment, taskName:String, taskDateTime:Calendar) {
        taskNames.add(taskName)
        var taskDateTimeStr = taskDateTime.getTime().toString()
        taskDateTimes.add(taskDateTimeStr)
        listItems.add("$taskName\n$taskDateTimeStr")
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
