package com.example.ardin.todoappexample

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.example.ardin.todoappexample.models.ToDoItem
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var mDatabase: DatabaseReference

    var toDoItemList: MutableList<ToDoItem>? = null
    lateinit var adapter: ToDoItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // init firebase
        mDatabase = FirebaseDatabase.getInstance().reference

        // using kotlin extension to find id in activity xml
        fab.setOnClickListener { view ->
            addNewItemDialog()
        }

        mDatabase = FirebaseDatabase.getInstance().reference
        toDoItemList = mutableListOf<ToDoItem>()
        adapter = ToDoItemAdapter(this, toDoItemList!!)
        items_list!!.setAdapter(adapter)
        mDatabase.orderByKey().addListenerForSingleValueEvent(itemListener)
    }

    var itemListener: ValueEventListener = object : ValueEventListener {
        override fun onDataChange(p0: DataSnapshot) {
            addDataToList(p0)
        }

        override fun onCancelled(p0: DatabaseError?) {
            Log.w("MainActifity", "LoadItem:canceled", p0?.toException())
        }
    }

    private fun addDataToList(dataSnapshot: DataSnapshot) {
        val items = dataSnapshot.children.iterator()

        if (items.hasNext()) {
            val toDoListIndex = items.next()
            val itemsIterator = toDoListIndex.children.iterator()

            while (itemsIterator.hasNext()) {
                //get current item
                val currentItem = itemsIterator.next()
                val todoItem = ToDoItem.create()

                //get current data in list

                val map = currentItem.getValue() as HashMap<String, Any>

                //key will return firebase ID

                todoItem.objectId = currentItem.key
                todoItem.done = map.get("done") as Boolean?
                todoItem.itemText = map.get("itemText") as String

                toDoItemList!!.add(todoItem)

            }
        }
        adapter.notifyDataSetChanged()
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
