package com.keikakupet

import java.util.*

class Task {

    var name: String? = null
    var priority: String? = null
    var deadline: Calendar = Calendar.getInstance()
    var exists: Boolean = true //unused as of yet, may be used for checkbox

    constructor(name: String, priority: String, deadline: Calendar){
        this.name = name
        this.priority = priority
        this.deadline = deadline
        this.exists = exists

    }


}
