package com.tori.schibsted.data.model.keyword

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class KeywordResponse (
    @SerializedName("keywords")
    @Expose
    var keywords: List<Keyword>? = null
)