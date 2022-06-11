package com.bangkit.capsstonebangkit.data.api.model


import com.google.gson.annotations.SerializedName

data class CommunityResponse(
    @SerializedName("communities")
    val communities: List<Community>
) {
    data class Community(
        @SerializedName("id_user")
        val idUser: Int,
        @SerializedName("id_community")
        val idCommunity: Int,
        @SerializedName("user_role")
        val userRole: String,
        @SerializedName("name")
        val name: String
    )
}