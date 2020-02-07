package com.keikakupet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.ListAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_to_do_list.*
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.ArrayAdapter
import android.widget.ListView

class ToDoListActivity : AppCompatActivity(), NewTaskDialogFragment.NewTaskDialogListener {

    private var toDoListItems = arrayListOf<String>()
    private var listAdapter: ArrayAdapter<String>? = null
    private var listView: ListView? = null

    override fun onDialogPositiveClick(dialog: DialogFragment, task:String) {
        //val addTaskBtn = findViewById<FloatingActionButton>(R.id.addTaskBtn)
        toDoListItems.add(task)
        //listAdapter?.notifyDataSetChanged()
        Snackbar.make(addTaskBtn, "Task Added Successfully", Snackbar.LENGTH_LONG).setAction("Action", null).show()
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
    }

    fun showNewTaskUI() {

        val newFragment = NewTaskDialogFragment.newInstance(R.string.new_task_dialog_title)
        newFragment.show(supportFragmentManager, "newtask")
    }

    private fun populateListView() {
        listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, toDoListItems)
        listView?.adapter = listAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do_list)

        // launch an ToDoListActivity

        val addTaskBtn = findViewById<FloatingActionButton>(R.id.addTaskBtn)

        addTaskBtn.setOnClickListener {
            //val startIntent = Intent(this, AddTaskActivity::class.java)
            //startActivity(startIntent)
            showNewTaskUI()
        }

        listView = findViewById(R.id.toDoListView)

        populateListView()
    }
}
