package com.projects.coal.giftcard.feature.presentation.details

import com.projects.coal.giftcard.feature.presentation.data.Card
import etr.android.reamp.mvp.ReampStateModel
import java.io.Serializable

class DetailsStateModel: ReampStateModel(), Serializable {
    var card: Card? = null

}
