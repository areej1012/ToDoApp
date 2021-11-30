package com.example.todoapp

import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ItemRowBinding
import kotlinx.android.synthetic.main.item_row.view.*

class RecyclerViewAdapter(private var toDoList: ArrayList<toDoList>) : RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {
    class ItemViewHolder (val binding : ItemRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val toDo = toDoList[position]
        holder.binding.apply {
            tvToDoItem.text = toDo.item
            cb.isSelected = toDo.isSelect
            cb.setOnCheckedChangeListener { _, isCliked ->
                if (isCliked){
                    tvToDoItem.setTextColor(Color.LTGRAY)

                }
                else{
                    tvToDoItem.setTextColor(Color.BLACK)
                    cb.isSelected = false

                }
                toDo.isSelect = !toDo.isSelect

            }
        }
    }


    override fun getItemCount(): Int = toDoList.size

    fun deleteItems(){
        toDoList.removeAll{
                item -> item.isSelect }
        for (i in toDoList){
            Log.e("chee","${i.isSelect} ${i.item}")
        }
        notifyDataSetChanged()

    }
    }
