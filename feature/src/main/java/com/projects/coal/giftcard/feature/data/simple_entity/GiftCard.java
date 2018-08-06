package com.projects.coal.giftcard.feature.data.simple_entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GiftCard implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("featured")
    @Expose
    private boolean featured;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("credits")
    @Expose
    private int credits;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("codes_count")
    @Expose
    private int codesCount;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("redeem_url")
    @Expose
    private String redeemUrl;
    private final static long serialVersionUID = -6836949185773765618L;

    public int getId() {
        return id;
    }

    public boolean isFeatured() {
        return featured;
    }

    public String getTitle() {
        return title;
    }

    public int getCredits() {
        return credits;
    }

    public String getImageUrl() {
        return imageUrl;
    }


    public int getCodesCount() {
        return codesCount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getDescription() {
        return description;
    }

    public String getRedeemUrl() {
        return redeemUrl;
    }

}