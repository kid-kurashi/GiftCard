package com.projects.coal.giftcard.feature.data.impl

import android.content.res.Resources
import com.google.gson.Gson
import com.projects.coal.giftcard.feature.R
import com.projects.coal.giftcard.feature.data.IApiService
import com.projects.coal.giftcard.feature.data.simple_entity.GiftCardsAnswer
import io.reactivex.Observable
import java.io.InputStreamReader


class ApiServiceImpl(val resources: Resources) : IApiService {

    override fun getGiftCards(): Observable<GiftCardsAnswer> {
        val ins = resources.openRawResource(
                resources.getIdentifier("providers",
                        "raw", resources.getResourcePackageName(R.raw.providers)))
        val reader = InputStreamReader(ins, "UTF-8")
        val result = Gson().fromJson(reader, GiftCardsAnswer::class.java)
        return Observable.just(result)
    }

}