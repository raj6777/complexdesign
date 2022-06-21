package com.example.localjsonfilecalling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.localjsonfilecalling.Adapter.CustomAdapter2
import com.example.localjsonfilecalling.model.DataModel

class newscreen : AppCompatActivity(), AdapterOnClick {
    lateinit var customAdapter2:CustomAdapter2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.newscreen_recycler)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview_new_screen)
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


        customAdapter2 = CustomAdapter2(MainActivity.selectedListEG,this@newscreen,this)
        recyclerView.adapter = customAdapter2

    }

    override fun onChecked(checkedUser: DataModel) {
        MainActivity.selectedListEG.add(checkedUser)
        //customAdapter.notifyDataSetChanged()
        customAdapter2.notifyDataSetChanged()
    }

    override fun onUnChecked(checkedUser: DataModel) {
        MainActivity.selectedListEG.remove(checkedUser)
       // customAdapter.notifyDataSetChanged()
        customAdapter2.notifyDataSetChanged()
        //("Not yet implemented")
    }
}