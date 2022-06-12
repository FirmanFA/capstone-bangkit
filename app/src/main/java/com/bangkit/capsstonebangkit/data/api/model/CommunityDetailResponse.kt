package com.bangkit.capsstonebangkit.data.api.model


import com.google.gson.annotations.SerializedName

data class CommunityDetailResponse(
    @SerializedName("rescode")
    val rescode: Int,
    @SerializedName("community")
    val community: List<Community>,
    @SerializedName("members")
    val members: List<Member>
) {
    data class Community(
        @SerializedName("username")
        val username: String,
        @SerializedName("email")
        val email: String,
        @SerializedName("image")
        val image: String,
        @SerializedName("token")
        val token: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("user_role")
        val userRole: String,
        @SerializedName("createdDate")
        val createdDate: String
    )

    data class Member(
        @SerializedName("username")
        val username: String,
        @SerializedName("image")
        val image: String,
        @SerializedName("email")
        val email: String,
        @SerializedName("user_role")
        val userRole: String
    )
}