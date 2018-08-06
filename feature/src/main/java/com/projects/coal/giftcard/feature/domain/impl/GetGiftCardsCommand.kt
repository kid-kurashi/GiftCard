package com.projects.coal.giftcard.feature.domain.impl

import com.projects.coal.giftcard.feature.data.IApiService
import com.projects.coal.giftcard.feature.data.simple_entity.GiftCardsAnswer
import com.projects.coal.giftcard.feature.data.simple_entity.Provider
import com.projects.coal.giftcard.feature.domain.IGetCommand
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class GetGiftCardsCommand(val apiService: IApiService) : IGetCommand {

    override fun getData(): Observable<List<Provider>> {
        return apiService.getGiftCards()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map { answer: GiftCardsAnswer -> answer.providers }
    }
}