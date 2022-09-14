package com.qure.presenation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.qure.domain.model.Category
import com.qure.domain.model.News
import com.qure.presenation.databinding.ItemCategoryBinding

class CategoryAdapter(private val itemClickListener: (Category) -> Unit) : RecyclerView.Adapter<CategoryAdapter.GridAdapter>() {

    private var categoryList : List<Category>? = null

    inner class GridAdapter(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Category) {

            Glide.with(itemView).load(item.img).into(binding.categoryImg)
            binding.categoryName.text = item.name
            itemView.setOnClickListener {
                item.run {
                    itemClickListener(this)
                }
            }


        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.GridAdapter {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryBinding.inflate(
            layoutInflater, parent, false
        )
        itemClickListener
        return GridAdapter(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.GridAdapter, position: Int) {
        holder.bind(categoryList!![position])
    }

    fun submitList(list: List<Category>?) {
        categoryList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return categoryList?.size ?:0
    }

}