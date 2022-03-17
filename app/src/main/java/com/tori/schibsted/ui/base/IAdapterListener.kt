package com.tori.schibsted.ui.base

import android.view.View
import android.view.ViewGroup

interface IAdapterListener {
    fun <T> clickListener(position: Int, model: T, view: View)
    fun  getViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder
}