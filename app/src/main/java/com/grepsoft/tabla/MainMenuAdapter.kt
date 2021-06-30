package com.grepsoft.tabla

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.grepsoft.tabla.databinding.MenuItemBinding

class MainMenuAdapter(val context : Context, val items : List<MenuItem>) : RecyclerView.Adapter<MainMenuAdapter.MenuItemViewHolder>() {

    inner class MenuItemViewHolder(private val binding : MenuItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(menuItem : MenuItem) {
            binding.apply {
                item = menuItem
                icon.setImageDrawable(ContextCompat.getDrawable(context, menuItem.icon))
                //title.text = item.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {
        return MenuItemViewHolder(
            MenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int {
        return items.size
    }
}
