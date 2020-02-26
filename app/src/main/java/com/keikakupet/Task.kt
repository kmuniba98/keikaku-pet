package com.keikakupet

import java.util.*

class Task {

    var name: String? = null
    var priority: String? = null
    var deadline: Calendar = Calendar.getInstance()

    constructor(name:String, priority:String, deadline:Calendar){
        this.name = name
        this.priority = priority
        this.deadline = deadline
    }
}
