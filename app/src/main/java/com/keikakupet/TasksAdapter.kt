package com.keikakupet

import android.R.attr.name
import android.R
import android.content.Context
import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

class TasksAdapter(context: Context, users: ArrayList<Task>) :
    ArrayAdapter<Task>(context, 0, users) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        // Get the data item for this position
        val user = getItem(position)
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.simple_list_item_2, parent, false)
        }
        // Lookup view for data population
        val taskName = convertView!!.findViewById(R.id.text1) as TextView
        val taskDateTime = convertView!!.findViewById(R.id.text2) as TextView
        // Populate the data into the template view using the data object
        taskName.setText(user!!.name)
        taskDateTime.setText(user!!.dateTime)
        // Return the completed view to render on screen
        return convertView
    }
}