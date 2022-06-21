package com.example.localjsonfilecalling

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.localjsonfilecalling.Adapter.CustomAdapter
import com.example.localjsonfilecalling.Adapter.CustomAdapter2
import com.example.localjsonfilecalling.model.DataModel
import com.google.gson.GsonBuilder
import org.json.JSONException
import java.io.IOException
import java.nio.charset.Charset


class MainActivity : AppCompatActivity(),AdapterOnClick {
    var userList =  ArrayList<DataModel>()


    companion object { var selectedListEG=ArrayList<DataModel>()}
    lateinit var customAdapter:CustomAdapter
    lateinit var customAdapter2:CustomAdapter2

    override fun onChecked(checkedUser: DataModel) {
            selectedListEG.add(checkedUser)
            customAdapter.notifyDataSetChanged()
            customAdapter2.notifyDataSetChanged()
    }

    override fun onUnChecked(unCheckedUser: DataModel) {
        selectedListEG.remove(unCheckedUser)
        customAdapter.notifyDataSetChanged()
        customAdapter2.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val secondrecyclerView = findViewById<RecyclerView>(R.id.my_recycler_view)
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = linearLayoutManager
        secondrecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        try {
            val values: String = loadJsonFromAsset()
            val gson = GsonBuilder().create()
            userList = gson.fromJson(values,Array<DataModel>::class.java).toList() as ArrayList<DataModel>
            //gson is used for converted to array from any data

        } catch (e: JSONException) {
            e.printStackTrace()
            Log.e("first", e.toString())
        }
        customAdapter = CustomAdapter(userList,this,this@MainActivity)
        recyclerView.adapter = customAdapter
        customAdapter2 = CustomAdapter2(selectedListEG,this@MainActivity,this)
        secondrecyclerView.adapter = customAdapter2
        val button:Button=findViewById(R.id.submit)

        button.setOnClickListener{
            if(selectedListEG.size>0){
                val intent = Intent(this@MainActivity, newscreen::class.java)
                startActivity(intent)
            }

        }

    }
    private fun loadJsonFromAsset(): String {
        var json: String?
        try {
            val inputStream = assets.open("localfile.json")//openfile
            val size = inputStream.available()
            val buffer = ByteArray(size)//read file as a buffer//binary data
            val charset: Charset = Charsets.UTF_8
            inputStream.read(buffer)//data will show the user after buffering
            inputStream.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            Log.e("second", ex.toString())
            return ""

        }
        return json
    }
}