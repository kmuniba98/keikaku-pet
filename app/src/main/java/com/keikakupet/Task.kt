package com.keikakupet

import java.util.*

class Task {

    var name: String? = null
    var priorityLevel: Int = 0
    var deadline: String? = null

    constructor(name: String, priorityLevel: Int, deadline: String){
        this.name = name
        this.priorityLevel = priorityLevel
        this.deadline = deadline
    }
}
