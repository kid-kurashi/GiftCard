package com.projects.coal.giftcard.feature.domain

import com.projects.coal.giftcard.feature.data.simple_entity.Provider
import io.reactivex.Observable

interface IGetCommand {
    fun getData(): Observable<List<Provider>>
}