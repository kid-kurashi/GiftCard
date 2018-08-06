package com.projects.coal.giftcard.feature.presentation.main

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.projects.coal.giftcard.feature.R
import com.projects.coal.giftcard.feature.data.simple_entity.GiftCard
import com.projects.coal.giftcard.feature.data.simple_entity.Provider
import com.projects.coal.giftcard.feature.domain.IImageLoader
import com.squareup.picasso.Callback
import java.lang.StringBuilder
import java.util.*


class GiftCardsAdapter : RecyclerView.Adapter<GiftCardsAdapter.BaseVH>() {

    var items: ArrayList<RecyclerItem> = ArrayList()

    private val TYPE_HEADER: Int = 0
    private val TYPE_LIST: Int = 1

    private lateinit var callback: CardClickedCallback

    interface RecyclerItem
    class ProviderHeader(val header: String) : RecyclerItem
    class ProviderList(val list: List<GiftCard>) : RecyclerItem

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseVH {
        val inflater = LayoutInflater.from(parent.context)

        if (viewType == TYPE_HEADER) {
            return HeaderCardsViewHolder(inflater.inflate(R.layout.item_header, parent, false))
        } else {
            return GiftCardsViewHolder(inflater.inflate(R.layout.item_recycler, parent, false), loader, callback)
        }
    }

    override fun onBindViewHolder(holder: BaseVH, position: Int) {
        holder.bind(position, items.get(position))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    open lateinit var loader: IImageLoader

    fun setImageLoader(loader: IImageLoader) {
        this.loader = loader
    }

    fun setItems(list: List<Provider>) {
        items.clear()
        list.forEach {
            items.add(ProviderHeader(it.title))
            items.add(ProviderList(it.giftCards))
        }
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        if (items[position] is ProviderHeader) return TYPE_HEADER else return TYPE_LIST
    }

    fun setCardClickedCallback(callback: CardClickedCallback) {
        this.callback = callback;
    }

    class GiftCardsViewHolder(itemView: View, imgLoader: IImageLoader, callback: CardClickedCallback) : BaseVH(itemView) {

        private val recycler: RecyclerView
        private val innerAdapter: InnerAdapter

        init {
            recycler = itemView.findViewById(R.id.item_list_recycler)
            recycler.setItemViewCacheSize(100)
            recycler.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            innerAdapter = InnerAdapter(imgLoader, callback)
            recycler.adapter = innerAdapter
        }

        override fun bind(position: Int, listItem: RecyclerItem) {
            if (listItem is ProviderList) {
                innerAdapter.setItems(listItem.list)
            }
        }


        class InnerAdapter(val imgLoader: IImageLoader, private val callback: CardClickedCallback) : RecyclerView.Adapter<InnerAdapter.InnerAdapterHolder>() {

            var itemsList: ArrayList<GiftCard> = ArrayList()

            fun setItems(list: List<GiftCard>) {
                itemsList.clear()
                itemsList.addAll(list)
                this.notifyDataSetChanged()
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerAdapterHolder {
                val v = LayoutInflater.from(parent.context)
                        .inflate(R.layout.card_item, parent, false)
                return InnerAdapterHolder(v, imgLoader)
            }

            override fun getItemCount(): Int {
                return itemsList.size
            }

            override fun onBindViewHolder(holder: InnerAdapterHolder, position: Int) {
                holder.bind(itemsList.get(position))
                holder.cardView.setOnClickListener {
                    callback.onCardClicked(itemsList.get(position), holder.cardView)
                }
            }

            class InnerAdapterHolder(item: View, val imgLoader: IImageLoader) : RecyclerView.ViewHolder(item) {

                val cardImage: ImageView
                val cardPrice: TextView
                val cardCoins: TextView
                val cardView: CardView

                init {
                    cardImage = item.findViewById(R.id.card_image)
                    cardPrice = item.findViewById(R.id.price_text)
                    cardCoins = item.findViewById(R.id.coins_price_text)
                    cardView = item.findViewById(R.id.card_view)
                }

                fun bind(card: GiftCard) {
                    imgLoader.getPicasso().load(card.imageUrl).into(cardImage, object : Callback {
                        override fun onSuccess() {
                            cardView.setCardBackgroundColor(getAverangeColor((cardImage.drawable as BitmapDrawable).bitmap))
                        }

                        override fun onError() {
                            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        }
                    })
                    cardPrice.text = StringBuilder()
                            .append(card.title.substringBefore(" "))
                            .toString()
                    cardCoins.text = card.credits.toString()
                }

                fun getAverangeColor(someBitmap: Bitmap?): Int {
                    val newBitmap = Bitmap.createScaledBitmap(someBitmap, 1, 1, true);
                    val color = newBitmap.getPixel(0, 0);
                    newBitmap.recycle();
                    return color;
                }

            }

        }

    }

    class HeaderCardsViewHolder(itemView: View) : BaseVH(itemView) {
        private var header: TextView = itemView.findViewById(R.id.category_header)

        override fun bind(position: Int, listItem: RecyclerItem) {
            if (listItem is ProviderHeader) {
                header.text = listItem.header
            }
        }
    }

    abstract class BaseVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(position: Int, listItem: RecyclerItem)
    }
}

interface CardClickedCallback {
    fun onCardClicked(giftCard: GiftCard, view: View)
}
