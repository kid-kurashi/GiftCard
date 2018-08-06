package com.projects.coal.giftcard.feature.presentation.main

import android.app.ActivityOptions
import android.util.Log
import android.view.View
import com.projects.coal.giftcard.feature.R
import com.projects.coal.giftcard.feature.data.simple_entity.GiftCard
import com.projects.coal.giftcard.feature.data.simple_entity.Provider
import com.projects.coal.giftcard.feature.domain.IGetCommand
import com.projects.coal.giftcard.feature.presentation.details.DetailsNavUnit
import etr.android.reamp.mvp.ReampPresenter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainPresenter(
        val command: IGetCommand,
        val cardViewHolder: CardViewHolder
) : ReampPresenter<MainStateModel>() {

    private var disposable: CompositeDisposable = CompositeDisposable()
    override fun onPresenterCreated() {
        super.onPresenterCreated()
        update()
    }

    fun update() {
        Log.e("PRESENTER", "update() called")
        showProgress()
        disposable = CompositeDisposable()
        disposable.add(command.getData()
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(this::setList, this::setError))
    }

    fun setList(list: List<Provider>) {
        stateModel.list = list
        stateModel.progress = false
        sendStateModel()
    }

    fun setError(throwable: Throwable) {
        if (!throwable.message.isNullOrEmpty()) {
            stateModel.error = throwable.message!!
        }
        stateModel.progress = false
        sendStateModel()
    }

    private fun showProgress() {
        stateModel.progress = true;
        sendStateModel()
    }

    override fun onDestroyPresenter() {
        super.onDestroyPresenter()
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }

    fun navToDetails(giftCard: GiftCard, view: View) {
        stateModel.card = giftCard
        stateModel.goToDetails = true
        cardViewHolder.setViewReference(view)
        sendStateModel()
    }

    fun navigate() {
        if (stateModel.goToDetails && stateModel.card != null) {
            stateModel.goToDetails = false
            val options: ActivityOptions = ActivityOptions.makeSceneTransitionAnimation(navigation.activity, cardViewHolder.getViewReference(), navigation.activity.getString(R.string.gift_card_transition));
            val bundle = options.toBundle();
            navigation.open(DetailsNavUnit().addCard(stateModel.card!!).addBundle(bundle))
        }
    }
}