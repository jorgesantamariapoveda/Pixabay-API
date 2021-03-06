package org.jsantamariap.androidavanzado.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_list.view.*
import org.jsantamariap.androidavanzado.R
import org.jsantamariap.androidavanzado.repository.model.ItemPixabay

class MainAdapter(
    private val context: Context,
    private val cbItemClick: CallbackItemClick,
    private val listItems: List<ItemPixabay>
) : RecyclerView.Adapter<MainAdapter.MainHolder>() {

    // MARK: - Item de la vista

    class MainHolder(v: View) : RecyclerView.ViewHolder(v) {
        internal val view = v
    }

    // MARK: - Lifecycle functions

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return MainHolder(view)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        listItems[position].let { item ->

            Glide.with(context)
                .load(item.largeImageURL)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.ic_launcher_background)
                )
                .into(holder.view.imageViewItemList)

            holder.view.cardViewItemList.setOnClickListener {
                cbItemClick.onItemClickPixabay(item)
            }
        }
    }
}