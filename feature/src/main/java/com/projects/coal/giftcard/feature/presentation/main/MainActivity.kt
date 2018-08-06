package com.projects.coal.giftcard.feature.presentation.main

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.projects.coal.giftcard.feature.App
import com.projects.coal.giftcard.feature.R
import com.projects.coal.giftcard.feature.data.simple_entity.GiftCard
import com.projects.coal.giftcard.feature.domain.IImageLoader
import com.projects.coal.giftcard.feature.domain.impl.GetGiftCardsCommand
import etr.android.reamp.mvp.ReampAppCompatActivity
import etr.android.reamp.mvp.ReampPresenter

class MainActivity : ReampAppCompatActivity<MainPresenter, MainStateModel>() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private val adapter: GiftCardsAdapter = GiftCardsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.main_recycler)
        recyclerView.setItemViewCacheSize(100)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter.setImageLoader(getImageLoader())
        adapter.setCardClickedCallback(object: CardClickedCallback{
            override fun onCardClicked(giftCard: GiftCard, view : View) {
                presenter.navToDetails(giftCard, view)
            }

        })
        recyclerView.adapter = adapter

        swipeRefreshLayout = findViewById(R.id.swipe_refresh)

        swipeRefreshLayout.setOnRefreshListener { presenter.update() }
    }

    private fun getImageLoader(): IImageLoader {
        return (application as App).getImageLoader()
    }

    override fun onCreateStateModel(): MainStateModel {
        return MainStateModel()
    }

    override fun onCreatePresenter(): ReampPresenter<MainStateModel> {
        return MainPresenter(
                GetGiftCardsCommand((application as App).getApiServiceImpl()),
                CardViewHolder()
        )
    }

    override fun onStateChanged(stateModel: MainStateModel?) {
        adapter.setItems(stateModel!!.list)
        toggleProgress(stateModel)
        presenter.navigate()
    }

    private fun toggleProgress(stateModel: MainStateModel) {
        recyclerView.setVisibility(if (stateModel.progress) View.GONE else View.VISIBLE)
        swipeRefreshLayout.setRefreshing(stateModel.progress)
    }
}
