package com.example.todoapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var rv : RecyclerView
    lateinit var toDoItem : ArrayList<toDoList>
    lateinit var fbAdd : FloatingActionButton
    lateinit var adapter: RecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv = findViewById(R.id.rv)
        fbAdd = findViewById(R.id.fbAdd)
        toDoItem = arrayListOf(toDoList("767"))
        rv.layoutManager = LinearLayoutManager(this)
        adapter = RecyclerViewAdapter(toDoItem)
        rv.adapter = adapter

        fbAdd.setOnClickListener {
            showAlert()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var num = 0
        for(i in toDoItem){
            if(i.isSelect){
                num++
            }

        }
        if(num > 0){
            Toast.makeText(this, "$num items deleted", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, "No items selected", Toast.LENGTH_LONG).show()
        }
        adapter.deleteItems()
        return super.onOptionsItemSelected(item)
    }

    private fun showAlert() {
        val dialogBuilder  = AlertDialog.Builder(this)
        val input = EditText(this)
        dialogBuilder.setPositiveButton("Add",DialogInterface.OnClickListener{
            dia , id -> toDoItem.add(toDoList(input.text.toString()))
            rv.adapter?.notifyDataSetChanged()
    })
            .setNegativeButton("Cancel",DialogInterface.OnClickListener {
                    dialogInterface, id ->  dialogInterface.cancel()

            })
        val alert = dialogBuilder.create()
        alert.setTitle("New Item")
        alert.setView(input)
        alert.show()
    }


}