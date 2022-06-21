package com.example.localjsonfilecalling.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.localjsonfilecalling.AdapterOnClick
import com.example.localjsonfilecalling.MainActivity
import com.example.localjsonfilecalling.R
import com.example.localjsonfilecalling.model.DataModel


class CustomAdapter(
    val datalist: ArrayList<DataModel>,
    var onclick: AdapterOnClick,
    var context: Context
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.row, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {

        val recyclermodel = datalist[position]
        val uri: String? = recyclermodel.img_name
        val id: Int = context.resources.getIdentifier(
            "com.example.localjsonfilecalling:drawable/$uri",
            null,
            null
        )
        holder.em.text = recyclermodel.email
        holder.fn.text = recyclermodel.first_name
        holder.ty.text = recyclermodel.type
        holder.im_n.setImageResource(id)
        holder.check.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                onclick.onChecked(datalist[position])
            } else {
                onclick.onUnChecked(datalist[position])
            }
        }

    }

    override fun getItemCount(): Int {
        return datalist.size

    }

    open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var fn: TextView = itemView.findViewById(R.id.tv_firstname)
        var em: TextView = itemView.findViewById(R.id.tv_email)
        var ty: TextView = itemView.findViewById(R.id.tv_type)
        var im_n: ImageView = itemView.findViewById(R.id.image)
        var check: CheckBox = itemView.findViewById(R.id.checkbox_cheese)

    }
}




