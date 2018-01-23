package com.example.ardin.todoappexample.interfaces

/**
 * Created by ardin on 23/01/18.
 */
interface ItemRowListener {
    fun modifyItemState(itemObjectId: String?, isDone: Boolean)
    fun onItemDelete(itemObjectId: String?)
}