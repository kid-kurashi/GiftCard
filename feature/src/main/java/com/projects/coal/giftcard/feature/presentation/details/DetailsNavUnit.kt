package com.projects.coal.giftcard.feature.presentation.details

import android.content.Intent
import android.os.Bundle
import com.projects.coal.giftcard.feature.data.simple_entity.GiftCard
import com.projects.coal.giftcard.feature.presentation.data.Card
import etr.android.reamp.navigation.Navigation
import etr.android.reamp.navigation.NavigationUnit

class DetailsNavUnit : NavigationUnit<Card>() {

    private lateinit var card: GiftCard

    private val KEY_ID = "KEY_ID"
    private val KEY_FEATURED = "KEY_FEATURED"
    private val KEY_TITLE = "KEY_TITLE"
    private val KEY_CREDITS = "KEY_CREDITS"
    private val KEY_IMAGE_URL = "KEY_IMAGE_URL"
    private val KEY_CODES_COUNT = "KEY_CODES_COUNT"
    private val KEY_CURRENCY = "KEY_CURRENCY"
    private val KEY_DESCRIPTION = "KEY_DESCRIPTION"
    private val KEY_REDEEM_URL = "KEY_REDEEM_URL"

    private val KEY_BACKGROUND_COLOR = "KEY_BACKGROUND_COLOR"

    override fun navigate(navigation: Navigation?) {
        val intent = Intent(navigation!!.activity, DetailsActivity::class.java)
        intent.putExtra(KEY_ID, card.id)
        intent.putExtra(KEY_FEATURED, card.isFeatured)
        intent.putExtra(KEY_TITLE, card.title)
        intent.putExtra(KEY_CREDITS, card.credits)
        intent.putExtra(KEY_IMAGE_URL, card.imageUrl)
        intent.putExtra(KEY_CODES_COUNT, card.codesCount)
        intent.putExtra(KEY_CURRENCY, card.currency)
        intent.putExtra(KEY_DESCRIPTION, card.description)
        intent.putExtra(KEY_REDEEM_URL, card.redeemUrl)
        intent.putExtra(KEY_BACKGROUND_COLOR, color)

        navigation.activity.startActivity(intent, bundle)
    }

    override fun getNavigationData(navigation: Navigation?): Card {
        val intent = navigation!!.activity.intent
        return Card.Builder()
                .addId(intent.getIntExtra(KEY_ID, 0))
                .addFeatured(intent.getBooleanExtra(KEY_FEATURED, false))
                .addTitle(intent.getStringExtra(KEY_TITLE))
                .addCredits(intent.getIntExtra(KEY_CREDITS, 0))
                .addImageUrl(intent.getStringExtra(KEY_IMAGE_URL))
                .addCodesCount(intent.getIntExtra(KEY_CODES_COUNT, 0))
                .addCurrency(intent.getStringExtra(KEY_CURRENCY))
                .addDescription(intent.getStringExtra(KEY_DESCRIPTION))
                .addRedeemUrl(intent.getStringExtra(KEY_REDEEM_URL))
                .addBackgroundColor(intent.getIntExtra(KEY_BACKGROUND_COLOR, 0))
                .build()
    }

    fun addCard(card: GiftCard): DetailsNavUnit {
        this.card = card
        return this
    }

    private  var color: Int? = null

    fun addColor(color: Int): DetailsNavUnit{
        this.color = color
        return this;
    }

    private lateinit var bundle: Bundle

    fun addBundle(bundle: Bundle): DetailsNavUnit {
        this.bundle = bundle
        return this;
    }
}
