package com.keikakupet

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView

class ItemListAdapter (var context: Context, var taskList: ArrayList<Task>): BaseAdapter(){

    //retrieves a task from list
    override fun getItem(position: Int): Any {
        return taskList.get(position)
    }

    //retrieves the id of a specific task from lsit
    override fun getItemId(position: Int): Long {
        return position.toLong()
     }

    //never used but gets total amount of tasks in task list
    override fun getCount(): Int {
        return taskList.size
     }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view:View = View.inflate(context , R.layout.listview_checkbox, null)

        //sets up variables and links them to id's set in xml
        var textOfTask: TextView = view.findViewById(R.id.taskContent)
        var priorityTxt: TextView = view.findViewById(R.id.taskPriority)
        var deadlineTxt: TextView = view.findViewById(R.id.taskDeadline)
        var trashBtn: Button = view.findViewById(R.id.trash_btn)
        var checkBx: CheckBox = view.findViewById(R.id.completeCheckBox)

        var taskInList: Task = taskList.get(position) //gets a specific task from task list

        //retrieves information from task (from the dialog fragment)
        textOfTask.text = taskInList.name
        priorityTxt.text = taskInList.priority
        deadlineTxt.text = taskInList.deadline.getTime().toString()

        //sets a listener for something to happen when the trash button is clicked
        trashBtn.setOnClickListener(View.OnClickListener {
            //removes from task list
            taskList.removeAt(position)
            notifyDataSetChanged()
        })

        //sets a listener for something to happen when the checkbox is clicked
        checkBx.setOnClickListener(View.OnClickListener {
            //removes from task list
            taskList.removeAt(position)
            notifyDataSetChanged()
        })

        return view!!
    }

}