package com.example.ardin.todoappexample

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.Toast
import com.example.ardin.todoappexample.models.ToDoItem
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // init firebase
        mDatabase = FirebaseDatabase.getInstance().reference

        // using kotlin extension to find id in activity xml
        fab.setOnClickListener { view ->
            addNewItemDialog()
        }
    }

    private fun addNewItemDialog() {
        // create alert dialog
        val alert = AlertDialog.Builder(this)

        // init edit text
        val itemEditText = EditText(this)

        alert.setMessage("Add New Item")

        alert.setTitle("Enter to do item next")

        //set view for editText to alert diaglog
        alert.setView(itemEditText)

        // action process when alert dialog submit
        alert.setPositiveButton("Submit") { dialog, positiveButton ->
            val todoItem = ToDoItem.create()

            todoItem.itemText = itemEditText.text.toString()
            todoItem.done = false

            //
            val newItem = mDatabase.child(Constants.FIREBASE_ITEM).push()
            todoItem.objectId = newItem.key

            newItem.setValue(todoItem)

            dialog.dismiss()
            Toast.makeText(this, "Item Saved with ID" + todoItem.objectId, Toast.LENGTH_SHORT).show()
        }

        alert.show()
    }
}
