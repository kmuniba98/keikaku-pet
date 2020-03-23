package com.keikakupet

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView

class ItemListAdapter (var context: Context, var arrayList: ArrayList<Task>): BaseAdapter(){

    override fun getItem(position: Int): Any {
        return arrayList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
     }

    override fun getCount(): Int {
        return arrayList.size
     }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view:View = View.inflate(context , R.layout.listview_checkbox, null)

        var textOfTask: TextView = view.findViewById(R.id.taskContent)
        var priorityTxt: TextView = view.findViewById(R.id.taskPriority)
        var deadlineTxt: TextView = view.findViewById(R.id.taskDeadline)
        var trashBtn: Button = view.findViewById(R.id.trash_btn)
        var checkBx: CheckBox = view.findViewById(R.id.completeCheckBox)

        var taskList: Task = arrayList.get(position)

        textOfTask.text = taskList.name
        priorityTxt.text = taskList.priority
        deadlineTxt.text = taskList.deadline.toString()

        trashBtn.setOnClickListener(View.OnClickListener {
            //do something
            //taskList.remove(position) //or some other task
            notifyDataSetChanged()
        })
        checkBx.setOnClickListener(View.OnClickListener {
            //do something
            //taskList.remove(position) //or some other task
            notifyDataSetChanged()
        })

        return view!!
    }

}