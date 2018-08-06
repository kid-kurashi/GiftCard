package com.projects.coal.giftcard.feature.presentation.main

import android.view.View

class CardViewHolder {

    lateinit var view: View

    fun setViewReference(view: View){
        this.view = view
    }
    fun getViewReference():View{
        return view
    }
}
