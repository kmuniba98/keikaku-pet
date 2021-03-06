package com.keikakupet

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.*
import androidx.fragment.app.DialogFragment
import java.util.*
import java.util.Calendar.*
import android.widget.CheckBox

class NewTaskDialogFragment: DialogFragment(){
    val taskDateTime = Calendar.getInstance()

    interface NewTaskDialogListener {
        fun onDialogPositiveClick(dialog:DialogFragment, task:Task)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    private var newTaskDialogListener: NewTaskDialogListener? = null

    companion object {
        fun newInstance(title: Int): NewTaskDialogFragment {
            val newTaskDialogFragment = NewTaskDialogFragment()
            val args = Bundle()
            args.putInt("dialog_title", title)
            newTaskDialogFragment.arguments = args
            return newTaskDialogFragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        // building the dialog
        val title = arguments?.getInt("dialog_title")
        val builder = AlertDialog.Builder(activity)
        if (title != null) {
            builder.setTitle(title)
        }
        val dialogView = activity?.layoutInflater?.inflate(R.layout.dialog_new_task, null)

        // get task date
        val datePicker = dialogView?.findViewById<DatePicker>(R.id.datePicker)
        datePicker?.init(taskDateTime.get(Calendar.YEAR), taskDateTime.get(Calendar.MONTH),
            taskDateTime.get(Calendar.DAY_OF_MONTH)
        ){ view, year, month, day ->
            taskDateTime.set(YEAR, year)
            taskDateTime.set(MONTH, month)
            taskDateTime.set(DAY_OF_MONTH, day)
        }

        // get task time
        val timePicker = dialogView?.findViewById<TimePicker>(R.id.timePicker)
        timePicker?.setOnTimeChangedListener { _, hourOfDay, minute ->
            taskDateTime.set(HOUR_OF_DAY, hourOfDay)
            taskDateTime.set(MINUTE, minute)
            taskDateTime.set(SECOND, 0)
        }

        // get task priority
        val priorityCheckBox = dialogView?.findViewById<CheckBox>(R.id.priorityCheckBox)
        var taskPriority = "standard priority"
        priorityCheckBox?.setOnCheckedChangeListener { buttonView, isChecked ->
            taskPriority = if (isChecked) "high priority" else "standard priority"
        }

        // create a task object with inputted data and send it to ToDoListActivity
        builder.setView(dialogView)
            .setPositiveButton(R.string.save, { dialog, id ->
                // get task name
                val taskName = dialogView?.findViewById<EditText>(R.id.taskEditText)?.text.toString()
                // create task object
                val task = Task(taskName, taskPriority, taskDateTime)
                newTaskDialogListener?.onDialogPositiveClick(this, task)})
            .setNegativeButton(android.R.string.cancel, { dialog, id -> newTaskDialogListener?.onDialogNegativeClick(this)})

        return builder.create()
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        try {
            newTaskDialogListener = activity as NewTaskDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(activity.toString() +
                    " must implement NewTaskDialogListener")
        }
    }
}