package com.projects.coal.giftcard.feature.presentation.details

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import com.projects.coal.giftcard.feature.App
import com.projects.coal.giftcard.feature.R
import com.projects.coal.giftcard.feature.domain.IImageLoader
import com.squareup.picasso.Callback
import etr.android.reamp.mvp.ReampAppCompatActivity
import etr.android.reamp.mvp.ReampPresenter

class DetailsActivity : ReampAppCompatActivity<DetailsPresenter, DetailsStateModel>() {

    private lateinit var imgLoader: IImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        imgLoader = (application as App).getImageLoader()

        val toolbar = findViewById<Toolbar>(R.id.details_toolbar)
        toolbar.setTitle(presenter.getTitle())
        setSupportActionBar(toolbar)
        if (getSupportActionBar() != null) {
            getSupportActionBar()!!.setDisplayShowTitleEnabled(true)
            getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
            getSupportActionBar()!!.setDisplayShowHomeEnabled(true)
        }

        val headerRootView: CardView = findViewById(R.id.details_card)
        val cardImage = headerRootView.findViewById<ImageView>(R.id.header_card_image)
        imgLoader.getPicasso().load(presenter.getImageUrl()).into(cardImage, object : Callback {
            override fun onSuccess() {
                headerRootView.setCardBackgroundColor(getAverangeColor((cardImage.drawable as BitmapDrawable).bitmap))
            }

            override fun onError() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
        val coins = headerRootView.findViewById<TextView>(R.id.header_coins_price_text)
        coins.text = presenter.getCoinsText().toString()
        val price = headerRootView.findViewById<TextView>(R.id.header_price)
        price.text = presenter.getTitle().substringBefore(" ")

        val detailsText = findViewById<TextView>(R.id.details_text)
        detailsText.text = presenter.getDescription()

        val detailsLink = findViewById<TextView>(R.id.details_link)
        detailsLink.text = presenter.getLink()

        val isFeatured = headerRootView.findViewById<TextView>(R.id.header_avaible_count)
        isFeatured.text = ((if (presenter.isFeatured() == null || presenter.isFeatured() == false) "No" else "Yes"))

        val count = headerRootView.findViewById<TextView>(R.id.header_avaible_count)
        count.text = presenter.codesCount().toString()

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateStateModel(): DetailsStateModel {
        return DetailsStateModel()
    }

    override fun onCreatePresenter(): ReampPresenter<DetailsStateModel> {
        return DetailsPresenter()
    }

    override fun onStateChanged(stateModel: DetailsStateModel?) {

    }

    fun getAverangeColor(someBitmap: Bitmap?): Int {
        val newBitmap = Bitmap.createScaledBitmap(someBitmap, 1, 1, true);
        val color = newBitmap.getPixel(0, 0);
        newBitmap.recycle();
        return color;
    }
}

