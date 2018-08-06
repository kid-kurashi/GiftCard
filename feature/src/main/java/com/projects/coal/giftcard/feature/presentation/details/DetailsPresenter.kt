package com.projects.coal.giftcard.feature.presentation.details

import etr.android.reamp.mvp.ReampPresenter

class DetailsPresenter: ReampPresenter<DetailsStateModel>() {

    override fun onPresenterCreated() {
        super.onPresenterCreated()
        stateModel.card = navigation.getData(DetailsNavUnit())
    }

    fun getHeadBackground(): Int {
        return stateModel.card!!.color
    }

    fun getTitle(): String {
        return stateModel.card!!.title!!
    }

    fun getImageUrl(): String {
        return stateModel.card!!.imageUrl!!
    }

    fun getCoinsText(): Int {
        return stateModel.card!!.credits!!
    }

    fun getDescription(): String {
        return stateModel.card!!.description!!

    }

    fun getLink(): String {
    return stateModel.card!!.redeemUrl!!
    }

    fun isFeatured(): Boolean {
        return stateModel.card!!.featured!!
    }

    fun codesCount(): Any {
        return stateModel.card!!.codesCount!!
    }
}
