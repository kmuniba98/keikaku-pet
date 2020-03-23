package com.keikakupet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_to_do_list.*
import android.widget.ArrayAdapter
import android.widget.ListView

class ToDoListActivity : AppCompatActivity(), NewTaskDialogFragment.NewTaskDialogListener {
    //private var tasks = arrayListOf<Task>()
    //private var listItems = arrayListOf<String>()
    private var listAdapter: ArrayAdapter<String>? = null
    private var listView: ListView? = null
    private var taskListAdapter: ItemListAdapter ? = null
    private var taskList: ArrayList<Task> ? = null

    //uses NewTaskDialogFragment to repopulate list with new tasks
    override fun onDialogPositiveClick(dialog: DialogFragment, task:Task) {
        taskList?.add(task)
        //listItems.add("${task.name}\n${task.deadline.getTime()}\n${task.priority}")
        listAdapter?.notifyDataSetChanged()
        populateListView()
        Snackbar.make(addTaskBtn, "Task Added Successfully", Snackbar.LENGTH_LONG).setAction("Action", null).show()
    }

    //does nothing but needs to be there
    override fun onDialogNegativeClick(dialog: DialogFragment) {
    }

    //pops up NewTaskDialogFragment
    fun showNewTaskUI() {
        val newFragment = NewTaskDialogFragment.newInstance(R.string.new_task_dialog_title) //name of the form
        newFragment.show(supportFragmentManager, "newtask")
    }

    //applies listview to things in task list
    private fun populateListView() {
        //listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        //listView?.adapter = listAdapter
            taskList = ArrayList()
            taskListAdapter = ItemListAdapter(applicationContext, taskList!!)
            listView?.adapter  = taskListAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do_list)

        listView = findViewById(R.id.toDoListView)
        taskList = ArrayList()
        taskListAdapter = ItemListAdapter(applicationContext, taskList!!)
        listView?.adapter  = taskListAdapter
        //button to add new tasks
        val addTaskBtn = findViewById<FloatingActionButton>(R.id.addTaskBtn)
        addTaskBtn.setOnClickListener {
            showNewTaskUI()
        }
        //test



        //finds listview tag in xml
    }
}
