package com.qure.onboarding.ui.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.qure.onboarding.AdatperListener.CategoryAdapterLiistener
import com.qure.onboarding.model.Category
import com.qure.onboarding.databinding.ItemCategoryBinding

class CategoryAdatper(private val listener: CategoryAdapterLiistener) : RecyclerView.Adapter<CategoryAdatper.GridAdapter>() {

    private var categoryList : List<Category>? = null

    inner class GridAdapter(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Category) {

            Glide.with(itemView).load(item.img).into(binding.categoryImg)
            binding.categoryName.text = item.name

            itemView.setOnClickListener {
                listener.onItemCategory(item)
            }


        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdatper.GridAdapter {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryBinding.inflate(
            layoutInflater, parent, false
        )

        return GridAdapter(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdatper.GridAdapter, position: Int) {
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