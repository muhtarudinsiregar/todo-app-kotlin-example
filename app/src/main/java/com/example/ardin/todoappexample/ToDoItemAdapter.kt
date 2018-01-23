package com.example.ardin.todoappexample

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import com.example.ardin.todoappexample.models.ToDoItem
import org.w3c.dom.Text

/**
 * Created by ardin on 22/01/18.
 */
class ToDoItemAdapter(context: Context, toDoItemList: MutableList<ToDoItem>): BaseAdapter() {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private val itemList = toDoItemList

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val objectId: String = itemList.get(position).objectId as String
        val itemText: String = itemList.get(position).itemText as String
        val done:Boolean = itemList.get(position).done as Boolean

        val view:View
        val vh: ListRowHolder

        if (convertView == null) {
            view = mInflater.inflate(R.layout.row_items, parent, false)
            vh = ListRowHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ListRowHolder
        }

        vh.label.text = itemText
        vh.isDone.isChecked = done

        return view
    }

    override fun getItem(position: Int): Any {
        return itemList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return itemList.size
    }

    private class ListRowHolder(row: View?) {
        val label: TextView = row!!.findViewById<TextView>(R.id.tv_item_text) as TextView
        val isDone: CheckBox = row!!.findViewById<TextView>(R.id.cb_item_is_done) as CheckBox
        val ibDeleteObject: ImageButton = row!!.findViewById<ImageButton>(R.id.iv_cross) as ImageButton
    }
}