package com.example.localjsonfilecalling.Adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.localjsonfilecalling.AdapterOnClick
import com.example.localjsonfilecalling.R
import com.example.localjsonfilecalling.model.DataModel


class CustomAdapter2(
    private val datalist1: List<DataModel>,
    private var context: Context,
    var onclick: AdapterOnClick,
) : RecyclerView.Adapter<CustomAdapter2.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter2.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_1, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: CustomAdapter2.ViewHolder, position: Int) {
        var model = datalist1[position]
        holder.fn1.text = model.first_name
        val uri: String? = model.img_name
        val id: Int = context.resources.getIdentifier(
            "com.example.localjsonfilecalling:drawable/$uri",
            null,
            null
        )
        holder.im_n1.setImageResource(id)
        holder.btn.setOnClickListener {
            onclick.onUnChecked(datalist1[position])
        }
    }

    override fun getItemCount(): Int {
        return datalist1.size
    }

    open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var fn1: TextView = itemView.findViewById(R.id.headerName)
        var im_n1: ImageView = itemView.findViewById(R.id.image1)
        var btn: ImageButton = itemView.findViewById(R.id.btn_cancel)
    }
}