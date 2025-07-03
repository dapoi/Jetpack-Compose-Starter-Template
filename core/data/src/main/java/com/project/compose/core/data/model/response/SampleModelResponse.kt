package com.project.compose.core.data.model.response

import com.google.gson.annotations.SerializedName
import com.project.compose.core.data.model.local.SampleModelEntity

data class SampleModelResponse(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("description") val description: String? = null
) {
    fun mapToEntity() = SampleModelEntity(id = id ?: 0, description = description.orEmpty())
}