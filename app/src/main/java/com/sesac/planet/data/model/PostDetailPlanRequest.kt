package com.sesac.planet.data.model

import com.google.gson.annotations.SerializedName

data class PostDetailPlanRequest(
    @SerializedName("plan_content") val planContent: String,
    @SerializedName("type") val type: String
) : BaseResponse()
