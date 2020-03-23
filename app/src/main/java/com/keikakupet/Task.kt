package com.keikakupet

import java.util.*

class Task {

    var name: String? = null
    var priority: String? = null
    var deadline: Calendar = Calendar.getInstance()
    //var taskItems = arrayListOf<String>()
    var exists: Boolean = true

    constructor(name: String, priority: String, deadline: Calendar){
        this.name = name
        this.priority = priority
        this.deadline = deadline
        this.exists = exists

    }
}
