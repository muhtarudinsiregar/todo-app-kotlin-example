package com.example.ardin.todoappexample

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // using kotlin extension to find id in activity xml
        fab.setOnClickListener { view ->
            addNewItemDialog()
        }
    }

    private fun addNewItemDialog() {
        val alert = AlertDialog.Builder(this)

        val itemEditText = EditText(this)

        alert.setMessage("Add New Item")

        alert.setTitle("Enter to do item next")

        alert.setView(itemEditText)
//
        alert.setPositiveButton("Submit") { dialog, positiveButton ->

        }
        alert.show()
    }
}
