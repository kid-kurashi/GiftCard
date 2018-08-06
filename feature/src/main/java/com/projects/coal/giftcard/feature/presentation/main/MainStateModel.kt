package com.projects.coal.giftcard.feature.presentation.main

import android.view.View
import com.projects.coal.giftcard.feature.data.simple_entity.GiftCard
import com.projects.coal.giftcard.feature.data.simple_entity.Provider
import etr.android.reamp.mvp.ReampStateModel
import java.io.Serializable

class MainStateModel(): ReampStateModel(), Serializable {
    var progress: Boolean = false;
    lateinit var list: List<Provider>
    var error: String = ""
    var card: GiftCard? = null
    var goToDetails: Boolean = false
}