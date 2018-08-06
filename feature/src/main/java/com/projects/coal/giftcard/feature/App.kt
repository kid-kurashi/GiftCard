package com.projects.coal.giftcard.feature

import android.app.Application
import android.widget.ImageView
import com.jakewharton.picasso.OkHttp3Downloader
import com.projects.coal.giftcard.feature.data.IApiService
import com.projects.coal.giftcard.feature.data.impl.ApiServiceImpl
import com.projects.coal.giftcard.feature.domain.IImageLoader
import com.squareup.picasso.LruCache
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class App : Application() {

    lateinit var apiService: IApiService
    lateinit var iImageLoader: IImageLoader

    override fun onCreate() {
        super.onCreate()

        apiService = ApiServiceImpl(applicationContext.resources)
        iImageLoader = createImageLoader();
    }

    private fun createImageLoader(): IImageLoader {
        val builder: Picasso.Builder = Picasso.Builder(this)
        builder.listener({ picasso, uri, exception -> exception.printStackTrace() })
        val picasso = builder
                .memoryCache(LruCache(555555555))
                .downloader(
                        OkHttp3Downloader(
                                OkHttpClient.Builder()
                                        .connectTimeout(15, TimeUnit.SECONDS)
                                        .readTimeout(15, TimeUnit.SECONDS)
                                        //If we need this, adds cookies
//                                        .addInterceptor(AddCookiesInterceptor())
                                        .build()
                        ))
                .build()

        return object: IImageLoader{
            override fun getPicasso(): Picasso {
                return picasso
            }

            override fun loadImage(url: String, imageView: ImageView) {
                picasso.load(url).into(imageView)
            }

            override fun loadImage(url: String, target: Target) {
                picasso.load(url).into(target)
            }
        }

    }

    fun getApiServiceImpl(): IApiService {
        return apiService
    }

    fun getImageLoader(): IImageLoader{
        return iImageLoader
    }
}