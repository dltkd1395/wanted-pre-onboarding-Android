package com.qure.presenation.base


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import com.qure.domain.model.News
import com.qure.presenation.databinding.ItemNewListBinding

abstract class BasePagingAdapter<E: Any>
    (itemCallback: DiffUtil.ItemCallback<E>)
    : PagingDataAdapter<E, BaseHolder<out ViewDataBinding, E>>(itemCallback) {

    override fun onBindViewHolder(holder: BaseHolder<out ViewDataBinding, E>, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onBindViewHolder(holder: BaseHolder<out ViewDataBinding, E>, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
        getItem(position)?.let { holder.bind(it, payloads) }
    }


}