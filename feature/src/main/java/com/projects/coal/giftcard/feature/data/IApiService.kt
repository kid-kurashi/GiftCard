package com.projects.coal.giftcard.feature.data

import com.projects.coal.giftcard.feature.data.simple_entity.GiftCardsAnswer
import io.reactivex.Observable

interface IApiService {

     fun getGiftCards(): Observable<GiftCardsAnswer>
}