package com.projects.coal.giftcard.feature.data.simple_entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GiftCardsAnswer extends BaseAnswer {

    @SerializedName("providers")
    @Expose
    private List<Provider> providers = null;
    private final static long serialVersionUID = 6670976937957154037L;

    public List<Provider> getProviders() {
        return providers;
    }

}
