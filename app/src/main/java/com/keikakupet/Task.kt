package com.keikakupet

class Task {

    var name: String? = null
    var priorityLevel: String? = null
    var deadline: String? = null

    constructor(name: String, priorityLevel: String, deadline: String){
        this.name = name
        this.priorityLevel = priorityLevel
        this.deadline = deadline
    }
}