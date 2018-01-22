package com.example.ardin.todoappexample.models

/**
 * Created by ardin on 22/01/18.
 */
class ToDoItem {
    companion object Factory {
        fun create(): ToDoItem = ToDoItem()
    }
    
    var objectId: String? = null
    var itemText: String? = null
    var done: Boolean? = false
}