package com.tori.schibsted.network.data.keyword

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.tori.schibsted.network.data.keyword.Keyword

data class KeywordResponse (
    @SerializedName("keywords")
    @Expose
    var keywords: List<Keyword>? = null
)