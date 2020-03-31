package com.keikakupet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_to_do_list.*
import android.widget.ListView

class ToDoListActivity : AppCompatActivity(), NewTaskDialogFragment.NewTaskDialogListener {
    //doesn't instantiate, just makes variable names accessible across functions
    private var listView: ListView? = null
    private var taskListAdapter: ItemListAdapter ? = null
    private var taskList: ArrayList<Task> ? = null



    //uses NewTaskDialogFragment to repopulate list with new tasks
    override fun onDialogPositiveClick(dialog: DialogFragment, task:Task) {
        //uses information from dialog fragment to add to taskList
        taskList?.add(task)
        //tells the adapter that data has changed
        taskListAdapter?.notifyDataSetChanged()
        //repopulate list with new data using function
        populateListView()
        //toast popup to notify user that they did something --- is this unnecessary?
        Snackbar.make(addTaskBtn, "Task Added Successfully", Snackbar.LENGTH_LONG).setAction("Action", null).show()
    }

    //does nothing but needs to be there
    override fun onDialogNegativeClick(dialog: DialogFragment) {
    }

    //pops up NewTaskDialogFragment
    fun showNewTaskUI() {
        val newFragment = NewTaskDialogFragment.newInstance(R.string.new_task_dialog_title) //name of the form that appears
        newFragment.show(supportFragmentManager, "newtask")
    }

    //applies listview  adapter  to things in task list
    private fun populateListView() {
            taskListAdapter = ItemListAdapter(applicationContext, taskList!!)
            listView?.adapter  = taskListAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do_list)

        listView = findViewById(R.id.toDoListView) //finds the xml to use for page
        taskList = ArrayList() //global variable to contain the tasks

        //button to add new tasks
        val addTaskBtn = findViewById<FloatingActionButton>(R.id.addTaskBtn)
        addTaskBtn.setOnClickListener {
            showNewTaskUI()
        }

    }
}
