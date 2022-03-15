package com.tori.schibsted.data.model.keyword

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Keyword (
    @SerializedName("parent_name")
    @Expose
    var parentName: String? = null,

    @SerializedName("sub_category")
    @Expose
    var subCategory: String? = null,

    @SerializedName("child_category")
    @Expose
    var childCategory: List<String>? = null
)