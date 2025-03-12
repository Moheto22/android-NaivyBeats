package com.example.naivybeats.activities.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.naivybeats.models.province.models.City

class ProvinceAdapter(
    context: Context,
    resource: Int,
    objects: List<City>
                     ) : ArrayAdapter<City>(context, resource, objects) {

    // Este método es para personalizar el texto que se muestra en el Spinner
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent)
        val province = getItem(position)

        val textView = view.findViewById<TextView>(android.R.id.text1)
        textView.text = province?.name

        return view
    }

    // Este método es para personalizar los elementos del dropdown (lista desplegable)
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getDropDownView(position, convertView, parent)
        val province = getItem(position)

        val textView = view.findViewById<TextView>(android.R.id.text1)
        textView.text = province?.name

        return view
    }
}

