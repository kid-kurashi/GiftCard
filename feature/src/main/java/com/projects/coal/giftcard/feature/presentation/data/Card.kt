package com.projects.coal.giftcard.feature.presentation.data

class Card private constructor(
        val id: Int?,
        val featured: Boolean?,
        val title: String?,
        val credits: Int?,
        val imageUrl: String?,
        val codesCount: Int?,
        val currency: String?,
        val description: String?,
        val redeemUrl: String?,
        val color: Int) {

    open class Builder {

        var id: Int? = null
        var featured: Boolean? = null
        var title: String? = null
        var credits: Int? = null
        var imageUrl: String? = null
        var codesCount: Int? = null
        var currency: String? = null
        var description: String? = null
        var redeemUrl: String? = null
        var color: Int = 0

        fun addId(id: Int): Builder {
            this.id = id
            return this;
        }

        fun addFeatured(featured: Boolean): Builder {
            this.featured = featured
            return this;
        }

        fun addTitle(title: String): Builder {
            this.title = title
            return this;
        }

        fun addCredits(credits: Int): Builder {
            this.credits = credits
            return this;
        }

        fun addImageUrl(imageUrl: String): Builder {
            this.imageUrl = imageUrl
            return this;
        }

        fun addCodesCount(codesCount: Int): Builder {
            this.codesCount = codesCount
            return this;
        }

        fun addCurrency(currency: String): Builder {
            this.currency = currency
            return this;
        }

        fun addDescription(description: String): Builder {
            this.description = description
            return this;
        }

        fun addRedeemUrl(redeemUrl: String): Builder {
            this.redeemUrl = redeemUrl
            return this;
        }

        fun build(): Card {
            return Card(
                    id,
                    featured,
                    title,
                    credits,
                    imageUrl,
                    codesCount,
                    currency,
                    description,
                    redeemUrl,
                    color)
        }

        fun addBackgroundColor(color: Int): Builder {
            this.color = color
            return this;
        }
    }
}
