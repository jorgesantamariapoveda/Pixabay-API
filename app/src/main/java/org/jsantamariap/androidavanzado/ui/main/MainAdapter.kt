package org.jsantamariap.androidavanzado.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.jsantamariap.androidavanzado.R

class MainAdapter(
        private val context: Context,
        private val listItems: ArrayList<String>) : RecyclerView.Adapter<MainAdapter.MainHolder>() {

    //! Representa la vista de cada item
    class MainHolder(v: View) : RecyclerView.ViewHolder(v) {
        internal val view = v
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return MainHolder(view)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        listItems[position].let {
            //! implementar
            //! holder.view.imageViewItemList = ...
        }
    }

}